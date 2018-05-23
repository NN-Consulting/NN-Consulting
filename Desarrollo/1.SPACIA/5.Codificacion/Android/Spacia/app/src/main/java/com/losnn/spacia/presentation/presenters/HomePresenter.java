package com.losnn.spacia.presentation.presenters;

import android.content.Context;


import com.losnn.spacia.R;
import com.losnn.spacia.data.Constants;
import com.losnn.spacia.data.local.SessionManager;
import com.losnn.spacia.data.remote.ServiceFactory;
import com.losnn.spacia.data.remote.request.BusinessRequest;
import com.losnn.spacia.data.remote.response.Data;
import com.losnn.spacia.presentation.contracts.HomeContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter{

    Context context;
    SessionManager sessionManager;
    HomeContract.View view;
    DateFormat dateFormat;

    public HomePresenter(Context context,HomeContract.View view){
        this.context=context;
        this.sessionManager= new SessionManager(context);
        this.view=view;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

    @Override
    public void start() {
        view.showSwipeLayout();
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("date",dateFormat.format(Calendar.getInstance().getTime()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (paramObject.toString()));

        BusinessRequest request = ServiceFactory.createService(BusinessRequest.class);
        Call<Data> call = request.getEvents(Constants.CONTENT_TYPE_JSON,Constants.CONTENT_TYPE_JSON,"Bearer "+sessionManager.getUserAccessToken(),body);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                view.hideSWipeLayout();
                if(response.code()==200) {
                    if (response.body().getData().size() != 0) {
                        view.initRecyclerView(response.body().getData());
                        view.hideEmpty();
                    } else {
                        view.showEmpty();
                    }
                }else{
                    view.showEmpty();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                view.hideSWipeLayout();
                view.showEmpty();
            }
        });
    }
}
