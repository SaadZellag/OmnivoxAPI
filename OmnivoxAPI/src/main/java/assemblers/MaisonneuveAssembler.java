package assemblers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import courses.CalendarEvent;
import courses.CourseAssignment;
import courses.CourseDocument;

/**
 * This class extends the {@link Assembler} class with its own private methods
 * to facilitate the creation of Course Elements for the Maisonneuve College.
 */
public class MaisonneuveAssembler extends Assembler {

	/**
	 * Used to format the {@link Date} Objects using the format d MMM yyyy with the
	 * Canadian French Locale for the {@link CourseDocument} Object
	 */
	private static final SimpleDateFormat documentFormatter = new SimpleDateFormat("d MMM yyyy", Locale.CANADA_FRENCH);

	/**
	 * Used to format the {@link Date} Objects using the format dd-MMM-yyyy with the
	 * Canadian French Locale for the {@link CourseAssignment} Object
	 */
	private static final SimpleDateFormat assignmentFormatter = new SimpleDateFormat("dd-MMM-yyyy",
			Locale.CANADA_FRENCH);

	/**
	 * Used to format the {@link Date} Objects using the format d MMMMMMMM yyyy with
	 * the Canadian French Locale for the {@link CalendarEvent} Object
	 */
	private static final SimpleDateFormat calendarEventFormatter = new SimpleDateFormat("d MMMMMMMM yyyy",
			Locale.CANADA_FRENCH);

	/**
	 * Used to convert the String recieved in the Maisonneuve Omnivox to Strings
	 * that are acceptable to parse into {@link Date} Objects
	 */
	private static final HashMap<String, String> realMonths = new HashMap<String, String>();
	
	static {
		realMonths.put("jan", "janv.");
		realMonths.put("fév", "févr.");
		realMonths.put("mar", "mars");
		realMonths.put("avr", "avr.");
		realMonths.put("mai", "mai");
		realMonths.put("jui", "juin");
		realMonths.put("jui", "juil.");
		realMonths.put("aoû", "août");
		realMonths.put("sep", "sept.");
		realMonths.put("oct", "oct.");
		realMonths.put("nov", "nov.");
		realMonths.put("déc", "déc.");
	}

	@Override
	public CourseDocument[] assembleDocuments(HtmlPage page) {
		List<HtmlElement> documents = page.getByXPath("//*[@class='itemDataGrid' or @class='itemDataGridAltern']");

		String courseName = page.<HtmlElement>getFirstByXPath("//*[@class='TitrePageLigne2']").asText();
		System.out.printf("Getting documents for %s...\n", courseName);

		CourseDocument[] return_array = new CourseDocument[documents.size()];

		int i = 0;
		for (HtmlElement document : documents) {

			String documentName = document.<HtmlElement>getFirstByXPath("./td[2]").asText();
			String distributed = document.<HtmlElement>getFirstByXPath("./td[3]").asText();
			String view = document.<HtmlElement>getFirstByXPath("./td[4]").asText();

			HtmlElement star = document.getFirstByXPath("./td[1]/img");
			boolean seen = star == null;

			return_array[i++] = formatDocument(courseName, documentName, distributed, view, seen);

		}

		return return_array;
	}

	@Override
	public CourseAssignment[] assembleAssignments(HtmlPage page) {
		List<HtmlElement> assignments = page.getByXPath("//*[@id='tabListeTravEtu']/tbody/tr[@height='30']");
		
		String courseName = page.<HtmlElement>getFirstByXPath("//*[@class='TitrePageLigne2']").asText();
		System.out.printf("Getting assignments for %s...\n", courseName);

		CourseAssignment[] return_array = new CourseAssignment[assignments.size()];

		int i = 0;
		for (HtmlElement assignment : assignments) {

			String title = assignment.<HtmlElement>getFirstByXPath("./td[2]").asText();
			String distributed = assignment.<HtmlElement>getFirstByXPath("./td[3]").asText();

			HtmlElement check = assignment.<HtmlElement>getFirstByXPath("./td/table/tbody/tr/td[2]/a");
			boolean completed = check != null;

			HtmlElement star = assignment.<HtmlElement>getFirstByXPath("./td[1]/img");
			boolean seen = star == null;

			return_array[i++] = formatAssignment(courseName, title, distributed, seen, completed);

		}

		return return_array;
	}

	@Override
	public CalendarEvent[] assembleCalendarEvents(HtmlPage page) {
		List<HtmlElement> events = page.getByXPath("//*[@id='tblCalendrierEvenement']/tbody/tr/td/div[4]/div");

		// If the user has the wrong calendar type
		if (events.size() == 0) {
			page = changeCalendar(page);
			events = page.getByXPath("//*[@id='tblCalendrierEvenement']/tbody/tr/td/div[4]/div");
		}

		CalendarEvent[] return_array = new CalendarEvent[events.size()];

		int i = 0;
		for (HtmlElement event : events) {

			String day = event.<HtmlElement>getFirstByXPath("./div/div[2]").asText();
			String month = event.<HtmlElement>getFirstByXPath("./div/div[3]").asText();
			int year = Calendar.getInstance().get(Calendar.YEAR);

			String title = event.<HtmlElement>getFirstByXPath("./div[3]/h3").asText();

			// Checking if it is a course event or general event
			HtmlElement courseNameElement = event.<HtmlElement>getFirstByXPath("./div[3]/div/span");
			String courseName = courseNameElement == null ? "Not A Course" : courseNameElement.asText();

			// Checking if there is a discription
			DomText descriptionDom = event.<DomText>getFirstByXPath("./div[3]/div/text()");
			String description = descriptionDom == null ? "No Description" : descriptionDom.asText();

			return_array[i++] = formatCalendarEvent(day, month, year, courseName, title, description);

		}

		return return_array;
	}

	// Private Methods

	/**
	 * Formats the given Strings and parses them into a {@link CourseDocument}
	 * object
	 * 
	 * @param courseName   Name of the course the document belongs to
	 * @param documentName Title of the document
	 * @param distributed  Date distributed
	 * @param view         The title of the document (will be set to link if its
	 *                     empty)
	 * @param seen         If the document is seen or not
	 */
	private static CourseDocument formatDocument(String courseName, String documentName, String distributed,
			String view, boolean seen) {
		
		
		// Formatting the Strings
		documentName = documentName.replace("\n", " ").replace("\r", "").strip();
		distributed = distributed.replace("\n", " ").replace("\r", "");
		distributed = distributed.substring(10);
		view = view.replace("\n", " ").replace("\r", "");
		view = view.isBlank() ? "Link" : view.strip();
		
		
		distributed = formatDate(distributed);
		

		try {
			return new CourseDocument(courseName, documentName, documentFormatter.parse(distributed), seen, view);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Formats the given Strings and parses them into a {@link CourseAssignment}
	 * object
	 * 
	 * @param courseName     Name of the course the assignment belongs to
	 * @param assignmentName Title of the assignment
	 * @param distributed    Date distributed
	 * @param completed      If the assignment has been submitted
	 * @param seen           If the assignment is seen or not
	 */
	private static CourseAssignment formatAssignment(String courseName, String assignmentName, String distributed,
			boolean seen, boolean completed) {

		// Formatting the Strings
		assignmentName = assignmentName.replace("\n", " ").replace("\r", "").strip();
		distributed = distributed.replace("\n", " ").replace("\r", "");
		distributed = distributed.substring(0, 11);
		distributed = formatDate(distributed);

		try {
			return new CourseAssignment(courseName, assignmentName, assignmentFormatter.parse(distributed), seen,
					completed);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to format the date as a String. Ex: Converts 03-f�v-2020 to
	 * 03-f�vr.-2020
	 */
	private static String formatDate(String s) {
		String[] splitted = s.split(" ");
		
		// Checking if it is spliced via - and not spaces
		if (splitted.length == 1) {
			splitted = s.split("-");
			s = String.join("-", splitted[0], realMonths.get(splitted[1]), splitted[2]);
		} else {
			s = String.join(" ", splitted[0], realMonths.get(splitted[1]), splitted[2]);
		}
		return s;
	}

	/**
	 * Formats the given Strings into a {@link CalendarEvent} object
	 * 
	 * @param day         The day as a number
	 * @param month       The month as the full month name
	 * @param year        The year as a number
	 * @param courseName  The course name
	 * @param title       The title of the event
	 * @param description The description of the event
	 * 
	 * @return The formatted CalendarEvent object
	 */
	private static CalendarEvent formatCalendarEvent(String day, String month, int year, String courseName,
			String title, String description) {

		// Formatting Strings
		courseName = courseName.replace("\n", " ").replace("\r", "").strip();
		title = title.replace("\n", " ").replace("\r", "").strip();
		description = description.replace("\n", " ").replace("\r", "").strip();

		try {
			return new CalendarEvent(courseName, title, calendarEventFormatter.parse(day + " " + month + " " + year),
					description);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}
