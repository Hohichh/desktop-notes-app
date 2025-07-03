package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.INoteDAO;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;
import java.util.UUID;

public class NoteDAO implements INoteDAO {
    public NoteDAO() {

    }

    @Override
    public void create(Note note) {

    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Note get(UUID id) {
        return null;
    }

    @Override
    public List<Note> getAll() {
        return List.of();
    }
}
