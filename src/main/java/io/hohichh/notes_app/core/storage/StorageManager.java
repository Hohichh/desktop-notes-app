package io.hohichh.notes_app.core.storage;

public interface StorageManager {
    public void create();
    public void delete();
    public void getNote();
    public void updateNote();
    public void getAllNotes();
}
