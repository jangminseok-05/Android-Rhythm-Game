package com.example.rabbit.Users;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.rabbit.BackActivity;
import com.example.rabbit.ChoiceMusicActivity;
import com.example.rabbit.Dao.MyDao;
import com.example.rabbit.MainActivity;
import com.example.rabbit.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnRegiste;
    private Button btnUp;
    private TextView textView;
    String name, password;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        requestPermissions();
    }

    private void requestPermissions() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.RECORD_AUDIO
                }, 0x0010);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegiste = (Button) findViewById(R.id.btn_registe);
        btnUp = (Button) findViewById(R.id.btn_up);
        btnRegiste.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, BackActivity.class));
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registe:
                //点击注册按钮，跳转进入注册页面
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_up:
                if (isEmptyForText()) {
                    MyDao dao = new MyDao(this);
                    Bundle bundle =new Bundle();
                    bundle.putString("username",etUsername.getText().toString().trim());

                    Log.i("TAG", "onClick: " + etUsername.getText().toString().trim() + "    " + etPassword.getText().toString().trim());
                    if (dao.check_password(etUsername.getText().toString().trim(), etPassword.getText().toString().trim())) {
                        Intent intent=new Intent(this, MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else {
                        Toast.makeText(this, "사용자 이름 또는 암호가 올바르지 않습니다. 확인하고 다시 입력하십시오.", Toast.LENGTH_LONG).show();
                    }
                    break;

                }
        }

    }

    //判断输入内容是否为空
    public boolean isEmptyForText() {
        name = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()) {
            return true;

        }
        Toast.makeText(this, "입력이 비어 있습니다.", Toast.LENGTH_LONG).show();
        return false;
    }


}