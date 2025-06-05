package io.hohichh.notesapp.core.storage.loaders;

import io.hohichh.notesapp.core.exceptions.StorageException;

public interface FileLoader <T>{
    void serialize(T object, String path) throws StorageException;
    T deserialize(String path) throws StorageException;
}
