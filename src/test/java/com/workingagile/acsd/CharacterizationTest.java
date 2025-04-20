package com.workingagile.acsd;

import com.workingagile.acsd.backend.Item;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterizationTest extends AbstractTestContainerTest {


    @Test
    public void prices_january_1_2025() throws Exception {

        // Arrange
        TestDataProcessor processor = new TestDataProcessor();
        // get test database connection
        Connection testDatabaseConnection = getTestdatabaseConnection();
        processor.setTestDatabaseConnection(testDatabaseConnection);

        // insert data
        PreparedStatement pstmt = testDatabaseConnection.prepareStatement(
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

        // Act - call the function
        processor.calculateData(false);

        // Assert
        List<Item> actualResultCourse = processor.getList();
        int actualSalesValue = processor.getSalesValue();
        // compare with the expected result
        assertEquals(3820, actualResultCourse.get(0).curr, "current price of course 1 is wrong");
        assertEquals(2800, actualResultCourse.get(1).curr, "current price of course 2 is wrong");
        assertEquals(2600, actualResultCourse.get(2).curr, "current price of course 3 is wrong");
        assertEquals(119340, actualSalesValue, "sales target value wrong");

        /*
        Type: CSPO
        When: 9 January 2025
        Remaining days before training course: 9
        Full Price: 4000
        Current price: 3820
        Number of seats: 30
        Remaining available seats: 7

        Type: CSO
        When: 10 January 2025
        Remaining days before training course: 10
        Full Price: 3000
        Current price: 2800
        Number of seats: 30
        Remaining available seats: 8

        Type: CSM
        When: 20 January 2025
        Remaining days before training course: 20
        Full Price: 3000
        Current price: 2600
        Number of seats: 30
        Remaining available seats: 27

        Remaining total sales target: 119340
        */

    }


    @Test
    public void prices_january_2_2025() throws Exception {

        // Arrange
        TestDataProcessor processor = new TestDataProcessor();
        // get test database connection
        Connection testDatabaseConnection = getTestdatabaseConnection();
        processor.setTestDatabaseConnection(testDatabaseConnection);

        // insert data
        PreparedStatement pstmt = testDatabaseConnection.prepareStatement(
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

        // Act - call the function
        processor.calculateData(true);

        // Assert
        List<Item> actualResultCourse = processor.getList();
        int actualSalesValue = processor.getSalesValue();
        // compare with the expected result
        assertEquals(3840, actualResultCourse.get(0).curr, "current price of course 1 is wrong");
        assertEquals(2820, actualResultCourse.get(1).curr, "current price of course 2 is wrong");
        assertEquals(2600, actualResultCourse.get(2).curr, "current price of course 3 is wrong");
        assertEquals(119640, actualSalesValue, "sales target value wrong");

    }






}
