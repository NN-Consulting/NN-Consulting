package com.losnn.spacia.presentation.presenters;

import android.content.Context;

import com.losnn.spacia.R;
import com.losnn.spacia.data.Constants;
import com.losnn.spacia.data.local.SessionManager;
import com.losnn.spacia.data.remote.ServiceFactory;
import com.losnn.spacia.data.remote.request.BusinessRequest;
import com.losnn.spacia.presentation.contracts.ForgotPasswordContract;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter  implements ForgotPasswordContract.Presenter {

    Context context;
    ForgotPasswordContract.View view;
    SessionManager sessionManager;

    public ForgotPasswordPresenter(Context context, ForgotPasswordContract.View view) {
        this.context = context;
        this.view = view;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void sendEmail(String email) {
        view.setLoadingIndicator(true);
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (paramObject.toString()));

        BusinessRequest request = ServiceFactory.createService(BusinessRequest.class);
        Call<Void> call = request.sendResetPassword(Constants.CONTENT_TYPE_JSON, Constants.CONTENT_TYPE_JSON, body);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                view.setLoadingIndicator(false);
                if (response.code() == 200) {
                    view.showMessageSuccesfully("");
                } else {
                    view.showMessageError(context.getResources().getString(R.string.email_wrong));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.showMessageError(context.getResources().getString(R.string.error_connect));
            }
        });
    }
}