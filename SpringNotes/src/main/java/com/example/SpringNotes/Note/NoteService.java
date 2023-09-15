package com.example.SpringNotes.Note;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    Map<Long,Note> notes = new HashMap<>();

    public Note add(Note note){
        if (note.getContent().length() == 0 || note.getTitle().length() == 0 || note.getId() <= 0) {
            throw new NoSuchElementException("This note is empty, ID must be greater than 0");
        }else if (notes.containsKey(note.getId())) {
            throw new NoSuchElementException("A note with such an ID is already scurrying");
        }

        notes.put(note.getId(),note);

        return note;
    }
    public void deleteById(long id) {
        verificationId(id);
        notes.remove(id);
    }
    public void update(Note note) {
        verificationId(note.getId());

        notes.put(note.getId(), note);
    }
    public Note getById(long id) {
        verificationId(id);

        return notes.get(id);
    }
    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public void verificationId(long id) {
        if (notes.get(id) == null) {
            throw new NoSuchElementException("The note is missing.");
        }
    }
}