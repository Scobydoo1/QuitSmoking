/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Thinkpad
 */
public class EmailUtils {

    //tạo một hàm để gửi email đến một địa chỉ cụ thể -> toEmail
    //tiêu để -> subject
    //nội dung -> body
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        // cấu hình email người gửi
        final String username = "phuocthinh25092004@gmail.com";
        final String password = "csgk yqbh iutq armx";

        //Cấu hình server SMTP (Simple Mail Transfer Protocol) là giao thức để gửi email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // bắt buộc có đăng nhập
        props.put("mail.smtp.starttls.enable", "true"); // bật mã hóa bảo mật TLS (Transport Layer Security) là giao thức mật mã
        // cung cấp bảo mật đầu cuối cho dữ liệu được gửi giữa các ứng dụng qua internet
        props.put("mail.smtp.host", "smtp.gmail.com"); // máy chủ SMTP
        props.put("mail.smtp.port", "587"); // cổng cho TLS

        //Create session to send mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create email content
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Email người gửi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Người nhận
            message.setSubject(subject); // Tiêu đề
            message.setText(body); // Nội dung
            ((MimeMessage) message).setSubject(subject, "UTF-8");
            message.setContent(body, "text/html; charset=UTF-8");

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
