package com.jiangjh.tripapp.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.util.CheckCode;
import com.jiangjh.tripapp.widget.ClearEditText;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 *
 * @author JiaHao.Huang
 * @date 2018/2/26
 */

public class LoginActivity extends AppCompatActivity{
    private Button btnLogin, btnSecurity;
    private ClearEditText edtPhone, edtSecurity;
    private CheckBox cbxAgreement;
    private TitleBar mTitleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initView(){
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSecurity = (Button) findViewById(R.id.btn_security_login);
        edtPhone = (ClearEditText) findViewById(R.id.edt_phone_login);
        edtSecurity = (ClearEditText) findViewById(R.id.edt_security_login);
        cbxAgreement = (CheckBox) findViewById(R.id.cbx_agreement_login);

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
                finish();
            }
        });
        btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSecurity.setEnabled(false);
                new CountDownTimer(30000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        String formatStr = getResources().getString(R.string.str_reget);
                        long second = millisUntilFinished / 1000;
                        btnSecurity.setText(String.format(formatStr, second));
                    }

                    @Override
                    public void onFinish() {
                        btnSecurity.setEnabled(true);
                        btnSecurity.setText("验证码");
                    }
                }.start();
            }
        });
        edtPhone.addTextChangedListener(tw);
        edtSecurity.addTextChangedListener(tw);
        cbxAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setEnabled(cbxAgreement.isChecked() && edtPhone.length() == 11 && edtSecurity.length() == 4);
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
            String phoneStr = edtPhone.getText().toString().trim();
            String codeStr = edtSecurity.getText().toString().trim();
            if (CheckCode.checkPhone(phoneStr) && codeStr.length() == 4 && cbxAgreement.isChecked()) {
                btnLogin.setEnabled(true);
            } else {
                btnLogin.setEnabled(false);
            }
        }
    };
}
