package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.db.StorageManager;

import java.util.List;

public class BasicNotebook implements Notebook {
    private final StorageManager storageManager;

    public BasicNotebook(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void createNote(Note note) {

    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public Note getNote(String id) {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return List.of();
    }

    @Override
    public void deleteNote(String id) {

    }
}
