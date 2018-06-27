package com.losnn.spacia.data.remote.request;

import com.losnn.spacia.data.Constants;
import com.losnn.spacia.data.remote.response.AuthenticationResponse;
import com.losnn.spacia.data.remote.response.Data;
import com.losnn.spacia.data.remote.response.ProfileResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BusinessRequest {


    @POST(Constants.LOGIN)
    Call<AuthenticationResponse> login (@Header("Content-type") String contentType,
                                        @Header("Accept") String accept, @Body RequestBody loginQuery);

    @POST(Constants.LOGOUT)
    Call<Void> logout (@Header("Content-type") String contentType,
                       @Header("Accept") String accept,@Header("Authorization") String token);

    @GET(Constants.GET_PROFILE)
    Call<ProfileResponse> getProfile (@Header("Accept") String accept, @Header("Authorization") String token);

    @POST(Constants.RESET_PASSWORD)
    Call<Void> sendResetPassword (@Header("Accept") String accept, @Header("Content-type") String content,@Body RequestBody email);

    @GET(Constants.GET_EVENTS)
    Call<Data> getEvents (@Header("Content-type") String contentType,
                          @Header("Accept") String accept, @Header("Authorization") String token);//,@Body RequestBody body);

}
