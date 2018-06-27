package com.losnn.spacia.presentation.contracts;

public interface ForgotPasswordContract {
    interface  View{
        void showMessageSuccesfully(String message);
        void showMessageError(String message);
        void setLoadingIndicator(boolean active);
    }
    interface  Presenter{
        void sendEmail(String email);
    }
}
