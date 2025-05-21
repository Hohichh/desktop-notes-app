package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.db.StorageManager;

import java.util.List;


//todo:релизнуть менеджер бд и подумать как скомпоновать это с стореджем картинок
public class BasicNotebook implements Notebook {
    private final StorageManager storageManager;

    public BasicNotebook(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void createNote(Note note) {
        storageManager.createNote(note);
    }

    @Override
    public void updateNote(Note note) {
        storageManager.updateNote(note);
    }

    @Override
    public Note getNote(String id) {
        return storageManager.getNote(id);
    }

    @Override
    public List<Note> getAllNotes() {
        return storageManager.getAllNotes();
    }

    @Override
    public void deleteNote(String id) {
        storageManager.deleteNote(id);
    }
}
