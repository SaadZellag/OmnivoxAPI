package courses;

import java.util.Date;

/**
 * This class represents a course assignment on the Lea page.
 * 
 * It requires the same values as its parent class {@link CourseElement} but
 * needs an extra parameter.
 * 
 * This class is Immutable and final.
 */
public final class CourseAssignment extends CourseElement {

	/**
	 * Represents if the document has been turned over or not.
	 */
	private final boolean completed;

	/**
	 * The only constructor for a Course Assignment.
	 * 
	 * @param courseName      Name of the course associated to the assignment
	 * @param title           Name of the assignment the teacher gave it
	 * @param distributedTime The time the assignment must be submitted for.
	 * @param isSeen          If the assignment is seen or not (has the star or not)
	 * @param isCompleted     If the assignment has been handed in.
	 */
	public CourseAssignment(String courseName, String title, Date distributedTime, boolean isSeen,
			boolean isCompleted) {
		super(courseName, title, distributedTime, isSeen);

		this.completed = isCompleted;
	}

	// Getters
	public boolean isCompleted() {
		return this.completed;
	}

}
