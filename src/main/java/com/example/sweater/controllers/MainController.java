package com.example.sweater.controllers;


import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repositoryes.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * GreetingController.
 *
 * @author Taras
 */
@Controller
public class MainController {
    private MessageRepo messageRepo;

    @Autowired
    public void setMessageRepo(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public void getMessageRepo(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "text") String text,
            @RequestParam(name = "tag") String tag,
            Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        Message message = new Message(user, text, tag);
        messageRepo.save(message);
        model.put("messages", messages);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

//    @PostMapping("/filter")
//    public String filterMessage(@RequestParam(name = "filter") String filter, Map<String, Object> model) {
//        if (filter.equals("")) {
//            return "redirect:/main";
//        }
//        model.put("messages", messageRepo.findByTag(filter));
//        return "main";
//    }

    @PostMapping("/delete/{id}")
    public String filterMessage(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        messageRepo.deleteById(id);
        return "redirect:/main";
    }

    @PostMapping("/return")
    public String returnMainPage() {
        return "redirect:/main";
    }
}