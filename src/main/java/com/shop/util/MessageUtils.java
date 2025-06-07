package com.shop.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageUtils {

    public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "redirectUri", required = false) String redirectUri,
            @RequestParam(value = "MessageMethod", required = false) MessageMethod MessageMethod,
            @RequestParam(value = "params", required = false) Map<String, Object> params, Model model) {

        model.addAttribute("message", message);
        model.addAttribute("redirectUri", redirectUri);
        model.addAttribute("MessageMethod", MessageMethod);
        model.addAttribute("params", params);

        return "utils/message-redirect";
    }

}