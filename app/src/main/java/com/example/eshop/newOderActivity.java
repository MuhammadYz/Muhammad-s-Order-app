package com.example.eshop;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class newOderActivity extends AppCompatActivity {
    EditText buyerName;
    EditText itemName;
    EditText quantitys;
    EditText amountInput;
    EditText comments;
    private TextView dates;
    EditText phoneContacts;
    EditText emails;
    EditText locations;
    private String modeOfPayment;
    DatePicker mDatePickerDialog;


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
    public void onCreateDialog(){

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int months=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        View view= LayoutInflater.from(this).inflate(R.layout.date_dialog,null);
        mDatePickerDialog = view.findViewById(R.id.datePicker);
        mDatePickerDialog.init(year,months,day,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Date")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year=mDatePickerDialog.getYear();
                        int months=mDatePickerDialog.getMonth();
                        int day=mDatePickerDialog.getDayOfMonth();
                        months=months++;

                        String date=String.valueOf(day+"/"+months+"/"+year);
                        dates.setText(date);
                    }
                });
        Dialog dialog=builder.create();
        dialog.show();
    }
    public void viewDatePicker(View view){
        onCreateDialog();
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
        else {
            Toast.makeText(this,"Fill all the fields please",Toast.LENGTH_SHORT).show();
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
