package com.workingagile.acsd;

import org.springframework.boot.SpringApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.workingagile.acsd.backend.persistence.ECommercePersistence.URL_PRODUCTION_DATABASE;

public class CreateTrainingCourses {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL_PRODUCTION_DATABASE, "postgres", "postgres")) {
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                            INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                               VALUES(1,'2025-01-09',9,30,7,'CSPO',4000);
                            INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                               VALUES(2,'2025-01-10',10,30,8,'CSO',3000);
                            INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price)
                                VALUES(3, '2025-01-20',20,30,27,'CSM',3000);
                        """
            );
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
