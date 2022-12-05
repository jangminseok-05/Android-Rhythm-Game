package com.example.rabbit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rabbit.Dao.MyDao;

public class BackActivity extends AppCompatActivity {

    private EditText etFind;
    private TextView textView2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        initView();
    }

    private void initView() {
        etFind = (EditText) findViewById(R.id.et_find);
        textView2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDao dao=new MyDao(getApplicationContext());
                textView2.setText(dao.find_password("your password is "+etFind.getText().toString()));

            }
        });
    }
}