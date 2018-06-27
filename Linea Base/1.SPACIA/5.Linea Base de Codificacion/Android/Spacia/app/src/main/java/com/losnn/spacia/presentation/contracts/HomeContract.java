package com.losnn.spacia.presentation.contracts;

import com.losnn.spacia.data.remote.response.EventResponse;

import java.util.List;

public interface HomeContract {
    interface View{
        void goToLogin();
        void showMessage(String message);
        void setLoadingIndicator(boolean active);

        void initRecyclerView(List<EventResponse> list);
        void showSwipeLayout();
        void hideSWipeLayout();
        void showEmpty();
        void hideEmpty();
    }
    interface Presenter{
        void logout();
        void start();
    }
}
