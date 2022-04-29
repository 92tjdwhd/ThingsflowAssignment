package com.thing.api.apiHelper

import com.thing.data.GitRepoIssueRequest
import com.thing.data.GitRepoIssueResponse
import retrofit2.Response

interface GitHubApiHelper {

    suspend fun requestGitRepoIssues(gitRepoIssueRequest: GitRepoIssueRequest):Response<ArrayList<GitRepoIssueResponse>>
}