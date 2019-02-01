package com.nier.paging.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.nier.paging.api.GithubService
import com.nier.paging.api.searchRepos
import com.nier.paging.db.GithubLocalCache
import com.nier.paging.model.Repo

/**
 * Created by fgd
 * Date 2019/1/30
 */
class RepoBoundaryCallback(
        private val query: String,
        private val service: GithubService,
        private val cache: GithubLocalCache
) : PagedList.BoundaryCallback<Repo>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        private const val TAG = "RepoBoundaryCallback"
    }


    private var lastRequestedPage = 1
    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isRequestInProgress = false
    private var wordInProgress = ""

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) {
            Log.e(TAG, "requestAndSaveData >>> already a request in progress, query words=$query")
            return
        }
        isRequestInProgress = true
        wordInProgress = query
        searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE,
                { repos ->
                    cache.insert(repos) {
                        Log.d(TAG, ">>> searchRepos finished...")
                        lastRequestedPage++
                        isRequestInProgress = false
                    }

                },
                { error ->
                    _networkErrors.postValue(error)
                    isRequestInProgress = false
                }

        )

    }

    override fun onZeroItemsLoaded() {
        Log.wtf(TAG, ">>> onZeroItemsLoaded invoked.")
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemtAtEnd: Repo) {
        Log.wtf(TAG, ">>> onItemAtEndLoaded invoked, itemAtEnd=$itemtAtEnd")
        requestAndSaveData(query)
    }
}
