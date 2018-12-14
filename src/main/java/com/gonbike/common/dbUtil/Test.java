package com.gonbike.common.dbUtil;

 

/**
 * Created by Shuaige on 2018/12/13.
 */
public class Test {

    
    public static void main(String[] args) {
        String v = Inflector.getInstance().pluralize("category");
        String v2 = Inflector.getInstance().singularize("categories");
        System.out.println("复数：" + v);
        System.out.println("单数：" + v2);
        
    }
}
