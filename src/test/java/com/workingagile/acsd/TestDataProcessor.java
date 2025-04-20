package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.RefactoredDataProcessor;

import java.sql.Connection;

public class TestDataProcessor extends RefactoredDataProcessor {

    protected void setTestDatabaseConnection(Connection testDatabaseConnection) {
        super.setTestDatabaseConnection(testDatabaseConnection);
    }

}
