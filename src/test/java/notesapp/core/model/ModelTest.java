package notesapp.core.model;

import io.hohichh.notesapp.core.model.ImageWrapper;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Model test cases")
public class ModelTest {
    @Nested
    @DisplayName("Note class test cases")
    class NoteTest{
        private Note note;
        private List<Media> mediaList;

        @BeforeEach
        void setUp() {
            note = new Note();
            note.setTitle("Dear diary");
            note.setContent("I can't tell how humiliating was it");
            mediaList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                mediaList.add(new Media(
                        UUID.randomUUID(),
                        note.getId(),
                        i + ".png",
                        "[" + i + "]"
                ));
            }
        }

        @Test
        public void gettersTest(){
            assertAll(
                    "Getting note fields with default constructor init",
                    () -> assertNotNull(note.getId()),
                    () -> assertEquals(note.getTitle(), "Dear diary"),
                    () -> assertEquals(note.getContent(), "I can't tell how humiliating was it"),
                    () -> assertNotNull(note.getCreatedAt()),
                    () -> assertNotNull(note.getUpdatedAt())
            );
        }

        @Test
        public void settersTest(){
            note.setTitle("Fuck this social constructs");
            note.setContent("Title 1");
            LocalDateTime ltdnow = LocalDateTime.now();
            assertAll(
                    () -> assertEquals(note.getTitle(), "Fuck this social constructs"),
                    () -> assertEquals(note.getContent(), "Title 1"),
                    () -> assertNotEquals(note.getUpdatedAt(), LocalDateTime.now()),
                    () -> {
                        note.setUpdatedAt(ltdnow);
                        note.setCreatedAt(note.getUpdatedAt());
                        assertEquals(note.getUpdatedAt(), ltdnow);
                    },
                    () -> assertEquals(note.getCreatedAt(), ltdnow)
            );
        }

        @Test
        public void mediaContentAddElementsTest(){
            assertAll(
                    //add one element
                    () -> assertNotEquals(note.getMediaContent(), null),
                    () -> {
                        note.addMedia(mediaList.getFirst());
                        assertEquals(note.getMediaContent().getFirst(), mediaList.getFirst());
                    },
                    () -> assertNotEquals(note.getMediaContent().size(), mediaList.size()),
                    () -> assertEquals(note.getMediaContent().size(), 1),

                    //add all elements from other test media list
                    () -> {
                        note.addMedia(mediaList.get(1));
                        note.addMedia(mediaList.get(2));
                        assertEquals(note.getMediaContent().get(1), mediaList.get(1));
                    },
                    () -> assertEquals(note.getMediaContent().get(2), mediaList.get(2)),
                    () -> assertEquals(note.getMediaContent().size(), mediaList.size()),
                    () -> assertEquals(note.getMediaContent().size(), 3)
            );
        }

        @Test
        public void mediaContentRemoveElementsTest(){
            for (Media media : mediaList) {
                note.addMedia(media);
            }
            assertAll(
                    () -> {
                        note.removeMedia(mediaList.get(0));
                        assertFalse(note.getMediaContent().contains(mediaList.get(0)));
                    },
                    () -> {
                        note.removeMedia(mediaList.get(1).getId());
                        assertFalse(note.getMediaContent().contains(mediaList.get(1)));
                    }
            );
        }

        @Test
        public void equalsNoteTest(){
            Note newNote = new Note(note.getId(), "", "", null, null, null);
            assertAll(
                    () -> assertSame(note, note),
                    () -> assertEquals(note, note),
                    () -> assertNotSame(note, newNote),
                    () -> assertEquals(note, newNote),
                    () -> assertEquals(note.hashCode(), note.hashCode()),
                    () -> assertNotEquals(note, null),
                    () -> assertNotEquals(note, new Object())
            );
        }

    }

    @Nested
    @DisplayName("Media class test cases")
    class MediaTest{
        private Media mediaObj;
        private Note note;

        @BeforeEach
        public void initMediaObj(){
            note = new Note();
            mediaObj = new Media(
                    UUID.randomUUID(),
                    note.getId(),
                    note.getId().toString() + "/img/img.png",
                    "[1]"
                    );
        }

        @Test
        public void gettersTest(){
            assertAll(
                    () -> assertNotEquals(mediaObj.getId(), null),
                    () -> assertNotEquals(mediaObj.getNoteId(), null),
                    () -> assertEquals(mediaObj.getNoteId(), note.getId()),
                    () -> assertEquals(mediaObj.getPath(), note.getId().toString() + "/img/img.png"),
                    () -> assertEquals(mediaObj.getInsertLabel(), "[" + 1 + "]")
            );
        }

        @Test
        public void settersTest(){
            mediaObj.setPath("new path");
            mediaObj.setInsertLabel("new label");
            assertAll(
                    () -> assertEquals(mediaObj.getPath(), "new path"),
                    () -> assertEquals(mediaObj.getInsertLabel(), "new label")
            );
        }

        @Test
        public void equalsMediaTest(){
            Media newMediaObj = new Media(
                    mediaObj.getId(),
                    mediaObj.getNoteId(),
                    "path",
                    "label"
            );
            assertAll(
                    () -> assertEquals(mediaObj, mediaObj),
                    () -> assertSame(mediaObj, mediaObj),
                    () -> assertEquals(mediaObj, newMediaObj),
                    () -> assertNotSame(mediaObj, newMediaObj),
                    () -> assertEquals(mediaObj.hashCode(), newMediaObj.hashCode()),
                    () -> assertNotEquals(mediaObj, null),
                    () -> assertNotEquals(mediaObj, new Object())
            );
        }

        @Test
        public void mediaClassExtensionToImageWrapper(){
            ImageWrapper imageWrapper = new ImageWrapper(mediaObj);
            assertAll(
                    () -> assertEquals(imageWrapper.getId(), mediaObj.getId()),
                    () -> assertEquals(imageWrapper.getNoteId(), mediaObj.getNoteId()),
                    () -> assertEquals(imageWrapper.getPath(), mediaObj.getPath()),
                    () -> assertEquals(imageWrapper.getInsertLabel(), mediaObj.getInsertLabel())
            );
        }
    }

    @Nested
    @DisplayName("ImageWrapper class test cases")
    class ImageWrapperTest{
        private ImageWrapper imageWrapper;
        private Note note;

        @BeforeEach
        public void initImageWrapper(){
            note = new Note();
            imageWrapper = new ImageWrapper(
                    UUID.randomUUID(),
                    note.getId(),
                    note.getId() + "img/img.png",
                    "[1]"
                    );
            Image mockFxImage = mock(Image.class);
            imageWrapper.setImage(mockFxImage);
        }

        @Test
        public void gettersTest(){
            assertAll(
                    () -> assertNotNull(imageWrapper.getId()),
                    () -> assertEquals(imageWrapper.getNoteId(), note.getId()),
                    () -> assertEquals(imageWrapper.getPath(), note.getId() + "img/img.png"),
                    () -> assertEquals(imageWrapper.getInsertLabel(), "[1]"),
                    () -> assertInstanceOf(Image.class, imageWrapper.getImage()),
                    () -> assertInstanceOf(ImageView.class, imageWrapper.getImageView())
            );
        }

        @Test
        public void settersTest(){
            Image newFxImage = mock(Image.class);
            Image oldFxImage = imageWrapper.getImage();
            imageWrapper.setPath("new path");
            imageWrapper.setImage(newFxImage);
            imageWrapper.setInsertLabel("new label");

            assertAll(
                    () -> assertEquals(imageWrapper.getPath(), "new path"),
                    () -> assertEquals(imageWrapper.getInsertLabel(), "new label"),
                    () -> assertNotEquals(imageWrapper.getImage(), oldFxImage),
                    () -> assertEquals(imageWrapper.getImage(), newFxImage)
            );
        }

        @Test
        public void equalsImageWrapperTest(){
            ImageWrapper newImageWrapper = new ImageWrapper(
                    imageWrapper.getId(),
                    imageWrapper.getNoteId(),
                    imageWrapper.getPath(),
                    imageWrapper.getInsertLabel()
            );

            assertAll(
                    () -> assertEquals(imageWrapper, imageWrapper),
                    () -> assertSame(imageWrapper, imageWrapper),
                    () -> assertEquals(imageWrapper, newImageWrapper),
                    () -> assertNotSame(imageWrapper, newImageWrapper),
                    () -> assertEquals(imageWrapper.hashCode(), newImageWrapper.hashCode()),
                    () -> assertNotEquals(imageWrapper, null),
                    () -> assertNotEquals(imageWrapper, new Object()),
                    () -> assertInstanceOf(Media.class, imageWrapper)
            );
        }
    }
}
