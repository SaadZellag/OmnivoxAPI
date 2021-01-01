package students;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import assemblers.Assembler;
import courses.CalendarEvent;
import courses.Course;
import courses.CourseAssignment;
import courses.CourseDocument;
import scrapers.OmnivoxScraper;

/**
 * This class is used to bind the {@link Assembler}, the {@link Student} and the
 * {@link OmnivoxScraper}. When given the objects, it will manage how the object's
 * inputs and outputs will be fed into each other.
 */
public class StudentManager {

	/**
	 * The object used to get all of the data online.
	 */
	private final OmnivoxScraper scraper;

	/**
	 * The object used to assemble the data and format it.
	 */
	private final Assembler assembler;

	/**
	 * The object used to store all of the formated data.
	 */
	private final Student student;

	public StudentManager(OmnivoxScraper scraper, Assembler assembler, Student student) {
		this.scraper = scraper;
		this.assembler = assembler;
		this.student = student;
	}
	
	/**
	 * Used to login and setting the Lea Page field via the scraper
	 * */
	public void login(String studentNumber, String password) {
		scraper.login(studentNumber, password);
		scraper.setLeaPage();
	}

	/**
	 * This method calls the scraper's {@link OmnivoxScraper#getDocumentPages}
	 * method. It then feeds it to the assembler. Finally it adds the assembled data
	 * to the student.
	 */
	public void getDocuments() {
		// Get all documents
		for (HtmlPage page : scraper.getDocumentPages()) {

			// Page is non null
			if (page == null) {
				throw new NullPointerException("Document Page is null");
			}

			CourseDocument[] assembled = assembler.assembleDocuments(page);

			// Checking if the array is not length 0 or non null
			if (assembled == null || assembled.length == 0)
				continue;

			String courseName = assembled[0].getCourseName();

			// Assuming this is the first method called
			student.addCourse(courseName, new Course(courseName));

			// Adding the course documents
			for (CourseDocument doc : assembled) {
				student.assignDocument(courseName, doc);
			}
		}
	}
	
	/**
	 * This method calls the scraper's {@link OmnivoxScraper#getAssignmentPages}
	 * method. It then feeds it to the assembler. Finally it adds the assembled data
	 * to the student.
	 */
	public void getAssignments() {
		// Get all assignments
		for (HtmlPage page : scraper.getAssignmentPages()) {

			// Page is non null
			if (page == null) {
				throw new NullPointerException("Document Page is null");
			}

			CourseAssignment[] assembled = assembler.assembleAssignments(page);

			// Checking if the array is not length 0
			if (assembled == null || assembled.length == 0)
				continue;

			String courseName = assembled[0].getCourseName();

			// Assuming it is the first method called
			student.addCourse(courseName, new Course(courseName));

			// Adding the course assignments
			for (CourseAssignment assignment : assembled) {
				student.assignAssignment(courseName, assignment);
			}

		}
	}

	public void getCalendarEvents() {
		for (CalendarEvent event : assembler.assembleCalendarEvents(scraper.getHomePage())) {
			student.assignCalendarEvent(event);
		}
	}

	public Student getStudent() {
		return this.student;
	}

	public OmnivoxScraper getScraper() {
		return this.scraper;
	}

	public Assembler getAssembler() {
		return this.assembler;
	}
}
