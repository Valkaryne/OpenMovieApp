package com.epam.valkaryne.openmovieapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.databinding.QueryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QueryHistoryAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<QueryModel> items = new ArrayList<>();

    public QueryHistoryAdapter(Context context) {
        this.context = context;
    }

    public void submitData(List<QueryModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public QueryModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        QueryItemBinding binding;
        if (view != null) {
            binding = DataBindingUtil.getBinding(view);
        } else {
            binding = QueryItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
        }
        binding.setQuery(items.get(position));
        return binding.getRoot();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
    }
}
