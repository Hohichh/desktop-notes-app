package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
abstract public class Media {
    protected final UUID id;
    protected final UUID noteId;
    protected String path;
    protected String insertLabel;

    public Media(UUID id, UUID noteId, String path, String insertLabel) {
        this.id = id;
        this.noteId = noteId;
        this.path = path;
        this.insertLabel = insertLabel;
    }
}
