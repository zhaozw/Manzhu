package com.zpfan.manzhu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 17/1/13.
 */

public class    TimeStringUtil {

    public static final String CNCode = "CN";
    public static final String TWCode = "TW";
    public static final String HKCode = "HK";

    /**
     * 根据传入的时间,获取和当前时间相比对应的字符串
     * @param ts 传入的秒数
     * @return
     */
    public static  String getFormatDateString(long ts) {
        //当前手机是否是中文 true 是中文
        boolean isChinese = Locale.getDefault().getCountry().equals(CNCode) || Locale.getDefault().getCountry().equals(TWCode) || Locale.getDefault().getCountry().equals(HKCode) ;

        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());//设置当前时间
        int yearNow = calender.get(Calendar.YEAR);//现在的年份
        int dayNow = calender.get(Calendar.DAY_OF_YEAR);//消息在这一年的day

        calender.setTime(new Date(ts * 1000));//设置传入的时间
        int yearMsg = calender.get(Calendar.YEAR);//消息的年份
        int monthMsg = calender.get(Calendar.MONTH);
        int dayMsg = calender.get(Calendar.DAY_OF_YEAR);//消息的day

        Date date = new Date(ts * 1000);//创建传入时间的date对象
        if (yearNow == yearMsg) {//说明是同一年
            if (dayNow == dayMsg) {//同一天
                return new SimpleDateFormat("HH:mm").format(date);
            } else if (dayNow - dayMsg == 1) {//昨天
                if (isChinese) {
                    return "昨天 " + new SimpleDateFormat("HH:mm").format(date);
                } else {
                    return "Yesterday, " + new SimpleDateFormat("HH:mm").format(date);
                }
            } else {//前天 以及 以前 同一年,10月30日 15:23  , October 30, 15:23
                if (isChinese){//是中文
                    return new SimpleDateFormat("M月d日 HH:mm").format(date);
                }else {
                    String englishMonth = getEnglishMonth(monthMsg);
                    return englishMonth + " " + new SimpleDateFormat("dd, HH:mm").format(date);
                }
            }

        } else {
            //不是同一年2015年4月23日 22:41 *  April 23 2015, 22:41
            if (isChinese){
                return new SimpleDateFormat("yyyy年M月d日 HH:mm").format(date);
            }else {
                String englishMonth = getEnglishMonth(monthMsg);
                return englishMonth + " " + new SimpleDateFormat("d yyyy, HH:mm").format(date);
            }
        }


    }

    /**
     * 获取英文对应的月
     * @param monthMsg
     * @return
     */
    private static String getEnglishMonth(int monthMsg) {
        String month = "";
        switch (monthMsg) {
            case 0://1月
                month = "January";
                break;
            case 1://2月
                month = "February";
                break;
            case 2://3月
                month = "March";
                break;
            case 3://4月
                month = "April";
                break;
            case 4://5月
                month = "May";
                break;
            case 5://6月
                month = "June";
                break;
            case 6://7月
                month = "July";
                break;
            case 7://8月
                month = "August";
                break;
            case 8://9月
                month = "September";
                break;
            case 9://10月
                month = "October";
                break;
            case 10://11月
                month = "November";
                break;
            case 11://12月
                month = "December";
                break;
        }
        return month;
    }


   /* public static Long getFormDataLong(long s)  {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt2 = null;
        try {
            if (dt2 != null) {
                dt2 = sdf.parse(s);
                long lTime = dt2.getTime();
                return lTime;
            } else {
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }*/


}
