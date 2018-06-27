package com.losnn.spacia.presentation.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.losnn.spacia.R;
import com.losnn.spacia.presentation.activities.ForgotPasswordActivity;

public class ConfirmDialogFragment extends DialogFragment {


    public static ConfirmDialogFragment newInstance() {
        return new ConfirmDialogFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog3);
        setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogfragment_confirm, container, false);

        Button btnOk = (Button) v.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ForgotPasswordActivity)getContext()).finish();
                dismiss();
            }
        });

        return v;
    }
}