package com.workingagile.acsd.backend;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DataProcessor {

	public static int salesValue;

	public static ArrayList<Item> list;

	// ----------------------------------------------------

	private static Connection database;

	private static DataProcessor dataProcessor;

	// ----------------------------------------------------

	private DataProcessor() {
	}

	static public DataProcessor getInstance() {

		if (dataProcessor == null) {
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
			dataProcessor = new DataProcessor();
		}
		return dataProcessor;
	}


	public void insertData(Item[] items) {
		try {
			for (int i = 0; i < items.length; i++) {
				String insertSql = "insert into tr_crs (id,tr_date,days,ttl_seats,avail,type,full_price) " +
						"values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = database.prepareStatement(insertSql);
				pstmt.setLong(1, items[i].id);
				pstmt.setDate(2, Date.valueOf(items[i].trDate));
				pstmt.setInt(3, items[i].days);
				pstmt.setInt(4, items[i].seats);
				pstmt.setInt(5, items[i].avail);
				pstmt.setString(6, items[i].type);
				pstmt.setInt(7, items[i].full);
				int res = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public void calculateData() {

		// read the prices from database
		try {
			String query = "select * from tr_crs";
			Statement st = database.createStatement();
			ResultSet rs = st.executeQuery(query);

			salesValue = 0;
			ArrayList<Item> newList = new ArrayList<>();

			while (rs.next()) {

				long id = rs.getLong("id");
				LocalDate trDate = rs.getDate("tr_date").toLocalDate();
				int days = rs.getInt("days");
				int ttlSeats = rs.getInt("ttl_seats");
				int avail = rs.getInt("avail");
				String type = rs.getString("type");
				int curr = rs.getInt("curr_price");
				int full = rs.getInt("full_price");

				Item item = new Item(id, trDate, days, ttlSeats, avail, type.trim(), curr, full);

				int daysDifference = (int) ChronoUnit.DAYS.between(LocalDate.now(), trDate);

				if (daysDifference >= 0) {

					newList.add(item);

					item.days = daysDifference;

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

					Email email = null;
					boolean minimumPriceViolation = false;
					if (item.type.equals("CSD") && item.curr < 900) {
						email = EmailBuilder.startingBlank()
								.from("system@working-agile.com")
								.to("admin@working-agile.com")
								.withSubject("Minimum price violation for CSD")
								.withPlainText("The minimum price for CSD has been violated: "
										+ item.curr
										+ ". Reset to " + 900)
										.buildEmail();
						minimumPriceViolation = true;
						item.curr = 900;
					} else if (item.type.equals("CSM") && item.curr < 1000) {
						email = EmailBuilder.startingBlank()
								.from("system@working-agile.com")
								.to("admin@working-agile.com")
								.withSubject("Minimum price violation for CSM")
								.withPlainText("The minimum price for CSM has been violated: "
										+ item.curr
										+ ". Reset to " + 1000)
								.buildEmail();
						minimumPriceViolation = true;
						item.curr = 1000;
					} else if (item.type.equals("CSPO") && item.curr < 1200) {
						email = EmailBuilder.startingBlank()
								.from("system@working-agile.com")
								.to("admin@working-agile.com")
								.withSubject("Minimum price violation for CSPO")
								.withPlainText("The minimum price for CSPO has been violated: "
										+ item.curr
										+ ". Reset to " + 1200)
								.buildEmail();
						minimumPriceViolation = true;
						item.curr = 1200;
					}

					// send warning email if minimum price guarantee violated
					if (minimumPriceViolation ) {
						try (Mailer mailer = MailerBuilder
								.withSMTPServer("localhost", 3025)
								.buildMailer()) {
							mailer.sendMail(email);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
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

		} catch(Exception ignored) {}
	}

}
