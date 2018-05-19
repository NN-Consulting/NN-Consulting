package com.losnn.spacia.presentation.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.data.remote.response.EventResponse;
import com.losnn.spacia.presentation.adapters.EventsRecyclerAdapter;
import com.losnn.spacia.presentation.adapters.ResourcesRecyclerAdapter;
import com.losnn.spacia.presentation.contracts.HomeContract;
import com.losnn.spacia.presentation.contracts.LoginContract;
import com.losnn.spacia.presentation.fragments.LoadingMessageDialogFragment;
import com.losnn.spacia.presentation.presenters.HomePresenter;
import com.losnn.spacia.presentation.presenters.LoginPresenter;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyR)
    LinearLayout ivEmpty;

    LinearLayoutManager layoutManager;

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

        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter.start();

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

    @Override
    public void initRecyclerView(List<EventResponse> list) {
        EventsRecyclerAdapter adapter = new EventsRecyclerAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSwipeLayout() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }

    }

    @Override
    public void hideSWipeLayout() {

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void showEmpty() {
        if(recyclerView != null) {
            recyclerView.setVisibility(View.GONE);
        }
        if(ivEmpty !=null) {
            ivEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hideEmpty() {
        if(recyclerView != null) {
            recyclerView.setVisibility(View.VISIBLE);
        }
        if(ivEmpty !=null) {
            ivEmpty.setVisibility(View.GONE);
        }

    }
}
