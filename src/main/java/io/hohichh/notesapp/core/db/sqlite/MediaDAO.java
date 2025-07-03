package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.IMediaDAO;
import io.hohichh.notesapp.core.model.Media;

import java.util.List;
import java.util.UUID;

public class MediaDAO implements IMediaDAO {
    public MediaDAO(){}

    @Override
    public void create(Media media) {

    }

    @Override
    public void update(Media media) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Media get(UUID id) {
        return null;
    }

    @Override
    public List<Media> getAll() {
        return List.of();
    }
}
