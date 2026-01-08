package com.workingagile.acsd.clientscripts;

import com.workingagile.acsd.backend.Item;
import org.springframework.web.client.RestClient;

public class GetTrainingCoursesRestCommand {

    public static void main(String[] args) {
        GetTrainingCoursesRestCommand.execute();
    }

    public static String execute()  {
        Item[] items = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .build().get().uri("training-courses").retrieve().body(Item[].class);

        StringBuilder sb = new StringBuilder();
        if (items != null) {
            for (Item item : items) {
                sb.append("Type: " + item.type).append(System.lineSeparator());
                sb.append("When: " + item.trDate).append(System.lineSeparator());
                sb.append("Remaining days before training course: " + item.days).append(System.lineSeparator());
                sb.append("Full Price: " + item.full).append(System.lineSeparator());
                sb.append("Current price: " + item.curr).append(System.lineSeparator());
                sb.append("Number of seats: " + item.seats).append(System.lineSeparator());
                sb.append("Remaining available seats: " + item.avail).append(System.lineSeparator());
                sb.append(System.lineSeparator());
            }
        } else {
            sb.append("No training courses found on the database!");
        }
        return sb.toString();
    }

}
