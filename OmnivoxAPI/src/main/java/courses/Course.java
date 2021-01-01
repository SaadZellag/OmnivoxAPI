package courses;

import java.util.ArrayList;
import java.util.Collections;

import students.Student;

/**
 * This class represents a Course that the student has taken. It contains the
 * documents and assignments associated to itself.
 * 
 * This class is final and Immutable.
 */
public final class Course {

	/**
	 * Represents the name of the course.
	 * 
	 * Note: It will be used as a key for the courses of the {@link Student} having
	 * that course.
	 */
	private final String courseName;

	/**
	 * Used to hold all of the document the student has in this course. It is a list
	 * since the number of documents are not defined and can vary.
	 */
	private final ArrayList<CourseDocument> courseDocuments = new ArrayList<CourseDocument>();

	/**
	 * Used to hold all of the assignments the student has in this course. It is a
	 * list since the number of assignment are not defined and can vary.
	 */
	private final ArrayList<CourseAssignment> courseAssignments = new ArrayList<CourseAssignment>();

	/**
	 * The only constructor for the Course Object.
	 * 
	 * @param courseName The name of the course.
	 */
	public Course(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Used to add a document to the course.
	 * 
	 * Will throw a NullPointerException to prevent having null elements in the
	 * list.
	 * 
	 * @param document The document to be passed
	 */
	public void addCourseDocument(CourseDocument document) {
		if (document == null) {
			throw new NullPointerException("Document is null");
		}

		this.courseDocuments.add(document);
	}

	/**
	 * Used to add an assignment to the course.
	 * 
	 * Will throw a NullPointerException to prevent having null elements in the
	 * list.
	 * 
	 * @param assignment The assignment to be passed
	 */
	public void addCourseAssignment(CourseAssignment assignment) {
		if (assignment == null) {
			throw new NullPointerException("Assignment is null");
		}

		this.courseAssignments.add(assignment);
	}

	/**
	 * Sorts the list and returns all of the course documents by date.
	 * 
	 * Will return an empty array if the list is empty.
	 * 
	 * @return Array of sorted documents by date
	 */
	public CourseDocument[] getDocuments() {
		Collections.sort(this.courseDocuments);
		return this.courseDocuments.toArray(new CourseDocument[0]); // Creating a shallow copy
	}

	/**
	 * Sorts the list of documents by time and gets the first x documents by newest
	 * date.
	 * 
	 * Will return an empty array if x is 0 or the list is empty.
	 * 
	 * @param x The number of documents to get
	 * 
	 * @return Array of the latest documents sorted from oldest to newest
	 */
	public CourseDocument[] getNewestDocuments(int x) {
		Collections.sort(this.courseDocuments);
		return getBottomDocuments(x); // Since newest documents have are the last in terms of time
	}

	/**
	 * Sorts the list of documents by time and gets the first x documents by oldest
	 * date.
	 * 
	 * Will return an empty array if x is 0 or the list is empty.
	 * 
	 * @param x The number of documents to get
	 * 
	 * @return Array starting with the oldest document with x documents
	 */
	public CourseDocument[] getOldestDocuments(int x) {
		Collections.sort(this.courseDocuments);
		return getTopDocuments(x); // Since oldest documents are first in terms of time
	}

	/**
	 * Sorts the list and returns all of the course assignments by date.
	 * 
	 * @return An array of the corresponding assignments. Note: Will return an empty
	 *         array if the list is empty.
	 */
	public CourseAssignment[] getAssignments() {
		Collections.sort(this.courseAssignments);
		return this.courseAssignments.toArray(new CourseAssignment[0]); // Creating a shallow copy
	}

	/**
	 * Sorts the list of assignments by time and gets the first x assignments by
	 * newest date.
	 * 
	 * @param x Number of assignments to get.
	 * 
	 * @return An array of the corresponding assignments. It will be ordered from
	 *         oldest to newest. Note: Will return an empty array if x is 0 or the
	 *         list is empty.
	 */
	public CourseAssignment[] getNewestAssignments(int x) {
		Collections.sort(this.courseAssignments);
		return getBottomAssignments(x); // Since newest assignments have are the last in terms of time
	}

	/**
	 * Sorts the list of assignments by time and gets the first x assignments by
	 * oldest date.
	 * 
	 * @param x Number of assignments to get.
	 * 
	 * @return An array of the corresponding assignments. Note: Will return an empty
	 *         array if x is 0 or the list is empty.
	 */
	public CourseAssignment[] getOldestAssignments(int x) {
		Collections.sort(this.courseAssignments);
		return getTopAssignments(x); // Since oldest assignments are first in terms of time
	}

	/**
	 * Used to get the first x documents of the list.
	 * 
	 * This method is called after the list is sorted so it can go in order without
	 * any problems.
	 * 
	 * @param x The number of documents to get
	 * 
	 * @return The first x documents of the list
	 */
	private CourseDocument[] getTopDocuments(int x) {
		// Checking if x is above the list's size to prevent an out of bounds Exception
		x = Math.min(this.courseDocuments.size(), x);

		// Creating a shallow copy of the array list as an array
		return this.courseDocuments.subList(0, x).toArray(new CourseDocument[0]);
	}

	/**
	 * Used to get the last x documents of the list.
	 * 
	 * This method is called after the list is sorted so it can go in order without
	 * any problems.
	 * 
	 * @param x The number of documents to get
	 * 
	 * @return The last x documents of the list
	 */
	private CourseDocument[] getBottomDocuments(int x) {
		int size = this.courseDocuments.size(); // To prevent clutter
		
		// Checking if x is above the list's size to prevent an out of bounds Exception
		x = Math.min(size, x);

		// Creating a shallow copy of the array list as an array
		return this.courseDocuments.subList(size - x, size).toArray(new CourseDocument[0]);
	}

	/**
	 * Used to get the first x assignments of the list.
	 * 
	 * This method is called after the list is sorted so it can go in order without
	 * any problems.
	 * 
	 * @param x The number of assignments to get
	 * 
	 * @return The first x assignments  of the list
	 */
	private CourseAssignment[] getTopAssignments(int x) {
		// Checking if x is above the list's size to prevent an out of bounds Exception
		x = Math.min(this.courseAssignments.size(), x);

		// Creating a shallow copy of the array list as an array
		return this.courseAssignments.subList(0, x).toArray(new CourseAssignment[0]);
	}

	/**
	 * Used to get the last x assignments of the list.
	 * 
	 * This method is called after the list is sorted so it can go in order without
	 * any problems.
	 * 
	 * @param x The number of assignments to get
	 * 
	 * @return The last x documents of the list
	 */
	private CourseAssignment[] getBottomAssignments(int x) {
		int size = this.courseAssignments.size(); // To prevent clutter
		
		// Checking if x is above the list's size to prevent an out of bounds Exception
		x = Math.min(size, x);

		// Creating a shallow copy of the array list as an array
		return this.courseAssignments.subList(size - x, size).toArray(new CourseAssignment[0]);
	}

	public String getCourseName() {
		return this.courseName;
	}
}
