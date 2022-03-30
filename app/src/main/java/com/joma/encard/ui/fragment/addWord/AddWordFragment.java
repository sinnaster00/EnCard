package com.joma.encard.ui.fragment.addWord;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.joma.encard.base.BaseBottomSheetDialogFragment;
import com.joma.encard.common.ISendWord;
import com.joma.encard.databinding.FragmentAddWordBinding;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddWordFragment extends BaseBottomSheetDialogFragment<FragmentAddWordBinding> {
    private final SendWord sendWord;

    public AddWordFragment(SendWord sendWord) {
        this.sendWord = sendWord;
    }

    @Override
    protected FragmentAddWordBinding bind() {
        return FragmentAddWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.btnSaveWord.setOnClickListener(view -> {
            String word = binding.etWord.getText().toString();
            if (!word.isEmpty()) {
                sendWord.send(word, getTag());
                dismiss();
            }
        });
    }

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupObserver() {

    }
    public interface SendWord{
    void send(String word, String category);
    }
}