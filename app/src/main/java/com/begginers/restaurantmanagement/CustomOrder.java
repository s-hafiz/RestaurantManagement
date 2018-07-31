package com.begginers.restaurantmanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class CustomOrder extends AppCompatActivity implements View.OnClickListener{
    private ImageView foodImg;
    private TextView foodTitle;
    private TextView foodPrice;
    private TextView custom_unit;
    private Button customUnit,addToCart;
    private Bundle bundle;
    String value=null;
    int money;
    private String formatCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initView();
        bundle = getIntent().getExtras();
        if (bundle !=null)
        {
            foodImg.setImageResource(bundle.getInt("image"));
            foodTitle.setText(bundle.getString("title"));
            calCurrency();
            foodPrice.setText(formatCurrency);
        }

        customUnit.setOnClickListener(this);
    }

    private void calCurrency()
    {
        value=bundle.getString("price");
        money=Integer.parseInt(value);
        Locale locale = new Locale("en","BD");
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(locale);
        formatCurrency = numberFormatter.format(money);
    }

    private void initView()
    {
        foodImg = (ImageView) findViewById(R.id.img_view);
        foodTitle= (TextView) findViewById(R.id.title_tv);
        foodPrice = (TextView) findViewById(R.id.price_tv);
        custom_unit= (TextView) findViewById(R.id.default_unit_tv);
        customUnit= (Button) findViewById(R.id.btn_cus_unit);
        addToCart= (Button) findViewById(R.id.btn_add_to_cart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_cus_unit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter The Number Of Unit");
                final LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );

                //Creating new edit text
                final EditText unit = new EditText(this);
                unit.setHint("No of unit");
                unit.setGravity(Gravity.CENTER);
                linearLayout.addView(unit,params);

                builder.setCancelable(false);
                builder.setView(linearLayout);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (unit.getText().toString().isEmpty())
                        {
                            Toast.makeText(CustomOrder.this, "No unit is set", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String unit_value=unit.getText().toString();
                            custom_unit.setText("No. Of Unit : "+unit_value);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(CustomOrder.this, "Default unit would be fixed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
        }
    }
}
