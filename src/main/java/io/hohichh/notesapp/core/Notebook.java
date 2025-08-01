package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.model.Note;

import java.util.List;
import java.util.UUID;

public interface Notebook {
     void createNote(Note note);
     void updateNote(Note note);
     Note getNote(UUID id);
     List<Note> getAllNotes();
     void deleteNote(UUID id);
}
