package courses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This abstract class is used as a base class for the following objects:
 * {@link CourseAssignment} {@link CourseDocument} {@link CalendarEvent}
 * 
 * It is used as a parent class for storing the title of the element, the Date
 * of the element and it's course name.
 * 
 * This class is Immutable. It also implements the Comparable interface to allow
 * sorting by date in the {@link Course} object's lists.
 */
public abstract class CourseElement implements Comparable<CourseElement> {

	/**
	 * This formatter is used to convert the {@link Date} field into a readable
	 * String.
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");

	/**
	 * The course name is the same as the one used as a key to get the
	 * {@link Course} object that holds this element.
	 * 
	 * This element is protected.
	 */
	protected final String courseName;

	/**
	 * The title represents the first element on the left most side of documents or
	 * assignments. It's used to display the name of the document, assignment or
	 * event.
	 * 
	 * This element is protected.
	 */
	protected final String title;

	/**
	 * The distributed time represents the date the document was created, the date
	 * the assignment is due for or the date of the calendar event.
	 */
	protected final Date distributedTime;

	/**
	 * This String is used to store the date since the field is immutable.
	 */
	protected final String distributedTimeStr;

	/**
	 * Represents if the element has been seen (has the star next to it).
	 * 
	 * Not applicable for the Calendar Event.
	 */
	protected final boolean seen;

	/**
	 * Only constructor for the CourseElement.
	 * 
	 * @param courseName      Name of the course associated to the element
	 * @param title           The title of the element. Ex: The name of the
	 *                        assignment.
	 * @param distributedTime The time the element has been created or is due.
	 * @param isSeen          If the document has been seen
	 */
	public CourseElement(String courseName, String title, Date distributedTime, boolean isSeen) {
		this.courseName = courseName;
		this.title = title;
		this.distributedTime = new Date(distributedTime.getTime()); // Making date Immutable
		this.distributedTimeStr = formatter.format(distributedTime);
		this.seen = isSeen;
	}

//	Getters
	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return new Date(this.distributedTime.getTime()); // Preventing the user modifying the object
	}

	public String getDateStr() {
		return this.distributedTimeStr;
	}

	public boolean isSeen() {
		return this.seen;
	}

	public String getCourseName() {
		return this.courseName;
	}

//	Overrides
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj instanceof CourseElement) {
			CourseElement casted = (CourseElement) obj;

			// Checks if both of the fields are equal
			return casted.title.equals(this.title) && casted.distributedTime.equals(this.distributedTime);

		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.title.hashCode() * 31 * 31 + this.courseName.hashCode() * 31 + this.distributedTime.hashCode();
	}

	@Override
	public int compareTo(CourseElement other) {

		int compared = this.distributedTime.compareTo(other.distributedTime);

		// If the two objects have the same released date or due date
		if (compared == 0) {
			// Comparing by element name
			return this.title.compareTo(other.title);
		} else {
			return compared;
		}
	}
}
