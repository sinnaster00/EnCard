package com.joma.encard.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.joma.encard.R;

import org.jetbrains.annotations.NotNull;

public abstract class BaseBottomSheetDialogFragment<VB extends ViewBinding> extends BottomSheetDialogFragment {
    protected VB binding;
    protected NavController controller;

    protected abstract VB bind();

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bind();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        setupUI();
        setupObserver();
        return binding.getRoot();
    }

    protected abstract void setupUI();

    protected abstract void setupObserver();
}
