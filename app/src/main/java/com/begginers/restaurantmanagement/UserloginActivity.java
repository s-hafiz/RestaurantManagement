package com.begginers.restaurantmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserloginActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText et_email;
    private EditText et_password;
    private Button btnLogin;
    private Button btnLinkToRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private Toolbar toolbar_L;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        initView();


        btnLinkToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void initView()
    {
        et_email= (EditText) findViewById(R.id.email);
        et_password= (EditText) findViewById(R.id.password);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister= (Button) findViewById(R.id.btnLinkToRegister);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize login toolbar
        toolbar_L = (Toolbar) findViewById(R.id.toobar_L);
        setSupportActionBar(toolbar_L);
    }



    @Override
    public void onClick(View v)
    {
        if (v==btnLinkToRegister)
        {
            finish();
            startActivity(new Intent(this,UserregActivity.class));
        }
        if (v==btnLogin)
        {
            userLogin();
        }
    }

    private void userLogin()
    {
        String email = et_email.getText().toString().trim();
        String pass = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please enter pass", Toast.LENGTH_SHORT).show();

            return;
        }

        progressDialog.setMessage("Please wait a sec...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(UserloginActivity.this,ProfileActivity.class));
                        }
                        else 
                        {
                            Toast.makeText(UserloginActivity.this, "Enter currect email and password", Toast.LENGTH_SHORT).show();
                            et_password.setText("");
                            et_email.setText("");
                            et_email.requestFocus();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
