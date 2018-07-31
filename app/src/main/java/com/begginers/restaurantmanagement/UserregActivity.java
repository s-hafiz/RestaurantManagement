package com.begginers.restaurantmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserregActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText editTextEmail;
    private EditText editTextPass;
    private Button btnRegister;
    private Button btnLinkToLogin;

    private String email;
    private String pass;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);

        initView();

        //Setting listener object
        btnRegister.setOnClickListener(this);
        btnLinkToLogin.setOnClickListener(this);
    }

    private void initView() {
        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPass = (EditText) findViewById(R.id.password);
        btnRegister  = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLogin);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        if (v==btnRegister)
        {
            userReg();
        }
        if (v==btnLinkToLogin)
        {
            finish();
            startActivity(new Intent(this,UserloginActivity.class));
        }
    }

    private void userReg()
    {
        email=editTextEmail.getText().toString().trim();
        pass =editTextPass.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //Stop further execution
            return;
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //Stop further execution
            return;
        }

        progressDialog.setMessage("Registering...wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            Toast.makeText(UserregActivity.this, "Successfully Registered !", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(UserregActivity.this,ProfileActivity.class));
                        }
                        else 
                        {
                            Toast.makeText(UserregActivity.this, "Could not register...please try again", Toast.LENGTH_SHORT).show();
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
