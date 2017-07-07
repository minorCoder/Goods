package com.niyaowoa.goods.utils;

import com.niyaowoa.goods.entity.PageData;

/**
 * Created by niyaowoa on 2017-04-11 .
 */
public class UserUtil {
    public static PageData  addUse(PageData pd){
        pd.put("age",Integer.parseInt(pd.getString("age")));
        pd.put("role",Integer.parseInt(pd.getString("role")));
        pd.put("password", MD5Util.convertMD5(setPassword(pd.getString("ID_card"))));
        pd.put("reg_date",DateUtil.getTime().toString());
        return pd;
    }

     public static String setPassword(String str){

        return str.substring(str.length()-6,str.length());
    }

    /**
     *处理查找条件
     */
    public static PageData dealSearch(PageData pd){
        if(pd.getString("info")==null||pd.getString("info").equals("null")){
            pd.put("info","");
        }
        if(pd.getString("start")==null||pd.getString("start").equals("null")){
            pd.put("start","1900-1-1");
        }
        if(pd.getString("end")==null||pd.getString("end").equals("null")){
            pd.put("end","9999-9-9");
        }
        return pd;
    }

}
