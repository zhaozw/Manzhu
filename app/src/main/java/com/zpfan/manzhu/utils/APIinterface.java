package com.zpfan.manzhu.utils;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * retrofit 访问网络的接口
 * Created by Administrator on 2017/6/9 0009.
 */

public interface APIinterface {

    /**
     * 登陆的接口
     *
     * @param phonenumber
     * @param password
     * @return
     */
    @GET("Login")
    Call<String> login(@Query("v_phonenumber") String phonenumber, @Query("v_password") String password);

    /**
     * 通过手机号码 获取用户头像的接口
     *
     * @param phonenumber
     * @return
     */
    @GET("getmemberavatore_by_phone")
    Call<String> getavator(@Query("v_phonenumber") String phonenumber);


    /**
     * 通过手机号 查看这个手机号是否被注册过
     *
     * @param phonenumber
     * @return
     */
    @GET("get_phone_reg_by_phone")
    Call<String> getphonuseable(@Query("v_phonenumber") String phonenumber);


    /**
     * 根据cn来判断用户是否注册过
     *
     * @param cn
     * @return
     */
    @GET("get_cn_reg_by_phone")
    Call<String> getcnuseable(@Query("v_cn") String cn);


    /**
     * 根据手机号获取验证码
     *
     * @param phone
     * @return
     */
    @GET("RegisterSendCode")
    Call<String> getwebCode(@Query("v_phone") String phone);


    /**
     * 注册的接口
     *
     * @param phonenumber
     * @param cnname
     * @param fcode
     * @param passsword
     * @param webcode
     * @return
     */
    @GET("Register")
    Call<String> register(@Query("v_phonenumber") String phonenumber, @Query("v_cn_name") String cnname,
                          @Query("v_fcode") String fcode, @Query("v_password") String passsword,
                          @Query("v_webcode") String webcode);


    /**
     * 通过手机号查找到用户的信息
     *
     * @param phonenumber
     * @param cn
     * @return
     */
    @GET("through_cn_phone_searchmember")
    Call<String> findpassword(@Query("v_phonenumber") String phonenumber, @Query("v_cn") String cn);


    /**
     * 根据手机号和验证码  来判断填写的验证码是否正确
     *
     * @param phonenumber
     * @param webcode
     * @return
     */
    @GET("through_cn_phone_validation_messagecode")
    Call<String> checkwebcode(@Query("v_phonenumber") String phonenumber, @Query("v_webcode") String webcode);


    /**
     * 把新密码 提交到服务器
     *
     * @param uid
     * @param pw
     * @return
     */
    @GET("through_cn_phone_reset_pwd")
    Call<String> importnewpw(@Query("member_uid") String uid, @Query("pwd_value") String pw);


    /**
     * 通过手机号和cn 来查询用户
     *
     * @param keyword
     * @return
     */
    @GET("getchatmemberlist_by_cnshopname")
    Call<String> seachuser(@Query("Page") String page, @Query("keyword") String keyword);


    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @Multipart
    @POST("CommUploadImg")
    Call<String> uploadimg(@Part MultipartBody.Part file);


    /**
     * 获取会员聊天对象的列表
     *
     * @param phone
     * @return
     */
    @GET("get_member_chatobj_list")
    Call<String> getchatobjlist(@Query("member_phone") String phone);


    /**
     * 获取最后一条消息
     *
     * @param from
     * @param to
     * @return
     */
    @GET("get_memberchat_list_lastlog")
    Call<String> getlastMessage(@Query("frommember_phone") String from, @Query("tomember_phone") String to);

    /**
     * 保存会员聊天对象记录
     *
     * @param myphone
     * @param tophone
     * @return
     */
    @GET("save_member_chat_obj")
    Call<String> saveChatbj(@Query("fromphone") String myphone, @Query("tophone") String tophone);


    /**
     * 保存会员聊天记录
     *
     * @return
     */
    @GET("save_member_chat_log")
    Call<String> saveChatLog(@Query("obj_A") String fromphone, @Query("obj_B") String tophone, @Query("message") String message, @Query("message_type") String messagetype);


    /**
     * 获取会员聊天记录
     *
     * @param fromphone
     * @param toPhone
     * @return
     */
    @GET("get_member_chatlog_list")
    Call<String> getChatlogList(@Query("Page") int i, @Query("frommember_phone") String fromphone, @Query("tomember_phone") String toPhone);

    /**
     * 获取商品列表的接口
     *
     * @param
     * @param
     * @return
     */
    @GET("getgoodslist")
    Call<String> getgoodslist(@QueryMap Map<String, String> map);


    /**
     * 首页获取
     *
     * @param cate
     * @param key
     * @param psid
     * @return
     */
    @GET("opera_searchkeywordresult_function")
    Call<String> searchkeyword(@Query("cate") String cate, @Query("key") String key, @Query("p_s_id") String psid);


    /**
     * 获取商品类型的接口
     *
     * @param cate
     * @param titele
     * @return
     */
    @GET("getproducttypelist")
    Call<String> getproducttype(@Query("catevalue") String cate, @Query("titlevalue") String titele);


    /**
     * \
     * 获取商家详细信息的接口
     *
     * @param uid
     * @return
     */
    @GET("getshopdetail")
    Call<String> getshopdetail(@Query("member_uid") String uid);


    /**
     * 获取相关cos作品的信息
     *
     * @param obj_cate
     * @param location_name
     * @param cosworkID_arry
     * @return
     */
    @GET("get_coswork_by_goodsmodle")
    Call<String> getcosworkbygoodsmodle(@Query("obj_cate") String obj_cate, @Query("location_name") String location_name, @Query("cosworkID_arry") String cosworkID_arry);


    /**
     * 将商品添加到收藏的方法
     *
     * @param obj_cate
     * @param obj_id
     * @param obj_member_uid
     * @return
     */
    @GET("opera_collection_function")
    Call<String> operacollectionfunction(@Query("obj_cate") String obj_cate, @Query("obj_id") String obj_id, @Query("obj_member_uid") String obj_member_uid);


    /**
     * 更新购物车数据的方法
     *
     * @param car_uid
     * @param member_uid
     * @param goods_uid
     * @param goods_ps_uid
     * @param bussiness_uid
     * @param car_count
     * @return
     */
    @GET("opera_add_update_shoppingcar_function")
    Call<String> operaaddupdateshopcart(@Query("car_uid") String car_uid, @Query("member_uid") String member_uid, @Query("goods_uid") String goods_uid, @Query("goods_ps_uid") String goods_ps_uid
            , @Query("bussiness_uid") String bussiness_uid, @Query("car_count") String car_count
    );

    /**
     * 获取购物车列表的方法
     *
     * @param member_uid
     * @param cate_obj
     * @return
     */
    @GET("get_shoppingcar_list")
    Call<String> getshopcarlist(@Query("member_uid") String member_uid, @Query("cate_obj") String cate_obj);

}