package org.example.citizenplan.controller;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CitizenController {

    @RequestMapping ("/m1")
    public String m1(){
        return "index";
    }

}
