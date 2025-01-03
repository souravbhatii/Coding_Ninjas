import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.regex.*;

public class SurveyFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String feedback = request.getParameter("feedback");

        // Create a boolean flag for validation
        boolean isValid = true;

        // Validate firstName (non-empty)
        if (firstName == null || firstName.trim().isEmpty()) {
            isValid = false;
            request.setAttribute("firstNameError", "Please enter your first name.");
        }

        // Validate lastName (non-empty)
        if (lastName == null || lastName.trim().isEmpty()) {
            isValid = false;
            request.setAttribute("lastNameError", "Please enter your last name.");
        }

        // Validate email (proper format)
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            isValid = false;
            request.setAttribute("emailError", "Please enter a valid email address.");
        }

        // Validate age (between 18 and 100)
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 18 || ageInt > 100) {
                isValid = false;
                request.setAttribute("ageError", "Please enter a valid age between 18 and 100.");
            }
        } catch (NumberFormatException e) {
            isValid = false;
            request.setAttribute("ageError", "Please enter a valid number for age.");
        }

        // Validate feedback (non-empty)
        if (feedback == null || feedback.trim().isEmpty()) {
            isValid = false;
            request.setAttribute("feedbackError", "Please provide your feedback.");
        }

        // If the form is valid, proceed with processing (or save to database, etc.)
        if (isValid) {
            // For now, just forward to a success page
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            // If invalid, forward back to the form with error messages
            request.getRequestDispatcher("survey.html").forward(request, response);
        }
    }

    // Helper method for validating email using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
