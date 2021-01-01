package students;

/**
 * This class is used to format the {@link Student} object's values to print a
 * nice table showing every element of the student.
 */
public class StudentPrinterConfiguration {

	/**
	 * String used to format the format used to print the element's values.
	 * 
	 * When formatted with the default document values, will end up like this: |
	 * %-3.3s | %-110.110s | %-13.13s | %-30.30s |
	 */
	private final String formatFormatter = "| %%-%1$d.%1$ds | %%-%2$d.%2$ds | %%-%3$d.%3$ds | %%-%4$d.%4$ds |\n";

	/*
	 * Document fields with default values ordered from left to right. Represents
	 * the default number of - between the columns
	 */
	private int documentSeenLength = 3;
	private int documentTitleLength = 110;
	private int documentReleasedLength = 13;
	private int documentFileNameLength = 30;

	/**
	 * Represents the format used to align the Student's values. When formatted with
	 * the default values. It should look like this:
	 * 
	 * | %-3.3s | %-110.110s | %-13.13s | %-30.30s |
	 */
	private String documentFormat;

	/**
	 * Represents the top and bottom line seperating the tables. When formatted with
	 * the default values. It should look like this. (Note: the number between the -
	 * represent how many are there.)
	 * 
	 * +--3--+--110--+--13--+--30--+
	 */
	private String documentLine;

	/*
	 * Assignment fields with default values ordered from left to right. Represents
	 * the default number of - between the columns
	 */
	private int assignmentCompletedLength = 9;
	private int assignmentSeenLength = 3;
	private int assignmentTitleLength = 30;
	private int assignmentReleasedLength = 13;

	/**
	 * Represents the format used to align the Student's values. When formatted with
	 * the default values. It should look like this:
	 * 
	 * | %-9.9s | %-3.3s | %-30.30s | %-13.13s |
	 */
	private String assignmentFormat;

	/**
	 * Represents the top and bottom line seperating the tables. When formatted with
	 * the default values. It should look like this. (Note: the number between the -
	 * represent how many are there.)
	 * 
	 * +--9--+--3--+--30--+--13--+
	 */
	private String assignmentLine;

	/*
	 * Calendar fields with default values ordered from left to right. Represents
	 * the default number of - between the columns
	 */
	private int calendarEventTitleLength = 40;
	private int calendarEventDescriptionLength = 50;
	private int calendarEventDateLength = 13;
	private int calendarEventCourseLength = 50;

	/**
	 * Represents the format used to align the Student's values. When formatted with
	 * the default values. It should look like this:
	 * 
	 * | %-40.40s | %-50.50s | %-13.13s | %-50.50s |
	 */
	private String calendarEventFormat;

	/**
	 * Represents the top and bottom line seperating the tables. When formatted with
	 * the default values. It should look like this. (Note: the number between the -
	 * represent how many are there.)
	 * 
	 * +--40--+--50--+--13--+--50--+
	 */
	private String calendarEventLine;

	/**
	 * Default constructor for the object.
	 */
	public StudentPrinterConfiguration() {
		formatAssignmentString();
		formatDocumentString();
		formatCalendarEventString();
	}

	/**
	 * Sets the format string used in the format and the line associated with the
	 * document.
	 */
	public void setDocumentFormat(int seenLength, int titleLength, int releasedLength, int fileNameLength) {
		this.documentSeenLength = seenLength;
		this.documentTitleLength = titleLength;
		this.documentReleasedLength = releasedLength;
		this.documentFileNameLength = fileNameLength;

		this.formatDocumentString();
	}

	/**
	 * Sets the format string used in the format and the line associated with the
	 * assignment.
	 */
	public void setAssignmentFormat(int completedLength, int seenLength, int titleLength, int releasedLength) {
		this.assignmentCompletedLength = completedLength;
		this.assignmentSeenLength = seenLength;
		this.assignmentTitleLength = titleLength;
		this.assignmentReleasedLength = releasedLength;

		this.formatAssignmentString();
	}

	/**
	 * Sets the format string used in the format and the line associated with the
	 * calendar.
	 */
	public void setCalendarEventFormat(int calendarEventTitleLength, int calendarEventDescriptionLength,
			int calendarEventDateLength, int calendarEventCourseLength) {
		this.calendarEventTitleLength = calendarEventTitleLength;
		this.calendarEventDescriptionLength = calendarEventDescriptionLength;
		this.calendarEventDateLength = calendarEventDateLength;
		this.calendarEventCourseLength = calendarEventCourseLength;

		this.formatCalendarEventString();
	}

	// Getters
	public String getDocumentFormat() {
		return this.documentFormat;
	}

	public String getAssignmentFormat() {
		return this.assignmentFormat;
	}

	public String getCalendarEventFormat() {
		return this.calendarEventFormat;
	}

	public String getDocumentLine() {
		return this.documentLine;
	}

	public String getAssignmentLine() {
		return this.assignmentLine;
	}

	public String getCalendarEventLine() {
		return this.calendarEventLine;
	}

	// Private methods
	/**
	 * Used to reduce the clutter when formatting a line.
	 */
	private String formatLine(int column1, int column2, int column3, int column4) {
		return "+" + "-".repeat(column1 + 2) + "+" + "-".repeat(column2 + 2) + "+" + "-".repeat(column3 + 2) + "+"
				+ "-".repeat(column4 + 2) + "+";
	}

	private void formatDocumentString() {
		this.documentFormat = String.format(formatFormatter, documentSeenLength, documentTitleLength,
				documentReleasedLength, documentFileNameLength);

		this.documentLine = formatLine(documentSeenLength, documentTitleLength, documentReleasedLength,
				documentFileNameLength);
	}

	private void formatAssignmentString() {
		this.assignmentFormat = String.format(formatFormatter, assignmentCompletedLength, assignmentSeenLength,
				assignmentTitleLength, assignmentReleasedLength);

		this.assignmentLine = formatLine(assignmentCompletedLength, assignmentSeenLength, assignmentTitleLength,
				assignmentReleasedLength);
	}

	private void formatCalendarEventString() {
		this.calendarEventFormat = String.format(formatFormatter, calendarEventTitleLength,
				calendarEventDescriptionLength, calendarEventDateLength, calendarEventCourseLength);

		this.calendarEventLine = formatLine(calendarEventTitleLength, calendarEventDescriptionLength,
				calendarEventDateLength, calendarEventCourseLength);
	}

}
