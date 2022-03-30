package com.joma.encard.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.encard.R;
import com.joma.encard.base.BaseFragment;
import com.joma.encard.data.model.roomModel.CategoryModel;
import com.joma.encard.databinding.FragmentHomeBinding;
import com.joma.encard.ui.fragment.addCategory.AddCategoryFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<FragmentHomeBinding>
        implements HomeAdapter.Clickable {
    private HomeViewModel viewModel;
    private HomeAdapter adapter;

    @Override
    protected FragmentHomeBinding bind() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClicker();
        deleteCategory();
    }

    private void deleteCategory() {
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
                CategoryModel model = adapter.getCategoryList()
                        .get(viewHolder.getAdapterPosition());
                viewModel.deleteCategory(model);
                viewModel.deleteAllWord(model.getCategory());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvCategory);
    }

    private void initClicker() {
        binding.btnAddCategory.setOnClickListener(view -> {
            new AddCategoryFragment().show(requireActivity().getSupportFragmentManager(), "");
        });
    }

    @Override
    protected void setupUI() {
        adapter = new HomeAdapter(this);
        binding.rvCategory.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    protected void setupObserver() {
        viewModel.getCategory();
        viewModel.getCategoryLiveData().observe(getViewLifecycleOwner(), categoryModels -> {
            adapter.setCategoryList(categoryModels);
        });
    }

    @Override
    public void click(String category) {
        controller.navigate(HomeFragmentDirections
                .actionNavigationHomeToWordFragment().setCategory(category));
    }
}