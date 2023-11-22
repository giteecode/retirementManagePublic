package com.ew.gerocomium.common.util;

import com.ew.gerocomium.common.constant.Constant;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Arrays;
import java.util.List;

public class SendEmailUtil {
    public static void sendEmail(List<String> emailList, String content) {
        for (String email : emailList) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                // 配置发送邮箱的host
                htmlEmail.setHostName(Constant.MAIL_HOST);
                // 配置发送邮箱和邮箱授权码
                htmlEmail.setAuthentication(Constant.MAIL, Constant.PASS);
                // 配置发送方
                htmlEmail.setFrom(Constant.MAIL);
                // 配置接收人
                htmlEmail.addTo(email);
                // 配置邮箱主题
                htmlEmail.setSubject(Constant.SUBJECT);
                // 配置编码格式
                htmlEmail.setCharset("UTF-8");
                // 设置发送的消息
                htmlEmail.setMsg(content);
                // 发送
                String send = htmlEmail.send();
                System.out.println(send);
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
    }
}
