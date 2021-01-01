package students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import courses.CalendarEvent;
import courses.Course;
import courses.CourseAssignment;
import courses.CourseDocument;

/**
 * This class represents a Student from any school.
 * 
 * It contains all of the courses assigned to that specific student.
 * 
 * It also contains all of the documents and assignments to allow giving a full
 * summary of all the courses.
 * 
 * It holds all of the calendar events associated to the student.
 */
public class Student {

	/**
	 * Represents all of the courses a Student has.
	 * 
	 * Note: The key for this hashmap and the field courseName for the
	 * {@link Course} object should be the same.
	 */
	private final HashMap<String, Course> courses = new HashMap<String, Course>();

	/**
	 * Represents all of the documents a Student has.
	 * 
	 * Used to present them all at once without separating them.
	 */
	private final ArrayList<CourseDocument> allDocuments = new ArrayList<CourseDocument>();

	/**
	 * Represents all of the assignments a Student has.
	 * 
	 * Used to present them all at once without separating them.
	 */
	private final ArrayList<CourseAssignment> allAssignments = new ArrayList<CourseAssignment>();

	/**
	 * Holds all of the {@link CalendarEvent} objects the student has.
	 */
	private final ArrayList<CalendarEvent> calandar = new ArrayList<CalendarEvent>();

	/**
	 * Adds the following key and course to the Student's courses.
	 * 
	 * Note: it will not replace the course if one was already created.
	 * 
	 * @param courseName Key used in the hashmap.
	 * @param course     Value added in the hashmap.
	 */
	public void addCourse(String courseName, Course course) {
		if (!this.courses.containsKey(courseName)) {
			this.courses.put(courseName, course);
		}
	}

	/**
	 * Adds the {@link CourseDocument} object to the corresponding course key.
	 * 
	 * @param courseName Key used in the hashmap.
	 * @param document   Value added in the hashmap.
	 */
	public void assignDocument(String courseName, CourseDocument document) {

		// Simple checks
		if (courseName == null) {
			throw new NullPointerException("CourseName is null");
		}
		// Note: document will be checked if null when added.

		this.courses.get(courseName).addCourseDocument(document); // Adding doc to the course
		this.allDocuments.add(document); // Adding doc to all documents
	}

	/**
	 * Adds the {@link CourseAssignment} object to the corresponding course key.
	 * 
	 * @param courseName Key used in the hashmap.
	 * @param assignment Value added in the hashmap.
	 */
	public void assignAssignment(String courseName, CourseAssignment assignment) {

		// Simple checks
		if (courseName == null) {
			throw new NullPointerException("CourseName is null");
		}
		// Note: assignment will be checked if null when added.

		this.courses.get(courseName).addCourseAssignment(assignment); // Adding assignment to the course
		this.allAssignments.add(assignment); // Adding assignment to all assignments
	}

	/**
	 * This method adds the corresponding CalendarEvent object to the calendar.
	 * 
	 * Note: If the event received has already passed, it will not add it. It allows
	 * a maximum of 24 hours since the event can still be today but at midnight and
	 * might still be relevant.
	 * 
	 * @param event Calendar Event to be added
	 */
	public void assignCalendarEvent(CalendarEvent event) {

		// Simple check
		if (event == null) {
			throw new NullPointerException("CalandarEvent recieved is null");
		}

		// Checking if the object has not already passed
		if (event.getDate().getTime() >= System.currentTimeMillis() - 86400000L) { // Milliseconds in a day
			this.calandar.add(event);
		}
	}

	/**
	 * Gets all of the documents by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * 
	 * @return The course's documents sorted by date.
	 */
	public CourseDocument[] getDocuments(String courseName) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}

		return this.courses.get(courseName).getDocuments();
	}

	/**
	 * Gets all of the documents by date for all of the courses.
	 * 
	 * @return All of the student's documents sorted by date.
	 */
	public CourseDocument[] getAllDocument() {
		Collections.sort(this.allDocuments);
		return this.allDocuments.toArray(new CourseDocument[0]);
	}

	/**
	 * Gets the first x documents by date in all of the courses.
	 * 
	 * @param x The number of documents to get
	 * 
	 * @return The x latest documents in all of the courses sorted by date.
	 */
	public CourseDocument[] getNewestDocuments(int x) {
		Collections.sort(this.allDocuments);
		int size = this.allDocuments.size(); // To prevent clutter
		x = Math.min(x, size); // To prevent out of bounds
		return this.allDocuments.subList(size - x, size).toArray(new CourseDocument[0]);
	}

	/**
	 * Gets the first x documents by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * @param x          The number of documents to get.
	 * 
	 * @return The x latest documents of the course sorted by date.
	 */
	public CourseDocument[] getNewestDocuments(String courseName, int x) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}
		return this.courses.get(courseName).getNewestDocuments(x);
	}

	/**
	 * Gets the x oldest documents by date in all of the courses.
	 * 
	 * @param x The number of documents
	 * 
	 * @return The x oldest documents in all of the courses sorted by date.
	 */
	public CourseDocument[] getOldestDocuments(int x) {
		Collections.sort(this.allDocuments);
		x = Math.min(this.allDocuments.size(), x); // To prevent out of bounds
		return this.allDocuments.subList(0, x).toArray(new CourseDocument[0]);
	}

	/**
	 * Gets the x oldest documents by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * @param x          The number of documents to get.
	 * 
	 * @return The x oldest documents of the course sorted by date.
	 */
	public CourseDocument[] getOldestDocuments(String courseName, int x) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}

		return this.courses.get(courseName).getOldestDocuments(x);
	}

	/**
	 * Gets all of the assignments by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * 
	 * @return The course's assignments by date.
	 */
	public CourseAssignment[] getAssignments(String courseName) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}

		return this.courses.get(courseName).getAssignments();
	}

	/**
	 * Gets all of the assignments by date for all of the courses.
	 * 
	 * @return All of the student's assignment sorted by date.
	 */
	public CourseAssignment[] getAllAssignments() {
		Collections.sort(this.allAssignments);
		return this.allAssignments.toArray(new CourseAssignment[0]);
	}

	/**
	 * Gets the first x assignmnets by date in all of the courses.
	 * 
	 * @param x The number of assignments to get
	 * 
	 * @return The x latest assignments in all of the courses sorted by date.
	 */
	public CourseAssignment[] getNewestAssignments(int x) {
		Collections.sort(this.allAssignments);
		int size = this.allAssignments.size(); // To prevent clutter
		x = Math.min(x, size); // To prevent out of bounds
		return this.allAssignments.subList(size - x, size).toArray(new CourseAssignment[0]);
	}

	/**
	 * Gets the first x assignments by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * @param x          The number of documents to get.
	 * 
	 * @return The x latest assignments of the course sorted by date.
	 */
	public CourseAssignment[] getNewestAssignments(String courseName, int x) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}

		return this.courses.get(courseName).getNewestAssignments(x);
	}

	/**
	 * Gets the x oldest assignments by date in all of the courses.
	 * 
	 * @param x The number of assignments
	 * 
	 * @return The x oldest assignment in all of the courses sorted by date.
	 */
	public CourseAssignment[] getOldestAssignments(int x) {
		Collections.sort(this.allAssignments);
		x = Math.min(this.allAssignments.size(), x); // To prevent out of bounds
		return this.allAssignments.subList(0, x).toArray(new CourseAssignment[0]);
	}

	/**
	 * Gets the x oldest assignments by date in the course.
	 * 
	 * @param courseName The key used in the hashmap.
	 * @param x          The number of assignments to get.
	 * 
	 * @return The x oldest assignments of the course sorted by date.
	 */
	public CourseAssignment[] getOldestAssignments(String courseName, int x) {
		if (courseName == null) {
			throw new NullPointerException("Course Name is null");
		}

		return this.courses.get(courseName).getOldestAssignments(x);
	}

	/**
	 * Gets all of the calendar events by date.
	 * 
	 * @return The calendar events by date.
	 */
	public CalendarEvent[] getCalendarEvents() {
		Collections.sort(this.calandar);
		return this.calandar.toArray(new CalendarEvent[0]);
	}

	/**
	 * Gets the first x calendar events by date.
	 * 
	 * @param x The number of calendar events to get.
	 * 
	 * @return The x latest calendar events sorted by date.
	 */
	public CalendarEvent[] getNewestCalendarEvent(int x) {
		Collections.sort(this.calandar);

		int size = this.calandar.size(); // To prevent clutter
		x = Math.min(x, size); // To prevent OutOfBoundsException

		return this.calandar.subList(size - x, size).toArray(new CalendarEvent[0]); // Creating a shallow copy
	}

	/**
	 * Gets the x oldest calendar events by date.
	 * 
	 * @param x The number of calendar events to get.
	 * 
	 * @return The x oldest calendar events sorted by date.
	 */
	public CalendarEvent[] getOldestCalendarEvents(int x) {
		Collections.sort(this.calandar);

		x = Math.min(this.calandar.size(), x); // To prevent OutOfBoundsException

		return this.calandar.subList(0, x).toArray(new CalendarEvent[0]); // Creating a shallow copy
	}


	/**
	 * This methods returns all the keys associated with the student's course.
	 * 
	 * @return All of the keys associated with a student
	 */
	public String[] getCourseKeys() {
		return this.courses.keySet().toArray(new String[0]); // Converting set to Array
	}
}
