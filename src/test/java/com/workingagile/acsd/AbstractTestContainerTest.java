package com.workingagile.acsd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractTestContainerTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    static String url;
    static String username;
    static String password;


    @BeforeAll
    static void beforeAll() {
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
        clearDatabase();
    }

    Connection getTestdatabaseConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearDatabase() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                    delete  from tr_crs;
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
                        tr_date DATE not null,
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






}
