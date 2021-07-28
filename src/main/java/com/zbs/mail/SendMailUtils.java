package com.zbs.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * description: MailUtils
 * date: 2020/5/3 12:26
 * author: zbs
 * version: 1.0
 */
public class SendMailUtils {

    private String host = ""; // smtp服务器
    private String from = ""; // 发件人地址
    private String to = ""; // 收件人地址
    private String affix = ""; // 附件地址
    private String affixName = ""; // 附件名称
    private String user = ""; // 用户名
    private String pwd = ""; // 密码
    private String subject = ""; // 邮件标题
    private String messageText = ""; //邮件内容消息文本

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public void setAffix(String affix, String affixName) {
        this.affix = affix;
        this.affixName = affixName;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * QQ邮箱端口587
     *
     * @param host 邮箱服务器地址
     * @param user 发送方用户邮件
     * @param pwd 发送方用户授权码
     */
    public void send(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;

        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(messageText);
            multipart.addBodyPart(contentPart);
            // 添加附件
//            BodyPart messageBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(affix);
            // 添加附件的内容
//            messageBodyPart.setDataHandler(new DataHandler(source));
            // 添加附件的标题
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//            messageBodyPart.setFileName("=?GBK?B?"
//                    + enc.encode(affixName.getBytes()) + "?=");
//            multipart.addBodyPart(messageBodyPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendMailUtils cn = new SendMailUtils();
        // 设置发件人地址、收件人地址和邮件标题
        //发给QQ邮箱用户
        cn.setAddress("fzctjh@163.com", "zhangbaoshuai@cph.citic", "一个不带附件的JavaMail邮件");
        //发给163邮箱用户
//        cn.setAddress("250468591@qq.com", "15732136890@163.com", "一个不带附件的JavaMail邮件");

        //设置邮件内容消息文本
        cn.setMessageText("测试发送一个邮件，zbs");

        // 设置要发送附件的位置和标题
        //cn.setAffix("f:/123.txt", "123.txt");

        /**
         * 设置smtp服务器以及邮箱的帐号和密码
         * 用QQ 邮箱作为发生者不好使 （原因不明）
         * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
         * 因为程序属于第三方登录，所以登录密码必须使用163的授权码
         */

        // 注意： [授权码和你平时登录的密码是不一样的]
        cn.send("smtp.163.com", "fzctjh@163.com", "SORAGWTNYMIEBSUH");

        //QQ邮箱授权
//        cn.send("smtp.qq.com", "250468591@qq.com", "wftfaksmqikwbjcb");


//        cn.send("smtp.exmail.qq.com", "mail@zqjc-jx.com", "n5ZhGLocfjk7h87m");
    }
}
