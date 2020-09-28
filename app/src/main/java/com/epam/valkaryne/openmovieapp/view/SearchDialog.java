package com.epam.valkaryne.openmovieapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.epam.valkaryne.openmovieapp.R;
import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.databinding.DialogSearchBinding;

public class SearchDialog extends DialogFragment {

    interface OnDialogInteraction {
        void performSearch(QueryModel queryModel);

        void clearHistory();
    }

    private OnDialogInteraction onDialogInteraction;
    private DialogSearchBinding binding;

    private String[] movieTypes;

    private SearchDialog(OnDialogInteraction onDialogInteraction) {
        this.onDialogInteraction = onDialogInteraction;
    }

    public static void show(FragmentManager fragmentManager, OnDialogInteraction onDialogInteraction) {
        new SearchDialog(onDialogInteraction).show(fragmentManager, "search_dialog");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogSearchBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext()).setView(binding.getRoot());

        ArrayAdapter<String> movieTypesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item);
        binding.searchType.setAdapter(movieTypesAdapter);

        movieTypes = getResources().getStringArray(R.array.search_movies_types);
        movieTypesAdapter.addAll(movieTypes);

        binding.searchPerformButton.setOnClickListener(view -> {
            dismiss();
            onDialogInteraction.performSearch(assembleQueryModel());
        });

        binding.searchClearButton.setOnClickListener(view -> {
            onDialogInteraction.clearHistory();
        });

        return alert.create();
    }

    private QueryModel assembleQueryModel() {
        String title = binding.searchInput.getText().toString();
        String year = binding.searchYear.getText().toString();
        String type = binding.searchType.getSelectedItem().toString();

        QueryModel model = new QueryModel(title, year);

        if (!type.equals(movieTypes[0])) {
            model.setType(type);
        }

        return model;
    }
}
