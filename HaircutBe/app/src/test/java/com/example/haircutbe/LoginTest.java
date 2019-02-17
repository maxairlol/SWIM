package com.example.haircutbe;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@Config(manifest = Config.NONE)
    @RunWith(RobolectricTestRunner.class)
public class LoginTest {
    private static final String KEY_EMPTY = "";
    private static final String ERROR_EMPTY = "error_empty";
    private static final String ERROR_LOGIN = "error_login";
    private static final String ERROR_PWD = "error_pwd";
    private static final String LOGIN_SUCCESS = "login_success";
    private static final String LOGIN_VALID = "maxair";
    private static final String PWD_VALID = "1234";
    private static final String LOGIN_INVALID = "ronex";
    private static final String PWD_INVALID = "qwerty";


    @Mock
    LoginActivity view = mock(LoginActivity.class);

    @Before
    public void initMocks() {

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                return null;
            }
        }).when(view).createToast(anyString());

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                return null;
            }
        }).when(view).startSession(anyString());
    }

    @Test
    public void for_empty_data_signIn() {
        String result = new LoginPresenter(view).signIn(KEY_EMPTY,KEY_EMPTY);
        assertEquals(ERROR_EMPTY, result);
    }

    @Test
    public void for_empty_login_signIn() {
        String result = new LoginPresenter(view).signIn(KEY_EMPTY,PWD_VALID);
        assertEquals(ERROR_EMPTY, result);
    }

    @Test
    public void for_empty_password_signIn() {
        String result = new LoginPresenter(view).signIn(LOGIN_VALID,KEY_EMPTY);
        assertEquals(ERROR_EMPTY, result);
    }

    @Test
    public void for_invalid_login_signIn() {
        String result = new LoginPresenter(view).signIn(LOGIN_INVALID,PWD_VALID);
        assertEquals(ERROR_LOGIN, result);
    }

    @Test
    public void for_invalid_password_signIn() {
        String result = new LoginPresenter(view).signIn(LOGIN_VALID,PWD_INVALID);
        assertEquals(ERROR_PWD, result);
    }

    @Test
    public void for_invalid_data_signIn() {
        String result = new LoginPresenter(view).signIn(LOGIN_INVALID,PWD_INVALID);
        assertEquals(ERROR_LOGIN, result);
    }

    @Test
    public void for_valid_data_signIn() {
        String result = new LoginPresenter(view).signIn(LOGIN_VALID,PWD_VALID);
        assertEquals(LOGIN_SUCCESS, result);
    }

    /*@Test
    public void some() {
       DBConnection dbConnection = new DBConnection("http://156.17.237.133:80/HaircutBe/reservations.php");
       String result = "";
        try {
            result = dbConnection.execute("reservation","maxair").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("error_pwd",result);
        System.out.print(result);

    }*/

}