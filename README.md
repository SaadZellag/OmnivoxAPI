# OmnivoxAPI
A simple API to access a student Omnivox account's information

This API is used to collect, assemble and display your Omnivox student information. 

## Getting Started
First, download the repository:
```
git clone https://github.com/SaadZellag/OmnivoxAPI.git
```
Then move into it:
```
cd OmnivoxAPI
```
To run the API without modifying it, cd into the "jars" refer to the "Usage" section:
```
cd Jars
```

## Usage
The use of this API is straightforward. If this project has already implemented your CEGEP interface, you can run this project's main method with your CEGEP implementation. Go to the "Jars" folder and enter the following command inside it:
```
java -cp OmnivoxAPI-0.0.1-SNAPSHOT.jar Main.java [CEGEP] [Student Number] [Password]
```

## How it works
It consists of 4 main parts: 
1. A "scraper" to gather all of the data from Omnivox. (Using gargoylesoftware)
2. An "assembler" to convert the HTML content to Java objects.
3. A "student" to keep all objects.
4. A "manager" to bind the three aforementioned elements.

## Usage
The use of this API is straightforward. If this project has already implemented your CEGEP interface, then you can run this project's main method with your CEGEP implementation. Download the "jars" folder and enter the following command inside it:
```
java -cp OmnivoxAPI-0.0.1-SNAPSHOT.jar Main.java [CEGEP] [Student Number] [Password]
```

## Examples for the supported CEGEPs:
```
java -cp OmnivoxAPI-0.0.1-SNAPSHOT.jar Main.java champlain 1234567 password
```
```
java -cp OmnivoxAPI-0.0.1-SNAPSHOT.jar Main.java maisonneuve 1234567 password
```

Want to use it for your CEGEP? Refer to the "Implementation" section to contribute to the project.

### Note this jar was built with Java 11

## Implementation
Currently, this API supports only the Champlain and Maisonneuve CEGEPs. To support your CEGEP interface, which is different than Champlain and Maisonneuve, you need to extend two classes (OmnivoxScraper and Assembler) and implement their abstract methods. 

OmnivoxScraper:
* getDocumentPages(): Gets the document page of each course.
* getAssignmentPages(): Gets the assignment page of each course.
* printWhatsNew(): Gets the information about what's new and prints it.
* setLeaPage(): Gets the link for the Lea Page and sets it to the leaPage field.

Assembler:
* assembleDocuments(HtmlPage page): Gets all the documents on a given page and assembles them into usable objects.
* assembleAssignments(HtmlPage page): Gets all the assignments on a given page and assembles them into usable objects.
* assembleCalendarEvents(HtmlPage page): Gets all the calendar events on the home page and assembles them into usable objects.

Please refer to the implemented method or documentation to better understand the methods.

## Help
If you want this project to support additional CEGEP(s), feel free to make a pull request.
