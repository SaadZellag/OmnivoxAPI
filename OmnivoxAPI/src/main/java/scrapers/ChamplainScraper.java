package scrapers;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class extends the {@link OmnivoxScraper} class and implements its 4
 * methods.
 */
public class ChamplainScraper extends OmnivoxScraper{
	
	/**
	 * The Omnivox login link for Champlain college.
	 */
	private static final String loginUrl = "https://champlaincollege-st-lambert.omnivox.ca/intr/Module/Identification/Login/Login.aspx";
	
	/**
	 * Default constructor for the Champlain Scraper.
	 * */
	public ChamplainScraper() {
		super(loginUrl);
	}

	@Override
	public HtmlPage[] getDocumentPages() {
		List<HtmlElement> classes = this.LeaPage.getByXPath("//*[@class='card-panel section-spacing']");
		
		// Creating return array
		HtmlPage[] return_array = new HtmlPage[classes.size()];
		
		for(int i = 0; i < classes.size(); i++) {
			
			HtmlElement button = classes.get(i).getFirstByXPath("./div[2]/a");
			
			try {
				return_array[i] = button.click();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return return_array;
	}

	@Override
	public HtmlPage[] getAssignmentPages() {
		List<HtmlElement> classes = this.LeaPage.getByXPath("//*[@class='card-panel section-spacing']");
		
		// Creating return array
		HtmlPage[] return_array = new HtmlPage[classes.size()];
		
		for(int i = 0; i < classes.size(); i++) {
			
			HtmlElement button = classes.get(i).getFirstByXPath("./div[2]/a[2]");
			
			try {
				return_array[i] = button.click();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return return_array;
	}

	@Override
	public void printWhatsNew() {
//		Check if there is anything new
		List<HtmlElement> whats_new = this.homePage.<HtmlElement>getByXPath("//*[@id=\"qdn-sans-bouton-wrapper\"]/a/div[2]");
		
		if(whats_new.size() > 0) {
			
//			If there is something new
//			Printing the text of the elements
			for(HtmlElement news : whats_new) {
				System.out.println(news.asText());
			}
		} else {
			System.out.println("Nothing New");
		}
	}

	@Override
	public void setLeaPage() {
		try {
			this.LeaPage = this.homePage.<HtmlElement>getFirstByXPath("//*[@id='region-raccourcis-services-skytech']/a[1]").click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
