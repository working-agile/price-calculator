package com.workingagile.acsd.backend;

import java.sql.*;
import java.util.ArrayList;

public class TrainingCourseService {

	public  int getRemainingSalesValue() {
		return trainingCourseManager.getRemainingSalesTarget();
	}

	public ArrayList<TrainingCourse> getScheduledTrainingCourses() {
		return trainingCourseManager.getScheduledTrainingCourses();
	}

	// ----------------------------------------------------

	private final TrainingCourseRepository repository;

	private final TrainingCourseManager trainingCourseManager;

	public TrainingCourseService() {
		trainingCourseManager = new TrainingCourseManager();
		repository = new TrainingCourseRepository();
	}

	public TrainingCourseService(TrainingCourseRepository repository) {
		trainingCourseManager = new TrainingCourseManager();
		this.repository = repository;
	}

	public void calculateCurrentPricesOfTrainingCourses(boolean advanceDay) {

		repository.initDatabaseConnection();

		ArrayList<TrainingCourse> trainingCourses =  repository.readTrainingCoursesFromDatabase();

		ArrayList<TrainingCourse> updatedTrainingCourses = trainingCourseManager.processTrainingCourses(trainingCourses, advanceDay);

		repository.updatePricesInDatabase(updatedTrainingCourses);

	}

}
