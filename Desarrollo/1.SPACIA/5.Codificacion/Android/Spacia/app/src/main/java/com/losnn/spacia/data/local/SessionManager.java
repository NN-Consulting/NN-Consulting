package com.losnn.spacia.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.losnn.spacia.data.entities.UserEntity;

import java.lang.reflect.Type;
import java.util.List;

public class SessionManager {

    private static final String PREFERENCE_NAME = "Spacia";
    private static final int PRIVATE_MODE = 0;

    /**
     * USER DATA SESSION - JSON
     */
    private static final String USER_ACCESS_TOKEN = "user_access_token";
    private static final String USER_REFRESH_TOKEN = "user_refresh_token";
    private static final String USER_JSON = "user_json";
    private static final String IS_LOGIN = "user_login";
    private static final String IS_INTRO = "intro";
    private static final String USER_LIST_VENUES = "user_list_venues";
    private static final String SCHEDULE_LIST ="user_list_schedule";
    private static final String LIST_COLUMN_HEADER="user_list_column";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;


    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin() {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    /*public void openSession(String token, UserEntity userEntity, AuthenticationResponse.Data authResponse,
                            List<ScheduleEntity> list, List<ColumnHeader> columnHeaderList) {
        editor.putBoolean(IS_LOGIN,true);
        editor.putBoolean(IS_INTRO,true);
        editor.putString(USER_ACCESS_TOKEN, token);
        if(userEntity!=null){
            Gson gson = new Gson();
            String response = gson.toJson(authResponse);
            String user= gson.toJson(userEntity);
            String listSchedule = gson.toJson(list);
            String listColumnHeader = gson.toJson(columnHeaderList);
            editor.putString(USER_LIST_VENUES, response);
            editor.putString(USER_JSON, user);
            editor.putString(SCHEDULE_LIST,listSchedule);
            editor.putString(LIST_COLUMN_HEADER,listColumnHeader);
        }
        editor.commit();≈
    }     */
    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_ACCESS_TOKEN, null);
        editor.putString(USER_REFRESH_TOKEN, null);
        editor.putString(USER_LIST_VENUES,null);
        editor.putString(USER_JSON, null);
        editor.putBoolean(IS_INTRO,true);
        editor.putString(SCHEDULE_LIST,null);
        editor.putString(LIST_COLUMN_HEADER,null);
        editor.commit();
    }

    public String getUserAccessToken() {
        if (isLogin()) {
            return preferences.getString(USER_ACCESS_TOKEN, "");
        } else {
            return "";
        }
    }

    public String getUserRefreshToken() {
        if (isLogin()) {
            return preferences.getString(USER_REFRESH_TOKEN, "");
        } else {
            return "";
        }
    }

    /*public void setUserEntity(UserEntity userEntity) {
        editor.remove(USER_JSON).commit();
        if (userEntity != null) {
            Gson gson = new Gson();
            String user = gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }
        editor.commit();
    }*/


    public UserEntity getUserEntity() {
        if (isLogin()) {
            String userData = preferences.getString(USER_JSON, null);
            try {
                return new Gson().fromJson(userData, UserEntity.class);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }



    private void resetFields() {
        //Login data
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_ACCESS_TOKEN, null);
        editor.putString(USER_REFRESH_TOKEN, null);
        editor.putString(USER_JSON, null);
    }

    public boolean isIntro(){
        return preferences.getBoolean(IS_INTRO, false);
    }

    public void setIntro() {
        editor.putBoolean(IS_INTRO, true);
        editor.commit();
    }

}
