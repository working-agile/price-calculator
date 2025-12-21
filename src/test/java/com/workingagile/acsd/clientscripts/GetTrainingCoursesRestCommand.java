package com.workingagile.acsd.clientscripts;

import com.workingagile.acsd.backend.Item;
import org.springframework.web.client.RestClient;

public class GetTrainingCoursesRestCommand {

    private static RestClient restClient;
    public static Item[] items;

    public static void main(String[] args) {
        GetTrainingCoursesRestCommand.execute();
    }

    public static void execute()  {
        items = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .build().get().uri("training-courses").retrieve().body(Item[].class);

        if (items != null) {
            for (Item item : items) {
                System.out.println("Type: " + item.type);
                System.out.println("When: " + item.trDate);
                System.out.println("Remaining days before training course: " + item.days);
                System.out.println("Full Price: " + item.full);
                System.out.println("Current price: " + item.curr);
                System.out.println("Number of seats: " + item.seats);
                System.out.println("Remaining available seats: " + item.avail);
                System.out.println();
            }
        }

    }

}
