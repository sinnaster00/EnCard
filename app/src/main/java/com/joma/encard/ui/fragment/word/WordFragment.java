package com.joma.encard.ui.fragment.word;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.joma.encard.base.BaseFragment;
import com.joma.encard.data.model.roomModel.WordModel;
import com.joma.encard.databinding.FragmentWordBinding;
import com.joma.encard.ui.dilog.Dialog;
import com.joma.encard.ui.fragment.addWord.AddWordFragment;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordFragment extends BaseFragment<FragmentWordBinding>
        implements AddWordFragment.SendWord, WordAdapter.OpenDialog {

    private String category;
    private WordViewModel viewModel;
    private WordAdapter adapter;


    @Override
    protected FragmentWordBinding bind() {
        return FragmentWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDelete();
    }

    private void initDelete() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView,
                                  @NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                WordModel model = adapter.getList().get(viewHolder.getAdapterPosition());
                viewModel.deleteWord(model);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvImage);
    }

    private void initListener() {
        binding.btnAddWord.setOnClickListener(view -> {
            new AddWordFragment(this)
                    .show(requireActivity()
                            .getSupportFragmentManager(), category);
        });
    }

    @Override
    protected void setupUI() {
        adapter = new WordAdapter(this);
        binding.rvImage.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
        initCategoryTag();
        initListener();
    }

    private void initCategoryTag() {
        category = WordFragmentArgs.fromBundle(getArguments()).getCategory();
    }

    @Override
    protected void setupObserver() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), pixabayResponseResource -> {
            switch (pixabayResponseResource.status) {
                case SUCCESS:
                    viewModel.getImage(category).observe(getViewLifecycleOwner(), wordModels -> {
                        adapter.setList(wordModels);
                    });
                    break;
                case LOADING:
                    Toast.makeText(requireContext(), "Load", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void send(String word, String category) {
        Log.e("send", word + " ");
        viewModel.loadImage(word, category);
    }

    @Override
    public void clickable(String img, String title) {
        new Dialog(title, img).show(requireActivity().getSupportFragmentManager(), category);
    }
}