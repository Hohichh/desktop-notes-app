package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.model.Note;

import java.util.List;
import java.util.UUID;

public interface Notebook {
    public void createNote(Note note);
    public void updateNote(Note note);
    public Note getNote(UUID id);
    public List<Note> getAllNotes();
    public void deleteNote(UUID id);
}
