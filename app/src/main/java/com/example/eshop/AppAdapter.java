package com.example.eshop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    private List<Order> orderList;
    private Context mcontext;

    public AppAdapter(Context mcontext) {
        this.mcontext = mcontext;
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
    public class AppViewHolder extends RecyclerView.ViewHolder{
        TextView item;
        TextView amount;
        TextView dates;
        TextView comment;

        public AppViewHolder(View itemView) {
            super(itemView);
            item=(itemView).findViewById(R.id.orderLIstitem);
          amount=(itemView).findViewById(R.id.orderlistamount);
            dates=(itemView).findViewById(R.id.orderlstdate);
            comment=(itemView).findViewById(R.id.orderlistcomment);
        }
    }
    public List<Order> getExpenditureList() {
        return orderList;
    }
    public void setOrderListData(List<Order> orderListData){
        orderList=orderListData;
        notifyDataSetChanged();
    }

}


