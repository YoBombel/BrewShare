package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BeersmithService {

    BeersmithParser beersmithParser;
    BeersmithMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(BeersmithService.class);

    public BeersmithService(BeersmithParser beersmithParser, BeersmithMapper mapper) {
        this.beersmithParser = beersmithParser;
        this.mapper = mapper;
    }

    public List<Beer> readBeersFromXmlFile(MultipartFile file) throws IOException {
        log.info("Reading file: {}", file.getName());
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("File is empty");
            }

            try (InputStream inputStream = file.getInputStream()) {
                List<BeersmithRecipe> beersmithRecipes = beersmithParser.parse(inputStream);
                return beersmithRecipes.stream()
                        .map(beersmithRecipe -> mapper.map(beersmithRecipe))
                        .toList();
            }
        } catch (IOException e) {
            //TODO REFACTOR THIS NONSENSE
            throw new IOException(e);
        }
    }
}