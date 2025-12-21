package com.workingagile.acsd.clientscripts;

import jakarta.mail.internet.MimeMessage;
import org.springframework.web.client.RestClient;

public class CheckForEmailsCommand {

    public static void main(String[] args) {
        CheckForEmailsCommand.execute();
    }

    public static void execute()  {

        String[][] emailMessages = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().get().uri("emails").retrieve().body(String[][].class);

        if (emailMessages != null) {
            System.out.println("Number of emails found on the email server: " + emailMessages.length);
            for (String[] emailMessage : emailMessages) {
                try {
                    System.out.println("Subject: " + emailMessage[0]);
                    System.out.println("Content: " + emailMessage[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No emails found on the email server!");
        }

    }

}
