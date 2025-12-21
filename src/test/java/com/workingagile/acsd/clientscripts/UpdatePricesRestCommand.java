package com.workingagile.acsd.clientscripts;

import com.workingagile.acsd.backend.Item;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class UpdatePricesRestCommand {


    private static RestClient restClient;

    public static void main(String[] args) {
        UpdatePricesRestCommand.execute();
    }


    public static void execute()  {

        RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().put().uri("update-training-course-prices")
                .retrieve().toBodilessEntity();
    }



}
