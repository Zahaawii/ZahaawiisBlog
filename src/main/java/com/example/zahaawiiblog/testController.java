package com.example.zahaawiiblog;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@Controller
public class testController {

    @GetMapping("/api/index")
        public String h() {
            return "index";
        }
    }

