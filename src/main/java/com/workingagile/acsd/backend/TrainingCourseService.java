package com.workingagile.acsd.backend;

import java.sql.*;
import java.util.ArrayList;

public class TrainingCourseService {

	public  int getRemainingSalesValue() {
		return trainingCourseManager.getRemainingSalesTarget();
	}

	public ArrayList<TrainingCourse> getScheduledTrainingCourses() {
		return trainingCourseManager.getScheduledTrainingCourses();
	}

	// ----------------------------------------------------

	private static Connection databaseConnection;

	private final TrainingCourseManager trainingCourseManager;

	public TrainingCourseService() {
		trainingCourseManager = new TrainingCourseManager();
	}

	public TrainingCourseService(Connection databaseConnection) {
		this();
		TrainingCourseService.databaseConnection = databaseConnection;
	}

	public void calculateCurrentPricesOfTrainingCourses(boolean advanceDay) {

		initDatabaseConnection();

		ArrayList<TrainingCourse> trainingCourses =  readTrainingCoursesFromDatabase();

		ArrayList<TrainingCourse> updatedTrainingCourses = trainingCourseManager.processTrainingCourses(trainingCourses, advanceDay);

		updatePricesInDatabase(updatedTrainingCourses);

	}


	private ArrayList<TrainingCourse> readTrainingCoursesFromDatabase() {

		ArrayList<TrainingCourse> trainingCourses = new ArrayList<>();

		try {
			String query = "select * from tr_crs";
			Statement st = databaseConnection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				long id = rs.getLong("id");
				String scheduledDate = rs.getString("tr_date");
				int daysBeforeTrainingCourse = rs.getInt("days");
				int totalNumberOfSeats = rs.getInt("ttl_seats");
				int remainingAvailableSeats = rs.getInt("avail");
				String type = rs.getString("type");
				int currentDiscountedPrice = rs.getInt("curr_price");
				int fullPrice = rs.getInt("full_price");

				TrainingCourse trainingCourse = new TrainingCourse(id, scheduledDate, daysBeforeTrainingCourse,
						totalNumberOfSeats, remainingAvailableSeats, type, currentDiscountedPrice, fullPrice);
				trainingCourses.add(trainingCourse);
			}
			st.close();

			return trainingCourses;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




	private void updatePricesInDatabase(ArrayList<TrainingCourse> trainingCourses) {
		try {
			for (int i = 0; i< trainingCourses.size(); i++) {

				String update = "update tr_crs set days=?, curr_price=? where id=? ";
				PreparedStatement pstmt = databaseConnection.prepareStatement(update);

				pstmt.setInt(1, trainingCourses.get(i).daysBeforeTrainingCourse);
				pstmt.setInt(2, trainingCourses.get(i).currentDiscountedPrice);
				pstmt.setLong(3, trainingCourses.get(i).id);

				int res = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void initDatabaseConnection() {
		try {
			if (databaseConnection == null) {
				// slow...
				String url = "jdbc:postgresql://127.0.0.1:5432/production_database";
				Class.forName("org.postgresql.Driver");
				databaseConnection = DriverManager.getConnection(url, "postgres", "postgres");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
