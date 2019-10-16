package com.rhmt.testandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rhmt.testandroid.DetailActivity;
import com.rhmt.testandroid.R;
import com.rhmt.testandroid.model.ListUser;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.mainViewHolder> {

    private ArrayList<ListUser> mDataUsers = new ArrayList<>();
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setmDataUsers(ArrayList<ListUser> items) {
        mDataUsers.clear();
        mDataUsers.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent , false);
        return new mainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mainViewHolder holder, int position) {
        holder.bind(mDataUsers.get(position));
        holder.itemView.setOnClickListener(new ClickAdapter(position, new ClickAdapter.onCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.Extra_id, mDataUsers.get(position).getId());
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mDataUsers.size();
    }

    class mainViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
         mainViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_ListId);
        }
        void bind(final ListUser listUser){
            tvId.setText("User Id : " + listUser.getId());
        }
    }
}
