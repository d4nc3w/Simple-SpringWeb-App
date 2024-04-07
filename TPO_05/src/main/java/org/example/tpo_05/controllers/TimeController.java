package org.example.tpo_05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/current-time") //localhost:8080/current-time?timeZone=UTC&format=DD-MM-YYYYhh:mm
    @ResponseBody                   //localhost:8080/current-time.html
    public String getCurrentTime(
            @RequestParam(value = "timeZone", required = false, defaultValue = "UTC") String timeZone,
            @RequestParam(value = "format", required = false, defaultValue = "hh:mm:ss.SSSS YYYY/MM/DD") String format) {
        try {
            /*SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            String currentTime = sdf.format(new Date());
            return "<div style=\"text-align: center;\"><h1>" + currentTime + "</h1></div>";*/
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime currentTime = LocalDateTime.now(ZoneId.of(timeZone));
            String displayedTime = "<div style=\"text-align: center;\"><h1>" + currentTime.format(formatter) + "</h1></div>";
            return displayedTime;
        } catch (Exception e) {
            return "Error occurred while fetching time: " + e.getMessage();
        }
    }

    @GetMapping("/current-year")    //localhost:8080/current-year
    @ResponseBody                      //localhost:8080/current-year.html
    public String getCurrentYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return "<div style=\"text-align: center;\"><h1>" + currentYear + "</h1></div>";
    }
}

