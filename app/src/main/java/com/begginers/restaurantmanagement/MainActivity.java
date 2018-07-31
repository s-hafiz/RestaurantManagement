package com.begginers.restaurantmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnUserlogin;
    private Button btnManagerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        btnUserlogin.setOnClickListener(this);
        btnManagerlogin.setOnClickListener(this);
    }

    private void initView() {
        btnUserlogin= (Button) findViewById(R.id.btnUsrlogin);
        btnManagerlogin= (Button) findViewById(R.id.btnManagerlogin);
    }

    @Override
    public void onClick(View v) {
        if (v==btnUserlogin)
        {
            finish();
            startActivity(new Intent(this,UserloginActivity.class));
        }
        if (v==btnManagerlogin)
        {
            finish();
            startActivity(new Intent(this,ManagerloginActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit")
                .setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}
