package app.githubrepositories.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import app.githubrepositories.model.RepositoryModel

interface DataRepository {
    fun getRepositories(query: String): LiveData<PagingData<RepositoryModel>>
}