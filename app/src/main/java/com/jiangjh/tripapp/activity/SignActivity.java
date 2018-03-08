package com.jiangjh.tripapp.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.database.MyDBOpenhelper;
import com.jiangjh.tripapp.widget.ClearEditText;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 *
 * @author JiaHao.Huang
 * @date 2018/3/8
 */

public class SignActivity extends AppCompatActivity {
    private TitleBar mTitleBar;
    private Button signBtn;
    private ClearEditText edtName, edtSecurity;
    private MyDBOpenhelper mMyDBOpenhelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mMyDBOpenhelper = new MyDBOpenhelper(this,"jjh.db",null,1);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setLeftDrawable(getResources().getDrawable(R.mipmap.icon_close));
        initView();
        initListener();
    }

    private void initView(){
        signBtn = findViewById(R.id.btn_sign);
        edtName = findViewById(R.id.edt_name_sign);
        edtSecurity = findViewById(R.id.edt_security_sign);

    }

    private void initListener(){
        mTitleBar.setLiftImageClickListener(new TitleBar.TitleBarLeftImageClickListener() {
            @Override
            public void onClickListener() {
                finish();
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(edtName.getText().toString().trim(),edtSecurity.getText().toString().trim());
                Toast.makeText(SignActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();


            }
        });
        edtName.addTextChangedListener(tw);
        edtSecurity.addTextChangedListener(tw);

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
                signBtn.setEnabled(true);
            } else {
                signBtn.setEnabled(false);
            }
        }
    };


    public void save(String name,String pwd){
        SQLiteDatabase database = mMyDBOpenhelper.getWritableDatabase();
        database.execSQL("INSERT INTO account(name,password) values(?,?)",
                new String[]{name,pwd});
    }
}
