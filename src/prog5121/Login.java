package prog5121;

import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login
{
//------------------------------------------------------------------------------
    //Declare variables
    private static String username;
    private static String password;
    private static String firstName;
    private static String lastName;

    private static String logUsername;
    private static String logPassword;

    private static String usernameCorrect;
    private static String usernameIncorrect;
    private static String passwordCorrect;
    private static String passwordIncorrect;

    private static boolean usernameStatus;
    private static boolean passwordStatus;
    private static boolean loginStatus;

//-----------------------------First Method Start-------------------------------
    //Check username as a boolean
    public boolean checkUserName()
    {
        //Input username
        username = JOptionPane.showInputDialog("Please enter a user name that contains "
                + "an underscore and is no longer than 5 characters");

        //Check that username is correctly formatted and If statement that returns boolean
        if (username.length() <= 5 && username.contains("_"))
        {
            usernameStatus = true;
            JOptionPane.showMessageDialog(null, usernameCorrect);

            return true;
        }
        return false;
    }
//-----------------------------First Method End---------------------------------

    
    
//---------------------------Second Method Start--------------------------------
    public boolean checkPasswordComplexity()
    {
        //Input password
        password = JOptionPane.showInputDialog("Please enter a password that contains "
                + "a capital letter, a number, a special charcter "
                + "and is at least 8 charcters long");

        //Check password length, capitalisation, whether it has a number and whether it has a special charcter
        String capitalCheck = "[A-Z]";
        String digitCheck = "[0-9]";

        Pattern digit = Pattern.compile(digitCheck);
        Matcher matched = digit.matcher(password);
        boolean containsDigit = matched.find();

        Pattern cap = Pattern.compile(capitalCheck);
        Matcher match = cap.matcher(password);
        boolean containsCapitalLetter = match.find();

        Pattern special = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = special.matcher(password);
        boolean containsSpecialCharacter = matcher.find();

        //If statement that returns boolean
        if (password.length() >= 8 && containsSpecialCharacter && containsCapitalLetter && containsDigit)
        {
            passwordStatus = true;
            JOptionPane.showMessageDialog(null, passwordCorrect);

            return true;
        }
        return false;
    }
//----------------------------------Second Method End---------------------------

    
    
//----------------------------------Third Method Start--------------------------
    public String registerUser()
    {
        //Enter username, password, first and last name
        firstName = JOptionPane.showInputDialog("Please enter your first name");

        lastName = JOptionPane.showInputDialog("Please enter your last name");

        //Strings for correct and incorrect responses
        usernameCorrect = "Username Formatted Correctly";
        usernameIncorrect = "Username Formatted Incorrectly";
        passwordCorrect = "Password Formatted Correctly";
        passwordIncorrect = "Password Formatted Incorrectly";

        //Run check user name method
        checkUserName();

        //While loop to prevent incorrect username from being entered
        while (usernameStatus == false)
        {
            JOptionPane.showMessageDialog(null, usernameIncorrect, "Error", JOptionPane.ERROR_MESSAGE);
            checkUserName();

            if (usernameStatus == true)
            {
                break;
            }
        }

        //Run check password method
        checkPasswordComplexity();

        //While loop to prevent incorrect password from being entered
        while (passwordStatus == false)
        {
            JOptionPane.showMessageDialog(null, passwordIncorrect, "Error", JOptionPane.ERROR_MESSAGE);
            checkPasswordComplexity();

            if (passwordStatus == true)
            {
                break;
            }
        }

        //Return correct Strings when both inputs are correctly formatted
        return usernameCorrect + passwordCorrect;
    }
//-------------------------------Third Method End-------------------------------

    
    
//-----------------------------Fourth Method Start------------------------------
    public boolean loginUser()
    {        
        //Input username and password again to check
        logUsername = JOptionPane.showInputDialog("Please enter your username");
        logPassword = JOptionPane.showInputDialog("Please enter your password");

//------------------------------------------------------------------------------     
        //Check that new username and password match original inputs        
        if (logUsername.equals(username) && logPassword.equals(password))
        {
            loginStatus = true;
            
            JOptionPane.showMessageDialog(null, "Login Successful");
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " 
                    + lastName + ", it is great to see you again. ");
           
            //Commented out kanban board for part 2 of POE
                Task kanban = new Task();
                kanban.task();

            return true;
        }

        return false;
    }
//-----------------------------Fourth Method End--------------------------------   

    
    
//-----------------------------Fifth Method Start------------------------------- 
    public String returnLoginStatus()
    {        
        //Strings for correct and incorrect login attempts
        String successfulLogin = "Login Successful";
        String failedLogin = "Login Unsuccessful";

        //Run login user method
        loginUser();

        //While loop to prevent incorrect logins
        while (loginStatus == false)
        {
            JOptionPane.showMessageDialog(null, "Login Unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
            loginUser();

            if (loginStatus == true)
            {
                break;
            }
        }        

        if (loginStatus == true)
        {
            return successfulLogin;
        }
        return failedLogin;
    }
    
//-----------------------------Fifth Method End---------------------------------
}
//---------------------------**** END OF FILE ****------------------------------