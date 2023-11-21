package com.yobombel.brewshare.beer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlingController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            //LOGGER.error("Sorry, can not open website");

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                //LOGGER.error("Page not found 404");
                return "error404";
            }
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                //LOGGER.error("Server error");
//                return "error-500";
//            } else if (statusCode == HttpStatus.FORBIDDEN.value()){
//                //LOGGER.error("Someone tried enter to restricted area. User not logged in.");
//                return "error-403";
//            }
        }
        //LOGGER.error("Other error, please contact with support: 666-999-666");
        return "error";

    }

}

