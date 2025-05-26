package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Note;

import java.util.List;

public interface Repository {
    public void createNote(Note note);
    public void deleteNote(String id);
    public Note getNote(String id);
    public void updateNote(Note note);
    public List<Note> getAllNotes();
}
