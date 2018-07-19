package com.example.eshop;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class newOderActivity extends AppCompatActivity {
    EditText buyerName;
    EditText itemName;
    EditText quantitys;
    EditText amountInput;
    EditText comments;
    private EditText dates;
    EditText phoneContacts;
    EditText emails;
    EditText locations;
    private String modeOfPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        buyerName=findViewById(R.id.etnameOfBuyer);
        itemName=findViewById(R.id.etItem);
        quantitys =findViewById(R.id.etQuantity);
        amountInput=findViewById(R.id.etAmount);
        comments =findViewById(R.id.etComment);
        dates =findViewById(R.id.etDate);
        phoneContacts =findViewById(R.id.etPhoneContact);
        emails =findViewById(R.id.etemail);
        locations =findViewById(R.id.etLocation);



    }
    public void saveOrderDetails(View makeOrderButton){
        String nameOfBuyer=buyerName.getText().toString();
        String item=itemName.getText().toString();
        String amount=amountInput.getText().toString();
        String quantity=quantitys.getText().toString();
        String location=locations.getText().toString();
        String date=dates.getText().toString();
        String email=emails.getText().toString();
        String phoneContact=phoneContacts.getText().toString();
        String comment=comments.getText().toString();
        RadioButton creditcard= findViewById(R.id.rdbCreditCard);
        RadioButton mobilemoney= findViewById(R.id.rdbMobileMoney);


        if(!nameOfBuyer.isEmpty()&&!item.isEmpty()&&!amount.isEmpty()&&!quantity.isEmpty()
                &&!location.isEmpty()&&!date.isEmpty()&& !email.isEmpty()
                &&!phoneContact.isEmpty() &&!comment.isEmpty()&&
                creditcard.isChecked()||mobilemoney.isChecked()) {

            Order neworder=new Order(nameOfBuyer, item, quantity,  amount,
                     modeOfPayment,  phoneContact,  email,  date,  location, comment);


                myDatabase().appDao().insertNewOrder(neworder);
            Toast.makeText(this,"You have successfully made your order i.e "+neworder.getItemName(),Toast.LENGTH_SHORT).show();
            Intent openMainActivity= new Intent(this,EshopMainActivity.class);
            startActivity(openMainActivity);

        }

    }
    public void getPaymentMode(View rdb){
        int viewid=rdb.getId();
        if(viewid==R.id.rdbCreditCard){
            modeOfPayment="Credit Card";
            Toast.makeText(this,"Via Credit card",Toast.LENGTH_SHORT).show();

        }
        else if(viewid==R.id.rdbMobileMoney){
            modeOfPayment="Mobile money";
            Toast.makeText(this,"Via Mobile money",Toast.LENGTH_SHORT).show();

        }


    }

    public AppDataBase myDatabase(){
        String DbName="room_db";
        AppDataBase appDataBase= Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,DbName).allowMainThreadQueries().build();
        return appDataBase;
    }
}
