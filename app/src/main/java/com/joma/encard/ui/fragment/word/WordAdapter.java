package com.joma.encard.ui.fragment.word;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joma.encard.data.model.roomModel.WordModel;
import com.joma.encard.databinding.ImgItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<WordModel> list = new ArrayList<>();
    private OpenDialog openDialog;

    public void setList(List<WordModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<WordModel> getList() {
        return list;
    }

    @NonNull
    @NotNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ImgItemBinding binding = ImgItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new WordViewHolder(binding);
    }

    public WordAdapter(OpenDialog openDialog) {
        this.openDialog = openDialog;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WordViewHolder holder, int position) {
        holder.onBind(list.get(position).getWord(), list.get(position).getImgUrl());
        holder.itemView.setOnClickListener(view -> {
            openDialog.clickable(list.get(position).getImgUrl(), list.get(position).getWord());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        private final ImgItemBinding binding;

        public WordViewHolder(@NonNull ImgItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(String word, String imgUrl) {
            binding.tvItemTitle.setText(word);
            Glide.with(binding.getRoot()).load(imgUrl).centerCrop().into(binding.imgItem);
        }
    }

    interface OpenDialog {
        void clickable(String img, String title);
    }
}
