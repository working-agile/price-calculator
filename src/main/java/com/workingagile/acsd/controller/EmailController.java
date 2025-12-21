package com.workingagile.acsd.controller;

import com.icegreen.greenmail.util.GreenMailUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import static com.workingagile.acsd.backend.mailer.EmailServer.greenMail;

@RestController
public class EmailController {

    @GetMapping("/emails")
    public String[][] getEmails() {

        greenMail.waitForIncomingEmail(Duration.ofSeconds(1).toMillis(), 1);
        MimeMessage[] messages = greenMail.getReceivedMessages();

        String[][] emailMessages = new String[messages.length][2];
        int i=0;

        for (MimeMessage message : messages) {
            try {
                String subject = message.getSubject();
                String body = GreenMailUtil.getBody(message);

                emailMessages[i++] = new String[]{subject, body};

                System.out.println("---------------------------------------");
                System.out.println("Subject: " + subject);
                System.out.println("Body: " + body);
                System.out.println("---------------------------------------");

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        return emailMessages;
    }

}
