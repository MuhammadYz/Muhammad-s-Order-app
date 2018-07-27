package com.example.eshop;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class EshopMainActivity extends AppCompatActivity implements AppAdapter.orderClickListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private RecyclerView appRecyclerView;
    private AppAdapter appAdapter;
    private CoordinatorLayout coordinatorLayout;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshop_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tv code to view our list in the this activity from the database
     orderList=myDatabase().appDao().selectAllOrders();
        coordinatorLayout=findViewById(R.id.coordinatorLayout);
        appRecyclerView=findViewById(R.id.myRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        appAdapter=new AppAdapter(this,this);
        appRecyclerView.setAdapter(appAdapter);
        appRecyclerView.setHasFixedSize(true);
        appRecyclerView.setLayoutManager(linearLayoutManager);
        appRecyclerView.setItemAnimator(new DefaultItemAnimator());
        appRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        ItemTouchHelper.SimpleCallback itemTouchhelper=new RecyclerItemTouchHelper(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,this);
        new ItemTouchHelper(itemTouchhelper).attachToRecyclerView(appRecyclerView);

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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Order order=orderList.get(position);
        if(viewHolder instanceof AppAdapter.AppViewHolder){
            String name=orderList.get(viewHolder.getAdapterPosition()).getItemName();

            //backup removed item
            final Order deletedOrder=orderList.get(viewHolder.getAdapterPosition());
            final int deletedIndex=viewHolder.getAdapterPosition();

            //remove Item From Recyclerview
            appAdapter.removeItem(viewHolder.getAdapterPosition());

            //Show confirmnation dialogue
            final AlertDialog.Builder builder;

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            {
                builder=new AlertDialog.Builder(this,android.R.style.Theme_Material_Dialog_Alert);
            }
            else {
                builder=new AlertDialog.Builder(this);
            }
            builder.setTitle("Delete Order")
                    .setMessage("Are you sure you want to permanently delete this order?")
                    .setCancelable(true)
                    .setNegativeButton("Undo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            appAdapter.restoreItem(deletedOrder,deletedIndex);
                            dialogInterface.cancel();
                        }
                    }).setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    myDatabase().appDao().deleteOder(order);
                    dialogInterface.cancel();
                }
            }).setIcon(android.R.drawable.ic_delete);
            AlertDialog alertDialog=builder.create();
            builder.show();







        }

    }

}
