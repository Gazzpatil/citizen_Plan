package org.example.citizenplan.controller;


import lombok.Getter;
import org.example.citizenplan.dto.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CitizenController {

    @RequestMapping ("/form")
    public String form(Model model){
        SearchResult searchResult=new SearchResult();
        model.addAttribute("searchResult",searchResult);
        return "index";
    }

}
