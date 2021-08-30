package com.hyena.pianku.tv;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyena.framework.clientlog.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private ListAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mListAdapter = new ListAdapter();
        List<String> items = new ArrayList<>();
        items.add("片库");
        mListAdapter.setItems(items);
        recyclerView.setAdapter(mListAdapter);
    }

    class ListAdapter extends RecyclerView.Adapter<ListHolder> {

        private List<String> items;

        public void setItems(List<String> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(getContext(), R.layout.layout_main_item, null);
            return new ListHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder holder, int position) {
            TextView textView = holder.itemView.findViewById(android.R.id.text1);
            textView.setText(items.get(position) + "-" + position);
            textView.setOnClickListener(v -> {
                if (position == 0) {
                    NavHostFragment.findNavController(MainFragment.this)
                            .navigate(R.id.action_MainFragment_to_PKFragment);
                } else if (position == 1) {
                    NavHostFragment.findNavController(MainFragment.this)
                            .navigate(R.id.action_MainFragment_to_ResourceListFragment);
                } else {
                    NavHostFragment.findNavController(MainFragment.this)
                            .navigate(R.id.action_MainFragment_to_PKFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (items == null)
                return 0;
            return items.size();
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {

        public ListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
