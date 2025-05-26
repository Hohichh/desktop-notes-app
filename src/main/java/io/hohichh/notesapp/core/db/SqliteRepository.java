package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.SqliteRepException;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

import static io.hohichh.notesapp.core.db.ImageQueries.CREATE_IMAGE;
import static io.hohichh.notesapp.core.db.NoteQueries.CREATE_NOTE;

public class SqliteRepository implements Repository {

    public SqliteRepository(String db_name) throws SqliteRepException {
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
            PreparedStatement psImage = conn.prepareStatement(CREATE_IMAGE)) {

            conn.setAutoCommit(false);

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
                psImage.setString(1, mediaObj.getId().toString());
                psImage.setString(2, mediaObj.getNoteId().toString());
                psImage.setString(3, mediaObj.getPath());
                psImage.setString(4, mediaObj.getInsertLabel());
                psImage.addBatch();
            }
            psImage.executeBatch();

            conn.commit();

        } catch (SQLException e) {
            throw new SqliteRepException("Can't create a note: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteNote(String id) {

    }

    @Override
    public Note getNote(String id) {
        return null;
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public List<Note> getAllNotes() {
        return List.of();
    }
}
