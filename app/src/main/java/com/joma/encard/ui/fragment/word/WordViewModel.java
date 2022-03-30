package com.joma.encard.ui.fragment.word;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.encard.common.Resource;
import com.joma.encard.data.model.pixabayModel.Hit;
import com.joma.encard.data.model.pixabayModel.PixabayResponse;
import com.joma.encard.data.model.roomModel.WordModel;
import com.joma.encard.repositories.pixabayRepository.PixabayRepository;
import com.joma.encard.repositories.roomRepository.RoomRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordViewModel extends ViewModel {
    private LiveData<Resource<PixabayResponse<Hit>>> liveData;
    private final PixabayRepository pixabayRepository;
    private final RoomRepository roomRepository;

    @Inject
    public WordViewModel(PixabayRepository pixabayRepository, RoomRepository roomRepository) {
        this.pixabayRepository = pixabayRepository;
        this.roomRepository = roomRepository;
        liveData = new MutableLiveData<>();
    }

    public LiveData<List<WordModel>> getImage(String category) {
        return roomRepository.getWord(category);
    }

    public void loadImage(String word, String category) {
        liveData = pixabayRepository.getImage(word, category);
    }

    public void deleteWord(WordModel model) {
        roomRepository.deleteWord(model);
    }

    public LiveData<Resource<PixabayResponse<Hit>>> getLiveData() {
        return liveData;
    }
}
