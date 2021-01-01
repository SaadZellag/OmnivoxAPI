# OmnivoxAPI
A simple API to access a student Omnivox account's information

This API is used to collect, assemble and display your Omnivox student information. 

## How it works
It consists of 4 main parts: 
1. A "scraper" to gather all of the data from Omnivox. (Using gargoylesoftware)
2. An "assembler" to convert the HTML content to Java objects.
3. A "student" to keep all objects.
4. A "manager" to bind the three aforementionned elements.

## Usage
The use of this API is very simple. If your CEGEP interface has already been implemented by this project, then you can simply run this project's 
main method with your CEGEP implementation. For example:
```
java -cp OmnivoxAPI-0.0.1-SNAPSHOT.jar Main.java [CEGEP] [Student Number] [Password]
```
## Implementation
Currently, this API supports only the Champlain and Maisonneuve CEGEPs. To support your CEGEP interface, that is different than Champlain and Maisonneuve, you need to extend two classes (OmnivoxScraper and Assembler) and implement their abstract methods. 

OmnivoxScraper:
* getDocumentPages(): Gets the document page of each course.
* getAssignmentPages(): Gets the assignment page of each course.
* printWhatsNew(): Gets the information about what's new and prints it.
* setLeaPage(): Gets the link for the Lea Page and sets it to the leaPage field.

Assembler:
* assembleDocuments(HtmlPage page): Gets all the documents in a given page and assembles them into usable objects.
* assembleAssignments(HtmlPage page): Gets all the assignments in a given page and assembles them into usable objects.
* assembleCalendarEvents(HtmlPage page): Gets all the calendar events in the home page and assembles them into usable objects.

Please refer to the implemented method or documentation to better understand the methods.

## Help
If you want this project to support additional CEGEP(s), feel free to make a pull request.
