package com.losnn.spacia.presentation.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.losnn.spacia.R;

public class LoadingMessageDialogFragment extends DialogFragment {
    public static LoadingMessageDialogFragment newInstance() {
        return new LoadingMessageDialogFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog3);
        setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogfragment_loading, container, false);

        TextView textView = (TextView) v.findViewById(R.id.txt_message);
        textView.setText(getArguments().getString("message"));
        return v;
    }
}
