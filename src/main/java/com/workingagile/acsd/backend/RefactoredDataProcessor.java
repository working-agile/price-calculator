package com.workingagile.acsd.backend;

import com.workingagile.acsd.backend.domain.Item;
import com.workingagile.acsd.backend.domain.TrainingClassProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RefactoredDataProcessor {

    private TrainingClassProcessor processor;

    private static Connection database;

    protected void setTestDatabaseConnection(Connection testDatabaseConnection) {
        database = testDatabaseConnection;
    }

    protected Connection getDatabaseConnection() {
        return database;
    }

    private static RefactoredDataProcessor theSingleton;

    protected RefactoredDataProcessor() {
        processor = new TrainingClassProcessor();
    }

    public static RefactoredDataProcessor getInstance() {
        if (theSingleton == null) {
            // simulating a slow initialization process
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            theSingleton = new RefactoredDataProcessor();
        }
        return theSingleton;
    }

    public int getSalesValue() {
        return processor.getSalesValue();
    }

    public List<Item> getList() {
        return processor.getList();
    }

    public void calculateData(boolean moveToNextDay) {

        initDatabaseConnection();

        ArrayList<Item> trainingCourses = readTrainingCoursesFromDatabase();

        processor.processTrainingCourses(trainingCourses, moveToNextDay);

        updatePricesInDatabase(trainingCourses);

    }

    private ArrayList<Item> readTrainingCoursesFromDatabase() {
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

    private static void initDatabaseConnection() {
        if (database == null) {
            try {

                String url = "jdbc:postgresql://127.0.0.1:5432/production_database";
                Class.forName("org.postgresql.Driver");
                database = DriverManager.getConnection(url, "postgres", "postgres");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void updatePricesInDatabase(ArrayList<Item> newList) {
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
