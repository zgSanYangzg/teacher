package org.tyrest.opendata.emails.sendcloud;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.opendata.emails.EmailSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttsw on 2017/1/13.
 */
@Component(value = "sendcloudEmail")
public class SendcloudEmail extends EmailSender {
    @Value("${email.sendcloud.apiUrl}")
    private String apiUrl;
    @Value("${email.sendcloud.apiUser}")
    private String apiUser;
    @Value("${email.sendcloud.apiKey}")
    private String apiKey;

    public void sendEmail(String fromName,String from,String to,String subject,String content) throws  Exception
    {

        HttpPost httpPost = new HttpPost(apiUrl);
        HttpClient httpClient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", to));
        params.add(new BasicNameValuePair("from", from));
        params.add(new BasicNameValuePair("fromName", fromName));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", content));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);

        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 正常返回, 解析返回数据
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            throw new BusinessException("邮件发送失败：状态："+response.getStatusLine().getStatusCode());
        }
        httpPost.releaseConnection();
    }

}
