package ru.askarov.notepad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.askarov.notepad.dao.RecordDao;
import ru.askarov.notepad.model.Record;

@Controller
@RequestMapping("/records")
public class RecordController {
    private RecordDao dao;

    @Autowired
    public RecordController(RecordDao dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("records", dao.index());
        return "records/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        model.addAttribute("record", dao.show(id));
        return "records/show";
    }

    @GetMapping("/new")
    public String newRecord(@ModelAttribute("record")Record record){
        return "records/new";
    }

    @PostMapping
    public String create(@ModelAttribute("record") Record record){
        dao.save(record);
        return "redirect:/records";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("record", dao.show(id));
        return "records/edit";
    }

    @PatchMapping("/{id}")
    public String update(//@ModelAttribute("record") Record record,
                         @RequestParam("title") String title,
                         @RequestParam("article") String article,
                         @PathVariable("id") long id){
        Record record = dao.show(id);
        record.setTitle(title);
        record.setArticle(article);
        dao.update(record);
        return "redirect:/records";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        dao.delete(id);
        return "redirect:/records";
    }
}
