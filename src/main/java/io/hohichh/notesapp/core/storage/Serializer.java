package io.hohichh.notesapp.core.storage;

import io.hohichh.notesapp.core.exceptions.StorageException;

public interface Serializer<T> {
    public void serialize(T object, String path) throws StorageException;
}
