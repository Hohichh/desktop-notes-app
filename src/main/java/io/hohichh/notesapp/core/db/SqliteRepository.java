package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.InitTableException;
import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.db.SQLiteDBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SqliteRepository implements Repository {

    public SqliteRepository(String db_name) throws InitTableException {
        try {
            SQLiteDBManager.useDataBase(db_name);
            SQLiteDBManager.initTables();
        } catch (SQLException e) {
            throw new InitTableException("Can't create tables: " + e.getMessage());
        }
    }

    @Override
    public void createNote(Note note) {

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
