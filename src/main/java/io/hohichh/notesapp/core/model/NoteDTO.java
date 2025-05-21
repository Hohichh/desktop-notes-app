package io.hohichh.notesapp.core.model;

import java.util.List;
import java.util.UUID;

public class NoteDTO extends Note {
    private List<ImageDTO> images;
    public NoteDTO() {
        super(UUID.randomUUID());
    }
}
