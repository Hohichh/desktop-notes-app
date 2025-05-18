package io.hohichh.notes_app.core.model;

import java.util.UUID;

public final class BasicNote extends Note {

    public BasicNote() {
        super(UUID.randomUUID());
    }
}
