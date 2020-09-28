package com.epam.valkaryne.openmovieapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;

import com.epam.valkaryne.openmovieapp.R;
import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.databinding.DialogSearchBinding;
import com.epam.valkaryne.openmovieapp.view.adapter.QueryHistoryAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchDialog extends DialogFragment {

    interface OnDialogInteraction {
        void performSearch(QueryModel queryModel);

        void clearHistory();
    }

    private OnDialogInteraction onDialogInteraction;
    private LiveData<List<QueryModel>> queryHistory;
    private DialogSearchBinding binding;

    private String[] movieTypes;

    private SearchDialog(OnDialogInteraction onDialogInteraction, LiveData<List<QueryModel>> queryHistory) {
        this.onDialogInteraction = onDialogInteraction;
        this.queryHistory = queryHistory;
    }

    public static void show(FragmentManager fragmentManager, OnDialogInteraction onDialogInteraction,
                            LiveData<List<QueryModel>> queryHistory) {
        new SearchDialog(onDialogInteraction, queryHistory)
                .show(fragmentManager, "search_dialog");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogSearchBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext()).setView(binding.getRoot());

        ArrayAdapter<String> movieTypesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item);
        binding.searchType.setAdapter(movieTypesAdapter);

        QueryHistoryAdapter historyAdapter = new QueryHistoryAdapter(getContext());
        binding.searchInput.setAdapter(historyAdapter);
        binding.searchInput.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                binding.searchInput.showDropDown();
            }
            return false;
        });
        binding.searchInput.setOnItemClickListener((adapterView, view, position, l) -> {
            QueryModel item = historyAdapter.getItem(position);
            if (item != null) {
                if (item.getType() != null && !item.getType().isEmpty()) {
                    for (String type : movieTypes) {
                        if (item.getType().equals(type)) {
                            binding.searchType.setSelection(Arrays.asList(movieTypes).indexOf(type));
                        }
                    }
                }
                binding.searchYear.setText(item.getYear());
                binding.searchInput.setText(item.getTitle());
            }
        });

        movieTypes = getResources().getStringArray(R.array.search_movies_types);
        movieTypesAdapter.addAll(movieTypes);

        binding.searchPerformButton.setOnClickListener(view -> {
            dismiss();
            onDialogInteraction.performSearch(assembleQueryModel());
        });

        binding.searchClearButton.setOnClickListener(view -> {
            onDialogInteraction.clearHistory();
        });

        queryHistory.observe(this, queryModels -> {
            historyAdapter.submitData(queryModels);
            if (queryModels.isEmpty()) {
                binding.searchClearButton.setEnabled(false);
            }
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
