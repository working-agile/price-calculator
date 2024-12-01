package com.workingagile.acsd;

import java.sql.*;
import java.util.ArrayList;

public class DataProcessor {

	public static int salesValue;

	public static ArrayList<Item> list;

	private static Connection database;

	private DataProcessor() throws Exception {
		System.out.println("Initializing DataProcessor. Very slow...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {}
	}

	public static void calculateData(boolean next) {

		if (database == null) {
			try {
				String url = "jdbc:mysql://production-database:3306/prices";
				Class.forName("com.mysql.cj.jdbc.Driver");
				database = DriverManager.getConnection(url, "prices-user", "prices-secret-password");
			} catch (Exception e) {
			}
		}

		// read the prices
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
				int curr = rs.getInt("curr");
				int full = rs.getInt("full");

				Item item = new Item(id, trDate, days, ttlSeats, avail, type, curr, full);

				if (!(item.days < 0 || (next && item.days == 0))) {

					newList.add(item);

					if (next) {
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

			// update database
			for (int i=0; i<newList.size(); i++) {

				String update = "update tr_crs set days=?, curr=? where id=? ";
				PreparedStatement pstmt = database.prepareStatement(update);

				pstmt.setInt(1, newList.get(i).days);
				pstmt.setInt(2, newList.get(i).curr);
				pstmt.setLong(3, newList.get(i).id);

				int res = pstmt.executeUpdate();
				System.out.println("Result: " + res);
			}
			list = newList;

		} catch(Exception exception) {}
	}


}
