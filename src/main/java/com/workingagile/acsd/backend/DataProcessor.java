package com.workingagile.acsd.backend;

import java.sql.*;
import java.util.ArrayList;

public class DataProcessor {

	public int salesValue;

	public ArrayList<Item> list;

	// ----------------------------------------------------

	private static Connection database;

	private static DataProcessor theSingleton;

	protected DataProcessor() {}

	protected void setTestDatabaseConnection(Connection testDatabaseConnection) {
		database = testDatabaseConnection;
	}

	protected Connection getDatabaseConnection() {
		return database;
	}

	public static DataProcessor getInstance() {
		if (theSingleton == null) {
			// simulating a slow initialization process
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {}
			theSingleton = new RefactoredDataProcessor();
		}
		return theSingleton;
	}

	public void calculateData(boolean moveToNextDay) {

		if (database == null) {
			try {

				String url = "jdbc:postgresql://127.0.0.1:5432/production_database";
				Class.forName("org.postgresql.Driver");
				database = DriverManager.getConnection(url, "postgres", "postgres");

			} catch (Exception e) {
			}
		}
		// read the prices from database
		try {
			String query = "select * from tr_crs";
			Statement st = database.createStatement();
			ResultSet rs = st.executeQuery(query);

			salesValue = 0;
			ArrayList<Item> newList = new ArrayList<>();

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

				if (!(item.days < 0 || (moveToNextDay && item.days == 0))) {

					newList.add(item);

					if (moveToNextDay) {
						item.days--;
					}

					if (item.days <= 10) {

						if (item.days <= 1 || (item.avail < 3 && item.days <= 5)) {
							item.curr = item.full;
						} else {
							if (item.type.equals("CSD")) {
								item.curr = item.full - (item.days * 30);
							} else {
								item.curr = item.full - (item.days * 20);
							}
						}

					} else if (item.days > 10) {

						if (item.days <= 1 || (item.avail < 3 && item.days <= 5)) {
							item.curr = item.full;
						} else {
							if (item.type.equals("CSM")) {
								item.curr = item.full - 500;
							} else {
								item.curr = item.full - 400;
							}
						}
					}

					if (item.type.equals("CSD") && item.curr < 900) {
						item.curr = 900;
					} else if (item.type.equals("CSM") && item.curr < 1000) {
						item.curr = 1000;
					} else if (item.type.equals("CSPO") && item.curr < 1200) {
						item.curr = 1200;
					}

					salesValue += (item.avail * item.curr);

				}
			}
			st.close();

			// update prices in database
			for (int i=0; i<newList.size(); i++) {

				String update = "update tr_crs set days=?, curr_price=? where id=? ";
				PreparedStatement pstmt = database.prepareStatement(update);

				pstmt.setInt(1, newList.get(i).days);
				pstmt.setInt(2, newList.get(i).curr);
				pstmt.setLong(3, newList.get(i).id);

				int res = pstmt.executeUpdate();
			}
			list = newList;

		} catch(Exception exception) {}
	}

}
