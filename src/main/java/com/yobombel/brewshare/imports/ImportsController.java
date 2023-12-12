package com.yobombel.brewshare.imports;

import com.yobombel.brewshare.beer.BeerService;
import com.yobombel.brewshare.imports.beersmith3.BeersmithService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("import/")
public class ImportsController {

    BeerService beerService;
    BeersmithService beersmithService;
    private static final Logger log = LoggerFactory.getLogger(ImportsController.class);


    public ImportsController(BeerService beerService, BeersmithService beersmithService) {
        this.beerService = beerService;
        this.beersmithService = beersmithService;
    }

    @GetMapping("")
    public String importMenu() {
        log.info("Request for import menu");
        return "importMenu";
    }

    @PostMapping("beersmith")
    public String uploadBeersmithFile(Model model, @RequestParam("beersmithFile") MultipartFile file) throws IOException {
        log.info("POST request - upload beersmith file");
        beerService.addAll(
                beersmithService.readBeersFromXmlFile(file));
        return "redirect:/beer/all";
    }

}