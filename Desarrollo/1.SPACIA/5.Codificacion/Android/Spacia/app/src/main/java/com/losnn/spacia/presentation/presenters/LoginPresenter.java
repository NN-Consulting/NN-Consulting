package com.losnn.spacia.presentation.presenters;

import android.content.Context;

import com.losnn.spacia.R;
import com.losnn.spacia.data.Constants;
import com.losnn.spacia.data.local.SessionManager;
import com.losnn.spacia.data.remote.Response.AuthenticationResponse;
import com.losnn.spacia.data.remote.Response.ProfileResponse;
import com.losnn.spacia.data.remote.ServiceFactory;
import com.losnn.spacia.data.remote.request.BusinessRequest;
import com.losnn.spacia.presentation.contracts.LoginContract;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter implements LoginContract.Presenter{

    SessionManager sessionManager;
    LoginContract.View view;
    Context context;

    public LoginPresenter(Context context,LoginContract.View view){
        this.context=context;
        this.view=view;
        sessionManager= new SessionManager(context);
    }
    @Override
    public void verifyCredentials(String email, String password) {
        view.setLoadingIndicator(true);
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("email",email);
            paramObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(paramObject.toString()));

        BusinessRequest request = ServiceFactory.createService(BusinessRequest.class);
        Call<AuthenticationResponse> call = request.login(Constants.CONTENT_TYPE_JSON,Constants.CONTENT_TYPE_JSON,body);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.code()==200){
                    getUser(response.body());
                }else{
                    if(response.code()==401){
                        view.setLoadingIndicator(false);
                        view.showMessage(context.getResources().getString(R.string.user_wrong));
                    }else{
                        view.setLoadingIndicator(false);
                        view.showMessage(context.getResources().getString(R.string.user_noexist));
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.showMessage(context.getResources().getString(R.string.error_connect));
            }
        });
    }

    private void getUser(final AuthenticationResponse authResponse) {

        final BusinessRequest userRequest = ServiceFactory.createService(BusinessRequest.class);
        Call<ProfileResponse> call = userRequest.getProfile(Constants.CONTENT_TYPE_JSON, "Bearer " + authResponse.getData().getAccess_token());
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code()==200){
                    sessionManager.openSession(authResponse.getData().getAccess_token(),response.body().getData());
                    view.logginSuccessfully();
                    view.setLoadingIndicator(false);
                }else{
                    view.setLoadingIndicator(false);
                    view.showMessage(context.getResources().getString(R.string.error_connect));
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.showMessage(context.getResources().getString(R.string.error_connect));
            }
        });
    }
}
