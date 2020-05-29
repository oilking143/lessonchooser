package com.example.testschoolschedule.model;

import com.example.testschoolschedule.BuildConfig;
import com.example.testschoolschedule.model.server.LessonResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class ApiClient {
    private static ApiClient sApiClient = null;
    private OkHttpClient mHttpClient;
    private Api mApi;
    private Gson gson;

    public static synchronized ApiClient getInstance() {
        if (sApiClient == null) {
            sApiClient = new ApiClient();
        }
        return sApiClient;
    }

    private ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       mHttpClient=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
           @NotNull
           @Override
           public Response intercept(@NotNull Chain chain) throws IOException {
               Request original = chain.request();
               Request request = original.newBuilder()
//                       .header("uid", uid)
//                       .header("imkey", imKey)
                       .method(original.method(), original.body())
                       .build();

               return chain.proceed(request);
           }
       }).addInterceptor(interceptor).build();

        mApi = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mHttpClient)
                .build()
                .create(Api.class);

    }

    private Api getApi() {
        return mApi;
    }

    public static Single<retrofit2.Response<LessonResponse>> getLesson() {
        return ApiClient.getInstance().getApi().getLesson();
    }

    public interface Api {

        @GET("exec")
        Single<retrofit2.Response<LessonResponse>> getLesson();
    }

}
