package com.losnn.spacia.presentation.activities;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.losnn.spacia.R;
import com.losnn.spacia.Spacia;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.data.local.SessionManager;
import com.losnn.spacia.data.remote.response.EventResponse;
import com.losnn.spacia.presentation.adapters.EventsRecyclerAdapter;
import com.losnn.spacia.presentation.contracts.HomeContract;
import com.losnn.spacia.presentation.fragments.LoadingMessageDialogFragment;
import com.losnn.spacia.presentation.presenters.HomePresenter;
import com.losnn.spacia.utils.ConnectivityReceiver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeContract.View,ConnectivityReceiver.ConnectivityReceiverListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyR)
    LinearLayout ivEmpty;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_internet)
            TextView txtInternet;

    LinearLayoutManager layoutManager;

    @BindView(R.id.btn_logout)
    ImageView btnLogout;
    HomeContract.Presenter presenter;

    LoadingMessageDialogFragment dialogFragment;
    SessionManager sessionManager;

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
        sessionManager = new SessionManager(getApplicationContext());


        txtName.setText(sessionManager.getUserEntity().getFirst_name().substring(0,1));

        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.start();
            }
        });

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

    @Override
    public void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        Spacia.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected){
            txtInternet.setVisibility(View.GONE);
        }else{
            txtInternet.setVisibility(View.VISIBLE);
        }
    }
}
