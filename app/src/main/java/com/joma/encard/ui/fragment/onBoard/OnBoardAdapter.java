package com.joma.encard.ui.fragment.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.encard.R;
import com.joma.encard.databinding.PageBoardBinding;

import org.jetbrains.annotations.NotNull;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.BoardViewHolder> {
    private PageBoardBinding binding;
    private final Clickable listener;
    private static final String category = "Категории";
    private static final String words = "Слова";
    private static final String learn = "Изучение";
    private static final String personalArea = "Личный Кабинет";
    private static final String letsGo = "Давай начнем!";

    private static final String categoryDes = "Создавайте собственные категории для слов";
    private static final String wordsDes = "Создавайте слова на английском и кликайте " +
            "на карточку чтобы увидеть его перевод и картинку для ассоциации";
    private static final String learnDes = "Свайпайте карточку вправо если вы запомнили," +
            "влево если пока ещё не уверены";
    private static final String personalAreaDes = "Следите за своими ачивками " +
            "и количеством очков опыта";

    public static int[] animateList = {R.raw.video_learning, R.raw.key_words, R.raw.learning,
            R.raw.child_learning, R.raw.book};
    private static final String[] titleList = {category, words, learn, personalArea, letsGo};
    private static final String[] descriptionList = {categoryDes, wordsDes, learnDes,
            personalAreaDes, " "};

    public OnBoardAdapter(Clickable listener) {
        this.listener = listener;
    }


    @NonNull
    @NotNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = PageBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new BoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BoardViewHolder holder, int position) {
        holder.onBind(position);
        binding.btnBoard.setOnClickListener(view -> {listener.listener();});
    }

    @Override
    public int getItemCount() {
        return titleList.length;
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        PageBoardBinding binding;

        public BoardViewHolder(@NonNull PageBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(int position) {
            binding.lottieAnimBoard.setAnimation(animateList[position]);
            binding.tvCategoryBoard.setText(titleList[position]);
            binding.tvDescriptionBoard.setText(descriptionList[position]);

            if (position == animateList.length - 1) {
                binding.btnBoard.setVisibility(View.VISIBLE);
                binding.tvDescriptionBoard.setVisibility(View.GONE);
            } else {
                binding.btnBoard.setVisibility(View.GONE);
            }
        }
    }
    interface Clickable {
        void listener();
    }
}
