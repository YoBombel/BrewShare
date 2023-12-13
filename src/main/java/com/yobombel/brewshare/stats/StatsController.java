package com.yobombel.brewshare.stats;

import com.yobombel.brewshare.stats.service.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

    StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("")
    public String getStats(Model model){
        model.addAttribute("stats", statsService.getStats());
        return "stats";
    }

}