package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class ShopCartbean implements Parcelable {




    /**
     * carshoplist : [{"IsPerson":true,"Member_Level":5,"Member_Name":"猪排子的店","Member_UID":"20169720335385547137","cargoodslist":[{"Bussiness_UID":"20169720335385547137","CarCount":1,"Goods_Spcification_UID":"","Goods_UID":"201611251261877191084","Member_UID":"201761214535744417547","SC_UID":"ShoppingCar201772416301418913497","goods_model":{"BrandID":"","Demand_FK":"","G_Agent_Member_UID":"","G_Area":"市辖区","G_AuditStatus":"审核通过","G_BasicLease":0,"G_City":"成都市","G_CommissionMoney":"23.00","G_ContactPhone":"18508103615","G_ContactQQ":"","G_CorrespAmount":"0.00","G_CosworkIDs":"","G_CourierCompanyID":0,"G_CourierMoney":"0.00","G_Cover":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg","G_DepositPrice":"23.00","G_DetailRemarks":"asdfasfasfasfasf","G_FixedPrice":"200.00","G_GoodsFreightNum":"","G_Hits":46,"G_Images":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg,","G_IsChange":false,"G_IsDepositDeal":true,"G_IsFreeService":false,"G_IsFreeShip":true,"G_IsMorePrice":false,"G_IsOfflineDeal":false,"G_IsOffline_rentdeal":false,"G_IsOnline_rentdeal":false,"G_IsRecommend":false,"G_IsRent":false,"G_IsSale":true,"G_IsServiceBook":false,"G_IsShelves":true,"G_IsShowIndex":false,"G_IsTJByShop":false,"G_Isdraught":false,"G_Isurgentsale":true,"G_MarketingPrice":"0.00","G_Member_OBJ":{"M_Account_Status":"正常","M_Area":"市辖区","M_Avatar":"\\/UploadFolder\\/image\\/201705\\/20175181253057401172.jpg","M_BusinessType":"品牌商家","M_City":"成都市","M_Email":"","M_FCode":"ArRwi","M_IDCard":"510105201512140000","M_IsBusiness":true,"M_IsLock":false,"M_IsRealNameAuth":true,"M_IsSkill":false,"M_MemberLevel":4,"M_Name":"猪排子","M_Phone":"18508103615","M_Province":"四川省","M_Pwd":"E10ADC3949BA59ABBE56E057F20F883E","M_RegIP":"171.217.145.83","M_RegTime":"2016-09-07 20:33:54","M_Sex":"女","M_StoreLevel":24,"M_UID":"20169720335385547137","M_UserName":"猪排子","N_AllLevel":28,"S_DisputeProportion":3.6,"member_identity":"COSER|后期|摄影师|舞师|妆娘|","order_deal_count":66},"G_NewOldDegree":"","G_NoPassReason":"","G_NoPassReasonDetail":"","G_OrderNum":0,"G_Province":"四川省","G_ReadCount":0,"G_RefreshTime":"2017-05-28 13:20:45","G_RenewalPrice":"0.00","G_SaleNum":2,"G_ShareNumber":0,"G_ShopOrderNum":0,"G_StockNum":86,"G_SubTitle":"adfasfasfa","G_Title":"定金订单测试分享佣金\u2026\u2026","G_Type":"新商品","G_UID":"201611251261877191084","G_UpTime":"2016-11-25 01:26:18","G_Weight":0.5,"G_appointment_mintime_value":0,"G_appointment_price_1":"0.00","G_appointment_price_2":"0.00","G_appointment_price_3":"0.00","G_appointment_time_value_1":0,"G_appointment_time_value_2":0,"G_appointment_time_value_3":0,"G_appointment_time_value_4":0,"G_appointment_time_value_5":0,"G_appointment_time_value_6":0,"GoodsTypeID":4,"GoodsTypeID2":0,"Member_UID":"20169720335385547137","TelevisionWorks_FK":"","VirtualRole_FK":"","attitude_number_value":"0.0","bbkpd_member_value":"4.5","bbmsxfd_member_value":"0","collection_number":0,"complatespeed_value":"0.0","czrkpd_member_value":"4.2","goods_cosworks":"","goods_parameter":"组套情况：未知,肩宽(CM)：46,胸围(CM)：52,袖长(CM)：59,衣长(CM)：68,腰围(CM)：48,裤长(CM)：75,身高(CM)：165,体重(KG)：95,臀围(CM)：54,面料柔软：未知,面料厚度：未知,版型指数：未知,衣长指数：未知,","goods_specifications":[],"id":104,"mjfhsd_member_value":"0","mjfutd_member_value":"0","mjkpd_member_value":"5.0","order_review_list":[],"order_sellerfigure":[{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdsaf","OR_Sellerdegrees":5,"OR_Sellerimpression":"adfasfas","OR_Time":"2017-06-09 17:55","OrderCate":"租赁订单","Order_UID":"2017691011962410638","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":88,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":4,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"沙发大发发生","OR_Sellerdegrees":4,"OR_Sellerimpression":"暗示法撒地方撒发生","OR_Time":"2017-06-09 06:39","OrderCate":"租赁订单","Order_UID":"2017695492184477357","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":87,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"","OR_Sellerdegrees":5,"OR_Sellerimpression":"法师法师发大水","OR_Time":"2017-06-07 17:59","OrderCate":"租赁订单","Order_UID":"20176716431440212793","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":84,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20161194442457287949","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":4,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"","OR_GoodsComment":"","OR_Sellerdegrees":0,"OR_Sellerimpression":"n\u2006x\u2006n\u2006n\u2006x\u2006n\u2006x\u2006n","OR_Time":"2017-05-17 08:14","OrderCate":"购物订单","Order_UID":"20175176171897923354","Store_UID":"20169720335385547137","goods_spcification_name":"","id":79,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"很好的东西，不错不错","OR_Sellerdegrees":5,"OR_Sellerimpression":"王小帅很好的东西，不错不错","OR_Time":"2017-02-17 18:01","OrderCate":"购物订单","Order_UID":"2017131336812111905","Store_UID":"20169720335385547137","goods_spcification_name":"","id":6,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20171193493153455371","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":5,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"fdsafafsaf","OR_Sellerdegrees":0,"OR_Sellerimpression":"adfasfasfasdfasfafsfda","OR_Time":"2017-02-07 18:01","OrderCate":"购物订单","Order_UID":"20172717585835909771","Store_UID":"20169720335385547137","goods_spcification_name":"","id":4,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdssafasfsaf","OR_Sellerdegrees":4,"OR_Sellerimpression":"afdasfsafsafsafsa","OR_Time":"2017-01-26 01:39","OrderCate":"租赁订单","Order_UID":"20171187375334227142","Store_UID":"20169720335385547137","goods_spcification_name":"","id":3,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":3,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"长春 v 不合格","OR_Sellerdegrees":3,"OR_Sellerimpression":"复古风句vg","OR_Time":"2017-01-18 04:11","OrderCate":"租赁订单","Order_UID":"2017118353458906882","Store_UID":"20169720335385547137","goods_spcification_name":"","id":2,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"}],"professionaldegree_value":"0.0","shop_coupon_count":3,"showtype":1}}]}]
     * idle_number : 1
     * new_number : 1
     * server_number : 1
     */

    private int idle_number;
    private int new_number;
    private int server_number;
    private ArrayList<CarshoplistBean> carshoplist;

    public int getIdle_number() {
        return idle_number;
    }

    public void setIdle_number(int idle_number) {
        this.idle_number = idle_number;
    }

    public int getNew_number() {
        return new_number;
    }

    public void setNew_number(int new_number) {
        this.new_number = new_number;
    }

    public int getServer_number() {
        return server_number;
    }

    public void setServer_number(int server_number) {
        this.server_number = server_number;
    }

    public ArrayList<CarshoplistBean> getCarshoplist() {
        return carshoplist;
    }

    public void setCarshoplist(ArrayList<CarshoplistBean> carshoplist) {
        this.carshoplist = carshoplist;
    }

    public static class CarshoplistBean implements Parcelable {


        /**
         * IsPerson : true
         * Member_Level : 5
         * Member_Name : 猪排子的店
         * Member_UID : 20169720335385547137
         * cargoodslist : [{"Bussiness_UID":"20169720335385547137","CarCount":1,"Goods_Spcification_UID":"","Goods_UID":"201611251261877191084","Member_UID":"201761214535744417547","SC_UID":"ShoppingCar201772416301418913497","goods_model":{"BrandID":"","Demand_FK":"","G_Agent_Member_UID":"","G_Area":"市辖区","G_AuditStatus":"审核通过","G_BasicLease":0,"G_City":"成都市","G_CommissionMoney":"23.00","G_ContactPhone":"18508103615","G_ContactQQ":"","G_CorrespAmount":"0.00","G_CosworkIDs":"","G_CourierCompanyID":0,"G_CourierMoney":"0.00","G_Cover":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg","G_DepositPrice":"23.00","G_DetailRemarks":"asdfasfasfasfasf","G_FixedPrice":"200.00","G_GoodsFreightNum":"","G_Hits":46,"G_Images":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg,","G_IsChange":false,"G_IsDepositDeal":true,"G_IsFreeService":false,"G_IsFreeShip":true,"G_IsMorePrice":false,"G_IsOfflineDeal":false,"G_IsOffline_rentdeal":false,"G_IsOnline_rentdeal":false,"G_IsRecommend":false,"G_IsRent":false,"G_IsSale":true,"G_IsServiceBook":false,"G_IsShelves":true,"G_IsShowIndex":false,"G_IsTJByShop":false,"G_Isdraught":false,"G_Isurgentsale":true,"G_MarketingPrice":"0.00","G_Member_OBJ":{"M_Account_Status":"正常","M_Area":"市辖区","M_Avatar":"\\/UploadFolder\\/image\\/201705\\/20175181253057401172.jpg","M_BusinessType":"品牌商家","M_City":"成都市","M_Email":"","M_FCode":"ArRwi","M_IDCard":"510105201512140000","M_IsBusiness":true,"M_IsLock":false,"M_IsRealNameAuth":true,"M_IsSkill":false,"M_MemberLevel":4,"M_Name":"猪排子","M_Phone":"18508103615","M_Province":"四川省","M_Pwd":"E10ADC3949BA59ABBE56E057F20F883E","M_RegIP":"171.217.145.83","M_RegTime":"2016-09-07 20:33:54","M_Sex":"女","M_StoreLevel":24,"M_UID":"20169720335385547137","M_UserName":"猪排子","N_AllLevel":28,"S_DisputeProportion":3.6,"member_identity":"COSER|后期|摄影师|舞师|妆娘|","order_deal_count":66},"G_NewOldDegree":"","G_NoPassReason":"","G_NoPassReasonDetail":"","G_OrderNum":0,"G_Province":"四川省","G_ReadCount":0,"G_RefreshTime":"2017-05-28 13:20:45","G_RenewalPrice":"0.00","G_SaleNum":2,"G_ShareNumber":0,"G_ShopOrderNum":0,"G_StockNum":86,"G_SubTitle":"adfasfasfa","G_Title":"定金订单测试分享佣金\u2026\u2026","G_Type":"新商品","G_UID":"201611251261877191084","G_UpTime":"2016-11-25 01:26:18","G_Weight":0.5,"G_appointment_mintime_value":0,"G_appointment_price_1":"0.00","G_appointment_price_2":"0.00","G_appointment_price_3":"0.00","G_appointment_time_value_1":0,"G_appointment_time_value_2":0,"G_appointment_time_value_3":0,"G_appointment_time_value_4":0,"G_appointment_time_value_5":0,"G_appointment_time_value_6":0,"GoodsTypeID":4,"GoodsTypeID2":0,"Member_UID":"20169720335385547137","TelevisionWorks_FK":"","VirtualRole_FK":"","attitude_number_value":"0.0","bbkpd_member_value":"4.5","bbmsxfd_member_value":"0","collection_number":0,"complatespeed_value":"0.0","czrkpd_member_value":"4.2","goods_cosworks":"","goods_parameter":"组套情况：未知,肩宽(CM)：46,胸围(CM)：52,袖长(CM)：59,衣长(CM)：68,腰围(CM)：48,裤长(CM)：75,身高(CM)：165,体重(KG)：95,臀围(CM)：54,面料柔软：未知,面料厚度：未知,版型指数：未知,衣长指数：未知,","goods_specifications":[],"id":104,"mjfhsd_member_value":"0","mjfutd_member_value":"0","mjkpd_member_value":"5.0","order_review_list":[],"order_sellerfigure":[{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdsaf","OR_Sellerdegrees":5,"OR_Sellerimpression":"adfasfas","OR_Time":"2017-06-09 17:55","OrderCate":"租赁订单","Order_UID":"2017691011962410638","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":88,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":4,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"沙发大发发生","OR_Sellerdegrees":4,"OR_Sellerimpression":"暗示法撒地方撒发生","OR_Time":"2017-06-09 06:39","OrderCate":"租赁订单","Order_UID":"2017695492184477357","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":87,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"","OR_Sellerdegrees":5,"OR_Sellerimpression":"法师法师发大水","OR_Time":"2017-06-07 17:59","OrderCate":"租赁订单","Order_UID":"20176716431440212793","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":84,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20161194442457287949","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":4,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"","OR_GoodsComment":"","OR_Sellerdegrees":0,"OR_Sellerimpression":"n\u2006x\u2006n\u2006n\u2006x\u2006n\u2006x\u2006n","OR_Time":"2017-05-17 08:14","OrderCate":"购物订单","Order_UID":"20175176171897923354","Store_UID":"20169720335385547137","goods_spcification_name":"","id":79,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"很好的东西，不错不错","OR_Sellerdegrees":5,"OR_Sellerimpression":"王小帅很好的东西，不错不错","OR_Time":"2017-02-17 18:01","OrderCate":"购物订单","Order_UID":"2017131336812111905","Store_UID":"20169720335385547137","goods_spcification_name":"","id":6,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20171193493153455371","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":5,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"fdsafafsaf","OR_Sellerdegrees":0,"OR_Sellerimpression":"adfasfasfasdfasfafsfda","OR_Time":"2017-02-07 18:01","OrderCate":"购物订单","Order_UID":"20172717585835909771","Store_UID":"20169720335385547137","goods_spcification_name":"","id":4,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdssafasfsaf","OR_Sellerdegrees":4,"OR_Sellerimpression":"afdasfsafsafsafsa","OR_Time":"2017-01-26 01:39","OrderCate":"租赁订单","Order_UID":"20171187375334227142","Store_UID":"20169720335385547137","goods_spcification_name":"","id":3,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":3,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"长春 v 不合格","OR_Sellerdegrees":3,"OR_Sellerimpression":"复古风句vg","OR_Time":"2017-01-18 04:11","OrderCate":"租赁订单","Order_UID":"2017118353458906882","Store_UID":"20169720335385547137","goods_spcification_name":"","id":2,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"}],"professionaldegree_value":"0.0","shop_coupon_count":3,"showtype":1}}]
         */

        private boolean IsPerson;
        private int Member_Level;
        private String Member_Name;
        private String Member_UID;
        private ArrayList<CargoodslistBean> cargoodslist;
        private ArrayList<CargoodslistBean> checkgoodslist = new ArrayList<>();
        private String liuyan = "给卖家的补充说明都可以写在这里";
        private double yunfei = 0;
        private String couponid = "";
        private String coupon = "0.00";
        private String jiaoyi = "";
        private String huoqujiaoyi = "";
        private boolean ischeckallgood = false;
        private boolean iscleancheck = false;
        private int showtype = 1;




        public int getShowtype() {
            return showtype;
        }

        public void setShowtype(int showtype) {
            this.showtype = showtype;
        }

        public boolean iscleancheck() {
            return iscleancheck;
        }

        public void setIscleancheck(boolean iscleancheck) {
            this.iscleancheck = iscleancheck;
        }

        public boolean ischeckallgood() {
            return ischeckallgood;
        }

        public void setIscheckallgood(boolean ischeckallgood) {
            this.ischeckallgood = ischeckallgood;
        }

        public String getHuoqujiaoyi() {
            return huoqujiaoyi;
        }

        public void setHuoqujiaoyi(String huoqujiaoyi) {
            this.huoqujiaoyi = huoqujiaoyi;
        }

        public String getJiaoyi() {
            return jiaoyi;
        }

        public void setJiaoyi(String jiaoyi) {
            this.jiaoyi = jiaoyi;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }





        public double getYunfei() {
            return yunfei;
        }

        public void setYunfei(double yunfei) {
            this.yunfei = yunfei;
        }

        public String getLiuyan() {
            return liuyan;
        }

        public void setLiuyan(String liuyan) {
            this.liuyan = liuyan;
        }

        public boolean isPerson() {
            return IsPerson;
        }

        public void setPerson(boolean person) {
            IsPerson = person;
        }

        public ArrayList<CargoodslistBean> getCheckgoodslist() {
            return checkgoodslist;
        }

        public void setCheckgoodslist(ArrayList<CargoodslistBean> checkgoodslist) {
            this.checkgoodslist = checkgoodslist;
        }

        public boolean isIsPerson() {
            return IsPerson;
        }

        public void setIsPerson(boolean IsPerson) {
            this.IsPerson = IsPerson;
        }

        public int getMember_Level() {
            return Member_Level;
        }

        public void setMember_Level(int Member_Level) {
            this.Member_Level = Member_Level;
        }

        public String getMember_Name() {
            return Member_Name;
        }

        public void setMember_Name(String Member_Name) {
            this.Member_Name = Member_Name;
        }

        public String getMember_UID() {
            return Member_UID;
        }

        public void setMember_UID(String Member_UID) {
            this.Member_UID = Member_UID;
        }

        public List<CargoodslistBean> getCargoodslist() {
            return cargoodslist;
        }

        public void setCargoodslist(ArrayList<CargoodslistBean> cargoodslist) {
            this.cargoodslist = cargoodslist;
        }

        public static class CargoodslistBean implements Parcelable {


            @Override
            public String toString() {
                return "CargoodslistBean{" +
                        "Bussiness_UID='" + Bussiness_UID + '\'' +
                        ", CarCount=" + CarCount +
                        ", Goods_Spcification_UID='" + Goods_Spcification_UID + '\'' +
                        ", Goods_UID='" + Goods_UID + '\'' +
                        ", Member_UID='" + Member_UID + '\'' +
                        ", SC_UID='" + SC_UID + '\'' +
                        ", goods_model=" + goods_model +
                        '}';
            }

            /**
             * Bussiness_UID : 20169720335385547137
             * CarCount : 1
             * Goods_Spcification_UID :
             * Goods_UID : 201611251261877191084
             * Member_UID : 201761214535744417547
             * SC_UID : ShoppingCar201772416301418913497
             * goods_model : {"BrandID":"","Demand_FK":"","G_Agent_Member_UID":"","G_Area":"市辖区","G_AuditStatus":"审核通过","G_BasicLease":0,"G_City":"成都市","G_CommissionMoney":"23.00","G_ContactPhone":"18508103615","G_ContactQQ":"","G_CorrespAmount":"0.00","G_CosworkIDs":"","G_CourierCompanyID":0,"G_CourierMoney":"0.00","G_Cover":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg","G_DepositPrice":"23.00","G_DetailRemarks":"asdfasfasfasfasf","G_FixedPrice":"200.00","G_GoodsFreightNum":"","G_Hits":46,"G_Images":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201611\\/20161125126498935245.jpg,","G_IsChange":false,"G_IsDepositDeal":true,"G_IsFreeService":false,"G_IsFreeShip":true,"G_IsMorePrice":false,"G_IsOfflineDeal":false,"G_IsOffline_rentdeal":false,"G_IsOnline_rentdeal":false,"G_IsRecommend":false,"G_IsRent":false,"G_IsSale":true,"G_IsServiceBook":false,"G_IsShelves":true,"G_IsShowIndex":false,"G_IsTJByShop":false,"G_Isdraught":false,"G_Isurgentsale":true,"G_MarketingPrice":"0.00","G_Member_OBJ":{"M_Account_Status":"正常","M_Area":"市辖区","M_Avatar":"\\/UploadFolder\\/image\\/201705\\/20175181253057401172.jpg","M_BusinessType":"品牌商家","M_City":"成都市","M_Email":"","M_FCode":"ArRwi","M_IDCard":"510105201512140000","M_IsBusiness":true,"M_IsLock":false,"M_IsRealNameAuth":true,"M_IsSkill":false,"M_MemberLevel":4,"M_Name":"猪排子","M_Phone":"18508103615","M_Province":"四川省","M_Pwd":"E10ADC3949BA59ABBE56E057F20F883E","M_RegIP":"171.217.145.83","M_RegTime":"2016-09-07 20:33:54","M_Sex":"女","M_StoreLevel":24,"M_UID":"20169720335385547137","M_UserName":"猪排子","N_AllLevel":28,"S_DisputeProportion":3.6,"member_identity":"COSER|后期|摄影师|舞师|妆娘|","order_deal_count":66},"G_NewOldDegree":"","G_NoPassReason":"","G_NoPassReasonDetail":"","G_OrderNum":0,"G_Province":"四川省","G_ReadCount":0,"G_RefreshTime":"2017-05-28 13:20:45","G_RenewalPrice":"0.00","G_SaleNum":2,"G_ShareNumber":0,"G_ShopOrderNum":0,"G_StockNum":86,"G_SubTitle":"adfasfasfa","G_Title":"定金订单测试分享佣金\u2026\u2026","G_Type":"新商品","G_UID":"201611251261877191084","G_UpTime":"2016-11-25 01:26:18","G_Weight":0.5,"G_appointment_mintime_value":0,"G_appointment_price_1":"0.00","G_appointment_price_2":"0.00","G_appointment_price_3":"0.00","G_appointment_time_value_1":0,"G_appointment_time_value_2":0,"G_appointment_time_value_3":0,"G_appointment_time_value_4":0,"G_appointment_time_value_5":0,"G_appointment_time_value_6":0,"GoodsTypeID":4,"GoodsTypeID2":0,"Member_UID":"20169720335385547137","TelevisionWorks_FK":"","VirtualRole_FK":"","attitude_number_value":"0.0","bbkpd_member_value":"4.5","bbmsxfd_member_value":"0","collection_number":0,"complatespeed_value":"0.0","czrkpd_member_value":"4.2","goods_cosworks":"","goods_parameter":"组套情况：未知,肩宽(CM)：46,胸围(CM)：52,袖长(CM)：59,衣长(CM)：68,腰围(CM)：48,裤长(CM)：75,身高(CM)：165,体重(KG)：95,臀围(CM)：54,面料柔软：未知,面料厚度：未知,版型指数：未知,衣长指数：未知,","goods_specifications":[],"id":104,"mjfhsd_member_value":"0","mjfutd_member_value":"0","mjkpd_member_value":"5.0","order_review_list":[],"order_sellerfigure":[{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdsaf","OR_Sellerdegrees":5,"OR_Sellerimpression":"adfasfas","OR_Time":"2017-06-09 17:55","OrderCate":"租赁订单","Order_UID":"2017691011962410638","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":88,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":4,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"沙发大发发生","OR_Sellerdegrees":4,"OR_Sellerimpression":"暗示法撒地方撒发生","OR_Time":"2017-06-09 06:39","OrderCate":"租赁订单","Order_UID":"2017695492184477357","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":87,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"","OR_Sellerdegrees":5,"OR_Sellerimpression":"法师法师发大水","OR_Time":"2017-06-07 17:59","OrderCate":"租赁订单","Order_UID":"20176716431440212793","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":84,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20161194442457287949","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":4,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"","OR_GoodsComment":"","OR_Sellerdegrees":0,"OR_Sellerimpression":"n\u2006x\u2006n\u2006n\u2006x\u2006n\u2006x\u2006n","OR_Time":"2017-05-17 08:14","OrderCate":"购物订单","Order_UID":"20175176171897923354","Store_UID":"20169720335385547137","goods_spcification_name":"","id":79,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"很好的东西，不错不错","OR_Sellerdegrees":5,"OR_Sellerimpression":"王小帅很好的东西，不错不错","OR_Time":"2017-02-17 18:01","OrderCate":"购物订单","Order_UID":"2017131336812111905","Store_UID":"20169720335385547137","goods_spcification_name":"","id":6,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20171193493153455371","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":5,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"fdsafafsaf","OR_Sellerdegrees":0,"OR_Sellerimpression":"adfasfasfasdfasfafsfda","OR_Time":"2017-02-07 18:01","OrderCate":"购物订单","Order_UID":"20172717585835909771","Store_UID":"20169720335385547137","goods_spcification_name":"","id":4,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdssafasfsaf","OR_Sellerdegrees":4,"OR_Sellerimpression":"afdasfsafsafsafsa","OR_Time":"2017-01-26 01:39","OrderCate":"租赁订单","Order_UID":"20171187375334227142","Store_UID":"20169720335385547137","goods_spcification_name":"","id":3,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":3,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"长春 v 不合格","OR_Sellerdegrees":3,"OR_Sellerimpression":"复古风句vg","OR_Time":"2017-01-18 04:11","OrderCate":"租赁订单","Order_UID":"2017118353458906882","Store_UID":"20169720335385547137","goods_spcification_name":"","id":2,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"}],"professionaldegree_value":"0.0","shop_coupon_count":3,"showtype":1}
             */

            private String Bussiness_UID;
            private int CarCount;
            private String Goods_Spcification_UID;
            private String Goods_UID;
            private String Member_UID;
            private String SC_UID;
            private GoodsModelBean goods_model;
            private boolean isChecked = false;
            private int ChangeCount;
            private String spUid = "";
            private int kycun  = 0;
            private String editMoney;
            private boolean nodelete = false;




            public boolean isNodelete() {
                return nodelete;
            }

            public void setNodelete(boolean nodelete) {
                this.nodelete = nodelete;
            }

            public String getEditMoney() {
                return editMoney;
            }

            public void setEditMoney(String editMoney) {
                this.editMoney = editMoney;
            }

            public int getKycun() {
                return kycun;
            }

            public void setKycun(int kycun) {
                this.kycun = kycun;
            }

            public int getChangeCount() {
                return ChangeCount;
            }

            public void setChangeCount(int changeCount) {
                ChangeCount = changeCount;
            }

            public String getSpUid() {
                return spUid;
            }

            public void setSpUid(String spUid) {
                this.spUid = spUid;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public String getBussiness_UID() {
                return Bussiness_UID;
            }

            public void setBussiness_UID(String Bussiness_UID) {
                this.Bussiness_UID = Bussiness_UID;
            }

            public int getCarCount() {
                return CarCount;
            }

            public void setCarCount(int CarCount) {
                this.CarCount = CarCount;
            }

            public String getGoods_Spcification_UID() {
                return Goods_Spcification_UID;
            }

            public void setGoods_Spcification_UID(String Goods_Spcification_UID) {
                this.Goods_Spcification_UID = Goods_Spcification_UID;
            }

            public String getGoods_UID() {
                return Goods_UID;
            }

            public void setGoods_UID(String Goods_UID) {
                this.Goods_UID = Goods_UID;
            }

            public String getMember_UID() {
                return Member_UID;
            }

            public void setMember_UID(String Member_UID) {
                this.Member_UID = Member_UID;
            }

            public String getSC_UID() {
                return SC_UID;
            }

            public void setSC_UID(String SC_UID) {
                this.SC_UID = SC_UID;
            }

            public GoodsModelBean getGoods_model() {
                return goods_model;
            }

            public void setGoods_model(GoodsModelBean goods_model) {
                this.goods_model = goods_model;
            }

            public static class GoodsModelBean implements Parcelable {


                @Override
                public String toString() {
                    return "GoodsModelBean{" +
                            "BrandID='" + BrandID + '\'' +
                            ", Demand_FK='" + Demand_FK + '\'' +
                            ", G_Agent_Member_UID='" + G_Agent_Member_UID + '\'' +
                            ", G_Area='" + G_Area + '\'' +
                            ", G_AuditStatus='" + G_AuditStatus + '\'' +
                            ", G_BasicLease=" + G_BasicLease +
                            ", G_City='" + G_City + '\'' +
                            ", G_CommissionMoney='" + G_CommissionMoney + '\'' +
                            ", G_ContactPhone='" + G_ContactPhone + '\'' +
                            ", G_ContactQQ='" + G_ContactQQ + '\'' +
                            ", G_CorrespAmount='" + G_CorrespAmount + '\'' +
                            ", G_CosworkIDs='" + G_CosworkIDs + '\'' +
                            ", G_CourierCompanyID=" + G_CourierCompanyID +
                            ", G_CourierMoney='" + G_CourierMoney + '\'' +
                            ", G_Cover='" + G_Cover + '\'' +
                            ", G_DepositPrice='" + G_DepositPrice + '\'' +
                            ", G_DetailRemarks='" + G_DetailRemarks + '\'' +
                            ", G_FixedPrice='" + G_FixedPrice + '\'' +
                            ", G_GoodsFreightNum='" + G_GoodsFreightNum + '\'' +
                            ", G_Hits=" + G_Hits +
                            ", G_Images='" + G_Images + '\'' +
                            ", G_IsChange=" + G_IsChange +
                            ", G_IsDepositDeal=" + G_IsDepositDeal +
                            ", G_IsFreeService=" + G_IsFreeService +
                            ", G_IsFreeShip=" + G_IsFreeShip +
                            ", G_IsMorePrice=" + G_IsMorePrice +
                            ", G_IsOfflineDeal=" + G_IsOfflineDeal +
                            ", G_IsOffline_rentdeal=" + G_IsOffline_rentdeal +
                            ", G_IsOnline_rentdeal=" + G_IsOnline_rentdeal +
                            ", G_IsRecommend=" + G_IsRecommend +
                            ", G_IsRent=" + G_IsRent +
                            ", G_IsSale=" + G_IsSale +
                            ", G_IsServiceBook=" + G_IsServiceBook +
                            ", G_IsShelves=" + G_IsShelves +
                            ", G_IsShowIndex=" + G_IsShowIndex +
                            ", G_IsTJByShop=" + G_IsTJByShop +
                            ", G_Isdraught=" + G_Isdraught +
                            ", G_Isurgentsale=" + G_Isurgentsale +
                            ", G_MarketingPrice='" + G_MarketingPrice + '\'' +
                            ", G_Member_OBJ=" + G_Member_OBJ +
                            ", G_NewOldDegree='" + G_NewOldDegree + '\'' +
                            ", G_NoPassReason='" + G_NoPassReason + '\'' +
                            ", G_NoPassReasonDetail='" + G_NoPassReasonDetail + '\'' +
                            ", G_OrderNum=" + G_OrderNum +
                            ", G_Province='" + G_Province + '\'' +
                            ", G_ReadCount=" + G_ReadCount +
                            ", G_RefreshTime='" + G_RefreshTime + '\'' +
                            ", G_RenewalPrice='" + G_RenewalPrice + '\'' +
                            ", G_SaleNum=" + G_SaleNum +
                            ", G_ShareNumber=" + G_ShareNumber +
                            ", G_ShopOrderNum=" + G_ShopOrderNum +
                            ", G_StockNum=" + G_StockNum +
                            ", G_SubTitle='" + G_SubTitle + '\'' +
                            ", G_Title='" + G_Title + '\'' +
                            ", G_Type='" + G_Type + '\'' +
                            ", G_UID='" + G_UID + '\'' +
                            ", G_UpTime='" + G_UpTime + '\'' +
                            ", G_Weight=" + G_Weight +
                            ", G_appointment_mintime_value=" + G_appointment_mintime_value +
                            ", G_appointment_price_1='" + G_appointment_price_1 + '\'' +
                            ", G_appointment_price_2='" + G_appointment_price_2 + '\'' +
                            ", G_appointment_price_3='" + G_appointment_price_3 + '\'' +
                            ", G_appointment_time_value_1=" + G_appointment_time_value_1 +
                            ", G_appointment_time_value_2=" + G_appointment_time_value_2 +
                            ", G_appointment_time_value_3=" + G_appointment_time_value_3 +
                            ", G_appointment_time_value_4=" + G_appointment_time_value_4 +
                            ", G_appointment_time_value_5=" + G_appointment_time_value_5 +
                            ", G_appointment_time_value_6=" + G_appointment_time_value_6 +
                            ", GoodsTypeID=" + GoodsTypeID +
                            ", GoodsTypeID2=" + GoodsTypeID2 +
                            ", Member_UID='" + Member_UID + '\'' +
                            ", TelevisionWorks_FK='" + TelevisionWorks_FK + '\'' +
                            ", VirtualRole_FK='" + VirtualRole_FK + '\'' +
                            ", attitude_number_value='" + attitude_number_value + '\'' +
                            ", bbkpd_member_value='" + bbkpd_member_value + '\'' +
                            ", bbmsxfd_member_value='" + bbmsxfd_member_value + '\'' +
                            ", collection_number=" + collection_number +
                            ", complatespeed_value='" + complatespeed_value + '\'' +
                            ", czrkpd_member_value='" + czrkpd_member_value + '\'' +
                            ", goods_cosworks='" + goods_cosworks + '\'' +
                            ", goods_parameter='" + goods_parameter + '\'' +
                            ", id=" + id +
                            ", mjfhsd_member_value='" + mjfhsd_member_value + '\'' +
                            ", mjfutd_member_value='" + mjfutd_member_value + '\'' +
                            ", mjkpd_member_value='" + mjkpd_member_value + '\'' +
                            ", professionaldegree_value='" + professionaldegree_value + '\'' +
                            ", shop_coupon_count=" + shop_coupon_count +
                            ", showtype=" + showtype +
                            ", goods_specifications=" + goods_specifications +
                            ", order_review_list=" + order_review_list +
                            ", order_sellerfigure=" + order_sellerfigure +
                            '}';
                }

                /**
                 * BrandID :
                 * Demand_FK :
                 * G_Agent_Member_UID :
                 * G_Area : 市辖区
                 * G_AuditStatus : 审核通过
                 * G_BasicLease : 0
                 * G_City : 成都市
                 * G_CommissionMoney : 23.00
                 * G_ContactPhone : 18508103615
                 * G_ContactQQ :
                 * G_CorrespAmount : 0.00
                 * G_CosworkIDs :
                 * G_CourierCompanyID : 0
                 * G_CourierMoney : 0.00
                 * G_Cover : http:\/\/www.anipiggy.com\/UploadFolder\/image\/201611\/20161125126498935245.jpg
                 * G_DepositPrice : 23.00
                 * G_DetailRemarks : asdfasfasfasfasf
                 * G_FixedPrice : 200.00
                 * G_GoodsFreightNum :
                 * G_Hits : 46
                 * G_Images : http:\/\/www.anipiggy.com\/UploadFolder\/image\/201611\/20161125126498935245.jpg,
                 * G_IsChange : false
                 * G_IsDepositDeal : true
                 * G_IsFreeService : false
                 * G_IsFreeShip : true
                 * G_IsMorePrice : false
                 * G_IsOfflineDeal : false
                 * G_IsOffline_rentdeal : false
                 * G_IsOnline_rentdeal : false
                 * G_IsRecommend : false
                 * G_IsRent : false
                 * G_IsSale : true
                 * G_IsServiceBook : false
                 * G_IsShelves : true
                 * G_IsShowIndex : false
                 * G_IsTJByShop : false
                 * G_Isdraught : false
                 * G_Isurgentsale : true
                 * G_MarketingPrice : 0.00
                 * G_Member_OBJ : {"M_Account_Status":"正常","M_Area":"市辖区","M_Avatar":"\\/UploadFolder\\/image\\/201705\\/20175181253057401172.jpg","M_BusinessType":"品牌商家","M_City":"成都市","M_Email":"","M_FCode":"ArRwi","M_IDCard":"510105201512140000","M_IsBusiness":true,"M_IsLock":false,"M_IsRealNameAuth":true,"M_IsSkill":false,"M_MemberLevel":4,"M_Name":"猪排子","M_Phone":"18508103615","M_Province":"四川省","M_Pwd":"E10ADC3949BA59ABBE56E057F20F883E","M_RegIP":"171.217.145.83","M_RegTime":"2016-09-07 20:33:54","M_Sex":"女","M_StoreLevel":24,"M_UID":"20169720335385547137","M_UserName":"猪排子","N_AllLevel":28,"S_DisputeProportion":3.6,"member_identity":"COSER|后期|摄影师|舞师|妆娘|","order_deal_count":66}
                 * G_NewOldDegree :
                 * G_NoPassReason :
                 * G_NoPassReasonDetail :
                 * G_OrderNum : 0
                 * G_Province : 四川省
                 * G_ReadCount : 0
                 * G_RefreshTime : 2017-05-28 13:20:45
                 * G_RenewalPrice : 0.00
                 * G_SaleNum : 2
                 * G_ShareNumber : 0
                 * G_ShopOrderNum : 0
                 * G_StockNum : 86
                 * G_SubTitle : adfasfasfa
                 * G_Title : 定金订单测试分享佣金……
                 * G_Type : 新商品
                 * G_UID : 201611251261877191084
                 * G_UpTime : 2016-11-25 01:26:18
                 * G_Weight : 0.5
                 * G_appointment_mintime_value : 0
                 * G_appointment_price_1 : 0.00
                 * G_appointment_price_2 : 0.00
                 * G_appointment_price_3 : 0.00
                 * G_appointment_time_value_1 : 0
                 * G_appointment_time_value_2 : 0
                 * G_appointment_time_value_3 : 0
                 * G_appointment_time_value_4 : 0
                 * G_appointment_time_value_5 : 0
                 * G_appointment_time_value_6 : 0
                 * GoodsTypeID : 4
                 * GoodsTypeID2 : 0
                 * Member_UID : 20169720335385547137
                 * TelevisionWorks_FK :
                 * VirtualRole_FK :
                 * attitude_number_value : 0.0
                 * bbkpd_member_value : 4.5
                 * bbmsxfd_member_value : 0
                 * collection_number : 0
                 * complatespeed_value : 0.0
                 * czrkpd_member_value : 4.2
                 * goods_cosworks :
                 * goods_parameter : 组套情况：未知,肩宽(CM)：46,胸围(CM)：52,袖长(CM)：59,衣长(CM)：68,腰围(CM)：48,裤长(CM)：75,身高(CM)：165,体重(KG)：95,臀围(CM)：54,面料柔软：未知,面料厚度：未知,版型指数：未知,衣长指数：未知,
                 * goods_specifications : []
                 * id : 104
                 * mjfhsd_member_value : 0
                 * mjfutd_member_value : 0
                 * mjkpd_member_value : 5.0
                 * order_review_list : []
                 * order_sellerfigure : [{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdsaf","OR_Sellerdegrees":5,"OR_Sellerimpression":"adfasfas","OR_Time":"2017-06-09 17:55","OrderCate":"租赁订单","Order_UID":"2017691011962410638","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":88,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":4,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"沙发大发发生","OR_Sellerdegrees":4,"OR_Sellerimpression":"暗示法撒地方撒发生","OR_Time":"2017-06-09 06:39","OrderCate":"租赁订单","Order_UID":"2017695492184477357","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":87,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"","OR_Sellerdegrees":5,"OR_Sellerimpression":"法师法师发大水","OR_Time":"2017-06-07 17:59","OrderCate":"租赁订单","Order_UID":"20176716431440212793","Store_UID":"20169720335385547137","goods_spcification_name":"175\\/65A，奶奶灰","id":84,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20161194442457287949","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":4,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"","OR_GoodsComment":"","OR_Sellerdegrees":0,"OR_Sellerimpression":"n\u2006x\u2006n\u2006n\u2006x\u2006n\u2006x\u2006n","OR_Time":"2017-05-17 08:14","OrderCate":"购物订单","Order_UID":"20175176171897923354","Store_UID":"20169720335385547137","goods_spcification_name":"","id":79,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"很好的东西，不错不错","OR_Sellerdegrees":5,"OR_Sellerimpression":"王小帅很好的东西，不错不错","OR_Time":"2017-02-17 18:01","OrderCate":"购物订单","Order_UID":"2017131336812111905","Store_UID":"20169720335385547137","goods_spcification_name":"","id":6,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"服务","Goods_UID":"20171193493153455371","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":8,"OP_Server_attitude":5,"OP_Server_completespeed":5,"OP_Server_professionaldegree":5,"OR_BabyPerformance":0,"OR_BabydescriptionMatch":0,"OR_Evaluation":"好评","OR_GoodsComment":"fdsafafsaf","OR_Sellerdegrees":0,"OR_Sellerimpression":"adfasfasfasdfasfafsfda","OR_Time":"2017-02-07 18:01","OrderCate":"购物订单","Order_UID":"20172717585835909771","Store_UID":"20169720335385547137","goods_spcification_name":"","id":4,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":5,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"afdssafasfsaf","OR_Sellerdegrees":4,"OR_Sellerimpression":"afdasfsafsafsafsa","OR_Time":"2017-01-26 01:39","OrderCate":"租赁订单","Order_UID":"20171187375334227142","Store_UID":"20169720335385547137","goods_spcification_name":"","id":3,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"},{"Comments_object":"买家","GoodsCate":"二手商品","Goods_UID":"20169811263489221726","Member_UID":"20169720303555307924","OP_Buyerdegrees":0,"OP_Buyersimpression":"","OP_Sellerdeliveryspeed":0,"OP_Sellerserviceattitude":0,"OP_ServerType":4,"OP_Server_attitude":0,"OP_Server_completespeed":0,"OP_Server_professionaldegree":0,"OR_BabyPerformance":3,"OR_BabydescriptionMatch":0,"OR_Evaluation":"中评","OR_GoodsComment":"长春 v 不合格","OR_Sellerdegrees":3,"OR_Sellerimpression":"复古风句vg","OR_Time":"2017-01-18 04:11","OrderCate":"租赁订单","Order_UID":"2017118353458906882","Store_UID":"20169720335385547137","goods_spcification_name":"","id":2,"reivewmember_level":19,"reviewmember_avator":"http:\\/\\/www.anipiggy.com\\/UploadFolder\\/image\\/201702\\/201722112561989462737.jpg"}]
                 * professionaldegree_value : 0.0
                 * shop_coupon_count : 3
                 * showtype : 1
                 */

                private String BrandID;
                private String Demand_FK;
                private String G_Agent_Member_UID;
                private String G_Area;
                private String G_AuditStatus;
                private int G_BasicLease;
                private String G_City;
                private String G_CommissionMoney;
                private String G_ContactPhone;
                private String G_ContactQQ;
                private String G_CorrespAmount;
                private String G_CosworkIDs;
                private int G_CourierCompanyID;
                private String G_CourierMoney;
                private String G_Cover;
                private String G_DepositPrice;
                private String G_DetailRemarks;
                private String G_FixedPrice;
                private String G_GoodsFreightNum;
                private int G_Hits;
                private String G_Images;
                private boolean G_IsChange;
                private boolean G_IsDepositDeal;
                private boolean G_IsFreeService;
                private boolean G_IsFreeShip;
                private boolean G_IsMorePrice;
                private boolean G_IsOfflineDeal;
                private boolean G_IsOffline_rentdeal;
                private boolean G_IsOnline_rentdeal;
                private boolean G_IsRecommend;
                private boolean G_IsRent;
                private boolean G_IsSale;
                private boolean G_IsServiceBook;
                private boolean G_IsShelves;
                private boolean G_IsShowIndex;
                private boolean G_IsTJByShop;
                private boolean G_Isdraught;
                private boolean G_Isurgentsale;
                private String G_MarketingPrice;
                private GMemberOBJBean G_Member_OBJ;
                private String G_NewOldDegree;
                private String G_NoPassReason;
                private String G_NoPassReasonDetail;
                private int G_OrderNum;
                private String G_Province;
                private int G_ReadCount;
                private String G_RefreshTime;
                private String G_RenewalPrice;
                private int G_SaleNum;
                private int G_ShareNumber;
                private int G_ShopOrderNum;
                private int G_StockNum;
                private String G_SubTitle;
                private String G_Title;
                private String G_Type;
                private String G_UID;
                private String G_UpTime;
                private double G_Weight;
                private int G_appointment_mintime_value;
                private String G_appointment_price_1;
                private String G_appointment_price_2;
                private String G_appointment_price_3;
                private int G_appointment_time_value_1;
                private int G_appointment_time_value_2;
                private int G_appointment_time_value_3;
                private int G_appointment_time_value_4;
                private int G_appointment_time_value_5;
                private int G_appointment_time_value_6;
                private int GoodsTypeID;
                private int GoodsTypeID2;
                private String Member_UID;
                private String TelevisionWorks_FK;
                private String VirtualRole_FK;
                private String attitude_number_value;
                private String bbkpd_member_value;
                private String bbmsxfd_member_value;
                private int collection_number;
                private String complatespeed_value;
                private String czrkpd_member_value;
                private String goods_cosworks;
                private String goods_parameter;
                private int id;
                private String mjfhsd_member_value;
                private String mjfutd_member_value;
                private String mjkpd_member_value;
                private String professionaldegree_value;
                private int shop_coupon_count;
                private int showtype;
                private List<FormatBean> goods_specifications;
                private List<FormatBean> order_review_list;
                private List<OrderSellerfigureBean> order_sellerfigure;
                private String yunfei;
                private String youhuijuan;
                private String jifen;
                private String server_unit_string;



                public String getServer_unit_string() {
                    return server_unit_string;
                }

                public void setServer_unit_string(String server_unit_string) {
                    this.server_unit_string = server_unit_string;
                }

                public String getYouhuijuan() {
                    return youhuijuan;
                }

                public void setYouhuijuan(String youhuijuan) {
                    this.youhuijuan = youhuijuan;
                }

                public String getJifen() {
                    return jifen;
                }

                public void setJifen(String jifen) {
                    this.jifen = jifen;
                }

                public String getYunfei() {
                    return yunfei;
                }

                public void setYunfei(String yunfei) {
                    this.yunfei = yunfei;
                }

                public String getBrandID() {
                    return BrandID;
                }

                public void setBrandID(String BrandID) {
                    this.BrandID = BrandID;
                }

                public String getDemand_FK() {
                    return Demand_FK;
                }

                public void setDemand_FK(String Demand_FK) {
                    this.Demand_FK = Demand_FK;
                }

                public String getG_Agent_Member_UID() {
                    return G_Agent_Member_UID;
                }

                public void setG_Agent_Member_UID(String G_Agent_Member_UID) {
                    this.G_Agent_Member_UID = G_Agent_Member_UID;
                }

                public String getG_Area() {
                    return G_Area;
                }

                public void setG_Area(String G_Area) {
                    this.G_Area = G_Area;
                }

                public String getG_AuditStatus() {
                    return G_AuditStatus;
                }

                public void setG_AuditStatus(String G_AuditStatus) {
                    this.G_AuditStatus = G_AuditStatus;
                }

                public int getG_BasicLease() {
                    return G_BasicLease;
                }

                public void setG_BasicLease(int G_BasicLease) {
                    this.G_BasicLease = G_BasicLease;
                }

                public String getG_City() {
                    return G_City;
                }

                public void setG_City(String G_City) {
                    this.G_City = G_City;
                }

                public String getG_CommissionMoney() {
                    return G_CommissionMoney;
                }

                public void setG_CommissionMoney(String G_CommissionMoney) {
                    this.G_CommissionMoney = G_CommissionMoney;
                }

                public String getG_ContactPhone() {
                    return G_ContactPhone;
                }

                public void setG_ContactPhone(String G_ContactPhone) {
                    this.G_ContactPhone = G_ContactPhone;
                }

                public String getG_ContactQQ() {
                    return G_ContactQQ;
                }

                public void setG_ContactQQ(String G_ContactQQ) {
                    this.G_ContactQQ = G_ContactQQ;
                }

                public String getG_CorrespAmount() {
                    return G_CorrespAmount;
                }

                public void setG_CorrespAmount(String G_CorrespAmount) {
                    this.G_CorrespAmount = G_CorrespAmount;
                }

                public String getG_CosworkIDs() {
                    return G_CosworkIDs;
                }

                public void setG_CosworkIDs(String G_CosworkIDs) {
                    this.G_CosworkIDs = G_CosworkIDs;
                }

                public int getG_CourierCompanyID() {
                    return G_CourierCompanyID;
                }

                public void setG_CourierCompanyID(int G_CourierCompanyID) {
                    this.G_CourierCompanyID = G_CourierCompanyID;
                }

                public String getG_CourierMoney() {
                    return G_CourierMoney;
                }

                public void setG_CourierMoney(String G_CourierMoney) {
                    this.G_CourierMoney = G_CourierMoney;
                }

                public String getG_Cover() {
                    return G_Cover;
                }

                public void setG_Cover(String G_Cover) {
                    this.G_Cover = G_Cover;
                }

                public String getG_DepositPrice() {
                    return G_DepositPrice;
                }

                public void setG_DepositPrice(String G_DepositPrice) {
                    this.G_DepositPrice = G_DepositPrice;
                }

                public String getG_DetailRemarks() {
                    return G_DetailRemarks;
                }

                public void setG_DetailRemarks(String G_DetailRemarks) {
                    this.G_DetailRemarks = G_DetailRemarks;
                }

                public String getG_FixedPrice() {
                    return G_FixedPrice;
                }

                public void setG_FixedPrice(String G_FixedPrice) {
                    this.G_FixedPrice = G_FixedPrice;
                }

                public String getG_GoodsFreightNum() {
                    return G_GoodsFreightNum;
                }

                public void setG_GoodsFreightNum(String G_GoodsFreightNum) {
                    this.G_GoodsFreightNum = G_GoodsFreightNum;
                }

                public int getG_Hits() {
                    return G_Hits;
                }

                public void setG_Hits(int G_Hits) {
                    this.G_Hits = G_Hits;
                }

                public String getG_Images() {
                    return G_Images;
                }

                public void setG_Images(String G_Images) {
                    this.G_Images = G_Images;
                }

                public boolean isG_IsChange() {
                    return G_IsChange;
                }

                public void setG_IsChange(boolean G_IsChange) {
                    this.G_IsChange = G_IsChange;
                }

                public boolean isG_IsDepositDeal() {
                    return G_IsDepositDeal;
                }

                public void setG_IsDepositDeal(boolean G_IsDepositDeal) {
                    this.G_IsDepositDeal = G_IsDepositDeal;
                }

                public boolean isG_IsFreeService() {
                    return G_IsFreeService;
                }

                public void setG_IsFreeService(boolean G_IsFreeService) {
                    this.G_IsFreeService = G_IsFreeService;
                }

                public boolean isG_IsFreeShip() {
                    return G_IsFreeShip;
                }

                public void setG_IsFreeShip(boolean G_IsFreeShip) {
                    this.G_IsFreeShip = G_IsFreeShip;
                }

                public boolean isG_IsMorePrice() {
                    return G_IsMorePrice;
                }

                public void setG_IsMorePrice(boolean G_IsMorePrice) {
                    this.G_IsMorePrice = G_IsMorePrice;
                }

                public boolean isG_IsOfflineDeal() {
                    return G_IsOfflineDeal;
                }

                public void setG_IsOfflineDeal(boolean G_IsOfflineDeal) {
                    this.G_IsOfflineDeal = G_IsOfflineDeal;
                }

                public boolean isG_IsOffline_rentdeal() {
                    return G_IsOffline_rentdeal;
                }

                public void setG_IsOffline_rentdeal(boolean G_IsOffline_rentdeal) {
                    this.G_IsOffline_rentdeal = G_IsOffline_rentdeal;
                }

                public boolean isG_IsOnline_rentdeal() {
                    return G_IsOnline_rentdeal;
                }

                public void setG_IsOnline_rentdeal(boolean G_IsOnline_rentdeal) {
                    this.G_IsOnline_rentdeal = G_IsOnline_rentdeal;
                }

                public boolean isG_IsRecommend() {
                    return G_IsRecommend;
                }

                public void setG_IsRecommend(boolean G_IsRecommend) {
                    this.G_IsRecommend = G_IsRecommend;
                }

                public boolean isG_IsRent() {
                    return G_IsRent;
                }

                public void setG_IsRent(boolean G_IsRent) {
                    this.G_IsRent = G_IsRent;
                }

                public boolean isG_IsSale() {
                    return G_IsSale;
                }

                public void setG_IsSale(boolean G_IsSale) {
                    this.G_IsSale = G_IsSale;
                }

                public boolean isG_IsServiceBook() {
                    return G_IsServiceBook;
                }

                public void setG_IsServiceBook(boolean G_IsServiceBook) {
                    this.G_IsServiceBook = G_IsServiceBook;
                }

                public boolean isG_IsShelves() {
                    return G_IsShelves;
                }

                public void setG_IsShelves(boolean G_IsShelves) {
                    this.G_IsShelves = G_IsShelves;
                }

                public boolean isG_IsShowIndex() {
                    return G_IsShowIndex;
                }

                public void setG_IsShowIndex(boolean G_IsShowIndex) {
                    this.G_IsShowIndex = G_IsShowIndex;
                }

                public boolean isG_IsTJByShop() {
                    return G_IsTJByShop;
                }

                public void setG_IsTJByShop(boolean G_IsTJByShop) {
                    this.G_IsTJByShop = G_IsTJByShop;
                }

                public boolean isG_Isdraught() {
                    return G_Isdraught;
                }

                public void setG_Isdraught(boolean G_Isdraught) {
                    this.G_Isdraught = G_Isdraught;
                }

                public boolean isG_Isurgentsale() {
                    return G_Isurgentsale;
                }

                public void setG_Isurgentsale(boolean G_Isurgentsale) {
                    this.G_Isurgentsale = G_Isurgentsale;
                }

                public String getG_MarketingPrice() {
                    return G_MarketingPrice;
                }

                public void setG_MarketingPrice(String G_MarketingPrice) {
                    this.G_MarketingPrice = G_MarketingPrice;
                }

                public GMemberOBJBean getG_Member_OBJ() {
                    return G_Member_OBJ;
                }

                public void setG_Member_OBJ(GMemberOBJBean G_Member_OBJ) {
                    this.G_Member_OBJ = G_Member_OBJ;
                }

                public String getG_NewOldDegree() {
                    return G_NewOldDegree;
                }

                public void setG_NewOldDegree(String G_NewOldDegree) {
                    this.G_NewOldDegree = G_NewOldDegree;
                }

                public String getG_NoPassReason() {
                    return G_NoPassReason;
                }

                public void setG_NoPassReason(String G_NoPassReason) {
                    this.G_NoPassReason = G_NoPassReason;
                }

                public String getG_NoPassReasonDetail() {
                    return G_NoPassReasonDetail;
                }

                public void setG_NoPassReasonDetail(String G_NoPassReasonDetail) {
                    this.G_NoPassReasonDetail = G_NoPassReasonDetail;
                }

                public int getG_OrderNum() {
                    return G_OrderNum;
                }

                public void setG_OrderNum(int G_OrderNum) {
                    this.G_OrderNum = G_OrderNum;
                }

                public String getG_Province() {
                    return G_Province;
                }

                public void setG_Province(String G_Province) {
                    this.G_Province = G_Province;
                }

                public int getG_ReadCount() {
                    return G_ReadCount;
                }

                public void setG_ReadCount(int G_ReadCount) {
                    this.G_ReadCount = G_ReadCount;
                }

                public String getG_RefreshTime() {
                    return G_RefreshTime;
                }

                public void setG_RefreshTime(String G_RefreshTime) {
                    this.G_RefreshTime = G_RefreshTime;
                }

                public String getG_RenewalPrice() {
                    return G_RenewalPrice;
                }

                public void setG_RenewalPrice(String G_RenewalPrice) {
                    this.G_RenewalPrice = G_RenewalPrice;
                }

                public int getG_SaleNum() {
                    return G_SaleNum;
                }

                public void setG_SaleNum(int G_SaleNum) {
                    this.G_SaleNum = G_SaleNum;
                }

                public int getG_ShareNumber() {
                    return G_ShareNumber;
                }

                public void setG_ShareNumber(int G_ShareNumber) {
                    this.G_ShareNumber = G_ShareNumber;
                }

                public int getG_ShopOrderNum() {
                    return G_ShopOrderNum;
                }

                public void setG_ShopOrderNum(int G_ShopOrderNum) {
                    this.G_ShopOrderNum = G_ShopOrderNum;
                }

                public int getG_StockNum() {
                    return G_StockNum;
                }

                public void setG_StockNum(int G_StockNum) {
                    this.G_StockNum = G_StockNum;
                }

                public String getG_SubTitle() {
                    return G_SubTitle;
                }

                public void setG_SubTitle(String G_SubTitle) {
                    this.G_SubTitle = G_SubTitle;
                }

                public String getG_Title() {
                    return G_Title;
                }

                public void setG_Title(String G_Title) {
                    this.G_Title = G_Title;
                }

                public String getG_Type() {
                    return G_Type;
                }

                public void setG_Type(String G_Type) {
                    this.G_Type = G_Type;
                }

                public String getG_UID() {
                    return G_UID;
                }

                public void setG_UID(String G_UID) {
                    this.G_UID = G_UID;
                }

                public String getG_UpTime() {
                    return G_UpTime;
                }

                public void setG_UpTime(String G_UpTime) {
                    this.G_UpTime = G_UpTime;
                }

                public double getG_Weight() {
                    return G_Weight;
                }

                public void setG_Weight(double G_Weight) {
                    this.G_Weight = G_Weight;
                }

                public int getG_appointment_mintime_value() {
                    return G_appointment_mintime_value;
                }

                public void setG_appointment_mintime_value(int G_appointment_mintime_value) {
                    this.G_appointment_mintime_value = G_appointment_mintime_value;
                }

                public String getG_appointment_price_1() {
                    return G_appointment_price_1;
                }

                public void setG_appointment_price_1(String G_appointment_price_1) {
                    this.G_appointment_price_1 = G_appointment_price_1;
                }

                public String getG_appointment_price_2() {
                    return G_appointment_price_2;
                }

                public void setG_appointment_price_2(String G_appointment_price_2) {
                    this.G_appointment_price_2 = G_appointment_price_2;
                }

                public String getG_appointment_price_3() {
                    return G_appointment_price_3;
                }

                public void setG_appointment_price_3(String G_appointment_price_3) {
                    this.G_appointment_price_3 = G_appointment_price_3;
                }

                public int getG_appointment_time_value_1() {
                    return G_appointment_time_value_1;
                }

                public void setG_appointment_time_value_1(int G_appointment_time_value_1) {
                    this.G_appointment_time_value_1 = G_appointment_time_value_1;
                }

                public int getG_appointment_time_value_2() {
                    return G_appointment_time_value_2;
                }

                public void setG_appointment_time_value_2(int G_appointment_time_value_2) {
                    this.G_appointment_time_value_2 = G_appointment_time_value_2;
                }

                public int getG_appointment_time_value_3() {
                    return G_appointment_time_value_3;
                }

                public void setG_appointment_time_value_3(int G_appointment_time_value_3) {
                    this.G_appointment_time_value_3 = G_appointment_time_value_3;
                }

                public int getG_appointment_time_value_4() {
                    return G_appointment_time_value_4;
                }

                public void setG_appointment_time_value_4(int G_appointment_time_value_4) {
                    this.G_appointment_time_value_4 = G_appointment_time_value_4;
                }

                public int getG_appointment_time_value_5() {
                    return G_appointment_time_value_5;
                }

                public void setG_appointment_time_value_5(int G_appointment_time_value_5) {
                    this.G_appointment_time_value_5 = G_appointment_time_value_5;
                }

                public int getG_appointment_time_value_6() {
                    return G_appointment_time_value_6;
                }

                public void setG_appointment_time_value_6(int G_appointment_time_value_6) {
                    this.G_appointment_time_value_6 = G_appointment_time_value_6;
                }

                public int getGoodsTypeID() {
                    return GoodsTypeID;
                }

                public void setGoodsTypeID(int GoodsTypeID) {
                    this.GoodsTypeID = GoodsTypeID;
                }

                public int getGoodsTypeID2() {
                    return GoodsTypeID2;
                }

                public void setGoodsTypeID2(int GoodsTypeID2) {
                    this.GoodsTypeID2 = GoodsTypeID2;
                }

                public String getMember_UID() {
                    return Member_UID;
                }

                public void setMember_UID(String Member_UID) {
                    this.Member_UID = Member_UID;
                }

                public String getTelevisionWorks_FK() {
                    return TelevisionWorks_FK;
                }

                public void setTelevisionWorks_FK(String TelevisionWorks_FK) {
                    this.TelevisionWorks_FK = TelevisionWorks_FK;
                }

                public String getVirtualRole_FK() {
                    return VirtualRole_FK;
                }

                public void setVirtualRole_FK(String VirtualRole_FK) {
                    this.VirtualRole_FK = VirtualRole_FK;
                }

                public String getAttitude_number_value() {
                    return attitude_number_value;
                }

                public void setAttitude_number_value(String attitude_number_value) {
                    this.attitude_number_value = attitude_number_value;
                }

                public String getBbkpd_member_value() {
                    return bbkpd_member_value;
                }

                public void setBbkpd_member_value(String bbkpd_member_value) {
                    this.bbkpd_member_value = bbkpd_member_value;
                }

                public String getBbmsxfd_member_value() {
                    return bbmsxfd_member_value;
                }

                public void setBbmsxfd_member_value(String bbmsxfd_member_value) {
                    this.bbmsxfd_member_value = bbmsxfd_member_value;
                }

                public int getCollection_number() {
                    return collection_number;
                }

                public void setCollection_number(int collection_number) {
                    this.collection_number = collection_number;
                }

                public String getComplatespeed_value() {
                    return complatespeed_value;
                }

                public void setComplatespeed_value(String complatespeed_value) {
                    this.complatespeed_value = complatespeed_value;
                }

                public String getCzrkpd_member_value() {
                    return czrkpd_member_value;
                }

                public void setCzrkpd_member_value(String czrkpd_member_value) {
                    this.czrkpd_member_value = czrkpd_member_value;
                }

                public String getGoods_cosworks() {
                    return goods_cosworks;
                }

                public void setGoods_cosworks(String goods_cosworks) {
                    this.goods_cosworks = goods_cosworks;
                }

                public String getGoods_parameter() {
                    return goods_parameter;
                }

                public void setGoods_parameter(String goods_parameter) {
                    this.goods_parameter = goods_parameter;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getMjfhsd_member_value() {
                    return mjfhsd_member_value;
                }

                public void setMjfhsd_member_value(String mjfhsd_member_value) {
                    this.mjfhsd_member_value = mjfhsd_member_value;
                }

                public String getMjfutd_member_value() {
                    return mjfutd_member_value;
                }

                public void setMjfutd_member_value(String mjfutd_member_value) {
                    this.mjfutd_member_value = mjfutd_member_value;
                }

                public String getMjkpd_member_value() {
                    return mjkpd_member_value;
                }

                public void setMjkpd_member_value(String mjkpd_member_value) {
                    this.mjkpd_member_value = mjkpd_member_value;
                }

                public String getProfessionaldegree_value() {
                    return professionaldegree_value;
                }

                public void setProfessionaldegree_value(String professionaldegree_value) {
                    this.professionaldegree_value = professionaldegree_value;
                }

                public int getShop_coupon_count() {
                    return shop_coupon_count;
                }

                public void setShop_coupon_count(int shop_coupon_count) {
                    this.shop_coupon_count = shop_coupon_count;
                }

                public int getShowtype() {
                    return showtype;
                }

                public void setShowtype(int showtype) {
                    this.showtype = showtype;
                }

                public List<FormatBean> getGoods_specifications() {
                    return goods_specifications;
                }

                public void setGoods_specifications(List<FormatBean> goods_specifications) {
                    this.goods_specifications = goods_specifications;
                }

                public List<FormatBean> getOrder_review_list() {
                    return order_review_list;
                }

                public void setOrder_review_list(List<FormatBean> order_review_list) {
                    this.order_review_list = order_review_list;
                }

                public List<OrderSellerfigureBean> getOrder_sellerfigure() {
                    return order_sellerfigure;
                }

                public void setOrder_sellerfigure(List<OrderSellerfigureBean> order_sellerfigure) {
                    this.order_sellerfigure = order_sellerfigure;
                }

                public static class GMemberOBJBean implements Parcelable {

                    /**
                     * M_Account_Status : 正常
                     * M_Area : 市辖区
                     * M_Avatar : \/UploadFolder\/image\/201705\/20175181253057401172.jpg
                     * M_BusinessType : 品牌商家
                     * M_City : 成都市
                     * M_Email :
                     * M_FCode : ArRwi
                     * M_IDCard : 510105201512140000
                     * M_IsBusiness : true
                     * M_IsLock : false
                     * M_IsRealNameAuth : true
                     * M_IsSkill : false
                     * M_MemberLevel : 4
                     * M_Name : 猪排子
                     * M_Phone : 18508103615
                     * M_Province : 四川省
                     * M_Pwd : E10ADC3949BA59ABBE56E057F20F883E
                     * M_RegIP : 171.217.145.83
                     * M_RegTime : 2016-09-07 20:33:54
                     * M_Sex : 女
                     * M_StoreLevel : 24
                     * M_UID : 20169720335385547137
                     * M_UserName : 猪排子
                     * N_AllLevel : 28
                     * S_DisputeProportion : 3.6
                     * member_identity : COSER|后期|摄影师|舞师|妆娘|
                     * order_deal_count : 66
                     */

                    private String M_Account_Status;
                    private String M_Area;
                    private String M_Avatar;
                    private String M_BusinessType;
                    private String M_City;
                    private String M_Email;
                    private String M_FCode;
                    private String M_IDCard;
                    private boolean M_IsBusiness;
                    private boolean M_IsLock;
                    private boolean M_IsRealNameAuth;
                    private boolean M_IsSkill;
                    private int M_MemberLevel;
                    private String M_Name;
                    private String M_Phone;
                    private String M_Province;
                    private String M_Pwd;
                    private String M_RegIP;
                    private String M_RegTime;
                    private String M_Sex;
                    private int M_StoreLevel;
                    private String M_UID;
                    private String M_UserName;
                    private int N_AllLevel;
                    private double S_DisputeProportion;
                    private String member_identity;
                    private int order_deal_count;
                    private String member_AvailableMoney;
                    private String member_AvailableIntegral;

                    public String getMember_AvailableMoney() {
                        return member_AvailableMoney;
                    }

                    public void setMember_AvailableMoney(String member_AvailableMoney) {
                        this.member_AvailableMoney = member_AvailableMoney;
                    }

                    public String getMember_AvailableIntegral() {
                        return member_AvailableIntegral;
                    }

                    public void setMember_AvailableIntegral(String member_AvailableIntegral) {
                        this.member_AvailableIntegral = member_AvailableIntegral;
                    }

                    public String getM_Account_Status() {
                        return M_Account_Status;
                    }

                    public void setM_Account_Status(String M_Account_Status) {
                        this.M_Account_Status = M_Account_Status;
                    }

                    public String getM_Area() {
                        return M_Area;
                    }

                    public void setM_Area(String M_Area) {
                        this.M_Area = M_Area;
                    }

                    public String getM_Avatar() {
                        return M_Avatar;
                    }

                    public void setM_Avatar(String M_Avatar) {
                        this.M_Avatar = M_Avatar;
                    }

                    public String getM_BusinessType() {
                        return M_BusinessType;
                    }

                    public void setM_BusinessType(String M_BusinessType) {
                        this.M_BusinessType = M_BusinessType;
                    }

                    public String getM_City() {
                        return M_City;
                    }

                    public void setM_City(String M_City) {
                        this.M_City = M_City;
                    }

                    public String getM_Email() {
                        return M_Email;
                    }

                    public void setM_Email(String M_Email) {
                        this.M_Email = M_Email;
                    }

                    public String getM_FCode() {
                        return M_FCode;
                    }

                    public void setM_FCode(String M_FCode) {
                        this.M_FCode = M_FCode;
                    }

                    public String getM_IDCard() {
                        return M_IDCard;
                    }

                    public void setM_IDCard(String M_IDCard) {
                        this.M_IDCard = M_IDCard;
                    }

                    public boolean isM_IsBusiness() {
                        return M_IsBusiness;
                    }

                    public void setM_IsBusiness(boolean M_IsBusiness) {
                        this.M_IsBusiness = M_IsBusiness;
                    }

                    public boolean isM_IsLock() {
                        return M_IsLock;
                    }

                    public void setM_IsLock(boolean M_IsLock) {
                        this.M_IsLock = M_IsLock;
                    }

                    public boolean isM_IsRealNameAuth() {
                        return M_IsRealNameAuth;
                    }

                    public void setM_IsRealNameAuth(boolean M_IsRealNameAuth) {
                        this.M_IsRealNameAuth = M_IsRealNameAuth;
                    }

                    public boolean isM_IsSkill() {
                        return M_IsSkill;
                    }

                    public void setM_IsSkill(boolean M_IsSkill) {
                        this.M_IsSkill = M_IsSkill;
                    }

                    public int getM_MemberLevel() {
                        return M_MemberLevel;
                    }

                    public void setM_MemberLevel(int M_MemberLevel) {
                        this.M_MemberLevel = M_MemberLevel;
                    }

                    public String getM_Name() {
                        return M_Name;
                    }

                    public void setM_Name(String M_Name) {
                        this.M_Name = M_Name;
                    }

                    public String getM_Phone() {
                        return M_Phone;
                    }

                    public void setM_Phone(String M_Phone) {
                        this.M_Phone = M_Phone;
                    }

                    public String getM_Province() {
                        return M_Province;
                    }

                    public void setM_Province(String M_Province) {
                        this.M_Province = M_Province;
                    }

                    public String getM_Pwd() {
                        return M_Pwd;
                    }

                    public void setM_Pwd(String M_Pwd) {
                        this.M_Pwd = M_Pwd;
                    }

                    public String getM_RegIP() {
                        return M_RegIP;
                    }

                    public void setM_RegIP(String M_RegIP) {
                        this.M_RegIP = M_RegIP;
                    }

                    public String getM_RegTime() {
                        return M_RegTime;
                    }

                    public void setM_RegTime(String M_RegTime) {
                        this.M_RegTime = M_RegTime;
                    }

                    public String getM_Sex() {
                        return M_Sex;
                    }

                    public void setM_Sex(String M_Sex) {
                        this.M_Sex = M_Sex;
                    }

                    public int getM_StoreLevel() {
                        return M_StoreLevel;
                    }

                    public void setM_StoreLevel(int M_StoreLevel) {
                        this.M_StoreLevel = M_StoreLevel;
                    }

                    public String getM_UID() {
                        return M_UID;
                    }

                    public void setM_UID(String M_UID) {
                        this.M_UID = M_UID;
                    }

                    public String getM_UserName() {
                        return M_UserName;
                    }

                    public void setM_UserName(String M_UserName) {
                        this.M_UserName = M_UserName;
                    }

                    public int getN_AllLevel() {
                        return N_AllLevel;
                    }

                    public void setN_AllLevel(int N_AllLevel) {
                        this.N_AllLevel = N_AllLevel;
                    }

                    public double getS_DisputeProportion() {
                        return S_DisputeProportion;
                    }

                    public void setS_DisputeProportion(double S_DisputeProportion) {
                        this.S_DisputeProportion = S_DisputeProportion;
                    }

                    public String getMember_identity() {
                        return member_identity;
                    }

                    public void setMember_identity(String member_identity) {
                        this.member_identity = member_identity;
                    }

                    public int getOrder_deal_count() {
                        return order_deal_count;
                    }

                    public void setOrder_deal_count(int order_deal_count) {
                        this.order_deal_count = order_deal_count;
                    }

                    public GMemberOBJBean() {
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.M_Account_Status);
                        dest.writeString(this.M_Area);
                        dest.writeString(this.M_Avatar);
                        dest.writeString(this.M_BusinessType);
                        dest.writeString(this.M_City);
                        dest.writeString(this.M_Email);
                        dest.writeString(this.M_FCode);
                        dest.writeString(this.M_IDCard);
                        dest.writeByte(this.M_IsBusiness ? (byte) 1 : (byte) 0);
                        dest.writeByte(this.M_IsLock ? (byte) 1 : (byte) 0);
                        dest.writeByte(this.M_IsRealNameAuth ? (byte) 1 : (byte) 0);
                        dest.writeByte(this.M_IsSkill ? (byte) 1 : (byte) 0);
                        dest.writeInt(this.M_MemberLevel);
                        dest.writeString(this.M_Name);
                        dest.writeString(this.M_Phone);
                        dest.writeString(this.M_Province);
                        dest.writeString(this.M_Pwd);
                        dest.writeString(this.M_RegIP);
                        dest.writeString(this.M_RegTime);
                        dest.writeString(this.M_Sex);
                        dest.writeInt(this.M_StoreLevel);
                        dest.writeString(this.M_UID);
                        dest.writeString(this.M_UserName);
                        dest.writeInt(this.N_AllLevel);
                        dest.writeDouble(this.S_DisputeProportion);
                        dest.writeString(this.member_identity);
                        dest.writeInt(this.order_deal_count);
                        dest.writeString(this.member_AvailableMoney);
                        dest.writeString(this.member_AvailableIntegral);
                    }

                    protected GMemberOBJBean(Parcel in) {
                        this.M_Account_Status = in.readString();
                        this.M_Area = in.readString();
                        this.M_Avatar = in.readString();
                        this.M_BusinessType = in.readString();
                        this.M_City = in.readString();
                        this.M_Email = in.readString();
                        this.M_FCode = in.readString();
                        this.M_IDCard = in.readString();
                        this.M_IsBusiness = in.readByte() != 0;
                        this.M_IsLock = in.readByte() != 0;
                        this.M_IsRealNameAuth = in.readByte() != 0;
                        this.M_IsSkill = in.readByte() != 0;
                        this.M_MemberLevel = in.readInt();
                        this.M_Name = in.readString();
                        this.M_Phone = in.readString();
                        this.M_Province = in.readString();
                        this.M_Pwd = in.readString();
                        this.M_RegIP = in.readString();
                        this.M_RegTime = in.readString();
                        this.M_Sex = in.readString();
                        this.M_StoreLevel = in.readInt();
                        this.M_UID = in.readString();
                        this.M_UserName = in.readString();
                        this.N_AllLevel = in.readInt();
                        this.S_DisputeProportion = in.readDouble();
                        this.member_identity = in.readString();
                        this.order_deal_count = in.readInt();
                        this.member_AvailableMoney = in.readString();
                        this.member_AvailableIntegral = in.readString();
                    }

                    public static final Creator<GMemberOBJBean> CREATOR = new Creator<GMemberOBJBean>() {
                        @Override
                        public GMemberOBJBean createFromParcel(Parcel source) {
                            return new GMemberOBJBean(source);
                        }

                        @Override
                        public GMemberOBJBean[] newArray(int size) {
                            return new GMemberOBJBean[size];
                        }
                    };
                }

                public static class OrderSellerfigureBean implements Parcelable {


                    /**
                     * Comments_object : 买家
                     * GoodsCate : 二手商品
                     * Goods_UID : 20169811263489221726
                     * Member_UID : 20169720303555307924
                     * OP_Buyerdegrees : 0
                     * OP_Buyersimpression :
                     * OP_Sellerdeliveryspeed : 0
                     * OP_Sellerserviceattitude : 0
                     * OP_ServerType : 4
                     * OP_Server_attitude : 0
                     * OP_Server_completespeed : 0
                     * OP_Server_professionaldegree : 0
                     * OR_BabyPerformance : 5
                     * OR_BabydescriptionMatch : 0
                     * OR_Evaluation : 中评
                     * OR_GoodsComment : afdsaf
                     * OR_Sellerdegrees : 5
                     * OR_Sellerimpression : adfasfas
                     * OR_Time : 2017-06-09 17:55
                     * OrderCate : 租赁订单
                     * Order_UID : 2017691011962410638
                     * Store_UID : 20169720335385547137
                     * goods_spcification_name : 175\/65A，奶奶灰
                     * id : 88
                     * reivewmember_level : 19
                     * reviewmember_avator : http:\/\/www.anipiggy.com\/UploadFolder\/image\/201702\/201722112561989462737.jpg
                     */

                    private String Comments_object;
                    private String GoodsCate;
                    private String Goods_UID;
                    private String Member_UID;
                    private int OP_Buyerdegrees;
                    private String OP_Buyersimpression;
                    private int OP_Sellerdeliveryspeed;
                    private int OP_Sellerserviceattitude;
                    private int OP_ServerType;
                    private int OP_Server_attitude;
                    private int OP_Server_completespeed;
                    private int OP_Server_professionaldegree;
                    private int OR_BabyPerformance;
                    private int OR_BabydescriptionMatch;
                    private String OR_Evaluation;
                    private String OR_GoodsComment;
                    private int OR_Sellerdegrees;
                    private String OR_Sellerimpression;
                    private String OR_Time;
                    private String OrderCate;
                    private String Order_UID;
                    private String Store_UID;
                    private String goods_spcification_name;
                    private int id;
                    private int reivewmember_level;
                    private String reviewmember_avator;

                    public String getComments_object() {
                        return Comments_object;
                    }

                    public void setComments_object(String Comments_object) {
                        this.Comments_object = Comments_object;
                    }

                    public String getGoodsCate() {
                        return GoodsCate;
                    }

                    public void setGoodsCate(String GoodsCate) {
                        this.GoodsCate = GoodsCate;
                    }

                    public String getGoods_UID() {
                        return Goods_UID;
                    }

                    public void setGoods_UID(String Goods_UID) {
                        this.Goods_UID = Goods_UID;
                    }

                    public String getMember_UID() {
                        return Member_UID;
                    }

                    public void setMember_UID(String Member_UID) {
                        this.Member_UID = Member_UID;
                    }

                    public int getOP_Buyerdegrees() {
                        return OP_Buyerdegrees;
                    }

                    public void setOP_Buyerdegrees(int OP_Buyerdegrees) {
                        this.OP_Buyerdegrees = OP_Buyerdegrees;
                    }

                    public String getOP_Buyersimpression() {
                        return OP_Buyersimpression;
                    }

                    public void setOP_Buyersimpression(String OP_Buyersimpression) {
                        this.OP_Buyersimpression = OP_Buyersimpression;
                    }

                    public int getOP_Sellerdeliveryspeed() {
                        return OP_Sellerdeliveryspeed;
                    }

                    public void setOP_Sellerdeliveryspeed(int OP_Sellerdeliveryspeed) {
                        this.OP_Sellerdeliveryspeed = OP_Sellerdeliveryspeed;
                    }

                    public int getOP_Sellerserviceattitude() {
                        return OP_Sellerserviceattitude;
                    }

                    public void setOP_Sellerserviceattitude(int OP_Sellerserviceattitude) {
                        this.OP_Sellerserviceattitude = OP_Sellerserviceattitude;
                    }

                    public int getOP_ServerType() {
                        return OP_ServerType;
                    }

                    public void setOP_ServerType(int OP_ServerType) {
                        this.OP_ServerType = OP_ServerType;
                    }

                    public int getOP_Server_attitude() {
                        return OP_Server_attitude;
                    }

                    public void setOP_Server_attitude(int OP_Server_attitude) {
                        this.OP_Server_attitude = OP_Server_attitude;
                    }

                    public int getOP_Server_completespeed() {
                        return OP_Server_completespeed;
                    }

                    public void setOP_Server_completespeed(int OP_Server_completespeed) {
                        this.OP_Server_completespeed = OP_Server_completespeed;
                    }

                    public int getOP_Server_professionaldegree() {
                        return OP_Server_professionaldegree;
                    }

                    public void setOP_Server_professionaldegree(int OP_Server_professionaldegree) {
                        this.OP_Server_professionaldegree = OP_Server_professionaldegree;
                    }

                    public int getOR_BabyPerformance() {
                        return OR_BabyPerformance;
                    }

                    public void setOR_BabyPerformance(int OR_BabyPerformance) {
                        this.OR_BabyPerformance = OR_BabyPerformance;
                    }

                    public int getOR_BabydescriptionMatch() {
                        return OR_BabydescriptionMatch;
                    }

                    public void setOR_BabydescriptionMatch(int OR_BabydescriptionMatch) {
                        this.OR_BabydescriptionMatch = OR_BabydescriptionMatch;
                    }

                    public String getOR_Evaluation() {
                        return OR_Evaluation;
                    }

                    public void setOR_Evaluation(String OR_Evaluation) {
                        this.OR_Evaluation = OR_Evaluation;
                    }

                    public String getOR_GoodsComment() {
                        return OR_GoodsComment;
                    }

                    public void setOR_GoodsComment(String OR_GoodsComment) {
                        this.OR_GoodsComment = OR_GoodsComment;
                    }

                    public int getOR_Sellerdegrees() {
                        return OR_Sellerdegrees;
                    }

                    public void setOR_Sellerdegrees(int OR_Sellerdegrees) {
                        this.OR_Sellerdegrees = OR_Sellerdegrees;
                    }

                    public String getOR_Sellerimpression() {
                        return OR_Sellerimpression;
                    }

                    public void setOR_Sellerimpression(String OR_Sellerimpression) {
                        this.OR_Sellerimpression = OR_Sellerimpression;
                    }

                    public String getOR_Time() {
                        return OR_Time;
                    }

                    public void setOR_Time(String OR_Time) {
                        this.OR_Time = OR_Time;
                    }

                    public String getOrderCate() {
                        return OrderCate;
                    }

                    public void setOrderCate(String OrderCate) {
                        this.OrderCate = OrderCate;
                    }

                    public String getOrder_UID() {
                        return Order_UID;
                    }

                    public void setOrder_UID(String Order_UID) {
                        this.Order_UID = Order_UID;
                    }

                    public String getStore_UID() {
                        return Store_UID;
                    }

                    public void setStore_UID(String Store_UID) {
                        this.Store_UID = Store_UID;
                    }

                    public String getGoods_spcification_name() {
                        return goods_spcification_name;
                    }

                    public void setGoods_spcification_name(String goods_spcification_name) {
                        this.goods_spcification_name = goods_spcification_name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getReivewmember_level() {
                        return reivewmember_level;
                    }

                    public void setReivewmember_level(int reivewmember_level) {
                        this.reivewmember_level = reivewmember_level;
                    }

                    public String getReviewmember_avator() {
                        return reviewmember_avator;
                    }

                    public void setReviewmember_avator(String reviewmember_avator) {
                        this.reviewmember_avator = reviewmember_avator;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.Comments_object);
                        dest.writeString(this.GoodsCate);
                        dest.writeString(this.Goods_UID);
                        dest.writeString(this.Member_UID);
                        dest.writeInt(this.OP_Buyerdegrees);
                        dest.writeString(this.OP_Buyersimpression);
                        dest.writeInt(this.OP_Sellerdeliveryspeed);
                        dest.writeInt(this.OP_Sellerserviceattitude);
                        dest.writeInt(this.OP_ServerType);
                        dest.writeInt(this.OP_Server_attitude);
                        dest.writeInt(this.OP_Server_completespeed);
                        dest.writeInt(this.OP_Server_professionaldegree);
                        dest.writeInt(this.OR_BabyPerformance);
                        dest.writeInt(this.OR_BabydescriptionMatch);
                        dest.writeString(this.OR_Evaluation);
                        dest.writeString(this.OR_GoodsComment);
                        dest.writeInt(this.OR_Sellerdegrees);
                        dest.writeString(this.OR_Sellerimpression);
                        dest.writeString(this.OR_Time);
                        dest.writeString(this.OrderCate);
                        dest.writeString(this.Order_UID);
                        dest.writeString(this.Store_UID);
                        dest.writeString(this.goods_spcification_name);
                        dest.writeInt(this.id);
                        dest.writeInt(this.reivewmember_level);
                        dest.writeString(this.reviewmember_avator);
                    }

                    public OrderSellerfigureBean() {
                    }

                    protected OrderSellerfigureBean(Parcel in) {
                        this.Comments_object = in.readString();
                        this.GoodsCate = in.readString();
                        this.Goods_UID = in.readString();
                        this.Member_UID = in.readString();
                        this.OP_Buyerdegrees = in.readInt();
                        this.OP_Buyersimpression = in.readString();
                        this.OP_Sellerdeliveryspeed = in.readInt();
                        this.OP_Sellerserviceattitude = in.readInt();
                        this.OP_ServerType = in.readInt();
                        this.OP_Server_attitude = in.readInt();
                        this.OP_Server_completespeed = in.readInt();
                        this.OP_Server_professionaldegree = in.readInt();
                        this.OR_BabyPerformance = in.readInt();
                        this.OR_BabydescriptionMatch = in.readInt();
                        this.OR_Evaluation = in.readString();
                        this.OR_GoodsComment = in.readString();
                        this.OR_Sellerdegrees = in.readInt();
                        this.OR_Sellerimpression = in.readString();
                        this.OR_Time = in.readString();
                        this.OrderCate = in.readString();
                        this.Order_UID = in.readString();
                        this.Store_UID = in.readString();
                        this.goods_spcification_name = in.readString();
                        this.id = in.readInt();
                        this.reivewmember_level = in.readInt();
                        this.reviewmember_avator = in.readString();
                    }

                    public static final Creator<OrderSellerfigureBean> CREATOR = new Creator<OrderSellerfigureBean>() {
                        @Override
                        public OrderSellerfigureBean createFromParcel(Parcel source) {
                            return new OrderSellerfigureBean(source);
                        }

                        @Override
                        public OrderSellerfigureBean[] newArray(int size) {
                            return new OrderSellerfigureBean[size];
                        }
                    };
                }

                public GoodsModelBean() {
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.BrandID);
                    dest.writeString(this.Demand_FK);
                    dest.writeString(this.G_Agent_Member_UID);
                    dest.writeString(this.G_Area);
                    dest.writeString(this.G_AuditStatus);
                    dest.writeInt(this.G_BasicLease);
                    dest.writeString(this.G_City);
                    dest.writeString(this.G_CommissionMoney);
                    dest.writeString(this.G_ContactPhone);
                    dest.writeString(this.G_ContactQQ);
                    dest.writeString(this.G_CorrespAmount);
                    dest.writeString(this.G_CosworkIDs);
                    dest.writeInt(this.G_CourierCompanyID);
                    dest.writeString(this.G_CourierMoney);
                    dest.writeString(this.G_Cover);
                    dest.writeString(this.G_DepositPrice);
                    dest.writeString(this.G_DetailRemarks);
                    dest.writeString(this.G_FixedPrice);
                    dest.writeString(this.G_GoodsFreightNum);
                    dest.writeInt(this.G_Hits);
                    dest.writeString(this.G_Images);
                    dest.writeByte(this.G_IsChange ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsDepositDeal ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsFreeService ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsFreeShip ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsMorePrice ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsOfflineDeal ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsOffline_rentdeal ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsOnline_rentdeal ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsRecommend ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsRent ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsSale ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsServiceBook ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsShelves ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsShowIndex ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_IsTJByShop ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_Isdraught ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.G_Isurgentsale ? (byte) 1 : (byte) 0);
                    dest.writeString(this.G_MarketingPrice);
                    dest.writeParcelable(this.G_Member_OBJ, flags);
                    dest.writeString(this.G_NewOldDegree);
                    dest.writeString(this.G_NoPassReason);
                    dest.writeString(this.G_NoPassReasonDetail);
                    dest.writeInt(this.G_OrderNum);
                    dest.writeString(this.G_Province);
                    dest.writeInt(this.G_ReadCount);
                    dest.writeString(this.G_RefreshTime);
                    dest.writeString(this.G_RenewalPrice);
                    dest.writeInt(this.G_SaleNum);
                    dest.writeInt(this.G_ShareNumber);
                    dest.writeInt(this.G_ShopOrderNum);
                    dest.writeInt(this.G_StockNum);
                    dest.writeString(this.G_SubTitle);
                    dest.writeString(this.G_Title);
                    dest.writeString(this.G_Type);
                    dest.writeString(this.G_UID);
                    dest.writeString(this.G_UpTime);
                    dest.writeDouble(this.G_Weight);
                    dest.writeInt(this.G_appointment_mintime_value);
                    dest.writeString(this.G_appointment_price_1);
                    dest.writeString(this.G_appointment_price_2);
                    dest.writeString(this.G_appointment_price_3);
                    dest.writeInt(this.G_appointment_time_value_1);
                    dest.writeInt(this.G_appointment_time_value_2);
                    dest.writeInt(this.G_appointment_time_value_3);
                    dest.writeInt(this.G_appointment_time_value_4);
                    dest.writeInt(this.G_appointment_time_value_5);
                    dest.writeInt(this.G_appointment_time_value_6);
                    dest.writeInt(this.GoodsTypeID);
                    dest.writeInt(this.GoodsTypeID2);
                    dest.writeString(this.Member_UID);
                    dest.writeString(this.TelevisionWorks_FK);
                    dest.writeString(this.VirtualRole_FK);
                    dest.writeString(this.attitude_number_value);
                    dest.writeString(this.bbkpd_member_value);
                    dest.writeString(this.bbmsxfd_member_value);
                    dest.writeInt(this.collection_number);
                    dest.writeString(this.complatespeed_value);
                    dest.writeString(this.czrkpd_member_value);
                    dest.writeString(this.goods_cosworks);
                    dest.writeString(this.goods_parameter);
                    dest.writeInt(this.id);
                    dest.writeString(this.mjfhsd_member_value);
                    dest.writeString(this.mjfutd_member_value);
                    dest.writeString(this.mjkpd_member_value);
                    dest.writeString(this.professionaldegree_value);
                    dest.writeInt(this.shop_coupon_count);
                    dest.writeInt(this.showtype);
                    dest.writeTypedList(this.goods_specifications);
                    dest.writeTypedList(this.order_review_list);
                    dest.writeTypedList(this.order_sellerfigure);
                    dest.writeString(this.yunfei);
                    dest.writeString(this.youhuijuan);
                    dest.writeString(this.jifen);
                }

                protected GoodsModelBean(Parcel in) {
                    this.BrandID = in.readString();
                    this.Demand_FK = in.readString();
                    this.G_Agent_Member_UID = in.readString();
                    this.G_Area = in.readString();
                    this.G_AuditStatus = in.readString();
                    this.G_BasicLease = in.readInt();
                    this.G_City = in.readString();
                    this.G_CommissionMoney = in.readString();
                    this.G_ContactPhone = in.readString();
                    this.G_ContactQQ = in.readString();
                    this.G_CorrespAmount = in.readString();
                    this.G_CosworkIDs = in.readString();
                    this.G_CourierCompanyID = in.readInt();
                    this.G_CourierMoney = in.readString();
                    this.G_Cover = in.readString();
                    this.G_DepositPrice = in.readString();
                    this.G_DetailRemarks = in.readString();
                    this.G_FixedPrice = in.readString();
                    this.G_GoodsFreightNum = in.readString();
                    this.G_Hits = in.readInt();
                    this.G_Images = in.readString();
                    this.G_IsChange = in.readByte() != 0;
                    this.G_IsDepositDeal = in.readByte() != 0;
                    this.G_IsFreeService = in.readByte() != 0;
                    this.G_IsFreeShip = in.readByte() != 0;
                    this.G_IsMorePrice = in.readByte() != 0;
                    this.G_IsOfflineDeal = in.readByte() != 0;
                    this.G_IsOffline_rentdeal = in.readByte() != 0;
                    this.G_IsOnline_rentdeal = in.readByte() != 0;
                    this.G_IsRecommend = in.readByte() != 0;
                    this.G_IsRent = in.readByte() != 0;
                    this.G_IsSale = in.readByte() != 0;
                    this.G_IsServiceBook = in.readByte() != 0;
                    this.G_IsShelves = in.readByte() != 0;
                    this.G_IsShowIndex = in.readByte() != 0;
                    this.G_IsTJByShop = in.readByte() != 0;
                    this.G_Isdraught = in.readByte() != 0;
                    this.G_Isurgentsale = in.readByte() != 0;
                    this.G_MarketingPrice = in.readString();
                    this.G_Member_OBJ = in.readParcelable(GMemberOBJBean.class.getClassLoader());
                    this.G_NewOldDegree = in.readString();
                    this.G_NoPassReason = in.readString();
                    this.G_NoPassReasonDetail = in.readString();
                    this.G_OrderNum = in.readInt();
                    this.G_Province = in.readString();
                    this.G_ReadCount = in.readInt();
                    this.G_RefreshTime = in.readString();
                    this.G_RenewalPrice = in.readString();
                    this.G_SaleNum = in.readInt();
                    this.G_ShareNumber = in.readInt();
                    this.G_ShopOrderNum = in.readInt();
                    this.G_StockNum = in.readInt();
                    this.G_SubTitle = in.readString();
                    this.G_Title = in.readString();
                    this.G_Type = in.readString();
                    this.G_UID = in.readString();
                    this.G_UpTime = in.readString();
                    this.G_Weight = in.readDouble();
                    this.G_appointment_mintime_value = in.readInt();
                    this.G_appointment_price_1 = in.readString();
                    this.G_appointment_price_2 = in.readString();
                    this.G_appointment_price_3 = in.readString();
                    this.G_appointment_time_value_1 = in.readInt();
                    this.G_appointment_time_value_2 = in.readInt();
                    this.G_appointment_time_value_3 = in.readInt();
                    this.G_appointment_time_value_4 = in.readInt();
                    this.G_appointment_time_value_5 = in.readInt();
                    this.G_appointment_time_value_6 = in.readInt();
                    this.GoodsTypeID = in.readInt();
                    this.GoodsTypeID2 = in.readInt();
                    this.Member_UID = in.readString();
                    this.TelevisionWorks_FK = in.readString();
                    this.VirtualRole_FK = in.readString();
                    this.attitude_number_value = in.readString();
                    this.bbkpd_member_value = in.readString();
                    this.bbmsxfd_member_value = in.readString();
                    this.collection_number = in.readInt();
                    this.complatespeed_value = in.readString();
                    this.czrkpd_member_value = in.readString();
                    this.goods_cosworks = in.readString();
                    this.goods_parameter = in.readString();
                    this.id = in.readInt();
                    this.mjfhsd_member_value = in.readString();
                    this.mjfutd_member_value = in.readString();
                    this.mjkpd_member_value = in.readString();
                    this.professionaldegree_value = in.readString();
                    this.shop_coupon_count = in.readInt();
                    this.showtype = in.readInt();
                    this.goods_specifications = in.createTypedArrayList(FormatBean.CREATOR);
                    this.order_review_list = in.createTypedArrayList(FormatBean.CREATOR);
                    this.order_sellerfigure = in.createTypedArrayList(OrderSellerfigureBean.CREATOR);
                    this.yunfei = in.readString();
                    this.youhuijuan = in.readString();
                    this.jifen = in.readString();
                }

                public static final Creator<GoodsModelBean> CREATOR = new Creator<GoodsModelBean>() {
                    @Override
                    public GoodsModelBean createFromParcel(Parcel source) {
                        return new GoodsModelBean(source);
                    }

                    @Override
                    public GoodsModelBean[] newArray(int size) {
                        return new GoodsModelBean[size];
                    }
                };
            }

            public CargoodslistBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Bussiness_UID);
                dest.writeInt(this.CarCount);
                dest.writeString(this.Goods_Spcification_UID);
                dest.writeString(this.Goods_UID);
                dest.writeString(this.Member_UID);
                dest.writeString(this.SC_UID);
                dest.writeParcelable(this.goods_model, flags);
                dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
                dest.writeInt(this.ChangeCount);
                dest.writeString(this.spUid);
                dest.writeInt(this.kycun);
                dest.writeString(this.editMoney);
                dest.writeByte(this.nodelete ? (byte) 1 : (byte) 0);
            }

            protected CargoodslistBean(Parcel in) {
                this.Bussiness_UID = in.readString();
                this.CarCount = in.readInt();
                this.Goods_Spcification_UID = in.readString();
                this.Goods_UID = in.readString();
                this.Member_UID = in.readString();
                this.SC_UID = in.readString();
                this.goods_model = in.readParcelable(GoodsModelBean.class.getClassLoader());
                this.isChecked = in.readByte() != 0;
                this.ChangeCount = in.readInt();
                this.spUid = in.readString();
                this.kycun = in.readInt();
                this.editMoney = in.readString();
                this.nodelete = in.readByte() != 0;
            }

            public static final Creator<CargoodslistBean> CREATOR = new Creator<CargoodslistBean>() {
                @Override
                public CargoodslistBean createFromParcel(Parcel source) {
                    return new CargoodslistBean(source);
                }

                @Override
                public CargoodslistBean[] newArray(int size) {
                    return new CargoodslistBean[size];
                }
            };
        }

        public CarshoplistBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.IsPerson ? (byte) 1 : (byte) 0);
            dest.writeInt(this.Member_Level);
            dest.writeString(this.Member_Name);
            dest.writeString(this.Member_UID);
            dest.writeTypedList(this.cargoodslist);
            dest.writeTypedList(this.checkgoodslist);
            dest.writeString(this.liuyan);
            dest.writeDouble(this.yunfei);
            dest.writeString(this.couponid);
            dest.writeString(this.coupon);
            dest.writeString(this.jiaoyi);
            dest.writeString(this.huoqujiaoyi);
            dest.writeByte(this.ischeckallgood ? (byte) 1 : (byte) 0);
            dest.writeByte(this.iscleancheck ? (byte) 1 : (byte) 0);
            dest.writeInt(this.showtype);
        }

        protected CarshoplistBean(Parcel in) {
            this.IsPerson = in.readByte() != 0;
            this.Member_Level = in.readInt();
            this.Member_Name = in.readString();
            this.Member_UID = in.readString();
            this.cargoodslist = in.createTypedArrayList(CargoodslistBean.CREATOR);
            this.checkgoodslist = in.createTypedArrayList(CargoodslistBean.CREATOR);
            this.liuyan = in.readString();
            this.yunfei = in.readDouble();
            this.couponid = in.readString();
            this.coupon = in.readString();
            this.jiaoyi = in.readString();
            this.huoqujiaoyi = in.readString();
            this.ischeckallgood = in.readByte() != 0;
            this.iscleancheck = in.readByte() != 0;
            this.showtype = in.readInt();
        }

        public static final Creator<CarshoplistBean> CREATOR = new Creator<CarshoplistBean>() {
            @Override
            public CarshoplistBean createFromParcel(Parcel source) {
                return new CarshoplistBean(source);
            }

            @Override
            public CarshoplistBean[] newArray(int size) {
                return new CarshoplistBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idle_number);
        dest.writeInt(this.new_number);
        dest.writeInt(this.server_number);
        dest.writeList(this.carshoplist);
    }

    public ShopCartbean() {
    }

    protected ShopCartbean(Parcel in) {
        this.idle_number = in.readInt();
        this.new_number = in.readInt();
        this.server_number = in.readInt();
        this.carshoplist = new ArrayList<CarshoplistBean>();
        in.readList(this.carshoplist, CarshoplistBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShopCartbean> CREATOR = new Parcelable.Creator<ShopCartbean>() {
        @Override
        public ShopCartbean createFromParcel(Parcel source) {
            return new ShopCartbean(source);
        }

        @Override
        public ShopCartbean[] newArray(int size) {
            return new ShopCartbean[size];
        }
    };
}
