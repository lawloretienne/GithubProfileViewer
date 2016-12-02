package com.sample.github.network;


import com.sample.github.network.models.response.Event;
import com.sample.github.network.models.response.Repository;
import com.sample.github.network.models.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by etiennelawlor on 5/17/16.
 */
public interface GithubService {

    String BASE_URL = "https://api.github.com/users/";

    @GET("{username}?client_id=378faee748383b4d5290&client_secret=0f5e45f7526789f077e9b0d2e052718b5122aecc")
    Call<User> getUser(@Path("username") String username);

    @GET("{username}/repos?sort=pushed&direction=desc&client_id=378faee748383b4d5290&client_secret=0f5e45f7526789f077e9b0d2e052718b5122aecc")
    Call<List<Repository>> getRepositories(@Path("username") String username, @Query("page") int page, @Query("per_page") int per_page);

    @GET("{username}/events?client_id=378faee748383b4d5290&client_secret=0f5e45f7526789f077e9b0d2e052718b5122aecc")
    Call<List<Event>> getEvents(@Path("username") String username, @Query("page") int page, @Query("per_page") int per_page);
}
