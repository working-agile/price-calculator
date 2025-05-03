package com.workingagile.acsd.backend;

import java.sql.*;
import java.util.ArrayList;

public class DataProcessor {

	public  int getSalesValue() {
		return itemProcessor.getRemainingSalesTarget();
	}

	public ArrayList<TrainingCourse> getList() {
		return itemProcessor.getList();
	}

	// ----------------------------------------------------

	private static Connection database;

	private ItemProcessor itemProcessor;

	public DataProcessor() {
		itemProcessor = new ItemProcessor();
	}

	public DataProcessor(Connection databaseConnection) {
		this();
		database = databaseConnection;
	}

	public void calculateData(boolean advanceDay) {

		initDatabaseConnection();

		ArrayList<TrainingCourse> trainingCourses =  readItemsFromDatabase();

		ArrayList<TrainingCourse> processedTrainingCourses = itemProcessor.processItems(trainingCourses, advanceDay);

		updatePricesInDatabase(processedTrainingCourses);

	}


	private ArrayList<TrainingCourse> readItemsFromDatabase() {

		ArrayList<TrainingCourse> trainingCourses = new ArrayList<>();

		// read the prices from database
		try {
			String query = "select * from tr_crs";
			Statement st = database.createStatement();
			ResultSet rs = st.executeQuery(query);

			ArrayList<TrainingCourse> newList = new ArrayList<>();

			while (rs.next()) {

				long id = rs.getLong("id");
				String trDate = rs.getString("tr_date");
				int days = rs.getInt("days");
				int ttlSeats = rs.getInt("ttl_seats");
				int avail = rs.getInt("avail");
				String type = rs.getString("type");
				int curr = rs.getInt("curr_price");
				int full = rs.getInt("full_price");

				TrainingCourse trainingCourse = new TrainingCourse(id, trDate, days, ttlSeats, avail, type, curr, full);
				trainingCourses.add(trainingCourse);
			}
			st.close();

			return trainingCourses;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




	private void updatePricesInDatabase(ArrayList<TrainingCourse> newList) {
		try {
			// update prices in database
			for (int i = 0; i< newList.size(); i++) {

				String update = "update tr_crs set days=?, curr_price=? where id=? ";
				PreparedStatement pstmt = database.prepareStatement(update);

				pstmt.setInt(1, newList.get(i).daysBeforeTrainingCourse);
				pstmt.setInt(2, newList.get(i).currentDiscountedPrice);
				pstmt.setLong(3, newList.get(i).id);

				int res = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void initDatabaseConnection() {
		try {
			if (database == null) {
				// slow...
				String url = "jdbc:postgresql://127.0.0.1:5432/production_database";
				Class.forName("org.postgresql.Driver");
				database = DriverManager.getConnection(url, "postgres", "postgres");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
