package com.begginers.restaurantmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.begginers.restaurantmanagement.CustomOrder;
import com.begginers.restaurantmanagement.R;
import com.begginers.restaurantmanagement.javaClass.FoodItem;

import java.util.ArrayList;

/**
 * Created by shafiz on 5/10/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    private ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
    private Context ctx;
    private String show = "";
    private Intent intent=null;

    public FoodAdapter(ArrayList<FoodItem> foodItems,Context ctx)
    {
        this.foodItems = foodItems;
        this.ctx = ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);

        //Creating view holder object
        ViewHolder viewHolder = new ViewHolder(view,ctx);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        FoodItem foodItem = foodItems.get(position);
        holder.imageView.setImageResource(foodItem.getImg_id());
        holder.tx_name.setText(foodItem.getName());
        holder.tx_price.setText("Price : "+foodItem.getPrice()+" tk");
    }

    @Override
    public int getItemCount()
    {
        return foodItems.size();
    }


    //Implementing click listener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView tx_name;
        TextView tx_price;
        Context ctx;

        //Holding user input of unit
        private String unit_select;
        FoodItem foodItem;

        public ViewHolder(View itemView,Context ctx)
        {
            super(itemView);
            //Initializing context
            this.ctx = ctx;

            itemView.setClickable(true);
            intent = new Intent(ctx, CustomOrder.class);

            //Registering click listener
            itemView.setOnClickListener(this);

            imageView = (ImageView) itemView.findViewById(R.id.imgView);
            tx_name = (TextView) itemView.findViewById(R.id.tx_name);
            tx_price= (TextView) itemView.findViewById(R.id.tx_price);
        }

        @Override
        public void onClick(View v) {
            /*final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(tx_name.getText().toString().trim()+"\n"+tx_price.getText().toString().trim());
            //Creating Linear layout
            final LinearLayout layout = new LinearLayout(ctx);

            //Setting orientation
            layout.setOrientation(LinearLayout.VERTICAL);

            //Setting parameters
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );
            //Setting margin on left and right

            //params.setMargins(320,0,320,0);

            //Creating edit text
            final EditText input = new EditText(ctx);
            input.setHint("Enter No. of Unit");

            //Setting text in the center
            input.setGravity(Gravity.CENTER);
            input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);


            layout.addView(input,params);
            //int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, v.getResources().getDisplayMetrics());

            builder.setCancelable(false);
            builder.setView(layout);

            //Setting positive buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Getting item position
                    int position = getAdapterPosition();
                    int unit=0;

                    //Creating Food item object of perticular position
                    foodItem = foodItems.get(position);

                    //Getting user order
                    unit_select = input.getText().toString();

                    //Checking if it is a empty or number
                    if (unit_select.equals(""))
                    {
                        unit_select="0";
                    }

                    //Converting string to integer
                    try
                    {
                         unit = Integer.parseInt(unit_select);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ctx, "Invalid Input! Please Enter Only Number.", Toast.LENGTH_SHORT).show();

                    }
                    int price = Integer.parseInt(foodItem.getPrice());

                    //Calculating total price
                    int total_price = unit * price;

                    //Checking condition if total price is 0 then toast a message
                    if (total_price==0)
                    {
                        Toast.makeText(ctx, "No orders", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        show = show + "Name : "+tx_name.getText().toString().trim()+" "+unit+" x "+price+" = "+total_price;
                        intent.putExtra("SHOW",show);
                        //Toast.makeText(ctx, "Total Price : "+total_price+" Taka\nAdding to cart.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Setting cancel button
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(ctx, "You cancel order. So nothing to add!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();*/
            int pos = getAdapterPosition();
            intent.putExtra("image",foodItems.get(pos).getImg_id());
            intent.putExtra("title",foodItems.get(pos).getName());
            intent.putExtra("price",foodItems.get(pos).getPrice());
            ctx.startActivity(intent);
        }
    }

    public void setFilter(ArrayList<FoodItem> newList)
    {
        foodItems = new ArrayList<>();
        foodItems.addAll(newList);
        notifyDataSetChanged();
    }
}
