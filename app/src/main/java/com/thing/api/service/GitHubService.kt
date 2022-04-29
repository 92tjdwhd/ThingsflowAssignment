package com.thing.api.service

import com.thing.data.GitRepoIssueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("repos/{org}/{repo}/issues")
    suspend fun requestGitRepoIssues(@Path("org") org: String, @Path("repo") repo: String) :Response<ArrayList<GitRepoIssueResponse>>
}