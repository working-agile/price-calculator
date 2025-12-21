package com.workingagile.acsd.backend.persistence;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ECommercePersistence {

    public static final String URL_PRODUCTION_DATABASE = "jdbc:postgresql://127.0.0.1:5432/production_database";

    private static PostgreSQLContainer<?> postgres;

    public static void bootingECommercePersistence() {
        startDatabase();
        createCustomersTableIfNotExists();
    }

    private static void startDatabase() {
        postgres = new PostgreSQLContainer<>(
                "postgres:16-alpine"
        );
        postgres.withPassword("postgres")
                .withUsername("postgres")
                .withDatabaseName("production_database")
                .withCreateContainerCmdModifier(
                        cmd ->
                                cmd.withHostConfig(new HostConfig().withPortBindings(
                                new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)))
                        ));
        postgres.start();
    }

    private static void createCustomersTableIfNotExists() {

        try (Connection conn = DriverManager.getConnection(URL_PRODUCTION_DATABASE, "postgres", "postgres")) {
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
