package com.tonglu.glossary.glossary.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tonglu.glossary.glossary.model.BigBangBean;
import com.tonglu.glossary.glossary.model.CodeSearchBean;
import com.tonglu.glossary.glossary.model.FanyiBean;
import com.tonglu.glossary.glossary.utils.BigBangRetrofitService;
import com.tonglu.glossary.glossary.utils.CommonApi;
import com.tonglu.glossary.glossary.utils.Constant;
import com.tonglu.glossary.glossary.utils.FanyiRetrofitService;
import com.tonglu.glossary.glossary.utils.SearchCodeRetrofitService;
import com.tonglu.glossary.glossary.view.viewInterface.BigBangInterface;
import com.tonglu.glossary.glossary.view.widget.BangWordView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tonglu on 2017/2/8.
 */
public class BigBangPresenter extends BasePresenter {
    private static final String TAG = "BigBangPresenter";
    BigBangInterface view;

    ArrayList<BigBangBean> bigBangBeens = new ArrayList<>();

    public BigBangPresenter(Context context, BigBangInterface view) {
        super(context, view);
        this.view = view;
    }

    public void bang(String bangContent) {


        Call<JsonArray> call = null;

        call = BigBangRetrofitService.getInstance().createService(CommonApi.class).bigBangApi(bangContent, Constant.XunfeiAppkey, "ws", "json");

        if (call != null) {
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                    JsonArray result = response.body();
                    if(result!=null){
                        JsonArray array1 = (JsonArray) result.get(0);

                        for (int i = 0; i < array1.size(); i++) {
                            JsonArray array = (JsonArray) array1.get(i);
                            for (int j = 0; j < array.size(); j++) {
                                JsonObject s = (JsonObject) array.get(j);
                                String cont = s.get("cont").getAsString();
                                BigBangBean bigBangBean = new BigBangBean();
                                bigBangBean.cont = cont;
                                bigBangBeens.add(bigBangBean);
                            }

                        }
                    }



                    view.showBangView(bigBangBeens);
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.i(TAG, "bigbang result：onFailure：" + t.getMessage());
                }
            });

        } else {
            Log.i(TAG, "bigbang result：call==null");
        }


    }

    public void fanyi(final BangWordView v) {
        Call<FanyiBean> call = null;
        String q = v.getText().toString();

        call = FanyiRetrofitService.getInstance().createService(CommonApi.class).fanyiApi(q, Constant.keyfrom, Constant.appkey, "data", "json", "1.1");
        if (call != null) {
            call.enqueue(new Callback<FanyiBean>() {
                @Override
                public void onResponse(Call<FanyiBean> call, Response<FanyiBean> response) {
                    FanyiBean result = response.body();

                    Log.i(TAG, "fanyi result：" + result.toString());

                    view.showPopView(v,result);
                }

                @Override
                public void onFailure(Call<FanyiBean> call, Throwable t) {

                }
            });
        } else {
            Log.i(TAG, "fanyi result：call==null");
        }

    }


}
