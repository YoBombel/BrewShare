package com.yobombel.brewshare.stats.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yobombel.brewshare.stats.model.BeerStatsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

//TODO REFACTOR
@Service
public class BjcpTagService {

    private static final Logger log = LoggerFactory.getLogger(BjcpTagService.class);
    private final Map<String, List<String>> bjcpStylesAndTags = new HashMap<>();

    public BjcpTagService() {
        setupStyleTags();
    }

    public Map<String, Integer> countStyles(List<BeerStatsDto> beerStatsDtos) {
        Map<String, Integer> stylesCount = new HashMap<>();

        beerStatsDtos.stream()
                .map(BeerStatsDto::style)
                .forEach(style -> stylesCount.merge(style, 1, Integer::sum));

        return stylesCount;
    }

    public Map<String, BigDecimal> countTagsPercentages(Map<String, Integer> stylesCount, List<String> tags) {
        Map<String, BigDecimal> tagsPercentages = new HashMap<>();
        Map<String, Integer> tagCount = countTagOccurrences(stylesCount, tags);
        int allTagsSum = sumCounts(tagCount);

        for (Map.Entry<String, Integer> entry : tagCount.entrySet()
        ) {
            tagsPercentages.put(entry.getKey(),
                    calculatePercentage(entry.getValue(), allTagsSum));
        }
        return sortMapByValue(tagsPercentages);
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private BigDecimal calculatePercentage(Integer value, int tagsSum) {
        return BigDecimal.valueOf((value * 100) / tagsSum);
    }

    private int sumCounts(Map<String, Integer> tagCounts) {
        int sum = 0;
        for (Integer i : tagCounts.values()
        ) {
            sum += i;
        }
        return sum;
    }

    private Map<String, Integer> countTagOccurrences(Map<String, Integer> stylesCount, List<String> tags) {
        Map<String, Integer> tagCounting = new HashMap<>();
        for (Map.Entry<String, Integer> entry : stylesCount.entrySet()
        ) {
            String currentStyle = entry.getKey();
            Integer currentStyleCount = entry.getValue();

            if (bjcpStylesAndTags.containsKey(currentStyle)) {

                List<String> currentStyleTags = bjcpStylesAndTags.get(currentStyle);
                String relevantTag = "";

                for (String currentTag : tags
                ) {
                    relevantTag = currentStyleTags.stream().filter(t -> t.contains(currentTag)).findFirst().orElse("");
                    if (!relevantTag.isBlank())
                        break;
                }

                if (!relevantTag.isBlank())
                    tagCounting.merge(formatName(relevantTag), currentStyleCount, Integer::sum);

            }
        }
        return tagCounting;
    }

    private String formatName(String t) {
        return (t.substring(0, 1).toUpperCase() +
                t.substring(1))
                .trim()
                .replace("-", " ");
    }

    private void setupStyleTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode arrayNode = objectMapper.readTree(new File("src/main/resources/bjcp/stylesTags.json"));
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
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}