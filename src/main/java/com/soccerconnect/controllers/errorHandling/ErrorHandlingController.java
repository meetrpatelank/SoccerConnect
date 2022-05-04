package com.soccerconnect.controllers.errorHandling;

import com.soccerconnect.controllers.MasterController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlingController extends MasterController {

    @GetMapping(value = "/errorHandler")
    public String errorHandling(Model model) {
        return welcome();
    }
}
