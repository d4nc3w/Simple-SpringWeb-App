package org.example.tpo_05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class ConvertController {

    @PostMapping("/convert") //localhost:8080/convert.html
    @ResponseBody
    public String convertNum(
            @RequestParam("value") String value,
            @RequestParam("base") int base,
            @RequestParam("convertTo") int convertTo) {

        try {
            BigInteger decimal = new BigInteger(value, base);
            String resultBase = decimal.toString(convertTo);

            String BIN = decimal.toString(2);
            String OCT = decimal.toString(8);
            String DEC = decimal.toString(10);
            String HEX = decimal.toString(16);

            StringBuilder response = new StringBuilder();
            response.append("<h1>Conversion Result</h1>");
            response.append("<h2>Value: ").append(value).append(", with base: ")
                    .append(base).append(", converted to base ").append(convertTo).append(":</h2>");
            response.append("<h3>Result: ").append(resultBase).append("</h3>");
            response.append("<h3>Other representations: </h3>");
            response.append("<h4>BIN: ").append(BIN).append("</h4>");
            response.append("<h4>OCT: ").append(OCT).append("</h4>");
            response.append("<h4>DEC: ").append(DEC).append("</h4>");
            response.append("<h4>HEX: ").append(HEX).append("</h4>");
            return response.toString();

        } catch (NumberFormatException e) {
            return "<h2>Invalid input value or bases</h2>";
        }
    }
}
