package com.example.myapplication;

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

    public CustomScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
        rect1 = new Rect();
        rect2 = new Rect();
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        linearLayoutManager.findViewByPosition(firstVisibleItem).getGlobalVisibleRect(rect1);
        linearLayoutManager.findViewByPosition(lastVisibleItem).getGlobalVisibleRect(rect2);
        Log.e("namtd1998", "onScrolled: " +
                checkItemVisible(firstVisibleItem, lastVisibleItem, rect1, rect2));
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    private int checkItemVisible(int firstVisibleItem, int lastVisibleItem,
                                 Rect rectFirst, Rect rectLast) {
        if (firstVisibleItem != -1 && lastVisibleItem != -1) {
            if (rectFirst.bottom > rectFirst.top) {
                int spaceFirst = rectFirst.bottom - rectFirst.top;
                percentVisibleFirstItem = (spaceFirst * 100) / linearLayoutManager
                        .findViewByPosition(firstVisibleItem).getHeight();
            }
            if (rectLast.bottom > rectLast.top) {
                spaceLast = rectLast.bottom - rectLast.top;
                percentVisibleLastItem = (spaceLast * 100) / linearLayoutManager
                        .findViewByPosition(lastVisibleItem).getHeight();
            }
            if (percentVisibleFirstItem > percentVisibleLastItem){
                return firstVisibleItem;
            } else {
                return lastVisibleItem;
            }
        }
        return -1;
    }

}
