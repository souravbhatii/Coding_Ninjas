import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.regex.*;

public class SurveyFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String feedback = request.getParameter("feedback");

        // Initialize error flags
        boolean isValid = true;
        String firstNameError = "";
        String lastNameError = "";
        String emailError = "";
        String ageError = "";
        String feedbackError = "";

        // Validate the form fields
        if (firstName == null || firstName.trim().isEmpty()) {
            isValid = false;
            firstNameError = "Please enter your first name.";
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            isValid = false;
            lastNameError = "Please enter your last name.";
        }

        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            isValid = false;
            emailError = "Please enter a valid email address.";
        }

        int ageInt = 0;
        try {
            ageInt = Integer.parseInt(age);
            if (ageInt < 18 || ageInt > 100) {
                isValid = false;
                ageError = "Please enter a valid age (18-100).";
            }
        } catch (NumberFormatException e) {
            isValid = false;
            ageError = "Please enter a valid number for age.";
        }

        if (feedback == null || feedback.trim().isEmpty()) {
            isValid = false;
            feedbackError = "Please provide your feedback.";
        }

        // If the form is valid, forward to a success page (you can save data to the database here)
        if (isValid) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            // If not valid, show the form again with error messages
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("age", age);
            request.setAttribute("feedback", feedback);
            request.setAttribute("firstNameError", firstNameError);
            request.setAttribute("lastNameError", lastNameError);
            request.setAttribute("emailError", emailError);
            request.setAttribute("ageError", ageError);
            request.setAttribute("feedbackError", feedbackError);

            // Forward back to the form (survey.html)
            request.getRequestDispatcher("survey.html").forward(request, response);
        }
    }

    // Helper method to validate email using regular expression
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
