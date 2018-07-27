package com.example.eshop;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    private List<Order> orderList;
    private Context mcontext;
    private orderClickListener ListClickListener;

    Order neworder;

    public AppDataBase myDatabase(){
        String DbName="room_db";
        AppDataBase appDataBase= Room.databaseBuilder(mcontext,
                AppDataBase.class,DbName).allowMainThreadQueries().build();
        return appDataBase;
    }



    public AppAdapter(Context mcontext, orderClickListener listClickListener) {
        this.mcontext = mcontext;
        ListClickListener = listClickListener;
    }

    @Override
    public AppAdapter.AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.order_list,parent,false);

        return new AppViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AppAdapter.AppViewHolder holder, int position) {
        Order order=orderList.get(position);
        String item=order.getItemName();
        String amount=order.getAmount();
        String comment=order.getComment();
        String date=order.getDate();

        holder.comment.setText(comment);
        holder.amount.setText(amount);
        holder.dates.setText(date);
        holder.item.setText(item);



    }



    @Override
    public int getItemCount() {
        if(orderList==null){return 0;}
        return orderList.size();

    }
    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item;
        TextView amount;
        TextView dates;
        TextView comment;
        public RelativeLayout viewForeground,viewBackground;


        public AppViewHolder(View itemView) {
            super(itemView);
            item=(itemView).findViewById(R.id.orderLIstitem);
          amount=(itemView).findViewById(R.id.orderlistamount);
            dates=(itemView).findViewById(R.id.orderlstdate);
            comment=(itemView).findViewById(R.id.orderlistcomment);

            viewForeground=(itemView).findViewById(R.id.view_foreground);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id=orderList.get(getAdapterPosition()).getId();
            ListClickListener.onItemClick(id);

        }
    }
    public interface orderClickListener{
        void onItemClick(int id);

    }
    public List<Order> getExpenditureList() {
        return orderList;
    }
    public void setOrderListData(List<Order> orderListData){
        orderList=orderListData;
        notifyDataSetChanged();
    }
    public void removeItem(int position){

        orderList.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(Order order, int position){
        orderList.add(position,order);
        myDatabase().appDao().updateDatabase(order);
        notifyItemInserted(position);
    }

}


