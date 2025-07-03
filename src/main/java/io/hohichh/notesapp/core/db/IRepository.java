package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;

public interface IRepository {
    void create(Note note) throws StorageException;
    void delete(String id) throws StorageException;
    Note get(String id) throws StorageException;
    void update(Note note) throws StorageException;
    List<Note> getAll() throws StorageException;
}
