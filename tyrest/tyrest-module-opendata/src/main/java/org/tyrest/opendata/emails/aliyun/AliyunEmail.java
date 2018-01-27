package org.tyrest.opendata.emails.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ttsw on 2017/1/13.
 */
@Component(value = "aliyunEmail")
public class AliyunEmail {

    @Value("${email.aliyun.regionId}")
    private String regionId;
    @Value("${email.aliyun.accessKey}")
    private String accessKey;
    @Value("${email.aliyun.accessSecret}")
    private String accessSecret;

    public void sendEmail(String accountName,String toAddr,String title,String body) throws Exception
    {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName(accountName);//控制台创建的发信地址
        request.setToAddress(toAddr);//目标地址,收件人地址来源是会员注册
        request.setAddressType(0);
        request.setReplyToAddress(true);
        request.setTagName("ttsw");//控制台创建的标签
        request.setSubject(title);
        request.setHtmlBody(body);
        SingleSendMailResponse httpResponse = client.getAcsResponse(request);
    }

    public void sample() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAImE0ebI6u4Yw9", "q30Uk9ZQGXCo31e7wuj9xFjQLJBrf5");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("2582565296@qq.com");//控制台创建的发信地址
            request.setToAddress("ttsw2010@163.com");//目标地址,收件人地址来源是会员注册
            request.setAddressType(0);
            request.setReplyToAddress(true);
            request.setFromAlias("发信人昵称");//发信人昵称
            request.setTagName("");//控制台创建的标签
            request.setSubject("邮件主题");
            request.setHtmlBody("test");
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        AliyunEmail ae = new AliyunEmail();
        ae.sample();
    }
}
