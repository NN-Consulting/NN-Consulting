package com.losnn.spacia.presentation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.presentation.contracts.HomeContract;
import com.losnn.spacia.presentation.contracts.LoginContract;
import com.losnn.spacia.presentation.fragments.LoadingMessageDialogFragment;
import com.losnn.spacia.presentation.presenters.HomePresenter;
import com.losnn.spacia.presentation.presenters.LoginPresenter;
import com.mobsandgeeks.saripaar.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.btn_logout)
    ImageView btnLogout;
    HomeContract.Presenter presenter;

    LoadingMessageDialogFragment dialogFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dialogFragment = LoadingMessageDialogFragment.newInstance();
        presenter = new HomePresenter(getApplicationContext(),this);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logout();
            }
        });
    }

    @Override
    public void goToLogin() {
        nextActivityNewTask(HomeActivity.this,null,LoginActivity.class,true);
    }

    @Override
    public void showMessage(String message) {
        showMessageError(message);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(dialogFragment!=null){
            if(active){
                Bundle b = new Bundle();
                b.putString("message",getApplication().getResources().getString(R.string.modal_logout));
                dialogFragment.setArguments(b);
                dialogFragment.show(getSupportFragmentManager(), "dialog");
            }
            else{
                dialogFragment.dismiss();
            }
        }
    }
}
