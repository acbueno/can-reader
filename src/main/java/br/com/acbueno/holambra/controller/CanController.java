package br.com.acbueno.holambra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.acbueno.holambra.service.CanService;


@Controller
public class CanController {

  @Autowired
  CanService service;

  @GetMapping("/")
  public String showMessage(Model model) {
    service.init();
    model.addAttribute("messages", service.findAllMessage());
    return "index";
  }

}
