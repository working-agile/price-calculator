package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestContainerTests {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    private static String url;
    private static String username;
    private static String password;


    @BeforeAll
    static void beforeAll() {


        // TODO: set the URL, password and user


        postgres.start();
        url = postgres.getJdbcUrl();
        username = postgres.getUsername();
        password = postgres.getPassword();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        System.out.println("JdbcUrl: " + url  );
        System.out.println("Username: " +  username);
        System.out.println("Password: " +  password);

        createCustomersTableIfNotExists();
        createTestTrainingCourses();

    }

    private void createTestTrainingCourses() {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
                PreparedStatement pstmt = conn.prepareStatement(
                        """
                                INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                                   VALUES(1,'9 January 2025',9,30,7,'CSPO',4000);
                                INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                                   VALUES(2,'10 January 2025',10,30,8,'CSO',3000);
                                INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                                    VALUES(3, '20 January 2025',20,30,27,'CSM',3000);
                            """
                );
                pstmt.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    private void createCustomersTableIfNotExists() {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                    create table if not exists tr_crs (
                        id bigint NOT NULL,
                        tr_date varchar(20) not null,
                        days integer,
                        ttl_seats integer,
                        avail integer,
                        type char(10),
                        curr_price integer,
                        full_price integer
                    )
                    """
            );
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void manual_tests_simulating_test_database() {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            DataProcessor.setTestConnection(connection);

            // 1. Show the current prices
            DataProcessor processor = DataProcessor.getInstance();

            processor.calculateData(false);

            System.out.println("Scheduled training courses:");
            for (Item item : processor.list) {
                System.out.println("------------------------------");
                System.out.println("Type: " + item.type);
                System.out.println("When: " + item.trDate);
                System.out.println("Remaining days before training course: " + item.days);
                System.out.println("Full Price: " + item.full);
                System.out.println("Current price: " + item.curr);
                System.out.println("Number of seats: " + item.seats);
                System.out.println("Remaining available seats: " + item.avail);
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Total sales target remaining: " + processor.salesValue);
            System.out.println("-----------------------------------------------------");


            // 2. Move to next day
            System.out.println("\n\nMove to next day\n");
            processor.calculateData(true);

            for (Item item : processor.list) {
                System.out.println("Current training courses:");
                System.out.println("------------------------------");
                System.out.println("Training course");
                System.out.println("Type: " + item.type);
                System.out.println("When: " + item.trDate);
                System.out.println("Remaining days before training course: " + item.days);
                System.out.println("Full Price: " + item.full);
                System.out.println("Current price: " + item.curr);
                System.out.println("Number of seats: " + item.seats);
                System.out.println("Remaining available seats: " + item.avail);
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Total sales target remaining: " + processor.salesValue);
            System.out.println("-----------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
