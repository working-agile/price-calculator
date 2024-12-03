package com.workingagile.acsd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

	public List<Item> getList() {
		return processor.getItems();
	}

	public int getSalesValue() {
		return processor.getSalesValue();
	}

	// ----------------------------------------------------

	private static Connection database;

	private static DataProcessor theSingleton;

	private static TrainingClassProcessor processor;

	private DataProcessor() {}

	public static DataProcessor getInstance() {
		if (theSingleton == null) {
			// simulating a slow initialization process
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {}
			initDatabaseConnection();
			theSingleton = new DataProcessor();
			processor = new TrainingClassProcessor();
		}
		return theSingleton;
	}

	public void calculateData(boolean next) {

			List<Item> currentTrainingCourses = loadTrainingCoursesFromDatabase();

			ArrayList<Item> newList = processor.processTrainingCourses(next, currentTrainingCourses);

			updateTrainingCoursesInDatabase(newList);

	}

	private List<Item> loadTrainingCoursesFromDatabase() {
		List<Item> currentTrainingCourses = null;
		try {
			String query = "select * from tr_crs";
			Statement st = database.createStatement();
			ResultSet rs = st.executeQuery(query);
			currentTrainingCourses = new ArrayList<>();
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

				currentTrainingCourses.add(item);
			}
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return currentTrainingCourses;
	}

	private void updateTrainingCoursesInDatabase(ArrayList<Item> newList) {
		for (int i = 0; i< newList.size(); i++) {

			try {
				String update = "update tr_crs set days=?, curr_price=? where id=? ";
				PreparedStatement pstmt = database.prepareStatement(update);

				pstmt.setInt(1, newList.get(i).days);
				pstmt.setInt(2, newList.get(i).curr);
				pstmt.setLong(3, newList.get(i).id);

				int res = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void initDatabaseConnection() {
		if (database == null) {
			try {
				String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
				Class.forName("org.postgresql.Driver");
				database = DriverManager.getConnection(url, "postgres", "postgres");
			} catch (Exception e) {
			}
		}
	}

}
