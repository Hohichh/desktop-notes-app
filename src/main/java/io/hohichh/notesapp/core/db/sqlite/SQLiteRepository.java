package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.Repository;
import io.hohichh.notesapp.core.exceptions.SqliteRepException;
import io.hohichh.notesapp.core.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import static io.hohichh.notesapp.core.db.queries.MediaQueries.*;
import static io.hohichh.notesapp.core.db.queries.NoteQueries.*;

public class SQLiteRepository implements Repository {
    SQLiteDBManager manager;

    public SQLiteRepository(SQLiteDBManager connManager) throws SqliteRepException {
        manager = connManager;
        try {
            manager.initTables();
        } catch (SQLException e) {
            throw new SqliteRepException("Can't create tables: " + e.getMessage(), e);
        }
    }

    @Override
    public void createNote(Note note) throws SqliteRepException {
        try(Connection conn = manager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(CREATE_NOTE);
            PreparedStatement psMedia = conn.prepareStatement(CREATE_MEDIA)) {

            conn.setAutoCommit(false);
            try{
                DTOMapper.noteToCreateStmt(note, psNote);
                psNote.executeUpdate();

                DTOMapper.mediaToCreateStmt(note.getMediaContent(), psMedia);
                psMedia.executeBatch();
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                throw new SQLException("Fail commit transaction: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new SqliteRepException("Can't create a note: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteNote(String id) throws SqliteRepException {
        try(Connection conn = manager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(DELETE_NOTE);
            PreparedStatement psMedia = conn.prepareStatement(DELETE_MEDIA_BY_NOTE_ID)){

            conn.setAutoCommit(false);
            try{
                psMedia.setString(1, id);
                psMedia.executeUpdate();

                psNote.setString(1, id);
                psNote.executeUpdate();
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                throw new SQLException("Fail commit transaction: " + e.getMessage(), e);
            }

        }catch (SQLException e) {
            throw new SqliteRepException("Can't delete note: " + e.getMessage(), e);
        }
    }

    @Override
    public Note getNote(String id) throws SqliteRepException {
        Note note = null;
        try(Connection conn = manager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(SELECT_NOTE);
            PreparedStatement psMedia = conn.prepareStatement(SELECT_MEDIA_BY_NOTE_ID)){

            psNote.setString(1, id);
            psMedia.setString(1, id);
            try(ResultSet rsNote = psNote.executeQuery();
                ResultSet rsMedia = psMedia.executeQuery();){

                note = DTOMapper.resultSetToNote(rsNote, rsMedia);
            }catch (SQLException e){
                throw new SQLException("Fail commit transaction: " + e.getMessage(), e);
            }
        }catch (SQLException e){
            throw new SqliteRepException("Can't get note: " + e.getMessage(), e);
        }
        return note;
    }

    @Override
    public void updateNote(Note note) throws SqliteRepException {
        try(Connection conn = manager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(UPDATE_NOTE);
            PreparedStatement psDelOldMedia = conn.prepareStatement(DELETE_MEDIA_BY_NOTE_ID);
            PreparedStatement psMedia = conn.prepareStatement(CREATE_MEDIA)){

            conn.setAutoCommit(false);
            try{
                psDelOldMedia.setString(1, note.getId().toString());
                psDelOldMedia.executeUpdate();

                DTOMapper.mediaToCreateStmt(note.getMediaContent(), psMedia);
                psMedia.executeUpdate();

                DTOMapper.noteToUpdateStmt(note, psNote);
                psNote.executeUpdate();

                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                throw new SQLException("Fail commit transaction: " + e.getMessage(), e);
            }
        }catch (SQLException e){
            throw new SqliteRepException("Can't update note: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Note> getAllNotes() throws SqliteRepException {
        List<Note> notes = null;
        try(Connection conn = manager.getConnection();
            PreparedStatement psNotes = conn.prepareStatement(SELECT_ALL_NOTES)){

            try(ResultSet rsNotes = psNotes.executeQuery()){
                notes = DTOMapper.resultSetToNoteList(rsNotes);
            } catch (SQLException e) {
                throw new SQLException("Fail commit transaction: " + e.getMessage(), e);
            }
        }catch (SQLException e){
            throw new SqliteRepException("Can't get all notes: " + e.getMessage(), e);
        }
        return notes;
    }
}
