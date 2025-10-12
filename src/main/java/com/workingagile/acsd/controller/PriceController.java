package com.workingagile.acsd.controller;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @GetMapping("/training-courses")
    public List<Item> getTrainingCourses() {
        return DataProcessor.list;
    }

    @GetMapping("/report")
    public int getReport() {
        return DataProcessor.salesValue;
    }


    @PutMapping("/training-courses")
    public void calculateTrainingCoursePrices() {

        // TODO

    }





}
