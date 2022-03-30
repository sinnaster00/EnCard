package com.joma.encard.repositories.roomRepository;

import androidx.lifecycle.LiveData;

import com.joma.encard.data.model.roomModel.CategoryModel;
import com.joma.encard.data.model.roomModel.WordModel;
import com.joma.encard.db.CategoryDao;
import com.joma.encard.db.WordDao;

import java.util.List;

import javax.inject.Inject;

public class RoomRepository {
    private final CategoryDao dao;
    private final WordDao wordDao;

    @Inject
    public RoomRepository(CategoryDao dao, WordDao wordDao) {
        this.dao = dao;
        this.wordDao = wordDao;
    }
    public LiveData<List<CategoryModel>> getCategory() {
        return dao.getCategory();
    }
    public void insertCategory(CategoryModel model) {
        dao.addCategory(model);

    }
    public LiveData<List<WordModel>> getWord(String category) {
        return wordDao.getWord(category);
    }

    public void deleteCategory(CategoryModel model) {
        dao.delete(model);
    }

    public void deleteWord(WordModel wordModels) {
        wordDao.delete(wordModels);
    }

    public void deleteAllWords(String category) {
        wordDao.deleteAllWords(wordDao.wordList(category));
    }
}
