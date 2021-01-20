package com.example.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomScrollListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager linearLayoutManager;
    private final Rect rect1;
    private final Rect rect2;
    private int percentVisibleFirstItem;
    private int percentVisibleLastItem;
    private int spaceLast;
    private float centerY;
    private Context mContext;

    public CustomScrollListener(LinearLayoutManager linearLayoutManager, Context context) {
        this.linearLayoutManager = linearLayoutManager;
        mContext = context;
        rect1 = new Rect();
        rect2 = new Rect();
        centerY = (float)context.getResources().getDisplayMetrics().heightPixels/2;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        if (firstVisibleItem+1<lastVisibleItem){
            firstVisibleItem++;
        }
        linearLayoutManager.findViewByPosition(firstVisibleItem).getGlobalVisibleRect(rect1);
        linearLayoutManager.findViewByPosition(lastVisibleItem).getGlobalVisibleRect(rect2);
        //
//        Log.d("thinhvh", "onScrolled: "+rect1 + " ------ react2 = "+rect2);
        Log.e("thinhvh", "onScrolled: " + checkItemVisible(firstVisibleItem, lastVisibleItem, rect1, rect2));
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    private int checkItemVisible(int firstVisibleItem, int lastVisibleItem,
                                 Rect rectFirst, Rect rectLast) {
        if (firstVisibleItem != -1 && lastVisibleItem != -1) {
//            if (rectFirst.bottom > rectFirst.top) {
//                int spaceFirst = rectFirst.bottom - rectFirst.top;
//                percentVisibleFirstItem = (spaceFirst * 100) / linearLayoutManager
//                        .findViewByPosition(firstVisibleItem).getHeight();
//            }
//            if (rectLast.bottom > rectLast.top) {
//                spaceLast = rectLast.bottom - rectLast.top;
//                percentVisibleLastItem = (spaceLast * 100) / linearLayoutManager
//                        .findViewByPosition(lastVisibleItem).getHeight();
//            }



            int centerPos1 = rectFirst.bottom - rectFirst.top;
            int centerPos2 = rectLast.bottom - rectLast.top;
            Log.d("thinhvh", "centerY = "+centerY );
            Log.d("thinhvh", "centerPos1 : "+centerPos1 +" __centerPos2 = "+centerPos2 );
            if (centerPos1-centerY>=centerPos2-centerY){
                return firstVisibleItem;
            }else return  lastVisibleItem;

//            if (percentVisibleFirstItem > percentVisibleLastItem){
//                return firstVisibleItem;
//            } else {
//                return lastVisibleItem;
//            }
        }
        return -1;
    }

}
