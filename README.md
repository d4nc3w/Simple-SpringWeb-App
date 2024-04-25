# Simple-SpringWeb-App
Simple Java Web application allowing user to retrieve current-time and year as well as convert numbers retrieved from user using Spring Web framework and REST technology.

    @Controller
    public class TimeYearController {
        @GetMapping("/current-time")
        @ResponseBody
        public String getCurrentTime(
                @RequestParam(value = "timeZone", required = false, defaultValue = "UTC") String timeZone,
                @RequestParam(value = "format", required = false, defaultValue = "hh:mm:ss.SSSS YYYY/MM/DD") String format) {
    
            try {
                // Setting up the SimpleDateFormat object with the provided format
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                String current_time = "";
    
                // Handling different time zone cases
                if (timeZone.equals("GMT")) {
                    timeZone = "Europe/London";
                } else if (timeZone.equals("EST")) {
                    timeZone = "America/New_York";
                } else if (timeZone.equals("CEST")) {
                    timeZone = "Europe/Sarajevo";
                } else if (timeZone.equals("JST")) {
                    timeZone = "Asia/Tokyo";
                } else if (timeZone.equals("PST")) {
                    timeZone = "America/Los_Angeles";
                } else if (timeZone.equals("CST")) {
                    timeZone = "Asia/Shanghai";
                } else if (timeZone.equals("CET")) {
                    timeZone = "Europe/Paris";
                } else if (timeZone.equals("AEST")) {
                    timeZone = "Australia/Sydney";
                } else if (timeZone.equals("BRST")) {
                    timeZone = "America/Sao_Paulo";
                } else if (timeZone.equals("SAST")) {
                    timeZone = "Africa/Johannesburg";
                }
    
                // Setting the time zone and formatting the current time
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                current_time = simpleDateFormat.format(new Date());
                StringBuilder response = new StringBuilder();
    
                // Building the HTML response
                response.append("<h1>Current Time</h1>");
                response.append("<h2>").append(current_time).append("</h2>");
                return response.toString();
    
            } catch (Exception e) {
                return "Error occurred while fetching time: " + e.getMessage();
            }
        }
    
        @GetMapping("/current-year")
        @ResponseBody
        public String getCurrentYear() {
            // Getting the current year using Calendar
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String current_Year = String.valueOf(year);
    
            // Building the HTML response
            StringBuilder response = new StringBuilder();
            response.append("<h1>Current Year</h1>");
            response.append("<h2>").append(current_Year).append("</h2>");
            return response.toString();
        }
    }

The TimeYearController class is a Spring MVC controller responsible for handling HTTP requests related to the current time and year.

The getCurrentTime method handles requests to the /current-time endpoint. It accepts parameters for the time zone and time format and returns the current time in HTML format.
The method uses SimpleDateFormat to format the current time according to the provided format and time zone.
The getCurrentYear method handles requests to the /current-year endpoint and returns the current year in HTML format.

    @Controller
    public class ConvertController {
    
        @PostMapping("/convert")
        @ResponseBody
        public String convertNum(
                @RequestParam("value") String value,
                @RequestParam("base") int base,
                @RequestParam("convertTo") int convertTo) {
    
            try {
                // Convert the provided value from the specified base to a BigInteger
                BigInteger decimal = new BigInteger(value, base);
                // Convert the BigInteger to the specified base
                String resultBase = decimal.toString(convertTo);
    
                // Convert the BigInteger to binary, octal, decimal, and hexadecimal representations
                String BIN = decimal.toString(2);
                String OCT = decimal.toString(8);
                String DEC = decimal.toString(10);
                String HEX = decimal.toString(16);
    
                // Building the HTML response
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

The ConvertController class is a Spring MVC controller responsible for handling HTTP requests for converting numbers between different bases.

The convertNum method handles requests to the /convert endpoint. It accepts parameters for the input value, base of the value, and the base to convert to.
The method converts the input value from the provided base to the target base using BigInteger.
The method also provides representations of the input value in binary, octal, decimal, and hexadecimal formats.
The method returns the conversion result and other representations in HTML format.

