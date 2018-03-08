package com.jiangjh.tripapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.database.MyDBOpenhelper;
import com.jiangjh.tripapp.widget.ClearEditText;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 *
 * @author JiaHao.Huang
 * @date 2018/2/26
 */

public class LoginActivity extends AppCompatActivity{
    private Button btnLogin;
    private ClearEditText edtName, edtSecurity;
    private TitleBar mTitleBar;
    private MyDBOpenhelper mMyDBOpenhelper;
    private TextView mTvSign;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
        mMyDBOpenhelper = new MyDBOpenhelper(this,"jjh.db",null,1);

    }

    private void initView(){
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtName = (ClearEditText) findViewById(R.id.edt_name_login);
        edtSecurity = (ClearEditText) findViewById(R.id.edt_security_login);
        mTvSign = findViewById(R.id.tv_sing);
        mTitleBar.setLeftDrawable(getResources().getDrawable(R.mipmap.icon_close));
    }

    private void initListener(){
        mTitleBar.setLiftImageClickListener(new TitleBar.TitleBarLeftImageClickListener() {
            @Override
            public void onClickListener() {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (find(edtName.getText().toString().trim(),edtSecurity.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("account",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",edtName.getText().toString().trim());
                    editor.apply();
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        edtName.addTextChangedListener(tw);
        edtSecurity.addTextChangedListener(tw);

        mTvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
    }

    private TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String nameStr = edtName.getText().toString().trim();
            String codeStr = edtSecurity.getText().toString().trim();
            if (!TextUtils.isEmpty(nameStr)&& !TextUtils.isEmpty(codeStr)) {
                btnLogin.setEnabled(true);
            } else {
                btnLogin.setEnabled(false);
            }
        }
    };

    public boolean find(String name,String password){
        SQLiteDatabase database = mMyDBOpenhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM account WHERE name = ?",
                new String[]{name});
        if (cursor.moveToFirst()){
            String dbName = cursor.getString(cursor.getColumnIndex("name"));
            String dbPwd = cursor.getString(cursor.getColumnIndex("password"));
            if (name.equals(dbName) && dbPwd.equals(password)){
                return true;
            }
        }
        cursor.close();
        return false;
    }

}
