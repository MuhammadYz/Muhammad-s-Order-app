package com.example.eshop;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class EshopMainActivity extends AppCompatActivity implements AppAdapter.orderClickListener {
    private RecyclerView appRecyclerView;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshop_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tv code to view our list in the this activity from the database

        appRecyclerView=findViewById(R.id.myRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        appAdapter=new AppAdapter(this,this);
        appRecyclerView.setAdapter(appAdapter);
        appRecyclerView.setHasFixedSize(true);
        appRecyclerView.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent openNewOrderActivity=new Intent(EshopMainActivity.this,newOderActivity.class);
                startActivity(openNewOrderActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eshop_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
        List<Order> orderList=myDatabase().appDao().selectAllOrders();
        appAdapter.setOrderListData(orderList);
    }
    public AppDataBase myDatabase(){
        String DbName="room_db";
        AppDataBase appDataBase= Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,DbName).allowMainThreadQueries().build();
        return appDataBase;
    }

    @Override
    public void onItemClick(int id) {
        Intent startDetailsActivity=new Intent(this,DetailsActivity.class);
        startDetailsActivity.putExtra(Intent.EXTRA_TEXT,id);
        startActivity(startDetailsActivity);

    }
}
