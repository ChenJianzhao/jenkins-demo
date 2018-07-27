package com.example.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET,produces ="text/html;charset=utf-8")
    @ResponseStatus(value = HttpStatus.OK)
    public String printWelcome( HttpServletResponse response) {
        response.addHeader("content-type", "text/html;charset=utf-8");
        return "welcome";
    }

    @RequestMapping(value = "/checkpreload.htm", method = RequestMethod.GET)
    @ResponseBody
    public String printSuccess() {
        return "success";
    }
}
