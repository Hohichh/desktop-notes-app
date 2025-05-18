package io.hohichh.notes_app.core;

import io.hohichh.notes_app.core.model.Note;

import java.util.List;

public interface Notebook {
    public void createNote(Note note);
    public void updateNote(Note note);
    public Note getNote(String id);
    public List<Note> getAllNotes();
    public void deleteNote(String id);
}
