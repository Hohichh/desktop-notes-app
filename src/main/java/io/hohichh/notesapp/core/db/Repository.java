package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;

public interface Repository {
    void createNote(Note note) throws StorageException;
    void deleteNote(String id) throws StorageException;
    Note getNote(String id) throws StorageException;
    void updateNote(Note note) throws StorageException;
    List<Note> getAllNotes() throws StorageException;
}
