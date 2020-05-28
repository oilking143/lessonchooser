package com.example.testschoolschedule.model;

import android.util.Log;

import com.example.testschoolschedule.model.server.LessonResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ApiServer {

    private CompositeDisposable mDisposables = new CompositeDisposable();
    private ApiServer() {

    }

    private static ApiServer sInstance;

    public static synchronized ApiServer getInstance() {
        if (sInstance == null) {
            sInstance = new ApiServer();
        }
        return sInstance;
    }


    public void getLessonApis(){
        mDisposables.add(ApiClient.getLesson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<LessonResponse>>() {
                    @Override
                    public void onSuccess(Response<LessonResponse> getAttentionCounts) {
                        LessonResponse response=getAttentionCounts.body();
                        Log.d("Response",response.getStudentA().toString());
//                        EventBus.getDefault().post(new DZApiCountEvent(Constant.AttentionTag,response.getMessage(),response));
                    }
                    @Override
                    public void onError(Throwable e) {
//                        EventBus.getDefault().post(new DZApiCountEvent(Constant.RESPONSE_FAIL_NET,"Fail",null));
                    }
                }));
    }

}
