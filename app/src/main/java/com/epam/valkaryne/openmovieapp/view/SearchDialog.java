package com.epam.valkaryne.openmovieapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.epam.valkaryne.openmovieapp.databinding.DialogSearchBinding;

public class SearchDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogSearchBinding binding = DialogSearchBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext()).setView(binding.getRoot());

        AlertDialog dialog = alert.create();

        return dialog;
    }
}
