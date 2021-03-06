package com.example.myapp.myapp.data.api;

import java.util.Map;

import com.example.myapp.myapp.data.bean.BannerBean;
import com.example.myapp.myapp.data.bean.FavoriteResponse;
import com.example.myapp.myapp.data.bean.HomeItemBean;
import com.example.myapp.myapp.data.bean.RegisterResponse;
import com.example.myapp.myapp.data.bean.WanAndroidBaseReponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yexing on 2018/3/29.
 */

public interface WandroidApi {

    /**
     * 轮播图
     *
     * @return
     */
    @GET(AppUrl.BANNER)
    Observable<BannerBean> getBanner();

    /**
     * 首页
     */
    @GET("article/list/{id}/json")
    Observable<HomeItemBean> getHome(@Path("id") int id);


    //get 请求用QueryMap,  post请求用FieldMap,还要加上注解  @FormUrlEncoded

    //下载操作
    @Streaming
    @GET
    Call<ResponseBody> downLoad(@Url String url, @QueryMap Map<String, String> params);

    /**
     * 上传操作
     */
    @Multipart
    @POST
    Call<String> upLoad(@Url String url, @Part MultipartBody.Part file);


    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<RegisterResponse> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username   账号
     * @param password   密码
     * @param repassword 重新输入密码
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<RegisterResponse> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 收藏站内文章
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<WanAndroidBaseReponse> collect(@Path("id") int id);

    /**
     * 取消收藏
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<WanAndroidBaseReponse> unCollect(@Path("id") int id);

    /**
     * 获取收藏列表
     *
     * @param id
     * @return
     */
    @GET("lg/collect/list/{id}/json")
    Observable<FavoriteResponse> favorite(@Path("id") int id);


}
