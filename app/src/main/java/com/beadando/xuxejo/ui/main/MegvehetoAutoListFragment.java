package com.beadando.xuxejo.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beadando.xuxejo.R;

public class MegvehetoAutoListFragment extends Fragment {

    private MegvehetoAutoViewModel mViewModel;

    public static MegvehetoAutoListFragment newInstance() {
        return new MegvehetoAutoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_megveheto_auto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MegvehetoAutoViewModel.class);
        RecyclerView rw = getView().findViewById(R.id.MegvehetoAutoRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        rw.setLayoutManager(layoutManager);
        mViewModel.getMegvehetoAutok().observe(getViewLifecycleOwner(), megvehetoAutok -> {
            MegvehetoAutoAdapter adapter = new MegvehetoAutoAdapter(megvehetoAutok);
            rw.setAdapter(adapter);
        });
    }
}