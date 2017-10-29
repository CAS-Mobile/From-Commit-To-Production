package ch.hsr.mge.gadgeothek.ui.login;


public interface LoginContract {

    interface View {

        void proceedWithLogin();

        void addErrorForEmail();

        void addErrorForPassword();

        void resetErrors();


    }

    interface UserActionsListener {

        void startLogin(String email, String password);

    }
}
