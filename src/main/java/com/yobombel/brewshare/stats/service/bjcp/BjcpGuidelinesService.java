package com.yobombel.brewshare.stats.service.bjcp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BjcpGuidelinesService {

    @Value("${bjcp.styles.url}")
    private String bjcpStylesUrl;

    private static final Logger log = LoggerFactory.getLogger(BjcpGuidelinesService.class);

    public Map<String, List<String>> setupStyleTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> bjcpStylesAndTags = new HashMap<>();
        try {
            JsonNode arrayNode = objectMapper.readTree(
                    new URL(bjcpStylesUrl));
            fillStylesAndTags(bjcpStylesAndTags, arrayNode);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return bjcpStylesAndTags;
    }

    private static void fillStylesAndTags(Map<String, List<String>> bjcpStylesAndTags, JsonNode arrayNode) {
        for (JsonNode elementNode : arrayNode) {
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
    }
}
