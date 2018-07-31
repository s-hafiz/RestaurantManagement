package com.begginers.restaurantmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.begginers.restaurantmanagement.adapter.FoodAdapter;
import com.begginers.restaurantmanagement.javaClass.FoodItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private FoodAdapter foodAdapter;

    private Toolbar toolbar;

    //Creating int image array
    private int[] imgId = {R.drawable.cheese_sandwich,R.drawable.cheeseburger_new,R.drawable.veggie_burger_new,R.drawable.garlic_bread_cheese_new
                            ,R.drawable.grilled_sandwich_new,R.drawable.masala_french_fries_new,R.drawable.penny_pasta
                            ,R.drawable.vegetable_sandwich_new};

    //Creating name and price array
    private String[] name;
    private String[] price;

    //Creating Arraylist of Food item
    ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Initialize all views and variable
        initView();

        //Checking is there any current user or not
        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,UserloginActivity.class));
        }


        //setting the variable values into view's card vies
        int i = 0;
        for (String newname : name)
        {
            FoodItem foodItem = new FoodItem(imgId[i],newname,price[i]);
            foodItems.add(foodItem);
            i++;
        }

        //setting layout manager and adapter to recycler view

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(foodAdapter);

    }

    private void initView()
    {
        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();

        //Initialize recycler view , adapter and layout manager and all the variable related to recycler view
        name = getResources().getStringArray(R.array.name);
        price= getResources().getStringArray(R.array.price);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        foodAdapter= new FoodAdapter(foodItems,this);
        gridLayoutManager = new GridLayoutManager(this,2);

        //Setting toolbar
        toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(toolbar);
    }

    //Confirm user that if he really want to quit
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        //Setting listener
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<FoodItem> newList = new ArrayList<>();
        for(FoodItem foodItem : foodItems)
        {
            String name = foodItem.getName().toLowerCase();
            String price = foodItem.getPrice();
            if (name.contains(newText))
            {
                newList.add(foodItem);
            }
            else if (price.contains(newText))
            {
                newList.add(foodItem);
            }
        }
        foodAdapter.setFilter(newList);
        return true;
    }

    /*  Bellow code will use for sign out

    firebaseAuth.signOut();
    finish();
    startActivity(new Intent(this,UserloginActivity.class));

    */
}
