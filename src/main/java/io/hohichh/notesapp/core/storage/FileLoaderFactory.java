package io.hohichh.notesapp.core.storage;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoaderFactory {
    static {
        register(Image.class, new FXImageLoader());
    }

    private static final Map<Class<?>, FileLoader<?>> fileLoaders = new HashMap<>();

    public static void register(final Class<?> clazz, final FileLoader<?> fileLoader) {
        fileLoaders.put(clazz, fileLoader);
    }

    public static void registerAll(Map<Class<?>, FileLoader<?>> fileLoaders) {
        fileLoaders.forEach(FileLoaderFactory::register);
    }

    @SuppressWarnings("unchecked")
    public static <T> FileLoader<T> getLoader(final Class<?> clazz) {
        FileLoader<?> fileLoader = fileLoaders.get(clazz);
        if (fileLoader == null) {
            throw new IllegalArgumentException("No file loader registered for " + clazz);
        }
        return (FileLoader<T>) fileLoader;
    }
}
