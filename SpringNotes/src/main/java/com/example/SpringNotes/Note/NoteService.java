package com.example.SpringNotes.Note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public Note add(Note note){
        noteRepository.save(note);
        return note;
    }
    public void deleteById(int id) {
        noteRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("The note is missing.");
                });
        noteRepository.deleteById(id);
    }
    public void update(Note note) {
        noteRepository.findById(note.getId())
                .stream()
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("The note is missing.");
                });
        noteRepository.save(note);
    }
    public Note getById(int id) {

        return  noteRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("The note is missing.");
                });
    }
    public List<Note> listAll() {
        return noteRepository.findAll();
    }
}
