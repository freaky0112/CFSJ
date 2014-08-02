package com.freaky.cfsj;

import android.os.Environment;

/**
 * Created by Freaky on 2014/8/2.
 */
public class Common {
    public static String DB_NAME="CFSJ.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.freaky.cfsj";

    public static  final String DB_PATH="/data"+ Environment.getDataDirectory().getAbsolutePath()+"/"+Common.PACKAGE_NAME;//
}
