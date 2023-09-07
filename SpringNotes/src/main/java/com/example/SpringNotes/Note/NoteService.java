package com.example.SpringNotes.Note;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    Map<Long,Note> notes = new HashMap<>();

    public Note add(Note note){
        if (note.getContent() == null || note.getTitle() == null || note.getId() <= 0) {
            throw new NoSuchElementException("This note is empty, ID must be greater than 0");
        }else if (notes.containsValue(note)) {
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

        Note oldNote = notes.get(note.getId());
        notes.replace(note.getId(), oldNote, note);


    }
    public Note getById(long id) {
        verificationId(id);

        return notes.get(id);
    }
    public List<Note> listAll() {
        List<Note> listAllNotes = new ArrayList<>();

        for (Map.Entry<Long, Note> entry : notes.entrySet()) {
            listAllNotes.add(entry.getValue());
        }
        return listAllNotes;
        
    }

    public void verificationId(long id) {
        if (notes.get(id) == null) {
            throw new NoSuchElementException("The note is missing.");
        }
    }
}
