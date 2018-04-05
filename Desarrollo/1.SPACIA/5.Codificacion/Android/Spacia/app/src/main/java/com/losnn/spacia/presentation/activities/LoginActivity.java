package com.losnn.spacia.presentation.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.losnn.spacia.MainActivity;
import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.ln_login)
    LinearLayout lnLogin;
    @BindView(R.id.txt_forgotpassword)
    TextView txtForgotPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        AnimationDrawable animationDrawable = (AnimationDrawable) lnLogin.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(LoginActivity.this,null,ForgotPasswordActivity.class,false);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(LoginActivity.this,null,MainActivity.class,false);
            }
        });
    }
}
