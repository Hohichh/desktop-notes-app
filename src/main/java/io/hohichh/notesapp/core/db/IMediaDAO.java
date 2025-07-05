package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IMediaDAO {
    void create(List<Media> listOfMedia) throws SQLException;
    void delete(Note note) throws SQLException;
    List<Media> getAll(Note note) throws SQLException;
}
