package com.example.withsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 修改和删除页面
public class EDoperationActivity extends AppCompatActivity {
    TextView nameTxt,phoneTxt,pwdTxt;
    Button change,delete;
    UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edoperation);
        userBean = (UserBean) getIntent().getSerializableExtra("withData");
        nameTxt = findViewById(R.id.nameTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        pwdTxt = findViewById(R.id.pwdTxt);
        change = findViewById(R.id.change);
        delete = findViewById(R.id.delete);

        nameTxt.setText(userBean.getName());
        phoneTxt.setText(userBean.getPhone());
        pwdTxt.setText(userBean.getPassWord());
        int id = userBean.getId();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean1 = new UserBean(nameTxt.getText().toString(),pwdTxt.getText().toString(),phoneTxt.getText().toString());
                userBean1.setId(id);
                boolean success = dataBaseHelper.updataInfo(userBean1);
                if (success) {
                    Toast.makeText(EDoperationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    userBean = userBean1;
                } else {
                    Toast.makeText(EDoperationActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = dataBaseHelper.deleteOne(userBean);
                if (success) {
                    Toast.makeText(EDoperationActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EDoperationActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(EDoperationActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}