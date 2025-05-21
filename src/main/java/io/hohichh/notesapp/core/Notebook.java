package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.model.Note;

import java.util.List;

public interface Notebook {
    public void createNote(Note note);
    public void updateNote(Note note);
    public Note getNote(String id);
    public List<Note> getAllNotes();
    public void deleteNote(String id);
}
