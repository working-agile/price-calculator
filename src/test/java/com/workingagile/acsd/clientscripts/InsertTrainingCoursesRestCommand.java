package com.workingagile.acsd.clientscripts;

import com.workingagile.acsd.backend.Item;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class InsertTrainingCoursesRestCommand {


    private static RestClient restClient;
    public static Item[] items;

    public static void main(String[] args) {
        InsertTrainingCoursesRestCommand.execute();
    }

/*
    INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
    VALUES(1,'2025-01-09',9,30,7,'CSPO',4000);
    INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
    VALUES(2,'2025-01-10',10,30,8,'CSO',3000);
    INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
    VALUES(3, '2025-01-20',20,30,27,'CSM',3000);


 */
    public static void execute()  {

        items = new Item[3];
        items[0] = new  Item(1, LocalDate.now().plusDays(10),
                10, 20, 15, "CSM", 0, 2850);
        items[1] = new  Item(2, LocalDate.now().plusDays(20),
                20, 20, 20, "CSM", 0, 2850);
        items[2] = new  Item(3, LocalDate.now().plusDays(30),
                30, 20, 20, "CSM", 0, 2850);

        RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().put().uri("insert-training-courses")
                .contentType(APPLICATION_JSON)
                .body(items
                ).retrieve().toBodilessEntity();

    }



}
