package org.tyrest.core.foundation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/22.
 */
public class SensitiveWordsUtil {


    private static final  String CHECK_API = "http://www.hoapi.com/index.php/Home/Api/check";
    private static final  String TOKEN = "3b7c72766ddea110df46a82b91e721fe";


    /**
     * 检测敏感词
     * @param str   被检测字符串
     * @return
     * @throws IOException
     */
    public static List<String>  checkWords(String str) throws IOException {
        HttpClientHelper helper = new HttpClientHelper();
        List<String> returnList = null;
        if(!ValidationUtil.isEmpty(str))
        {
            str = str.replaceAll(" ","");
        }
        //str = URLEncoder.encode(str,"utf-8");
        String resultStr = helper.post(CHECK_API+"?str="+str+"&token="+TOKEN,new HashMap<String, String>());
        ObjectMapper mapper = new ObjectMapper();
        Map checkResult =mapper.readValue(resultStr,Map.class);
        if("1".equals(checkResult.get("code")))
        {
            List<Map<String,Object>> errorList = ( List<Map<String,Object>>)checkResult.get("error");
            if(!ValidationUtil.isEmpty(errorList))
            {
                returnList = new ArrayList<>();
                for (Map<String,Object> map : errorList)
                {
                    returnList.add((String)map.get("word"));
                }
            }
        }
        return returnList;
    }


    public  static  void main(String [] args)
    {
        try {
            checkWords("我是金三胖");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
