package com.example.jocelinthomas.loginfirebase;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText forgotpwdemail;
    Button back, reset;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        auth = FirebaseAuth.getInstance();

        forgotpwdemail = (EditText) findViewById(R.id.forgotpwdemail);
        back = (Button) findViewById(R.id.back);
        reset = (Button) findViewById(R.id.reset);

        back.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == reset) {
            resetpwd();
            //startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void resetpwd() {
        //getting email and password from edit texts
        String email = forgotpwdemail.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }


       /* if (isValidEmail(email)) {
            Toast.makeText(ForgotPasswordActivity.this, "Email is valid", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(ForgotPasswordActivity.this, "Email is invalid", Toast.LENGTH_LONG).show();
        }*/

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                } /*else {
                    Toast.makeText(ForgotPasswordActivity.this, "Failed,Please try again later", Toast.LENGTH_LONG).show();
                }*/
            }
        });


    }

   /* private boolean isEmailValid(String email) {
        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
        return matcher.matches();
    }*/


   /* public static boolean isValidEmail(String email) {
        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        CharSequence inputString = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
