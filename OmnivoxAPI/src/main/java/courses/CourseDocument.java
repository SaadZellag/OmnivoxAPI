package courses;

import java.util.Date;

/**
 * This class represents a course document on the Lea page.
 * 
 * It requires the same values as its parent class {@link CourseElement} but
 * needs an extra parameter
 * 
 * This class is Immutable and final.
 */
public final class CourseDocument extends CourseElement {

	/**
	 * Represents the name of the attachment given by the teacher. It could be the
	 * document name, the link or nothing.
	 */
	private final String documentName;

	/**
	 * Only constructor for the CourseDocument.
	 * 
	 * @param courseName      Name of the course associated to the document
	 * @param title           The name of the document given by the teacher
	 * @param distributedTime The time the document has been released
	 * @param isSeen          If the document has been seen by the user. (If it has
	 *                        a star)
	 * @param documentName    The name of the attachement associated with the
	 *                        document
	 */
	public CourseDocument(String courseName, String title, Date distributedTime, boolean isSeen, String documentName) {
		super(courseName, title, distributedTime, isSeen);

		this.documentName = documentName;
	}

//	Getters
	public String getDocumentName() {
		return this.documentName;
	}

//	Overrides
	@Override
	public int hashCode() {
		return super.hashCode() * 31 + this.documentName.hashCode();
	}
}
