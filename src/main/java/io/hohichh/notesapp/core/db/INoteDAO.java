package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;
import java.util.UUID;

public interface INoteDAO {
    void create(Note note);
    void update(Note note);
    void delete(UUID id);
    Note get(UUID id);
    List<Note> getAll();
}
