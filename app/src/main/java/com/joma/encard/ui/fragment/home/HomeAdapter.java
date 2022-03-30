package com.joma.encard.ui.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.encard.data.model.roomModel.CategoryModel;
import com.joma.encard.data.model.roomModel.WordModel;
import com.joma.encard.databinding.CategoryItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<CategoryModel> categoryList = new ArrayList<>();
    private List<WordModel> wordList = new ArrayList<>();
    private Clickable listener;

    public HomeAdapter(Clickable listener) {
        this.listener = listener;
    }

    public void setCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public List<WordModel> getWordList() {
        return wordList;
    }

    public List<CategoryModel> getCategoryList() {
        return categoryList;
    }

    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = CategoryItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {
        holder.onBind(categoryList.get(position));
        holder.itemView.setOnClickListener(view -> {
            listener.click(categoryList.get(position).getCategory());
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;

        public HomeViewHolder(@NonNull CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(CategoryModel model) {
            binding.tvItemCategory.setText(model.getCategory());
        }
    }

    interface Clickable {
        void click(String category);
    }
}
