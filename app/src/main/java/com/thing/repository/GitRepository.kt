package com.thing.repository

import com.thing.api.apiHelper.GitHubApiHelper
import com.thing.data.GitRepoIssueRequest
import com.thing.data.GitRepoIssueResponse
import retrofit2.Response
import javax.inject.Inject

class GitRepository @Inject constructor(private val gitHubApiHelper: GitHubApiHelper) {

    /**
     * git 저장소 이슈 데이터 요청
     */
    suspend fun requestGitRepIssues(gitRepoIssueRequest: GitRepoIssueRequest):Response<ArrayList<GitRepoIssueResponse>>{
        return gitHubApiHelper.requestGitRepoIssues(gitRepoIssueRequest)
    }
}