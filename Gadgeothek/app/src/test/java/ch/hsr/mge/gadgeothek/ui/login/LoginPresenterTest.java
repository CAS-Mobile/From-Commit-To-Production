package ch.hsr.mge.gadgeothek.ui.login;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginPresenterTest {

    private static final String VALID_EMAIL = "abc@def.ch";
    private static final String INVALID_EMAIL = "abcdef.ch";
    private static final String EMPTY = "";
    private static final String VALID_PASSWORD = "pa$$w0rd";


    private LoginPresenter presenter;
    private LoginContract.View mockView;


    @Before
    public void setup() {
        mockView = Mockito.mock(LoginContract.View.class);
        presenter = new LoginPresenter(mockView);
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        presenter.startLogin(VALID_EMAIL, VALID_PASSWORD);
        Mockito.verify(mockView).resetErrors();
        Mockito.verify(mockView).proceedWithLogin();
    }

    @Test
    public void shouldNotLoginWithEmptyEmail() {
        presenter.startLogin(EMPTY, VALID_PASSWORD);
        Mockito.verify(mockView).resetErrors();
        Mockito.verify(mockView).addErrorForEmail();
        Mockito.verify(mockView, Mockito.never()).proceedWithLogin();
    }

    @Test
    public void shouldNotLoginWithEmptyPassword() {
        presenter.startLogin(VALID_EMAIL, EMPTY);
        Mockito.verify(mockView).resetErrors();
        Mockito.verify(mockView).addErrorForPassword();
        Mockito.verify(mockView, Mockito.never()).proceedWithLogin();
    }

    @Test
    public void shouldLoginWithInvalidEmail() {
        presenter.startLogin(INVALID_EMAIL, VALID_PASSWORD);
        Mockito.verify(mockView).resetErrors();
        Mockito.verify(mockView).proceedWithLogin();
    }


}
