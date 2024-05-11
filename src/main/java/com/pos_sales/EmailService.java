package com.pos_sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendResetEmail(String toEmail, String resetToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(toEmail);
            helper.setSubject("Password Reset");
            helper.setText("<p>Click <a href='https://dilven-springboot.onrender.com/forgotpassword?token=" + resetToken + "'>this link</a> to reset your password</p>", true);

            javaMailSender.send(message);
            
            System.out.println("Mail sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}
