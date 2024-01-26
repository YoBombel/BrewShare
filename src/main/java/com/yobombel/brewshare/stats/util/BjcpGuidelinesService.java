package com.yobombel.brewshare.stats.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BjcpGuidelinesService {

    private BjcpGuidelinesService() {
        throw new IllegalStateException("Utility class shouldn't be initialized.");
    }

    private static final Logger log = LoggerFactory.getLogger(BjcpGuidelinesService.class);

    //TODO add dark-lagerfamily fix
    public static Map<String, List<String>> setupStyleTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> bjcpStylesAndTags = new HashMap<>();
        try {
            JsonNode arrayNode = objectMapper.readTree( //TODO extract method
                    new URL("https://raw.githubusercontent.com/ascholer/bjcp-styleview/main/styles.json"));
            for (JsonNode elementNode : arrayNode) { //TODO extract method
                String styleName = elementNode.get("name").toString();
                styleName = styleName.trim().replace("\"", "");

                String tagsString = elementNode.get("tags").toString();
                List<String> tags = Arrays.stream(
                                tagsString.split(","))
                        .map(String::trim)
                        .map(s -> s.replace("\"", ""))
                        .toList();
                bjcpStylesAndTags.put(styleName, tags);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return bjcpStylesAndTags;
    }
}
