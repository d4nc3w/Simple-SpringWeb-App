package org.example.tpo_05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeYearController {
    @GetMapping("/current-time") //localhost:8080/current-time?timeZone=UTC&format=DD-MM-YYYYhh:mm
    @ResponseBody                  //localhost:8080/current-time
    public String getCurrentTime(
            @RequestParam(value = "timeZone", required = false, defaultValue = "UTC") String timeZone,
            @RequestParam(value = "format", required = false, defaultValue = "hh:mm:ss.SSSS YYYY/MM/DD") String format) {
        try {                                                       //"hh:mm:ss.SSS YYYY/MM/dd" <- to have correct date
                                                                    //big DD: 2024/04/102, small dd: 2024/04/11
            /*SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            String currentTime = sdf.format(new Date());
            return currentTime;

            ZonedDateTime time = ZonedDateTime.now(ZoneId.of(timeZone));
            DateFormat df = new SimpleDateFormat(format);
            df.setTimeZone(TimeZone.getTimeZone(time.getZone()));
            String current_time = df.format(new Date());
            */

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            String current_time = "";

            if(timeZone.equals("GMT")){
                timeZone = "Europe/London";
            }
            else if (timeZone.equals("EST")){
                timeZone = "America/New_York";
            }
            else if (timeZone.equals("CEST")){
                timeZone = "Europe/Sarajevo";
            }
            else if (timeZone.equals("JST")){
                timeZone = "Asia/Tokyo";
            }
            else if (timeZone.equals("PST")){
                timeZone = "Amercia/Los_Angeles";
            }
            else if (timeZone.equals("CST")){
                timeZone = "Asia/Shanghai";
            }
            else if (timeZone.equals("CET")){
                timeZone = "Europe/Paris";
            }
            else if (timeZone.equals("AEST")){
                timeZone = "Australia/Sydney";
            }
            else if (timeZone.equals("BRST")){
                timeZone = "America/Sao_Paulo";
            }
            else if (timeZone.equals("SAST")){
                timeZone = "Africa/Johannesburg";
            }
            //And more...

            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            current_time = simpleDateFormat.format(new Date());
            StringBuilder response = new StringBuilder();

            response.append("<h1>Current Time</h1>");
            response.append("<h2>").append(current_time).append("</h2>");
            return response.toString();

        } catch (Exception e) {
            return "Error occurred while fetching time: " + e.getMessage();
        }
    }

    @GetMapping("/current-year") //localhost:8080/current-year
    @ResponseBody
    public String getCurrentYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String current_Year = String.valueOf(year);
        StringBuilder response =  new StringBuilder();
        response.append("<h1>Current Year</h1>");
        response.append("<h2>").append(current_Year).append("</h2>");
        return response.toString();
    }
}

