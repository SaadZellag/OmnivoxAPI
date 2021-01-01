package students;

import courses.CalendarEvent;
import courses.CourseAssignment;
import courses.CourseDocument;

/**
 * This class is used to print information about a student passed into 
 * using a {@link StudentPrinterConfiguration} object.
 * */
public class StudentPrinter {

	private final Student student;
	private final StudentPrinterConfiguration configuration;

	public StudentPrinter(Student student) {
		this(student, new StudentPrinterConfiguration());
	}
	
	public StudentPrinter(Student student, StudentPrinterConfiguration configuration) {
		this.student = student;
		this.configuration = configuration;
	}

	/**
	 * Prints the documents of a student using 4 categories: New, Title, Date of
	 * release and Name of the attachment.
	 * 
	 * Prints documents for all the courses separately.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's documents.
	 */
	public void printDocuments() {

		// For every course
		for (String courseName : this.student.getCourseKeys()) {

			System.out.println("\n" + courseName);
			printDocumentTopPart();

			// For every document
			for (CourseDocument doc : this.student.getDocuments(courseName)) {
				System.out.printf(this.configuration.getDocumentFormat(), doc.isSeen() ? "" : " X ", doc.getTitle(),
						doc.getDateStr(), doc.getDocumentName());
			}

			printDocumentLine();
		}
	}

	/**
	 * Prints the documents of a student using 4 categories: New, Title, Date of
	 * release and Name of the attachment.
	 * 
	 * Prints documents for all the courses separately.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's documents.
	 * 
	 * @param x The number of documents to be printed
	 */
	public void printDocuments(int x) {

		// For every course
		for (String courseName : this.student.getCourseKeys()) {

			System.out.println("\n" + courseName);
			printDocumentTopPart();

			// For every document
			for (CourseDocument doc : this.student.getNewestDocuments(courseName, x)) {
				System.out.printf(this.configuration.getDocumentFormat(), doc.isSeen() ? "" : " X ", doc.getTitle(),
						doc.getDateStr(), doc.getDocumentName());
			}

			printDocumentLine();
		}
	}

	/**
	 * Prints all of the documents of a student using 4 categories: New, Title, Date
	 * of release and Name of the attachment.
	 * 
	 * Prints all of the documents in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's documents.
	 */
	public void printAllDocuments() {
		printDocumentTopPart();

		for (CourseDocument doc : this.student.getAllDocument()) {
			System.out.printf(this.configuration.getDocumentFormat(), doc.isSeen() ? "" : " X ", doc.getTitle(),
					doc.getDateStr(), doc.getDocumentName());
		}

		printDocumentLine();
	}

	/**
	 * Prints top x newest document using 4 categories: New, Title, Date of release
	 * and Name of the attachment.
	 * 
	 * Print all of the documents in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's documents.
	 * 
	 * @param x The number of documents to be printed
	 */
	public void printAllDocuments(int x) {
		printDocumentTopPart();
		for (CourseDocument doc : this.student.getNewestDocuments(x)) {
			System.out.printf(this.configuration.getDocumentFormat(), doc.isSeen() ? "" : " X ", doc.getTitle(),
					doc.getDateStr(), doc.getDocumentName());
		}
		printDocumentLine();
	}

	/**
	 * Prints the assignments of a student using 4 categories: Completed, New, Title
	 * and Submit Date.
	 * 
	 * Prints assignments for all the courses separately.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's assignments.
	 */
	public void printAssignments() {
		// For every course
		for (String courseName : this.student.getCourseKeys()) {

			System.out.println("\n" + courseName);
			printAssignmentTopPart();

			// For every assignment
			for (CourseAssignment a : this.student.getAssignments(courseName)) {

				System.out.printf(this.configuration.getAssignmentFormat(), a.isCompleted() ? "    X    " : "",
						a.isSeen() ? "" : " X ", a.getTitle(), a.getDateStr());
			}
			printAssignmentLine();
		}
	}

	/**
	 * Prints the first x assignments of a student using 4 categories: Completed,
	 * New, Title and Submit Date.
	 * 
	 * Prints assignments for all the courses separately.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's assignments.
	 * 
	 * @param x The number of assignments printed per course.
	 */
	public void printAssignments(int x) {
		// For every course
		for (String courseName : this.student.getCourseKeys()) {

			System.out.println("\n" + courseName);
			printAssignmentTopPart();

			// For every assignment
			for (CourseAssignment a : this.student.getNewestAssignments(courseName, x)) {

				System.out.printf(this.configuration.getAssignmentFormat(), a.isCompleted() ? "    X    " : "",
						a.isSeen() ? "" : " X ", a.getTitle(), a.getDateStr());
			}
			printAssignmentLine();
		}
	}

	/**
	 * Prints all of the assignments of a student using 4 categories: Completed,
	 * New, Title and Submit Date.
	 * 
	 * Prints all of the assignments in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's assignments.
	 */
	public void printAllAssignments() {
		printAssignmentTopPart();

		// For every document
		for (CourseAssignment a : this.student.getAllAssignments()) {
			System.out.printf(this.configuration.getAssignmentFormat(), a.isCompleted() ? "    X    " : "",
					a.isSeen() ? "" : " X ", a.getTitle(), a.getDateStr());
		}
		printAssignmentLine();
	}

	/**
	 * Prints the first x assignments of a student using 4 categories: Completed,
	 * New, Title and Submit Date.
	 * 
	 * Prints all of the assignments in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's assignments.
	 * 
	 * @param x The latest x assignments to be printed
	 */
	public void printAllAssignments(int x) {
		printAssignmentTopPart();

		// For every document
		for (CourseAssignment a : this.student.getNewestAssignments(x)) {
			System.out.printf(this.configuration.getAssignmentFormat(), a.isCompleted() ? "    X    " : "",
					a.isSeen() ? "" : " X ", a.getTitle(), a.getDateStr());
		}
		printAssignmentLine();
	}

	/**
	 * Prints all of the calendar events of a student using 4 categories: Title,
	 * Description, Date and Course.
	 * 
	 * Prints all of the events in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's events.
	 */
	public void printCalendarEvents() {
		System.out.println();
		printCalendarTopPart();

		// For every calendar event
		for (CalendarEvent e : this.student.getCalendarEvents()) {
			System.out.printf(this.configuration.getCalendarEventFormat(), e.getTitle(), e.getDescription(),
					e.getDateStr(), e.getCourseName());
		}
		printCalendarEventLine();
	}

	/**
	 * Prints the x next calendar events of a student using 4 categories: Title,
	 * Description, Date and Course.
	 * 
	 * Prints all of the events in one table by date.
	 * 
	 * Uses the {@link StudentPrinterConfiguration} object to print a nice and
	 * structured grid to get a clear summary of the student's events.
	 * 
	 * @param x The number of calendar events to get.
	 */
	public void printCalendarEvents(int x) {
		System.out.println();
		printCalendarTopPart();

		// For every calendar event
		for (CalendarEvent e : this.student.getOldestCalendarEvents(x)) {
			System.out.printf(this.configuration.getCalendarEventFormat(), e.getTitle(), e.getDescription(),
					e.getDateStr(), e.getCourseName());
		}
		printCalendarEventLine();
	}

	// Private methods

	/**
	 * Used to print the top of the table showing the labels.
	 */
	private void printDocumentTopPart() {
		printDocumentLine();
		System.out.printf(this.configuration.getDocumentFormat(), "New", "Title", "Released", "FileName");
		printDocumentLine();
	}

	/**
	 * Used to print a line like this +---+---+---+---+ depending on the
	 * {@link StudentPrinterConfiguration} object.
	 */
	private void printDocumentLine() {
		System.out.println(this.configuration.getDocumentLine());
	}

	/**
	 * Used to print the top of the table showing the labels.
	 */
	private void printAssignmentTopPart() {
		printAssignmentLine();
		System.out.printf(this.configuration.getAssignmentFormat(), "Completed", "New", "Title", "Sumbit Date");
		printAssignmentLine();
	}

	/**
	 * Used to print a line like this +---+---+---+---+ depending on the
	 * {@link StudentPrinterConfiguration} object.
	 */
	private void printAssignmentLine() {
		System.out.println(this.configuration.getAssignmentLine());
	}

	/**
	 * Used to print the top of the table showing the labels.
	 */
	private void printCalendarTopPart() {
		printCalendarEventLine();
		System.out.printf(this.configuration.getCalendarEventFormat(), "Title", "Description", "Date", "Course");
		printCalendarEventLine();
	}

	/**
	 * Used to print a line like this +---+---+---+---+ depending on the
	 * {@link StudentPrinterConfiguration} object.
	 */
	private void printCalendarEventLine() {
		System.out.println(this.configuration.getCalendarEventLine());
	}

	public Student getStudent() {
		return this.student;
	}

	public StudentPrinterConfiguration getConfiguration() {
		return this.configuration;
	}
}
