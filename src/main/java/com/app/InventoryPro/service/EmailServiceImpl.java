package com.app.InventoryPro.service;

import com.app.InventoryPro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendLowStockAlert(User user, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Low Stock Alert");
        message.setText("Dear " + user.getUsername() + ",\n\nThe stock for " + productName + " is running low.");
        mailSender.send(message);
    }

    @Override
    public void sendHighStockAlert(User user, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("High Stock Alert");
        message.setText("Dear " + user.getUsername() + ",\n\nThe stock for " + productName + " is high.");
        mailSender.send(message);
    }

    @Override
    public void sendReorderReminder(User user, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reorder Reminder");
        message.setText("Dear " + user.getUsername() + ",\n\nThis is a reminder to reorder " + productName + ".");
        mailSender.send(message);
    }

    @Override
    public void sendEmailChangeNotification(User user, String newEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Change Notification");
        message.setText("Dear " + user.getUsername() + ",\n\nYour email has been changed to " + newEmail + ".");
        mailSender.send(message);
    }
}
