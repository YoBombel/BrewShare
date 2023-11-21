package com.yobombel.brewshare.beer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlingController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandlingController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            log.error("Sorry, can not open website");

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                log.error("Page not found 404");
                return "error404";
            }
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                //log.error("Server error");
//                return "error-500";
//            } else if (statusCode == HttpStatus.FORBIDDEN.value()){
//                //log.error("Someone tried enter to restricted area. User not logged in.");
//                return "error-403";
//            }
        }
        log.error("Other error, please contact with support: 666-999-666");
        return "error";

    }

}