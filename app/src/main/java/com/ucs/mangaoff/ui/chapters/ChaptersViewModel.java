package com.ucs.mangaoff.ui.chapters;

import com.ucs.mangaoff.MainActivity;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChaptersViewModel implements Serializable {

    RetrofitEndpoints service;
    MainActivity activity;
    ResponseMangasData mangaData;

    public ChaptersViewModel(RetrofitEndpoints service,
                             MainActivity activity,
                             ResponseMangasData mangaData) {
        this.service = service;
        this.activity = activity;
        this.mangaData = mangaData;
    }

    public void getChapters(Callback<ResponseChapters> callback) {
        Call<ResponseChapters> call = service.getChapters("https://api.mangadex.org/manga/" +
                mangaData.getId() +
                "/feed?limit=96&includes[]=scanlation_group&includes[]=user&order[volume]=desc&order[chapter]=desc&offset=0&contentRating[]=safe");
        call.enqueue(new Callback<ResponseChapters>() {
            @Override
            public void onResponse(Call<ResponseChapters> call, Response<ResponseChapters> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseChapters> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    // routes

    void routeToPages(String chapterHash, List<String> pages) {
        activity.routeToPages(chapterHash, pages,0);
    }
}