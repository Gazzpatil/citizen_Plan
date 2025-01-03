package org.example.citizenplan.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.example.citizenplan.dto.SearchResult;
import org.example.citizenplan.model.CitizenPlan;
import org.example.citizenplan.service.CitizenPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CitizenController {
    @Autowired
   CitizenPlanService citizenPlanService;

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        System.out.println("**************** application/octet-stream ******************");
        String headerKey = "Content-Disposition";
        LocalDateTime localDateTime=LocalDateTime.now();
        String local=localDateTime.toString();
        String headerValue = String.format("attachment; filename=\"%s\" CitizenPlan.xls", local);
        response.setHeader(headerKey, headerValue);
        citizenPlanService.exportToExcel(response);

    }



    @RequestMapping ("/form")
    public String form(Model model){
        SearchResult searchResult=new SearchResult();
        model.addAttribute("searchResult",searchResult);
        System.out.println(citizenPlanService.getPlanName() +" citizen plan");
        model.addAttribute("planName",citizenPlanService.getPlanName());
        model.addAttribute("planStatus",citizenPlanService.getPlanStatus());
        System.out.println("citizenPlanService.getPlanStatus() "+citizenPlanService.getPlanStatus());
        return "index";
    }

    @PostMapping("/citizenPlanList")
    public String search(@ModelAttribute("searchResult") SearchResult searchResult, Model model){
        List<CitizenPlan> citizenPlan = citizenPlanService.getCitizenPlan(searchResult);
        System.out.println(citizenPlan+"************** all data controller **********************");
        model.addAttribute("citizenPlan",citizenPlan);
        model.addAttribute("planName",citizenPlanService.getPlanName());
        model.addAttribute("planStatus",citizenPlanService.getPlanStatus());
        return "index";
    }

}
