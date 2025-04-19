package com.workingagile.acsd;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ECommerceBackend {

    private static final String URL_PRODUCTION_DATABASE = "jdbc:postgresql://127.0.0.1:5432/production_database";

    private static PostgreSQLContainer<?> postgres;

    public static void bootingECommerceSystem() {
        startDatabase();
        createCustomersTableIfNotExists();
        createTrainingCourses();
    }

    private static void startDatabase() {
        postgres = new PostgreSQLContainer<>(
                "postgres:16-alpine"
        );
        postgres.withPassword("postgres")
                .withUsername("postgres")
                .withDatabaseName("production_database")
                .withCreateContainerCmdModifier(
                        cmd -> cmd.withHostConfig(new HostConfig().withPortBindings(
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

    private static void createTrainingCourses() {

        try (Connection conn = DriverManager.getConnection(URL_PRODUCTION_DATABASE, "postgres", "postgres")) {
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

}
