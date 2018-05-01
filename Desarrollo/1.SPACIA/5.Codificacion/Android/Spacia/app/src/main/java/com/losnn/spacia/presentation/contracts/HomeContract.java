package com.losnn.spacia.presentation.contracts;

public interface HomeContract {
    interface View{
        void goToLogin();
        void showMessage(String message);
        void setLoadingIndicator(boolean active);
    }
    interface Presenter{
        void logout();
    }
}
