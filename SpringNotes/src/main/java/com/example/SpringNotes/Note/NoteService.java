package com.example.SpringNotes.Note;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    Map<Long,Note> notes = new HashMap<>();

    public Note add(Note note){
        if (note.getContent() == null || note.getTitle() == null || note.getId() <= 0) {
            throw new NoSuchElementException("This note is empty, ID must be greater than 0");
        }else if (notes.get(note.getId()) != null) {
            throw new NoSuchElementException("A note with such an ID is already scurrying");
        }
        notes.put(note.getId(), note);
        return note;
    }
    public void deleteById(long id) {
        if (notes.size() < id) {
            throw new NoSuchElementException("No note found for this: " + id);
        }
        notes.remove(id);
    }
    public void update(Note note) {
        if (notes.size() < note.getId()) {
            throw new NoSuchElementException("Note does not exist.");
        }

        Note newNote = new Note();
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());

    }
    public Note getById(long id) {
        return notes.get(id);
    }
    public Map<Long, Note> listAll() {
        return notes;
    }


}
