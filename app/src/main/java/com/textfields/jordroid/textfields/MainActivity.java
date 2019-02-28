package com.textfields.jordroid.textfields;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    /***********************************************************
     *  Attributes
     **********************************************************/

    private TextInputLayout login_til_email;
    private TextInputLayout login_til_password;

    private TextInputEditText login_tiet_email;
    private TextInputEditText login_tiet_password;

    private MaterialButton login_btn_connection;
    private Drawable validate;
    private Drawable error;

    /***********************************************************
     *  Managing LifeCycle
     **********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validate = ContextCompat.getDrawable(this, R.drawable.ic_login_validate);
        error = ContextCompat.getDrawable(this, R.drawable.ic_login_error);

        validate.setBounds(0, 0, 48, 48);
        error.setBounds(0, 0, 48, 48);

        findById();
        listener();
    }

    private void findById() {
        login_til_email = findViewById(R.id.login_til_email);
        login_til_password = findViewById(R.id.login_til_password);

        login_tiet_email = findViewById(R.id.login_tiet_email);
        login_tiet_password = findViewById(R.id.login_tiet_password);

        login_btn_connection = findViewById(R.id.login_btn_connection);
    }

    private void listener() {
        login_btn_connection.setOnClickListener(v -> {
            if(verifyUserInput()) {
                // TODO do something
            }
        });
    }

    /***********************************************************
     *  Private Methods
     **********************************************************/

    /**
     * Check all input from user
     *
     * @return true if all is correct
     */
    private boolean verifyUserInput() {

        if (login_tiet_email.getText() != null && !Patterns.EMAIL_ADDRESS.matcher(login_tiet_email.getText().toString()).matches()) {
            login_tiet_email.setCompoundDrawables(null, null, error, null);
            login_til_email.setBoxStrokeColor(ContextCompat.getColor(this, R.color.warningColor));
            login_til_email.setErrorEnabled(true);
            login_til_email.setError("Email format is not good");
            return false;
        } else {
            login_tiet_email.setCompoundDrawables(null, null, validate, null);
            login_til_email.setBoxStrokeColor(ContextCompat.getColor(this, R.color.colorPrimary));
            login_til_email.setErrorEnabled(false);
        }

        if (login_tiet_password.getText() != null && login_tiet_password.getText().length() < 8) {
            login_til_password.setBoxStrokeColor(ContextCompat.getColor(this, R.color.warningColor));
            login_til_password.setErrorEnabled(true);
            login_til_password.setError("Incorrect password format");
            return false;
        } else {
            login_til_password.setBoxStrokeColor(ContextCompat.getColor(this, R.color.colorPrimary));
            login_til_password.setErrorEnabled(false);
        }

        return true;
    }
}
