package ch.hsr.mge.gadgeothek.ui.login;


public class LoginPresenter implements LoginContract.UserActionsListener {


    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void startLogin(String email, String password) {
        view.resetErrors();

        boolean userInformationValid = true;
        if (!isValidEmail(email)) {
            view.addErrorForEmail();
            userInformationValid = false;
        }

        if (!isValidPassword(password)) {
            view.addErrorForPassword();
            userInformationValid = false;
        }

        if (userInformationValid) {
            view.proceedWithLogin();
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.length() > 0;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() > 0;
    }

}
