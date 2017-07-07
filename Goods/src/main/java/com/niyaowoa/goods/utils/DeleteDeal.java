package com.niyaowoa.goods.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niyaowoa on 2017-04-10 .
 */
public class DeleteDeal {
    /**
     * 转为数字
     * @param userid
     * @return
     */
    public static List dealString(String userid){
            List list  = null;
            if(userid.startsWith("[")&&userid.endsWith("]")) {
               list = new ArrayList();
               userid = userid.substring(1,userid.length()-1);
               String[] path = userid.split(",");
               for(String str:path){
                   list.add(Integer.parseInt(str.substring(1,str.length()-1)));
               }

            }

            return list;
    }
    /**
     * 转为字符串
     * @param categoryid
     * @return
     */
    public static List dealToString(String categoryid){
        List list  = null;
        if(categoryid.startsWith("[")&&categoryid.endsWith("]")) {
            list = new ArrayList();
            categoryid = categoryid.substring(1,categoryid.length()-1);
            String[] path = categoryid.split(",");
            for(String str:path){
                list.add(str.substring(1,str.length()-1));
            }

        }

        return list;
    }

}
