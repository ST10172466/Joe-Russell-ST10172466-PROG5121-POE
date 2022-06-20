package prog5121;

import prog5121.Login;
import org.junit.Test;
import org.junit.FixMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class LoginTest
{

    Login log = new Login();

    public LoginTest()
    {
    }

    //Testing checkUserName method to check username
    @Test
    public void AtestCheckUserName()
    {
        Boolean expected = true;
        Boolean actual = log.checkUserName();
        assertEquals(expected, actual);
    }

    //Testing checkPasswordComplexity method to check password
    @Test
    public void BtestCheckPasswordComplexity()
    {
        Boolean expected = true;
        Boolean actual = log.checkPasswordComplexity();
        assertEquals(expected, actual);
    }

    //Testing registerUser method to check username and password
    @Test
    public void CtestRegisterUser()
    {
        String expected = "Username Formatted Correctly" + "Password Formatted Correctly";
        String actual = log.registerUser();
        assertEquals(expected, actual);                  
    }

    //Testing loginUser method to check new inputs
    @Test
    public void DtestLoginUser()            
    {
        Boolean expected = true;
        Boolean actual = log.loginUser();
        assertEquals(expected, actual);
    }

    //Testing returnLoginStatus method
    @Test
    public void EtestReturnLoginStatus()
    {
        String expected = "Login Successful";
        String actual = log.returnLoginStatus();
        assertEquals(expected, actual);
    }

}
