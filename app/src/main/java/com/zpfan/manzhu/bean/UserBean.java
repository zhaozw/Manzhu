package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class UserBean {

    /**
     * M_Account_Status : 正常
     * M_Area : 未填写
     * M_Avatar : /UploadFolder/image/201706/20176121583178955595.jpg
     * M_BusinessType :
     * M_City : 成都
     * M_Email :
     * M_FCode : rayPi
     * M_IDCard :
     * M_IsBusiness : false
     * M_IsLock : false
     * M_IsRealNameAuth : false
     * M_IsSkill : false
     * M_MemberLevel : 1
     * M_Name :
     * M_Phone : 15029498047
     * M_Province : 四川
     * M_Pwd : E10ADC3949BA59ABBE56E057F20F883E
     * M_RegIP : 125.69.44.64
     * M_RegTime : /Date(1497232643623+0800)/
     * M_Sex : 男
     * M_StoreLevel : 1
     * M_UID : 20176129572340587534
     * M_UserName : zzz
     * N_AllLevel : 2
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
    private String lastMessage;
    private  int unredCount = 0;
    private long lasttime;
    private  String lastStringtime;
    private  String member_identity;
    private String S_DisputeProportion;
    private String order_deal_count;

    public String getMember_identity() {
        return member_identity;
    }

    public void setMember_identity(String member_identity) {
        this.member_identity = member_identity;
    }

    public String getS_DisputeProportion() {
        return S_DisputeProportion;
    }

    public void setS_DisputeProportion(String s_DisputeProportion) {
        S_DisputeProportion = s_DisputeProportion;
    }

    public String getOrder_deal_count() {
        return order_deal_count;
    }

    public void setOrder_deal_count(String order_deal_count) {
        this.order_deal_count = order_deal_count;
    }

    public String getLastStringtime() {
        return lastStringtime;
    }

    public void setLastStringtime(String lastStringtime) {
        this.lastStringtime = lastStringtime;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
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
}
