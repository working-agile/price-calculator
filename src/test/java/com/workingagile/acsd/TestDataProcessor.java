package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;

import java.sql.Connection;

public class TestDataProcessor extends DataProcessor {

    protected void setTestDatabaseConnection(Connection testDatabaseConnection) {
        super.setTestDatabaseConnection(testDatabaseConnection);
    }

}
