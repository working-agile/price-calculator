package com.workingagile.acsd.clientscripts;

import org.springframework.web.client.RestClient;

public class UpdatePricesRestCommand {

    public static void main(String[] args) {
        UpdatePricesRestCommand.execute();
    }

    public static void execute()  {

        RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().put().uri("update-training-course-prices")
                .retrieve().toBodilessEntity();

        System.out.println("Training course prices updated successfully!");
    }

}
