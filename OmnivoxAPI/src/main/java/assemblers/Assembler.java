package assemblers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

import courses.CalendarEvent;
import courses.CourseAssignment;
import courses.CourseDocument;
import courses.CourseElement;

/**
 * This abstract class is used to assemble the {@link CourseElement} from the
 * HtmlPages given. You need to extend this class and implement its methods. You
 * will receive the Html page corresponding to the method you are using.
 */
public abstract class Assembler {

	/**
	 * Assembles all of the documents in the Lea document page.
	 * 
	 * When given the Lea Document Page, this method needs to find all of the
	 * documents in the HtmlPage and format them to a CourseDocument
	 * 
	 * @param page The Lea Document page
	 * 
	 * @return All of the Course Documents found in the page
	 */
	public abstract CourseDocument[] assembleDocuments(HtmlPage page);

	/**
	 * Assembles all of the assignments in the Lea Assignment page.
	 * 
	 * When given the Lea Assignment Page, this method needs to find all of the
	 * assignments in the HtmlPage and format them to a CourseAssignment
	 * 
	 * @param page The Lea Assignment page
	 * 
	 * @return All of the Course Assignments found in the page
	 */
	public abstract CourseAssignment[] assembleAssignments(HtmlPage page);

	/**
	 * Assembles all of the calendar event in the home page.
	 * 
	 * When given the home Page, this method needs to find all of the assignments in
	 * the HtmlPage and format them to a CourseAssignment
	 * 
	 * @param page The Omnivox home page
	 * 
	 * @return All of the Calendar Events found in the page
	 */
	public abstract CalendarEvent[] assembleCalendarEvents(HtmlPage page);

	/**
	 * Changes the calendar mode on the Omnivox homepage.
	 * 
	 * Makes a HTTP request to the Omnivox Quick View Button since it wasn't
	 * clickable.
	 * 
	 * This method has been tested with Champlain and Maisonneuve. If it generates
	 * an error, it needs to be overriden.
	 * 
	 * @param homePage The Omnivox Homepage
	 * 
	 * @return The refreshed homePage
	 */
	protected HtmlPage changeCalendar(HtmlPage homePage) {
		try {

			// Make a request to change the calendar type
			URL url = new URL(homePage.getBaseURL()
					+ "UI/WebParts/Intraflex_CalendrierScolaire/Webpart_Affichage_Selector.ashx?t="
					+ System.currentTimeMillis());
			WebRequest calendarRequest = new WebRequest(url, HttpMethod.POST);

			// Adding request params
			ArrayList<NameValuePair> requestParams = new ArrayList<NameValuePair>();
			requestParams.add(new NameValuePair("isModeVueParJour", "true"));

			calendarRequest.setRequestParameters(requestParams);

			// Sending the request
			homePage.getWebClient().getPage(calendarRequest);

			// Refreshing the page after the request was made
			return (HtmlPage) homePage.refresh();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
