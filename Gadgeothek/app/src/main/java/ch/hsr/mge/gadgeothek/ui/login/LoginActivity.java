package ch.hsr.mge.gadgeothek.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.ui.AbstractAuthenticationActivity;
import ch.hsr.mge.gadgeothek.ui.RegisterActivity;

public class LoginActivity extends AbstractAuthenticationActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText emailView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;
    private LoginContract.UserActionsListener userActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userActionListener = new LoginPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the login form.
        emailView = (EditText) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.password);

        emailView.setText("m@hsr.ch");
        passwordView.setText("12345");

        SharedPreferences preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String email = preferences.getString(EMAIL, null);
        String password = preferences.getString(PASSWORD, null);
        emailView.setText(email);
        passwordView.setText(password);

        findViewById(R.id.signInButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();
                userActionListener.startLogin(email, password);
            }
        });

        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
    }

    protected void loginSucceeded(boolean isAutoLogin) {
        startMainActivity(isAutoLogin);
    }

    protected void loginFailed(String message) {
        showProgress(loginFormView, progressView, false);
        showInDismissableSnackbar(loginFormView, message);
    }

    public void onRegisterButton(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_to_left, R.anim.slide_out_right_to_left);
    }

    @Override
    public void proceedWithLogin() {
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        hideSoftKeyboard(loginFormView);
        showProgress(loginFormView, progressView, true);
        login(email, password, false);
    }

    @Override
    public void addErrorForEmail() {
        passwordView.setError(getString(R.string.error_field_required));
        passwordView.requestFocus();
    }

    @Override
    public void addErrorForPassword() {
        passwordView.setError(getString(R.string.error_field_required));
        passwordView.requestFocus();
    }

    @Override
    public void resetErrors() {
        emailView.setError(null);
        passwordView.setError(null);
    }
}
