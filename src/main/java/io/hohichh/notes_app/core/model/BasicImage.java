package io.hohichh.notes_app.core.model;

import java.util.UUID;

//todo Подумать как инициализируется изображение (при вставке его в ноту нужно сохранять его на диск)
//todo придумать как получать путь к изображению и главное, что делать с mime-типом
public class BasicImage extends MediaWrapper{
    public BasicImage(UUID note_id, String note_path) {
        super(UUID.randomUUID(),note_id,note_path);
    }
}
