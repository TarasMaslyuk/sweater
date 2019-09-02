/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2019 VTB Group. All rights reserved.
 */

package com.example.sweater.controllers;


import com.example.sweater.domain.Message;
import com.example.sweater.repositoryes.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam(name = "text") String text, @RequestParam(name = "tag") String tag, Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        Message message = new Message(text, tag);
        messageRepo.save(message);
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filterMessage(@RequestParam(name = "filter") String filter, Map<String, Object> model) {
        model.put("messages", messageRepo.findByTag(filter));
        return "main";
    }
    @PostMapping("/delete/{id}")
    public String filterMessage(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        messageRepo.deleteById(id);
        System.out.println("sad");
        return "redirect:/main";

    }
}