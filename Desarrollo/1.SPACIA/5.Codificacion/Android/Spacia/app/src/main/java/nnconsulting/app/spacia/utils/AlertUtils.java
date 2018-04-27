package nnconsulting.app.spacia.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;

public class AlertUtils {

    Map<Context, AlertDialog> alertHolder;
    Map<Context, ProgressDialog> progressHolder;

    private static AlertUtils ourInstance = new AlertUtils();

    public static AlertUtils getInstance() {
        return ourInstance;
    }

    private AlertUtils() {
        alertHolder = new HashMap<Context, AlertDialog>();
        progressHolder = new HashMap<Context, ProgressDialog>();
    }

    /**
     * Shows alert - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     * @param context
     * @param title
     * @param message
     */
    public void showAlert(Context context, String title, String message) {
        showAlert(context, title, message, null);
    }

    /**
     * Shows alert - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     * @param context
     * @param title
     * @param message
     * @param onClickListener
     */
    public void showAlert(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
        // Checks if an alert already exists - if so it gets dismissed
        hideAlert(context);

        // Checks if a progress dialog already exists - if so it gets dismissed
        hideProgress(context);

        // Adds defaul click listener
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            };
        }

        // Creates, stores, and shows
        AlertDialog alert = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton("Aceptar", onClickListener).create();
        alertHolder.put(context, alert);
        alert.show();
    }

    /**
     * Hides an alert if one is shown for this context
     * @param context
     */
    public void hideAlert(Context context) {
        AlertDialog alert = alertHolder.get(context);
        if (alert != null) {
            alert.dismiss();
            alert = null;

            alertHolder.remove(context);
        }
    }

    /**
     * Shows progress dialog - if an alert or progress dialog is already visible for this context (Activity), it will get dismissed
     * @param context
     * @param title
     * @param message
     */
    public void showProgress(Context context, String title, String message) {
        // Checks if an alert already exists - if so it gets dismissed
        hideAlert(context);

        // Checks if a progress dialog already exists - if so it gets dismissed
        hideProgress(context);

        ProgressDialog progress = ProgressDialog.show(context, title, message, true);
        progressHolder.put(context, progress);
    }

    /**
     * Hides a progress dialog if one is shown for this context
     * @param context
     */
    public void hideProgress(Context context) {
        ProgressDialog progress = progressHolder.get(context);
        if (progress != null) {
            progress.dismiss();
            progress = null;

            progressHolder.remove(context);
        }
    }

}