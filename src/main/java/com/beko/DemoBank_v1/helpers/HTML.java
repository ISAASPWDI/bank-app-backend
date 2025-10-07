package com.beko.DemoBank_v1.helpers;

public class HTML {

    public static String htmlEmailTemplate(String token, String code) {
        String url = "http://127.0.0.1:8070/verify?token=" + token + "&code=" + code;
        String emailTemplate = "<html><body>" +
                "<h1>Welcome!</h1>" +
                "<p>Please, click on the following link to verify your account:</p>" +
                "<a href='" + url + "'>Verify my account</a>" +
                "</body></html>";
        return emailTemplate;
    }
}
