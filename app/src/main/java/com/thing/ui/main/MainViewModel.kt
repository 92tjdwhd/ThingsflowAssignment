package com.thing.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thing.adapter.GitIssueListAdapter
import com.thing.data.GitRepoIssueRequest
import com.thing.data.GitRepoIssueResponse
import com.thing.repository.GitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val gitRepository: GitRepository) : ViewModel() {

    /**
     * 업데이트 이슈
     */
    val updateGitIssue: MutableLiveData<ArrayList<GitRepoIssueResponse>?> = MutableLiveData()

    val moveIssueDetailEvent: MutableLiveData<GitRepoIssueResponse> = MutableLiveData()

    val moveThings: MutableLiveData<Unit> = MutableLiveData()

    val orgRepoName: MutableLiveData<String> = MutableLiveData("")

    val titleClickEvent: MutableLiveData<Unit> = MutableLiveData()

    init {
        viewModelScope.launch {
            val result = gitRepository.requestGitRepIssues(GitRepoIssueRequest("google", "dagger"))
            updateGitIssue.postValue(result.body())
            orgRepoName.value = "google/dagger"
        }
    }

    val gitListClickListener = object : GitIssueListAdapter.ItemClickListener {
        override fun onClick(gitRepoIssueResponse: GitRepoIssueResponse?) {
            if(gitRepoIssueResponse == null) {
                moveThings.value = Unit
            }else{
                moveIssueDetailEvent.value = gitRepoIssueResponse
            }
        }
    }

    fun titleClickListener() {
        titleClickEvent.value = Unit
    }

    fun requestGitRepoIssues(org: String, repo: String) {
        viewModelScope.launch {
            val result = gitRepository.requestGitRepIssues(GitRepoIssueRequest(org, repo))

            if (result.isSuccessful) {
                updateGitIssue.postValue(result.body())
                orgRepoName.value = "${org}/${repo}"
            }
        }
    }
}