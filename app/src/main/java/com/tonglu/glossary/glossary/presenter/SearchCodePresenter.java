package com.tonglu.glossary.glossary.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tonglu.glossary.glossary.model.CodeSearchBean;
import com.tonglu.glossary.glossary.model.CodeSearchItemBean;
import com.tonglu.glossary.glossary.utils.CommonApi;
import com.tonglu.glossary.glossary.utils.SearchCodeRetrofitService;
import com.tonglu.glossary.glossary.view.activity.SearchCodeDetailActivity;
import com.tonglu.glossary.glossary.view.viewInterface.SearchCodeInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tonglu on 2017/2/14.
 */
public class SearchCodePresenter extends BasePresenter {
    private static final String TAG = "SearchCodePresenter";
    SearchCodeInterface view;
    CodeSearchBean result;

    public SearchCodePresenter(Context context, SearchCodeInterface view) {

        super(context, view);
        this.view = view;

    }

    public void searchCode(String q) {
        Call<CodeSearchBean> call = null;

        call = SearchCodeRetrofitService.getInstance().createService(CommonApi.class).searchCodeApi(q, 1, 100);
        if (call != null) {
            call.enqueue(new Callback<CodeSearchBean>() {
                @Override
                public void onResponse(Call<CodeSearchBean> call, Response<CodeSearchBean> response) {
                    result = response.body();

                    ArrayList<CodeSearchItemBean> results = result.results;

                    view.setAdapterData(result);


                    Log.i(TAG, "searchCode result：" + result.searchterm);
                }

                @Override
                public void onFailure(Call<CodeSearchBean> call, Throwable t) {

                }
            });
        } else {
            Log.i(TAG, "searchCode result：call==null");
        }

    }


    public void onItemClik(int position) {
        if (result != null && result.results != null && result.results.size() > position) {
            String url = result.results.get(position).url;
            Intent intent = new Intent(context, SearchCodeDetailActivity.class);
            intent.putExtra("url", url);
            context.startActivity(intent);
        }


    }
}
