Online Survey Form with Java Servlet
Overview
This project consists of an Online Survey Form implemented using Java Servlets for backend processing, HTML for the user interface, and Bootstrap for styling. It provides basic form validation on the server side and shows dynamic feedback to the user regarding input errors.

Technologies Used:
Java (Servlets): Handles the backend logic and form validation.
HTML: Displays the form to the user.
Bootstrap: Provides styling and layout for the form.
JSP (JavaServer Pages): Handles dynamic content rendering for error messages.
Features:
Form Fields: First Name, Last Name, Email, Age, and Feedback.
Server-Side Validation: Ensures that the form fields are correctly filled.
Error Handling: Displays error messages next to the respective fields if the user enters invalid data.
Form Data Persistence: After validation fails, the form retains the entered data.
Success Page: Redirects to a success page after a successful form submission.
Directory Structure
bash
Copy code
/OnlineSurveyForm
├── /src
│   └── SurveyFormServlet.java   # Java Servlet for handling the form submission and validation
├── /webapp
│   ├── /WEB-INF
│   │   └── web.xml             # Servlet mapping configuration
│   ├── survey.html             # HTML form for collecting user input
│   └── success.jsp             # Success page displayed after form submission
├── styles.css                  # Custom CSS file for styling the form (optional)
└── README.md                   # This readme file
How to Run the Application
Step 1: Set Up a Servlet Container
This project requires a Java Servlet container such as Apache Tomcat for running Java servlets.

Download and install Tomcat from here.
Set up the CATALINA_HOME environment variable to point to the Tomcat installation directory.
Ensure that Java is installed and set up correctly on your machine.
Step 2: Build and Deploy the Application
Project Setup:

Create a new Dynamic Web Project in Eclipse (or any Java IDE that supports Servlets).
Copy the survey.html, success.jsp, SurveyFormServlet.java, and web.xml to their appropriate directories in your project.
Configure web.xml:

Open web.xml in the WEB-INF directory and ensure the servlet and servlet-mapping sections are correctly configured. Here is the configuration:
xml
Copy code

Compile and Deploy:
Compile the project and deploy it to your Tomcat server.
If using Eclipse, right-click on your project and select Run on Server to deploy it to Tomcat.
Step 3: Running the Application
Once your application is deployed to Tomcat, navigate to the following URL:

bash
Copy code
http://localhost:8080/OnlineSurveyForm/survey.html
Survey Form: Fill in the fields and submit the form.

If any field is invalid (e.g., email format is incorrect, age is out of range), an error message will appear next to the corresponding field.
Once all fields are validated successfully, the form will be forwarded to the success page (success.jsp).
Explanation of Code
1. survey.html (Form Page)
The HTML form allows the user to enter their details:

First Name, Last Name, Email, Age, and Feedback.
The form is styled with Bootstrap for a responsive layout.
The form sends data to the SurveyFormServlet using a POST request.
2. SurveyFormServlet.java (Java Servlet)
The SurveyFormServlet handles form submission and validation.
The doPost() method is responsible for processing the form:
It collects the form data (First Name, Last Name, Email, Age, Feedback).
Validates each field:
First Name and Last Name must be non-empty.
Email must be in a valid format.
Age must be between 18 and 100.
Feedback must not be empty.
If validation fails, the servlet forwards the user back to the form with error messages.
If validation passes, the servlet forwards the user to the success.jsp page.
3. success.jsp (Success Page)
This simple page displays a message confirming that the form has been successfully submitted.
4. web.xml (Servlet Mapping)
The web.xml file maps the servlet to the URL /surveyForm. This is the endpoint where the form sends its data for processing.
Additional Features and Enhancements
Error Handling: Currently, the form validates fields like email, age, etc., and provides feedback. More complex validation rules can be added as needed (e.g., password strength, additional fields).
Database Integration: If you need to store form responses, you can modify the servlet to insert the validated data into a database (e.g., MySQL or PostgreSQL).
Client-Side Validation: For a better user experience, you could also add JavaScript validation that provides instant feedback on the client side before the form is submitted to the server.
Troubleshooting
Tomcat Not Running:

Ensure that Tomcat is started and configured properly. Check if it’s running by visiting:
arduino
Copy code
http://localhost:8080
Form Submission Not Working:

Ensure that the servlet mapping in web.xml matches the action URL in the form (/surveyForm).
Error Messages Not Displaying:

Check the Java Servlet code to ensure that error messages are correctly set in the request attributes and that the form is forwarded with these attributes.
License
This project is open-source and free to use. Feel free to make modifications or improvements.