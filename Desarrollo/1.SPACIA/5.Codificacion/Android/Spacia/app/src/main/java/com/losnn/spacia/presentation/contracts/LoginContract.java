package com.losnn.spacia.presentation.contracts;

public interface LoginContract  {

    interface  View{
        void showMessage(String message);
        void setLoadingIndicator(boolean active);
        void logginSuccessfully();
    }
    interface  Presenter{
        void verifyCredentials(String email,String password);
    }
}
