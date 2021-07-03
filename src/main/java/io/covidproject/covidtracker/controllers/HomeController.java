package io.covidproject.covidtracker.controllers;

import io.covidproject.covidtracker.models.LocationStats;
import io.covidproject.covidtracker.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allstats = covidDataService.getAlLStats();
        int totalCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allstats);
        model.addAttribute("totalReportedCases", totalCases);
        return "home";
    }
}
