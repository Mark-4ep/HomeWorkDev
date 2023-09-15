package com.example.SpringNotes.mvc;

import com.example.SpringNotes.Note.Note;
import com.example.SpringNotes.Note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
 private final NoteService noteService;
 private long number = 1;
    @GetMapping(value = "/list" )
    public ModelAndView getList() {
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public String createNote() {
        return ("create-note");
    }

    @PostMapping(value = "/create")
    public RedirectView create(@ModelAttribute Note note) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        note.setId(number++);
        noteService.add(note);
        return redirectView;
    }



        @GetMapping(value = "/edit")
        public String editNote(Model model, @RequestParam long id) {
            Note note = noteService.getById(id);
            model.addAttribute("note" , note);
            return ("edit-note");
        }
        @PostMapping(value = "/edit")
        public RedirectView edit(@ModelAttribute Note note) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/note/list");
            noteService.update(note);
            return redirectView;
        }


    @PostMapping("/delete")
    public RedirectView delete(@ModelAttribute Note note) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.deleteById(note.getId());
        return redirectView;
    }



}
