package io.hohichh.notes_app.core.model;

import java.util.List;
import java.util.UUID;

public final class NoteDTO extends Note {
    private List<ImageDTO> images;
    public NoteDTO() {
        super(UUID.randomUUID());
    }
}
