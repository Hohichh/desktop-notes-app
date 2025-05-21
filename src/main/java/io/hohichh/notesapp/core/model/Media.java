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

    public Media(UUID id, UUID noteId, String path) {
        this.id = id;
        this.noteId = noteId;
        this.path = path;
    }
}
