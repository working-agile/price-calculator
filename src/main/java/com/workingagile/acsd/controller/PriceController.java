package com.workingagile.acsd.controller;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceController {

    @GetMapping("/training-courses")
    public List<Item> getTrainingCourses() { return DataProcessor.list; }

    @GetMapping("/report")
    public int getReport() {
        return DataProcessor.salesValue;
    }

    @PutMapping("/update-training-course-prices")
    public void calculateTrainingCoursePrices() {

        DataProcessor.getInstance().calculateData();
    }

    @PutMapping("/insert-training-courses")
    public void insertTrainingCoursePrices(@RequestBody Item[] newItems) {

        DataProcessor.getInstance().insertData(newItems);
    }


}
