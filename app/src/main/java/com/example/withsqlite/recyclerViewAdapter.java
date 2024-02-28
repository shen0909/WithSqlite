package com.example.withsqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
    ArrayList<UserBean> items;
    Context context;

    public recyclerViewAdapter(ArrayList<UserBean> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public recyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.phone.setText(items.get(position).getPhone());
        holder.password.setText(items.get(position).getPassWord());
        int i = position;

        // 单击列表项将对象传递到下一个页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EDoperationActivity.class);
                UserBean userBean = items.get(position);
                intent.putExtra("withData", userBean);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                boolean success = dataBaseHelper.deleteOne(items.get(i));
                if (success) {
                    items.remove(i); // 删除成功后从列表中移除
                    notifyDataSetChanged();
                    items = dataBaseHelper.getList();
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
                return success;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, password;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            password = itemView.findViewById(R.id.passWord);
        }
    }
}
