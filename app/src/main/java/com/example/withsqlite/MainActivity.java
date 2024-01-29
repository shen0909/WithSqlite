package com.example.withsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,phone,passwaord;
    Button add,delete;
    ArrayList<UserBean> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycleView();
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        name = findViewById(R.id.editTextText);
        phone = findViewById(R.id.editTextPhone);
        passwaord = findViewById(R.id.editTextTextPassword);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean = new UserBean(name.getText().toString(),passwaord.getText().toString(),phone.getText().toString());
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(userBean);
                if(success){
                    Toast.makeText(MainActivity.this,userBean.getName()+userBean.getPhone()+userBean.getPassWord(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"添加失败",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycleView11);

        items.add(new UserBean("杨滨溶","1223434","1223444"));
        items.add(new UserBean("沈成林","1223434","1223444"));
        items.add(new UserBean("MuYi","1223434","1223444"));
        items.add(new UserBean("胡现需","1223434","1223444"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerViewAdapter adapter = new recyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    // 创建数据库
}