package com.example.eshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
