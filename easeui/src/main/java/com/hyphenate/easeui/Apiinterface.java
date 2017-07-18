package com.hyphenate.easeui;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static com.baidu.location.d.j.q;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public interface Apiinterface  {

    /**
     * 通过手机号码 获取用户头像的接口
     * @param phonenumber
     * @return
     */
    @GET("getmemberavatore_by_phone")
    Call<String> getavator(@Query("v_phonenumber") String phonenumber);

    /**
     * 上传图片
     * @param file
     * @return
     */
    @Multipart
    @POST("CommUploadImg")
    Call<String> uploadimg(@Part List<MultipartBody.Part> parts);

    /**
     * 通过手机号和cn 来查询用户
     * @param keyword
     * @return
     */
    @GET("getchatmemberlist_by_cnshopname")
    Call<String> seachuser(@Query("keyword") String keyword);


    /**
     * 通过接口来保存聊天信息
     * @param myphon
     * @param objphone
     * @param message
     * @param messagetype
     * @return
     */
    @GET("save_member_chat_log")
    Call<String> saveChatLog(@Query("obj_A") String myphon, @Query("obj_B") String objphone, @Query("message") String message, @Query("message_type") String messagetype);


    /**
     * 获取会员聊天记录
     * @param fromphone
     * @param toPhone
     * @return
     */
    @GET("get_member_chatlog_list")
    Call<String> getChatlogList(@Query("Page") int i,@Query("frommember_phone") String fromphone, @Query("tomember_phone")String toPhone);


    /**
     * 保存会员聊天对象记录
     * @param myphone
     * @param tophone
     * @return
     */
    @GET("save_member_chat_obj")
    Call<String> saveChatbj(@Query("fromphone")  String myphone,@Query("tophone")  String tophone );







}
