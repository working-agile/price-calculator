package com.workingagile.acsd.approval;
import com.workingagile.acsd.clientscripts.GetTrainingCoursesRestCommand;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ApprovalTest {

    @Test
    public void test_current_prices_of_training_courses() {

        // Arrange
        // InsertTrainingCoursesRestCommand.execute();
        // UpdatePricesRestCommand

        // Act
        String output = GetTrainingCoursesRestCommand.execute();

        // Assert
        Approvals.verify(output);

    }

}
