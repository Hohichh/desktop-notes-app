package io.hohichh.notes_app.core.storage;

import java.io.FileNotFoundException;

public interface Deserializer<T> {
    public T deserialize(String path) throws FileNotFoundException;
}
