/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nier.paging.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nier.paging.data.GithubRepository
import com.nier.paging.model.Repo
import com.nier.paging.model.RepoSearchResult

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [GithubRepository] to get the data.
 */
class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
        private const val TAG = "RepoViewModel"
    }

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<RepoSearchResult> = Transformations.map(queryLiveData) {
        val result = repository.search(it)
        Log.d(TAG, "[repoResult] Transformations >>>>> live data from database ${result.data.hashCode()}")
        result
    }

    val repos: LiveData<PagedList<Repo>> = Transformations.switchMap(repoResult
    ) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult
    ) { it -> it.networkErrors }

//    val repos: LiveData<PagedList<Repo>> = Transformations.switchMap(repoResult) { it ->
//        Log.d(TAG, "[repos] Transformations >>>>> live data from database ${it.data.hashCode()}")
//        it.data
//    }
//    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it -> it.networkErrors }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = lastQueryValue()
            if (immutableQuery != null) {
                repository.requestMore(immutableQuery)
            }
        }
    }

    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}
