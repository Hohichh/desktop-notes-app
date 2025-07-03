package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;

import java.util.List;
import java.util.UUID;

public interface IMediaDAO {
    void create(Media media);
    void update(Media media);
    void delete(UUID id);
    Media get(UUID id);
    List<Media> getAll();
}
