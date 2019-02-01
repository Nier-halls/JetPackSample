package com.nier.paging.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nier.paging.api.GithubService
import com.nier.paging.api.searchRepos
import com.nier.paging.db.GithubLocalCache
import com.nier.paging.model.RepoSearchResult

/**
 * Created by fgd
 * Date 2019/1/25
 */
class GithubRepository(
        private val service: GithubService,
        private val cache: GithubLocalCache) {

    private var lastRequestedPage = 1
    private val networkErrors = MutableLiveData<String>()
    private var isRequestInProgress = false
    private var wordInProgress = ""

    fun search(query: String): RepoSearchResult {
        Log.d("GithubRepository", "New query: $query")
//        lastRequestedPage = 1 //每次都新建一个Callback来处理？
//        requestAndSaveData(query)

//        val data = cache.reposByName(query)

        // Get data source from the local cache
        val dataSourceFactory = cache.reposByName(query)
        val boundaryCallback = RepoBoundaryCallback(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory,
                PagedList.Config
                        .Builder()
                        .setPageSize(DATABASE_PAGE_SIZE)
                        .setPrefetchDistance(0)
                        .build())
                .setBoundaryCallback(boundaryCallback)
                .build()

        //Get the network errors exposed by the boundary callback
        return RepoSearchResult(data, networkErrors)
    }

    @Deprecated("removed")
    fun requestMore(query: String) {
        requestAndSaveData(query)
    }

    @Deprecated("removed")
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
                    networkErrors.postValue(error)
                    isRequestInProgress = false
                }
        )
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        private const val DATABASE_PAGE_SIZE = 20
        private const val TAG = "GithubDataRepo"
    }
}
