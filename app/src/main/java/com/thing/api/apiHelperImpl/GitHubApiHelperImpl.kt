package com.thing.api.apiHelperImpl

import com.thing.api.apiHelper.GitHubApiHelper
import com.thing.api.service.GitHubService
import com.thing.data.GitRepoIssueRequest
import com.thing.data.GitRepoIssueResponse
import retrofit2.Response
import javax.inject.Inject

class GitHubApiHelperImpl @Inject constructor(private val gitHubService: GitHubService) : GitHubApiHelper {
    override suspend fun requestGitRepoIssues(gitRepoIssueRequest: GitRepoIssueRequest): Response<ArrayList<GitRepoIssueResponse>> =
        gitHubService.requestGitRepoIssues(gitRepoIssueRequest.org, gitRepoIssueRequest.repo)
}