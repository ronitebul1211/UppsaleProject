package com.example.android.uppsaleproject.ui;

import android.app.Dialog;
import android.os.Bundle;

import com.example.android.uppsaleproject.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {

    static final String ERROR_MESSAGE_KEY = "messageId";

    /*
    get new instance of alert dialog fragment
    */
    public static AlertDialogFragment newInstance(String messageId) {

        AlertDialogFragment fragment = new AlertDialogFragment();

        // set fragment bundle with an error message
        Bundle args = new Bundle();
        args.putString(ERROR_MESSAGE_KEY, messageId);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getErrorMessage())
                .setNeutralButton(getString(R.string.alert_positive_button), null);

        return builder.create();
    }

    /*
    get error message from bundle
    */
    private String getErrorMessage(){
        return getArguments().getString(ERROR_MESSAGE_KEY);
    }


}

