package com.example.rabbit.Users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.rabbit.Dao.MyDao;
import com.example.rabbit.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername;
    private EditText etPasswordSet;
    private EditText etPasswordComfirm;
    private Button btnConfirm;
    String name=null;
    String password=null;
    String password_confirm=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPasswordSet = (EditText) findViewById(R.id.et_password_set);
        etPasswordComfirm = (EditText) findViewById(R.id.et_password_comfirm);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                if(isEmptyForText()){
                    if(isSamePassword()&isPhoneOrEmail(etUsername.getText().toString().trim())){
                        //将注册信息存入数据库
                        MyDao dao=new MyDao(this);
                        dao.add_user(name,password);
                        Toast.makeText(this,"등록 성공",Toast.LENGTH_LONG).show();
                        //注册成功，返回登录页面
                        startActivity(new Intent(this,LoginActivity.class));
                    }
                }
                break;
        }
    }

    //判断输入内容是否为空
    public boolean isEmptyForText(){
        name=etUsername.getText().toString().trim();
        password=etPasswordSet.getText().toString().trim();
        password_confirm=etPasswordComfirm.getText().toString().trim();
        if(!name.isEmpty()&&!password.isEmpty()&&!password_confirm.isEmpty()){
            return true;

        }
        Toast.makeText(this,"입력이 비어 있습니다.",Toast.LENGTH_LONG).show();
        return false;
    }
    public boolean isPhoneOrEmail(String str){
        if(isEmail(str)){
            return true;

        }else {
            Toast.makeText(this,"입력하는 사용자 이름은 휴대폰 번호 또는 이메일 주소여야 합니다.",Toast.LENGTH_LONG).show();
            return false;

        }

    }
    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }



    public boolean isSamePassword(){
        password=etPasswordSet.getText().toString().trim();
        password_confirm=etPasswordComfirm.getText().toString().trim();
        if(password.equals(password_confirm)){
            return true;
        }
        return false;
    }
}