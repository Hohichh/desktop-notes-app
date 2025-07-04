package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    void create(Note note) throws StorageException;
    void delete(UUID id) throws StorageException;
    Note get(UUID id) throws StorageException;
    void update(Note note) throws StorageException;
    List<Note> getAll() throws StorageException;
}
