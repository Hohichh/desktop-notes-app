package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface INoteDAO {
    void create(Note note) throws SQLException;
    void update(Note note) throws SQLException;
    void delete(UUID id) throws SQLException;
    Note get(UUID id) throws SQLException;
    List<Note> getAll() throws SQLException;
}
