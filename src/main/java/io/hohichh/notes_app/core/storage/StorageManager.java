package io.hohichh.notes_app.core.storage;

import io.hohichh.notes_app.core.model.Note;

import java.util.List;

public interface StorageManager {
    public void createNote(Note note);
    public void deleteNote(String id);
    public Note getNote(String id);
    public void updateNote(Note note);
    public List<Note> getAllNotes();
}
