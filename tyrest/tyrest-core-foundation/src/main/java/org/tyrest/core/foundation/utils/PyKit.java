package org.tyrest.core.foundation.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * freeapis, Inc.
 * Copyright (C): 2016
 * <p>
 * Description:
 * TODO
 * <p>
 * Author:Administrator
 * Date:2016年10月26日 13:32
 */
public class PyKit {
    public static String pin(String chinese) throws Exception {
        String pinyin = "";
        HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();
        pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] pinyinArray = null;
        for(char ch : chinese.toCharArray()){
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch,pinyinOutputFormat);
            pinyin += ValidationUtil.isEmpty(pinyinArray) ? ch : pinyinArray[0];
        }
        return pinyin;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(PyKit.pin("长沙/"));
    }
}
