package nnconsulting.app.spacia.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void setLoadingIndicator(boolean active);

    void showMessage(String message);

    void showErrorMessage(String message);

}