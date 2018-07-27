package com.example.eshop;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {


    public RecyclerItemTouchHelperListener listener;




    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener=listener;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }
    @Override
public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,int actionState){
        if(viewHolder!=null){
             View foregroundView=((AppAdapter.AppViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }


}
@Override
public void onChildDrawOver(Canvas c,RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder, float dx,
                            float dy, int actionState, boolean isCurrelentlyActive){
        View foreground=((AppAdapter.AppViewHolder) viewHolder).viewForeground;
    getDefaultUIUtil().onDrawOver(c, recyclerView, foreground, dx, dy, actionState, isCurrelentlyActive);
}

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
      View foreground=((AppAdapter.AppViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foreground);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());


    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener{
        void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);

    }

}
