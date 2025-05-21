package io.hohichh.notes_app.core.storage;

import java.io.File;

public interface Serializer<T> {
    public void serialize(T object, String path);
}
