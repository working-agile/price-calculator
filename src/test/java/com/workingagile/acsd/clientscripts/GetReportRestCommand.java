package com.workingagile.acsd.clientscripts;

import org.springframework.web.client.RestClient;

public class GetReportRestCommand {

    private static RestClient restClient;

    public static void main(String[] args) {
        GetReportRestCommand.execute();
    }

    public static void execute() {

        int salesTarget = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build().get().uri("report").retrieve().body(Integer.class);

        System.out.println("---------------------------------------");
        System.out.println("Remaining sales target: " + salesTarget);
        System.out.println("---------------------------------------");
    }
}
