package com.workingagile.acsd.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

	private static Connection database;

	private static RefactoredDataProcessor theSingleton;

	protected DataProcessor() {}

	protected void setTestDatabaseConnection(Connection testDatabaseConnection) {
		database = testDatabaseConnection;
	}

	protected Connection getDatabaseConnection() {
		return database;
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

	public void calculateData(boolean moveToNextDay) {
	}

	public List<Item> getList() {
		return getInstance().getList();
	}

	public int getSalesValue() {
		return getInstance().getSalesValue();
	}

}
