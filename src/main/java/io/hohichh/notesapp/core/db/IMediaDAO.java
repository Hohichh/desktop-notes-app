package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IMediaDAO {
    void create(List<Media> listOfMedia) throws SQLException;
    void delete(UUID noteId) throws SQLException;
    List<Media> getAll(UUID noteId) throws SQLException;
}
