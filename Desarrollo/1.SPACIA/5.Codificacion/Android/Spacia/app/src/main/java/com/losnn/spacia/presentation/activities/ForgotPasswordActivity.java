package com.losnn.spacia.presentation.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.presentation.contracts.ForgotPasswordContract;
import com.losnn.spacia.presentation.fragments.ConfirmDialogFragment;
import com.losnn.spacia.presentation.fragments.LoadingMessageDialogFragment;
import com.losnn.spacia.presentation.presenters.ForgotPasswordPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordContract.View, Validator.ValidationListener {

    @BindView(R.id.iv_forgot_logo)
    ImageView ivSplashLogo;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    Validator validator;

    @Email(message = "Email inválido")
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_recuperar_pass)
    Button btnForgotPass;

    ForgotPasswordContract.Presenter presenter;
    LoadingMessageDialogFragment dialogFragment;

    ConfirmDialogFragment confirmDialogFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        ButterKnife.bind(this);
        //animateImageView(ivSplashLogo);
        dialogFragment = LoadingMessageDialogFragment.newInstance();

        presenter = new ForgotPasswordPresenter(getApplicationContext(),this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                presenter.sendEmail(etEmail.getText().toString());
            }
        });

    }


    public void animateImageView(final ImageView v) {
        final int orange = getResources().getColor(R.color.colorAccent);

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaOrange = adjustAlpha(orange, mul);
                v.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    v.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();

    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showMessageSuccesfully(String message) {
        confirmDialogFragment = ConfirmDialogFragment.newInstance();
        confirmDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(dialogFragment!=null){
            if(active){
                Bundle b = new Bundle();
                b.putString("message",getApplication().getResources().getString(R.string.loading_process));
                dialogFragment.setArguments(b);
                dialogFragment.show(getSupportFragmentManager(), "dialog");
            }
            else{
                dialogFragment.dismiss();

            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        presenter.sendEmail(etEmail.getText().toString());
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
                //showMessage(message);
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
