package io.hohichh.notes_app.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
abstract public class MediaWrapper {
    protected final UUID id;
    protected final UUID note_id;
    protected String path;

    public MediaWrapper(UUID id, UUID note_id, String path) {
        this.id = id;
        this.note_id = note_id;
        this.path = path;
    }
}
