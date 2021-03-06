package com.example.eshop;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

public class DetailsActivity extends AppCompatActivity {
TextView itemDisplay;
TextView nameDisplay;
TextView locationDisplay;
TextView commentDisplay;
TextView emailtDisplay;
TextView modeOfDisplayDisplay;
TextView phoneContactDisplay;
TextView dateDisplay;
TextView quantityDisplay;
TextView amountDisplay;

int itemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameDisplay=findViewById(R.id.nameDisplay);
        phoneContactDisplay=findViewById(R.id.phoneContactDisplay);
        emailtDisplay=findViewById(R.id.emailDisplay);
        dateDisplay=findViewById(R.id.dateDisplay);
        commentDisplay=findViewById(R.id.commentDisplay);
        modeOfDisplayDisplay=findViewById(R.id.modeOfpaymentDisplay);
        locationDisplay=findViewById(R.id.locationDisplay);
        quantityDisplay=findViewById(R.id.quantityDisplay);
        amountDisplay=findViewById(R.id.amountDisplay);
        itemDisplay=findViewById(R.id.itemDisplay);

        Intent intent=getIntent();

        if(intent!=null&& intent.hasExtra(intent.EXTRA_TEXT)){
            itemId=intent.getIntExtra(intent.EXTRA_TEXT,-1);
            Order order=myDatabase().appDao().getID(itemId);
            UpdateDetailsInterface(order);
        }

    }
    public void UpdateDetailsInterface(Order order){
        nameDisplay.setText(order.getNameOfBuyer());
        phoneContactDisplay.setText(order.getPhoneContact());
        commentDisplay.setText(order.getComment());
        modeOfDisplayDisplay.setText(order.getModeOfPayment());
        locationDisplay.setText(order.getLocation());
        itemDisplay.setText(order.getItemName());
        amountDisplay.setText(order.getAmount());
        quantityDisplay.setText(order.getQuantity());
        dateDisplay.setText(order.getDate());


    }
    public AppDataBase myDatabase(){
        String DbName="room_db";
        AppDataBase appDataBase= Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,DbName).allowMainThreadQueries().build();
        return appDataBase;
    }
}
