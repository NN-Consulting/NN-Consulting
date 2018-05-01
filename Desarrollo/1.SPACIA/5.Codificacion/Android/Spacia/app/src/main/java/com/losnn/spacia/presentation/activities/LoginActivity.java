package com.losnn.spacia.presentation.activities;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.losnn.spacia.presentation.fragments.LoadingMessageDialogFragment;
import com.losnn.spacia.presentation.presenters.LoginPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.losnn.spacia.MainActivity;
import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.presentation.contracts.LoginContract;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;


import java.util.List;

public class LoginActivity extends BaseActivity implements LoginContract.View, Validator.ValidationListener {
    @BindView(R.id.ln_login)
    LinearLayout lnLogin;
    @BindView(R.id.txt_forgotpassword)
    TextView txtForgotPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Email(message = "Email inválido")
    @BindView(R.id.et_email)
            EditText etEmail;
    @NotEmpty(message = "Campo requerido")
    @BindView(R.id.et_password)
            EditText etPassword;
    @BindView(R.id.txt_response)
            TextView txtResponse;

    LoginContract.Presenter presenter;
    LoadingMessageDialogFragment dialogFragment;

    Validator validator;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter =new LoginPresenter(getApplicationContext(),this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        AnimationDrawable animationDrawable = (AnimationDrawable) lnLogin.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        dialogFragment = LoadingMessageDialogFragment.newInstance();


        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(LoginActivity.this,null,ForgotPasswordActivity.class,false);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                txtResponse.setVisibility(View.GONE);
                validator.validate();
            }
        });




    }

    @Override
    public void showMessage(String message) {
        txtResponse.setVisibility(View.VISIBLE);
        txtResponse.setText(message);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(dialogFragment!=null){
            if(active){
                Bundle b = new Bundle();
                b.putString("message",getApplication().getResources().getString(R.string.modal_login));
                dialogFragment.setArguments(b);
                dialogFragment.show(getSupportFragmentManager(), "dialog");
            }
            else{
                dialogFragment.dismiss();

            }
        }
    }

    @Override
    public void logginSuccessfully() {
        nextActivity(LoginActivity.this,null,HomeActivity.class,true);
    }


    @Override
    public void onValidationSucceeded() {
        presenter.verifyCredentials(etEmail.getText().toString(),etPassword.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else if (view instanceof TextView) {
                ((TextView) view).setError(message);
            } else {
                showMessage(message);
            }
        }
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }
}
