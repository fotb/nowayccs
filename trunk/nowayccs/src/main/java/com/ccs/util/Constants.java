package com.ccs.util;

import java.util.HashMap;

public class Constants {
    /*ϵͳȨ�޶���*/
    public static long SYS_PERMISSION_YWSL = 10; //ҵ������
    public static long SYS_PERMISSION_SHYWCL = 20; //����ҵ����
    public static long SYS_PERMISSION_SHYWCX = 21; //�鿴���������ϸ��Ϣ
    public static long SYS_PERMISSION_SWYWCL = 30; //����ҵ����
    public static long SYS_PERMISSION_SHKHHF = 40; //����ͻ��ط�
    public static long SYS_PERMISSION_SWKHWF = 50; //����ͻ��ط�
    public static long SYS_PERMISSION_BBCX = 60; //�����ѯ
    public static long SYS_PERMISSION_QZZXXCX = 70; //��������Ϣ��ѯ
    public static long SYS_PERMISSION_ZYZFWHZ = 80; //һ��֮�������߻���
    public static long SYS_PERMISSION_YWZXWH = 90; //ҵ����ѯά��
    public static long SYS_PERMISSION_YWZXCX = 100; //ҵ����ѯ��ѯ
    public static long SYS_PERMISSION_YHGL = 110; //�û�����
    public static long SYS_PERMISSION_JSQXWH = 120; //��ɫȨ��ά��
    public static long SYS_PERMISSION_JDSQWH = 130; //�ֵ�����ά��
    public static long SYS_PERMISSION_SQZYZWH = 140; //һ��֮��������ά��
    public static long SYS_PERMISSION_SQZYZCX = 141; //һ��֮�������߲�ѯ
    public static long SYS_PERMISSION_WWQYLBWH = 150; //������ҵ���ά��
    public static long SYS_PERMISSION_FWQYWH = 160; //������ҵά��
    public static long SYS_PERMISSION_FWQYCX = 161; //������ҵ��ѯ
    public static long SYS_PERMISSION_SJZDWH = 170; //�����ֵ�ά��
    public static long SYS_PERMISSION_ZQLJSZ = 180; //��ϯ��������


    /*���͵Ķ�������-1*/
    public static final String TOP_CODE = "-1";

    /*���ݿ��ֶ�Ϊ�գ�Ĭ�ϵ�����-1*/
    public static final long DATABASE_NULL_NUMBER = -1;

    /*�Ա�  1�� 2Ů*/
    public static final String SYS_SEX_MAN = "1";
    public static final String SYS_SEX_WOMAN = "2";
    public static final HashMap SYS_SEX_HASHMAP = new HashMap();
    static {
        SYS_SEX_HASHMAP.put(String.valueOf(SYS_SEX_MAN), "��");
        SYS_SEX_HASHMAP.put(String.valueOf(SYS_SEX_WOMAN), "Ů");
    }

    /*������״̬��0ֹͣ���� 1������*/
    public static final String SYS_VOLUNTEER_STATUS_YES = "1";
    public static final String SYS_VOLUNTEER_STATUS_NO = "0";
    public static final HashMap SYS_VOLUNTEER_STATUS_HASHMAP = new HashMap();
    static {
        SYS_VOLUNTEER_STATUS_HASHMAP.put(String.valueOf(
                SYS_VOLUNTEER_STATUS_YES), "������");
        SYS_VOLUNTEER_STATUS_HASHMAP.put(String.valueOf(SYS_VOLUNTEER_STATUS_NO),
                                         "ֹͣ����");
    }

    /*Ա��״̬:0:��ְ 1��ְ*/
    public static final String SYS_USER_STATUS_NO = "0";
    public static final String SYS_USER_STATUS_YES = "1";
    public static final HashMap SYS_USER_STATUS_HASHMAP = new HashMap();
    static {
        SYS_USER_STATUS_HASHMAP.put(String.valueOf(SYS_USER_STATUS_NO), "��ְ");
        SYS_USER_STATUS_HASHMAP.put(String.valueOf(SYS_USER_STATUS_YES), "��ְ");
    }


    /*ϵͳ�Ƿ��־��0�� 1��*/
    public static final String SYS_YESNO_NO = "0";
    public static final String SYS_YESNO_YES = "1";
    public static final HashMap SYS_YESNO_HASHMAP = new HashMap();
    static {
        SYS_YESNO_HASHMAP.put(String.valueOf(SYS_YESNO_NO), "��");
        SYS_YESNO_HASHMAP.put(String.valueOf(SYS_YESNO_YES), "��");
    }

    /*ҵ��״̬��*/
    public static final String SYS_INFOMATION_STATES_DB = "1"; //1������
    public static final String SYS_INFOMATION_STATES_CLZ = "2"; // 2��������
    public static final String SYS_INFOMATION_STATES_YJA = "3"; // 3���ѽ᰸
    public static final String SYS_INFOMATION_STATES_YQX = "4"; //4����ȡ��
    public static final HashMap SYS_INFOMATION_STATES_HASHMAP = new HashMap();
    static {
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_DB), "����");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_CLZ), "������");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_YJA), "�ѽ᰸");
        SYS_INFOMATION_STATES_HASHMAP.put(String.valueOf(
                SYS_INFOMATION_STATES_YQX), "��ȡ��");
    }

    /*�������ͣ�*/
    public static final String INFOMATION_HELPTYPE_REFER = "1"; //1��ѯ������
    public static final String INFOMATION_HELPTYPE_LIFE = "2"; //2���������
    public static final String INFOMATION_HELPTYPE_AFFAIR = "3"; //3���������
    public static final String INFOMATION_HELPTYPE_FERTILITY = "4"; //4����������
    public static final HashMap INFOMATION_HELPTYPE_HASHMAP = new HashMap();
    static {
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_REFER), "��ѯ������");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_LIFE),
                                        "���������");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_AFFAIR), "���������");
        INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(
                INFOMATION_HELPTYPE_FERTILITY), "����������");
    }

    /*������������ͣ�*/
    public static final String LIFEINFOMATION_RECEIVETYPE_ZYZ = "1"; //1һ��֮��������
    public static final String LIFEINFOMATION_RECEIVETYPE_QY = "2"; //2������ҵ
    public static final HashMap LIFEINFOMATION_RECEIVETYPE_HASHMAP = new
            HashMap();
    static {
        LIFEINFOMATION_RECEIVETYPE_HASHMAP.put(String.valueOf(
                LIFEINFOMATION_RECEIVETYPE_ZYZ), "һ��֮��������");
        LIFEINFOMATION_RECEIVETYPE_HASHMAP.put(String.valueOf(
                LIFEINFOMATION_RECEIVETYPE_QY), "������ҵ");
    }

    /*�����ֵ����ͣ�*/
    public static final String DICT_DICTTYPE_LLFS = "LLFS"; //ҵ�����緽ʽ :LLFS
    public static final String DICT_DICTTYPE_MYD = "MYD"; //�����      :MYD
    public static final String DICT_DICTTYPE_QZFS = "QZFS"; //������ʽ    :QZQY
    public static final String DICT_DICTTYPE_QZQY = "QZQY"; //��������    :QZQY
    public static final String DICT_DICTTYPE_ZXLX = "ZXLX"; //ҵ����ѯ����:ZXLX
    public static final String DICT_DICTTYPE_SLRQ = "SLRQ"; //������Ⱥ:GROUP
    public static final String DICT_DICTTYPE_COMMONLB = "XXLB"; //������Ϣ���:COMMONLB
    public static final HashMap<String, String> DICT_DICTTYPE_HASHMAP = new HashMap<String, String>();
    static {
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_LLFS), "ҵ�����緽ʽ");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_MYD), "�����");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_QZFS), "������ʽ");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_QZQY), "��������");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_ZXLX), "ҵ����ѯ����");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_SLRQ), "������Ⱥ");
        DICT_DICTTYPE_HASHMAP.put(String.valueOf(DICT_DICTTYPE_COMMONLB), "��Ϣ���");
    }


    /*��������*/
    /*
    public static final int HELP_TYPE_QUERY = 1; //��ѯ������
    public static final int HELP_TYPE_LIFE = 2; //���������
    public static final int HELP_TYPE_TRANSACTION = 3; //���������
    public static HashMap HELP_TYPE_HASHMAP = new HashMap();
    static {
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_QUERY), "��ѯ������");
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_LIFE), "���������");
        HELP_TYPE_HASHMAP.put(String.valueOf(HELP_TYPE_TRANSACTION), "���������");
    }
    */
}
