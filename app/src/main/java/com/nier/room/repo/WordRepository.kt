package com.nier.room.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.nier.room.dao.WordDao
import com.nier.room.data.Word

/**
 * Created by fgd
 * Date 2019/1/22
 */
class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}