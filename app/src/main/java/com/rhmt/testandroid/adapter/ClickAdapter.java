package com.rhmt.testandroid.adapter;

import android.view.View;

public class ClickAdapter implements View.OnClickListener {

    private int position;
    private onCallback onItemCallback;

    public ClickAdapter(int position,onCallback onItemCallback) {
        this.onItemCallback = onItemCallback;
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        onItemCallback.onItemClicked(view, position);
    }

    interface onCallback{
        void onItemClicked(View view, int position);
    }
}
