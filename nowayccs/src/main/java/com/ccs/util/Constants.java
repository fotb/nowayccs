package com.ccs.util;

import java.util.HashMap;

public class Constants {
    /*系统权限定义*/
    public static long SYS_PERMISSION_YWSL = 10; //业务受理
    public static long SYS_PERMISSION_SHYWCL = 20; //生活业务处理
    public static long SYS_PERMISSION_SHYWCX = 21; //查看生活服务详细信息
    public static long SYS_PERMISSION_SWYWCL = 30; //事务业务处理
    public static long SYS_PERMISSION_SHKHHF = 40; //生活客户回访
    public static long SYS_PERMISSION_SWKHWF = 50; //事务客户回访
    public static long SYS_PERMISSION_BBCX = 60; //报表查询
    public static long SYS_PERMISSION_QZZXXCX = 70; //求助者信息查询
    public static long SYS_PERMISSION_ZYZFWHZ = 80; //一技之长服务者汇总
    public static long SYS_PERMISSION_YWZXWH = 90; //业务咨询维护
    public static long SYS_PERMISSION_YWZXCX = 100; //业务咨询查询
    public static long SYS_PERMISSION_YHGL = 110; //用户管理
    public static long SYS_PERMISSION_JSQXWH = 120; //角色权限维护
    public static long SYS_PERMISSION_JDSQWH = 130; //街道社区维护
    public static long SYS_PERMISSION_SQZYZWH = 140; //一技之长服务者维护
    public static long SYS_PERMISSION_SQZYZCX = 141; //一技之长服务者查询
    public static long SYS_PERMISSION_WWQYLBWH = 150; //服务企业类别维护
    public static long SYS_PERMISSION_FWQYWH = 160; //服务企业维护
    public static long SYS_PERMISSION_FWQYCX = 161; //服务企业查询
    public static long SYS_PERMISSION_SJZDWH = 170; //数据字典维护
    public static long SYS_PERMISSION_ZQLJSZ = 180; //座席联机设置


    /*树型的顶级代码-1*/
    public static final String TOP_CODE = "-1";

    /*数据库字段为空，默认的数字-1*/
    public static final long DATABASE_NULL_NUMBER = -1;

    /*性别：  1男 2女*/
    public static final String SYS_SEX_MAN = "1";
    public static final String SYS_SEX_WOMAN = "2";
    public static final HashMap SYS_SEX_HASHMAP = new HashMap();
    static {
        SYS_SEX_HASHMAP.put(String.valueOf(SYS_SEX_MAN), "男");
        SYS_SEX_HASHMAP.put(String.valueOf(SYS_SEX_WOMAN), "女");
    }

    /*服务者状态：0停止服务 1服务中*/
    public static final String SYS_VOLUNTEER_STATUS_YES = "1";
    public static final String SYS_VOLUNTEER_STATUS_NO = "0";
    public static final HashMap SYS_VOLUNTEER_STATUS_HASHMAP = new HashMap();
    static {
        SYS_VOLUNTEER_STATUS_HASHMAP.put(String.valueOf(
                SYS_VOLUNTEER_STATUS_YES), "服务中");
        SYS_VOLUNTEER_STATUS_HASHMAP.put(String.valueOf(SYS_VOLUNTEER_STATUS_NO),
                                         "停止服务");
    }

    /*员工状态:0:离职 1在职*/
    public static final String SYS_USER_STATUS_NO = "0";
    public static final String SYS_USER_STATUS_YES = "1";
    public static final HashMap SYS_USER_STATUS_HASHMAP = new HashMap();
    static {
        SYS_USER_STATUS_HASHMAP.put(String.valueOf(SYS_USER_STATUS_NO), "离职");
        SYS_USER_STATUS_HASHMAP.put(String.valueOf(SYS_USER_STATUS_YES), "在职");
    }


    /*系统是否标志：0否 1是*/
    public static final String SYS_YESNO_NO = "0";
    public static final String SYS_YESNO_YES = "1";
    public static final HashMap SYS_YESNO_HASHMAP = new HashMap();
    static {
        SYS_YESNO_HASHMAP.put(String.valueOf(SYS_YESNO_NO), "否");
        SYS_YESNO_HASHMAP.put(String.valueOf(SYS_YESNO_YES), "是");
    }

    /*业务状态：*/
    public static final String SYS_INFOMATION_STATES_DB = "1"; //1、待办
    public static final String SYS_INFOMATION_STATES_CLZ = "2"; // 2、处理中
    public static final String SYS_INFOMATION_STATES_YJA = "3"; // 3、已结案
    public static final String SYS_INFOMATION_STATES_YQX = "4"; //4、已取消
    public static final HashMap SYS_INFOMATION_STATES_HASHMAP = new HashMap();
    static {
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_DB), "待办");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_CLZ), "处理中");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_YJA), "已结案");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_YQX), "已取消");
    }

    /*求助类型：*/
    public static final String INFOMATION_HELPTYPE_REFER = "1"; //1咨询服务类
    public static final String INFOMATION_HELPTYPE_LIFE = "2"; //2生活服务类
    public static final String INFOMATION_HELPTYPE_AFFAIR = "3"; //3事务服务类
    public static final String INFOMATION_HELPTYPE_FERTILITY = "4"; //4生产力服务
    public static final HashMap INFOMATION_HELPTYPE_HASHMAP = new HashMap();
    static {
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_REFER), "咨询服务类");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_LIFE),
                                        "生活服务类");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_AFFAIR), "事务服务类");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_FERTILITY), "生产力服务");
    }

    /*生活类接受类型：*/
    public static final String LIFEINFOMATION_RECEIVETYPE_ZYZ = "1"; //1一技之长服务者
    public static final String LIFEINFOMATION_RECEIVETYPE_QY = "2"; //2服务企业
    public static final HashMap LIFEINFOMATION_RECEIVETYPE_HASHMAP = new
            HashMap();
    static {
        LIFEINFOMATION_RECEIVETYPE_HASHMAP.put(String.valueOf(
                LIFEINFOMATION_RECEIVETYPE_ZYZ), "一技之长服务者");
        LIFEINFOMATION_RECEIVETYPE_HASHMAP.put(String.valueOf(
                LIFEINFOMATION_RECEIVETYPE_QY), "服务企业");
    }

    /*数据字典类型：*/
    public static final String DICT_DICTTYPE_LLFS = "LLFS"; //业务联络方式 :LLFS
    public static final String DICT_DICTTYPE_MYD = "MYD"; //满意度      :MYD
    public static final String DICT_DICTTYPE_QZFS = "QZFS"; //求助方式    :QZQY
    public static final String DICT_DICTTYPE_QZQY = "QZQY"; //求助区域    :QZQY
    public static final String DICT_DICTTYPE_ZXLX = "ZXLX"; //业务咨询类型:ZXLX
    public static final String DICT_DICTTYPE_SLRQ = "SLRQ"; //受理人群:GROUP
    public static final String DICT_DICTTYPE_COMMONLB = "XXLB"; //共享信息类别:COMMONLB
    public static final HashMap<String, String> DICT_DICTTYPE_HASHMAP = new HashMap<String, String>();
    static {
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_LLFS), "业务联络方式");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_MYD), "满意度");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_QZFS), "求助方式");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_QZQY), "求助区域");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_ZXLX), "业务咨询类型");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_SLRQ), "受理人群");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_COMMONLB), "信息类别");
    }


    /*求助类型*/
    /*
    public static final int HELP_TYPE_QUERY = 1; //咨询服务类
    public static final int HELP_TYPE_LIFE = 2; //生活服务类
    public static final int HELP_TYPE_TRANSACTION = 3; //事务服务类
    public static HashMap HELP_TYPE_HASHMAP = new HashMap();
    static {
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_QUERY), "咨询服务类");
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_LIFE), "生活服务类");
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_TRANSACTION), "事务服务类");
    }
    */
}
