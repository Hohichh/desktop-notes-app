package io.hohichh.notesapp.core.exceptions;

public class ImageLoadException extends StorageException{
    public ImageLoadException(){
        super();
    }

    public ImageLoadException(final String message) {
        super(message);
    }

    public ImageLoadException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ImageLoadException(final Throwable cause) {
        super(cause);
    }
}
