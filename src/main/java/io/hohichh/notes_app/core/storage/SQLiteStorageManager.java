package io.hohichh.notes_app.core.storage;

import io.hohichh.notes_app.core.model.Note;

import java.util.List;

public class SQLiteStorageManager implements StorageManager{
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
