package com.workingagile.acsd.bdd;

import com.workingagile.acsd.TrainingCourse;
import com.workingagile.acsd.TrainingCourseFactory;
import com.workingagile.acsd.TrainingCourseService;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyStepDefs {

    @DataTableType
    public TrainingCourse definitionTrainingCourse(Map<String, String> dataTable) {

        String typeTrainingCourse = dataTable.get("training course");
        int fullPrice = Integer.parseInt(dataTable.get("full price"));
        String scheduledDateStr = dataTable.get("scheduled date");
        String currentDateStr = dataTable.get("current date");

        LocalDate scheduledDate = LocalDate.parse(scheduledDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.parse(currentDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int daysBeforeTrainingCourse = (int) DAYS.between(currentDate, scheduledDate);

        return TrainingCourseFactory.createTrainingCourse(
            typeTrainingCourse, scheduledDateStr, daysBeforeTrainingCourse,
                10, 10, true, fullPrice);
    }

    TrainingCourse[] trainingCourses = new TrainingCourse[1];
    TrainingCourseService service;

    @Given("the following training course has been scheduled:")
    public void the_following_training_course(TrainingCourse trainingCourse) {

        System.out.println("Given training course: " + trainingCourse);

        trainingCourses[0] = trainingCourse;
        service = new TrainingCourseService(trainingCourses);
    }

    @When("a client checks for the current price")
    public void aClientChecksForTheCurrentPrice() {

        service.updateCurrentPrices();
    }

    @Then("the discounted price should be {int}")
    public void the_discounted_price_should_be(int expectedDiscountedPrice) {

        assertThat(trainingCourses[0].getCurrentDiscountedPrice(), is(equalTo(expectedDiscountedPrice)));

    }


}
