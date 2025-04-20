package com.workingagile.acsd.backend;

import com.workingagile.acsd.backend.domain.Item;
import com.workingagile.acsd.backend.domain.TrainingClassProcessor;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RefactoredDataProcessor extends DataProcessor {

    TrainingClassProcessor processor;

    public RefactoredDataProcessor() {
        processor = new TrainingClassProcessor();
    }

    public int getSalesValue() {
        return processor.getSalesValue();
    }

    public List<Item> getList() {
        return processor.getList();
    }


    public void calculateData(boolean moveToNextDay) {

        System.out.println("Calling RefactoredDataProcessor.calculate()");

        Connection database = getDatabaseConnection();

        database = initDatabaseConnection(database);

        ArrayList<Item> trainingCourses = readTrainingCoursesFromDatabase(database);

        processor.processTrainingCourses(trainingCourses, moveToNextDay);

        updatePricesInDatabase(trainingCourses, database);

    }



    private ArrayList<Item> readTrainingCoursesFromDatabase(Connection database) {
        ArrayList<Item> newList = new ArrayList<>();
        Statement st = null;
        try  {
            String query = "select * from tr_crs";
            st = database.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                long id = rs.getLong("id");
                String trDate = rs.getString("tr_date");
                int days = rs.getInt("days");
                int ttlSeats = rs.getInt("ttl_seats");
                int avail = rs.getInt("avail");
                String type = rs.getString("type");
                int curr = rs.getInt("curr_price");
                int full = rs.getInt("full_price");

                Item item = new Item(id, trDate, days, ttlSeats, avail, type, curr, full);
                newList.add(item);
            }
            st.close();

            return newList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static @Nullable Connection initDatabaseConnection(Connection database) {
        if (database == null) {
            try {

                String url = "jdbc:postgresql://127.0.0.1:5432/production_database";
                Class.forName("org.postgresql.Driver");
                database = DriverManager.getConnection(url, "postgres", "postgres");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return database;
    }

    private static void updatePricesInDatabase(ArrayList<Item> newList, Connection database) {
        try {
            for (int i = 0; i< newList.size(); i++) {

                String update = "update tr_crs set days=?, curr_price=? where id=? ";
                PreparedStatement pstmt = database.prepareStatement(update);

                pstmt.setInt(1, newList.get(i).days);
                pstmt.setInt(2, newList.get(i).curr);
                pstmt.setLong(3, newList.get(i).id);

                int res = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
