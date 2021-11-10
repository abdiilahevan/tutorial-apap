package apap.tutorial.emsidi.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController{
    @GetMapping("/")
    private String home(){return "home";}
}