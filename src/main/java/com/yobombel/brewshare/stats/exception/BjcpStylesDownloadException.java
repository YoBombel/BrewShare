package com.yobombel.brewshare.stats.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BjcpStylesDownloadException extends RuntimeException{

    private static final Logger log = LoggerFactory.getLogger(BjcpStylesDownloadException.class);

    public BjcpStylesDownloadException(String url){
        super("BJCP styles guidelines download failed, URL: " + url);
        log.info("BJCP styles guidelines download failed, URL: " + url);
    }

}
