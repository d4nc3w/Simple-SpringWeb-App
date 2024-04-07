package org.example.tpo_05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class ConvertController {

    @PostMapping("/convert")    //localhost:8080/convert.html
    @ResponseBody
    public String convertNum(
            @RequestParam("value") String value,
            @RequestParam("base") int base,
            @RequestParam("convertTo") int convertTo) {

        try {
            BigInteger decimal = new BigInteger(value, base);
            String resultBase = decimal.toString(convertTo);

            String bin = decimal.toString(2);
            String oct = decimal.toString(8);
            String dec = decimal.toString();
            String hex = decimal.toString(16).toUpperCase();
            StringBuilder response = new StringBuilder();

            response.append("<h2>Conversion Result</h2>");
            response.append("<p>Value ").append(value).append(" (base ").append(base).append(") converted to base ").append(convertTo).append(":</p>");
            response.append("<p>Result: ").append(resultBase).append("</p>");
            response.append("<p>Binary: ").append(bin).append("</p>");
            response.append("<p>Octal: ").append(oct).append("</p>");
            response.append("<p>Decimal: ").append(dec).append("</p>");
            response.append("<p>Hexadecimal: ").append(hex).append("</p>");
            return response.toString();

        } catch (NumberFormatException e) {
            return "Error: Invalid input value or bases.";
        }
    }
}
