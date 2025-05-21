package io.hohichh.notesapp.core.storage;

import io.hohichh.notesapp.core.exceptions.StorageException;

import java.io.FileNotFoundException;

public interface Deserializer<T> {
    public T deserialize(String path) throws StorageException;
}
