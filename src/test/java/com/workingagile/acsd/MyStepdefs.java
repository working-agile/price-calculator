package com.workingagile.acsd;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
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

public class MyStepdefs {

    @DataTableType
    public TrainingCourse definitionTrainingCourse(Map<String, String> dataTable) {

        String typeTrainingCourse = dataTable.get("training course");
        String scheduledDate = dataTable.get("scheduled date");
        int fullPrice = Integer.parseInt(dataTable.get("full price"));

        return new TrainingCourse(
                typeTrainingCourse,
                LocalDate.parse(scheduledDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                0,
                fullPrice,
                0);
    }

    TrainingCourse theTrainingCourse;

    @Given("the following training course:")
    public void the_following_training_course(TrainingCourse trainingCourse) {
        theTrainingCourse = trainingCourse;
    }

    @ParameterType(".*")
    public LocalDate course_date(String courseDate) {
        return LocalDate.parse(courseDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    Item[] processedItems;

    @When("a client asks for a quote on {course_date}")
    public void a_client_asks_for_a_quote_on(LocalDate courseDate) {

        int daysBetween = (int) DAYS.between(courseDate, theTrainingCourse.scheduledDate);

        Item item = new Item(daysBetween + 1, 10, 10, true,
                theTrainingCourse.type,
                theTrainingCourse.fullPrice);
        Item[] items = new Item[]{item};

        processedItems = DataProcessor.processData(items);

    }

    @Then("the discounted price should be {int}")
    public void the_discounted_price_should_be(int discountedPrice) {

        assertThat(processedItems[0].price, is(equalTo(discountedPrice)));
    }

}
