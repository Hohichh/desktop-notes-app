package io.hohichh.notesapp.core.storage;

import io.hohichh.notesapp.core.exceptions.StorageException;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public interface FileLoader <T>{
    void serialize(T object, String path) throws StorageException;
    T deserialize(String path) throws StorageException;

    default void deleteFile(String path) throws StorageException {
        try{
            Path filePath = Paths.get(path);
            Files.deleteIfExists(filePath);
        } catch (IOException e){
            throw new StorageException("Can't delete file: " + e.getMessage(), e);
        }
    }

    default void deleteDirectory(String pathToDelete) throws StorageException {
        Path dirPath = Paths.get(pathToDelete);
        try(Stream<Path> paths = Files.walk(dirPath)){
            paths.sorted(Comparator.reverseOrder())
                    .forEach( path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new StorageException("Can't delete directory: " + path, e);
                        }
                    });
        }catch (IOException e){
            throw new StorageException("Can't delete directory: " + pathToDelete, e);
        }
    }
}
