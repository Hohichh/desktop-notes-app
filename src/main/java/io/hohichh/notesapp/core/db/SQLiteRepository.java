package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.SqliteRepException;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static io.hohichh.notesapp.core.db.MediaQueries.*;
import static io.hohichh.notesapp.core.db.NoteQueries.*;


public class SQLiteRepository implements Repository {

    public SQLiteRepository(String db_name) throws SqliteRepException {
        try {
            SQLiteDBManager.useDataBase(db_name);
            SQLiteDBManager.initTables();
        } catch (SQLException e) {
            throw new SqliteRepException("Can't create tables: " + e.getMessage(), e);
        }
    }

    @Override
    public void createNote(Note note) throws SqliteRepException {
        try(Connection conn = SQLiteDBManager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(CREATE_NOTE);
            PreparedStatement psMedia = conn.prepareStatement(CREATE_MEDIA)) {

            conn.setAutoCommit(false);

            try{
                psNote.setString(1, note.getId().toString());
                psNote.setString(2, note.getTitle());
                psNote.setString(3, note.getContent());
                psNote.setLong(4, note.getCreatedAt()
                        .atZone(ZoneId.systemDefault()).toEpochSecond()
                );
                psNote.setLong(5, note.getUpdatedAt()
                        .atZone(ZoneId.systemDefault()).toEpochSecond()
                );
                psNote.executeUpdate();

                for(Media mediaObj : note.getMediaContent()){
                    psMedia.setString(1, mediaObj.getId().toString());
                    psMedia.setString(2, mediaObj.getNoteId().toString());
                    psMedia.setString(3, mediaObj.getPath());
                    psMedia.setString(4, mediaObj.getInsertLabel());
                    psMedia.addBatch();
                }
                psMedia.executeBatch();
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                throw new SQLException("Can't commit transaction: " + e.getMessage(), e);
            }

        } catch (SQLException e) {
            throw new SqliteRepException("Can't create a note: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteNote(String id) throws SqliteRepException {
        try(Connection conn = SQLiteDBManager.getConnection();
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
                throw new SQLException("Can't commit transaction: " + e.getMessage(), e);
            }

        }catch (SQLException e) {
            throw new SqliteRepException("Can't delete note: " + e.getMessage(), e);
        }
    }

    @Override
    public Note getNote(String id) throws SqliteRepException {
        Note note = null;
        try(Connection conn = SQLiteDBManager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(SELECT_NOTE);
            PreparedStatement psMedia = conn.prepareStatement(SELECT_MEDIA_BY_NOTE_ID)){

            conn.setAutoCommit(false);
            try{
                psNote.setString(1, id);
                ResultSet rsNote = psNote.executeQuery();
                psMedia.setString(1, id);
                ResultSet rsMedia = psMedia.executeQuery();
                conn.commit();

                if(rsNote.next()){
                    note = new Note(UUID.fromString(
                            rsNote.getString("id")));
                    note.setTitle(rsNote.getString("title"));
                    note.setContent(rsNote.getString("content"));
                    note.setCreatedAt(LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(rsNote.getLong("created_at")),
                            ZoneId.systemDefault()
                    ));
                    note.setUpdatedAt(LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(rsNote.getLong("updated_at")),
                            ZoneId.systemDefault()
                    ));

                    while(rsMedia.next()){
                        Media mediaObj = new Media(
                                UUID.fromString(rsNote.getString("id")),
                                UUID.fromString(rsNote.getString("note_id")),
                                rsMedia.getString("path"),
                                rsMedia.getString("insert_label")
                        );
                        note.addMedia(mediaObj);
                    }
                }
            }catch (SQLException e){
                conn.rollback();
                throw new SQLException("Can't commit transaction: " + e.getMessage(), e);
            }
            return note;
        }catch (SQLException e){
            throw new SqliteRepException("Can't get note: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateNote(Note note) {
        try(Connection conn = SQLiteDBManager.getConnection();
            PreparedStatement psNote = conn.prepareStatement(UPDATE_NOTE);
            PreparedStatement psMedia = conn.prepareStatement("")){

        }catch (SQLException e){

        }
    }

    @Override
    public List<Note> getAllNotes() {
        return List.of();
    }
}
