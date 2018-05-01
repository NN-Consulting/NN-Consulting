package com.losnn.spacia.presentation.presenters;

import android.content.Context;


import com.losnn.spacia.R;
import com.losnn.spacia.data.Constants;
import com.losnn.spacia.data.local.SessionManager;
import com.losnn.spacia.data.remote.ServiceFactory;
import com.losnn.spacia.data.remote.request.BusinessRequest;
import com.losnn.spacia.presentation.contracts.HomeContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter{

    Context context;
    SessionManager sessionManager;
    HomeContract.View view;

    public HomePresenter(Context context,HomeContract.View view){
        this.context=context;
        this.sessionManager= new SessionManager(context);
        this.view=view;
    }
    @Override
    public void logout() {
        view.setLoadingIndicator(true);
        BusinessRequest request = ServiceFactory.createService(BusinessRequest.class);
        Call<Void> call = request.logout(Constants.CONTENT_TYPE_JSON,Constants.CONTENT_TYPE_JSON,"Bearer "+sessionManager.getUserAccessToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                view.setLoadingIndicator(false);
                if(response.code()==200){
                    sessionManager.closeSession();
                    view.goToLogin();
                }else{
                    view.showMessage(context.getResources().getString(R.string.error_connect));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.showMessage(context.getResources().getString(R.string.error_connect));
            }
        });
    }
}
