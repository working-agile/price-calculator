package com.workingagile.acsd.clientscripts;

import com.workingagile.acsd.backend.Item;
import org.springframework.web.client.RestClient;
import java.time.LocalDate;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class InsertTrainingCoursesRestCommand {

    public static void main(String[] args) {
        InsertTrainingCoursesRestCommand.execute();
    }

    public static void execute()  {

        Item[] items = new Item[4];
        items[0] = new  Item(1, LocalDate.now().plusDays(10),
                10, 20, 15, "CSM", 0, 2850);
        items[1] = new  Item(2, LocalDate.now().plusDays(20),
                20, 20, 20, "CSM", 0, 2850);
        items[2] = new  Item(3, LocalDate.now().plusDays(30),
                30, 20, 20, "CSM", 0, 2850);
        items[3] = new  Item(3, LocalDate.now().plusDays(30),
                30, 20, 20, "CSPO", 0, 1300);

        RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().put().uri("insert-training-courses")
                .contentType(APPLICATION_JSON)
                .body(items
                ).retrieve().toBodilessEntity();

        System.out.println("New training courses inserted successfully!");
    }


}
