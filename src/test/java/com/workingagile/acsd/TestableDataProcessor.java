package com.workingagile.acsd;

public class TestableDataProcessor extends DataProcessor {

    // extension point

    public void calculateDataInstance(boolean next) {

        // this is the extension point
        super.calculateDataInstance(next);
    }

}
