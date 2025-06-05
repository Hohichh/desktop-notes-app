package io.hohichh.notesapp.core.storage;

public interface FileManager {
    void save(Object obj, String path);
    Object load(String path);
    void delete(String path);
}
