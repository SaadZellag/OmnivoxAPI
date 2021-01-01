package courses;

import java.util.Date;

/**
 * This class represents a course event on the calendar of the homepage.
 * 
 * It needs only 3 of the 4 parameters required by it's parent class since a
 * calendar event cannot be seen.
 * 
 * You also need to provide an extra parameter.
 * 
 * This class is Immutable and final.
 */
public final class CalendarEvent extends CourseElement {

	/**
	 * Represents the description or type of the Calendar event.
	 * 
	 * Ex: Document to read, Assignment to submit, Class event
	 */
	private final String description;

	/**
	 * This is the only constructor for a Calendar Event.
	 * 
	 * @param courseName  Name of the course associated to the event
	 * @param title       The name of the event on the calendar. (In bold)
	 * @param date        The time of the event
	 * @param description Represents the type of event Ex: Document to read,
	 *                    Assignment to submit
	 */
	public CalendarEvent(String courseName, String title, Date date, String description) {
		super(courseName, title, date, true);
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
