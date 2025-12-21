package com.workingagile.acsd.backend.mailer;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

public class EmailServer {

    public static GreenMail greenMail;

    public static void bootingEmailServer() {

        greenMail = new GreenMail(ServerSetupTest.ALL);
        greenMail.start();

        System.out.println("Email server booted successfully!");
    }

}
