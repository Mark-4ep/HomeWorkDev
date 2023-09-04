package Note;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class NoteService {
    List<Note> notes = new ArrayList<>();
    public Note add(Note note){
        if (note.getContent() == null || note.getTitle() == null) {
            throw new NoSuchElementException("This note is empty. Create a note");
        }
            notes.add(note);
        note.setId(notes.indexOf(note));
        return note;
    }

    public void deleteById(long id) {
        if (notes.size() < id) {
            throw new NoSuchElementException("No note found for this: " + id);
        }
        notes.remove((int)id);
    }

    public void update(Note note) {
        if (notes.size() < note.getId()) {
            throw new NoSuchElementException("Note does not exist.");
        }

        Note newNote = new Note();
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        notes.set((int)note.getId(),newNote);

    }

    public Note getById(long id) {
        return notes.get((int) id);
    }
    public List<Note> listAll() {
        return notes;
    }


}
