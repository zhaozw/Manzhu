package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class UserBean {


    /**
     * M_Account_Status : 正常
     * M_Area : 未填写
     * M_Avatar : \/UploadFolder\/image\/201706\/20176261434328136097.jpg
     * M_BusinessType : 一般商家
     * M_City : 成都
     * M_Email :
     * M_FCode : DZwVv
     * M_IDCard :
     * M_IsBusiness : true
     * M_IsLock : false
     * M_IsRealNameAuth : false
     * M_IsSkill : false
     * M_MemberLevel : 1
     * M_Name :
     * M_Phone : 15555555555
     * M_Province : 四川
     * M_Pwd : FCEA920F7412B5DA7BE0CF42B8C93759
     * M_RegIP : 125.69.44.64
     * M_RegTime : 2017-06-12 14:53:57
     * M_Sex : 男
     * M_StoreLevel : 1
     * M_UID : 201761214535744417547
     * M_UserName : cccc
     * N_AllLevel : 2
     * S_DisputeProportion : 0
     * cer_bindemail :
     * cer_bindphone : 15555555555
     * coser_figure_data : {"MCD_Bust":0,"MCD_ClothLength":0,"MCD_Height":0,"MCD_Hipline":0,"MCD_LongPants":0,"MCD_ShoeSize":0,"MCD_ShoulderWidth":0,"MCD_Sleeve":0,"MCD_TheWaist":0,"MCD_Weight":0,"Member_UID":null,"id":113}
     * discoupon_count : 5
     * member_AvailableIntegral : 2135
     * member_AvailableMoney : 9840
     * member_buy_ordercount : 185
     * member_cos_count : 0
     * member_identity : COSER|
     * member_rent_ordercount : 14
     * member_sell_ordercount : 6
     * member_sellrent_ordercount : 0
     * message_count : 16
     * order_deal_count : 1
     * qq_bind_status : false
     * weibo_bind_status : false
     * weixin_bind_status : false
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
    private String S_DisputeProportion;
    private String cer_bindemail;
    private String cer_bindphone;
    private CoserFigureDataBean coser_figure_data;
    private int discoupon_count;
    private String member_AvailableIntegral;
    private String member_AvailableMoney;
    private int member_buy_ordercount;
    private int member_cos_count;
    private String member_identity;
    private int member_rent_ordercount;
    private int member_sell_ordercount;
    private int member_sellrent_ordercount;
    private int message_count;
    private int order_deal_count;
    private String qq_bind_status;
    private String weibo_bind_status;
    private String weixin_bind_status;
    private String lastMessage;
    private  int unredCount = 0;
    private long lasttime;
    private  String lastStringtime;
    private String follow_see_count;

    public String getFollow_see_count() {
        return follow_see_count;
    }

    public void setFollow_see_count(String follow_see_count) {
        this.follow_see_count = follow_see_count;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnredCount() {
        return unredCount;
    }

    public void setUnredCount(int unredCount) {
        this.unredCount = unredCount;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public String getLastStringtime() {
        return lastStringtime;
    }

    public void setLastStringtime(String lastStringtime) {
        this.lastStringtime = lastStringtime;
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

    public String getS_DisputeProportion() {
        return S_DisputeProportion;
    }

    public void setS_DisputeProportion(String S_DisputeProportion) {
        this.S_DisputeProportion = S_DisputeProportion;
    }

    public String getCer_bindemail() {
        return cer_bindemail;
    }

    public void setCer_bindemail(String cer_bindemail) {
        this.cer_bindemail = cer_bindemail;
    }

    public String getCer_bindphone() {
        return cer_bindphone;
    }

    public void setCer_bindphone(String cer_bindphone) {
        this.cer_bindphone = cer_bindphone;
    }

    public CoserFigureDataBean getCoser_figure_data() {
        return coser_figure_data;
    }

    public void setCoser_figure_data(CoserFigureDataBean coser_figure_data) {
        this.coser_figure_data = coser_figure_data;
    }

    public int getDiscoupon_count() {
        return discoupon_count;
    }

    public void setDiscoupon_count(int discoupon_count) {
        this.discoupon_count = discoupon_count;
    }

    public String getMember_AvailableIntegral() {
        return member_AvailableIntegral;
    }

    public void setMember_AvailableIntegral(String member_AvailableIntegral) {
        this.member_AvailableIntegral = member_AvailableIntegral;
    }

    public String getMember_AvailableMoney() {
        return member_AvailableMoney;
    }

    public void setMember_AvailableMoney(String member_AvailableMoney) {
        this.member_AvailableMoney = member_AvailableMoney;
    }

    public int getMember_buy_ordercount() {
        return member_buy_ordercount;
    }

    public void setMember_buy_ordercount(int member_buy_ordercount) {
        this.member_buy_ordercount = member_buy_ordercount;
    }

    public int getMember_cos_count() {
        return member_cos_count;
    }

    public void setMember_cos_count(int member_cos_count) {
        this.member_cos_count = member_cos_count;
    }

    public String getMember_identity() {
        return member_identity;
    }

    public void setMember_identity(String member_identity) {
        this.member_identity = member_identity;
    }

    public int getMember_rent_ordercount() {
        return member_rent_ordercount;
    }

    public void setMember_rent_ordercount(int member_rent_ordercount) {
        this.member_rent_ordercount = member_rent_ordercount;
    }

    public int getMember_sell_ordercount() {
        return member_sell_ordercount;
    }

    public void setMember_sell_ordercount(int member_sell_ordercount) {
        this.member_sell_ordercount = member_sell_ordercount;
    }

    public int getMember_sellrent_ordercount() {
        return member_sellrent_ordercount;
    }

    public void setMember_sellrent_ordercount(int member_sellrent_ordercount) {
        this.member_sellrent_ordercount = member_sellrent_ordercount;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }

    public int getOrder_deal_count() {
        return order_deal_count;
    }

    public void setOrder_deal_count(int order_deal_count) {
        this.order_deal_count = order_deal_count;
    }

    public String getQq_bind_status() {
        return qq_bind_status;
    }

    public void setQq_bind_status(String qq_bind_status) {
        this.qq_bind_status = qq_bind_status;
    }

    public String getWeibo_bind_status() {
        return weibo_bind_status;
    }

    public void setWeibo_bind_status(String weibo_bind_status) {
        this.weibo_bind_status = weibo_bind_status;
    }

    public String getWeixin_bind_status() {
        return weixin_bind_status;
    }

    public void setWeixin_bind_status(String weixin_bind_status) {
        this.weixin_bind_status = weixin_bind_status;
    }

    public static class CoserFigureDataBean {
        /**
         * MCD_Bust : 0
         * MCD_ClothLength : 0
         * MCD_Height : 0
         * MCD_Hipline : 0
         * MCD_LongPants : 0
         * MCD_ShoeSize : 0
         * MCD_ShoulderWidth : 0
         * MCD_Sleeve : 0
         * MCD_TheWaist : 0
         * MCD_Weight : 0
         * Member_UID : null
         * id : 113
         */

        private int MCD_Bust;
        private int MCD_ClothLength;
        private int MCD_Height;
        private int MCD_Hipline;
        private int MCD_LongPants;
        private int MCD_ShoeSize;
        private int MCD_ShoulderWidth;
        private int MCD_Sleeve;
        private int MCD_TheWaist;
        private int MCD_Weight;
        private Object Member_UID;
        private int id;

        public int getMCD_Bust() {
            return MCD_Bust;
        }

        public void setMCD_Bust(int MCD_Bust) {
            this.MCD_Bust = MCD_Bust;
        }

        public int getMCD_ClothLength() {
            return MCD_ClothLength;
        }

        public void setMCD_ClothLength(int MCD_ClothLength) {
            this.MCD_ClothLength = MCD_ClothLength;
        }

        public int getMCD_Height() {
            return MCD_Height;
        }

        public void setMCD_Height(int MCD_Height) {
            this.MCD_Height = MCD_Height;
        }

        public int getMCD_Hipline() {
            return MCD_Hipline;
        }

        public void setMCD_Hipline(int MCD_Hipline) {
            this.MCD_Hipline = MCD_Hipline;
        }

        public int getMCD_LongPants() {
            return MCD_LongPants;
        }

        public void setMCD_LongPants(int MCD_LongPants) {
            this.MCD_LongPants = MCD_LongPants;
        }

        public int getMCD_ShoeSize() {
            return MCD_ShoeSize;
        }

        public void setMCD_ShoeSize(int MCD_ShoeSize) {
            this.MCD_ShoeSize = MCD_ShoeSize;
        }

        public int getMCD_ShoulderWidth() {
            return MCD_ShoulderWidth;
        }

        public void setMCD_ShoulderWidth(int MCD_ShoulderWidth) {
            this.MCD_ShoulderWidth = MCD_ShoulderWidth;
        }

        public int getMCD_Sleeve() {
            return MCD_Sleeve;
        }

        public void setMCD_Sleeve(int MCD_Sleeve) {
            this.MCD_Sleeve = MCD_Sleeve;
        }

        public int getMCD_TheWaist() {
            return MCD_TheWaist;
        }

        public void setMCD_TheWaist(int MCD_TheWaist) {
            this.MCD_TheWaist = MCD_TheWaist;
        }

        public int getMCD_Weight() {
            return MCD_Weight;
        }

        public void setMCD_Weight(int MCD_Weight) {
            this.MCD_Weight = MCD_Weight;
        }

        public Object getMember_UID() {
            return Member_UID;
        }

        public void setMember_UID(Object Member_UID) {
            this.Member_UID = Member_UID;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
