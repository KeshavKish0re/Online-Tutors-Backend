 package com.onlinetutors.backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.onlinetutors.backend.entity.Enquiry;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEnquiryMail(String name, String email, String phone, String grade, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("raahgir01@gmail.com"); // admin email
        mail.setSubject("New Enquiry Received - Online Tutors");

        mail.setText(
            "New enquiry received:\n\n" +
            "Name: " + name + "\n" +
            "Email: " + email + "\n" +
            "Phone: " + phone + "\n" +
            "Class: " + grade + "\n" +
            "Message: " + message
        );

        mailSender.send(mail);
    }
    public void sendStudentAutoReply(Enquiry enquiry) {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(enquiry.getEmail());
    message.setSubject("Thanks for contacting Online Tutors");

    message.setText(
        "Dear " + enquiry.getName() + ",\n\n" +
        "Thank you for contacting Online Tutors.\n\n" +
        "We have received your enquiry regarding class " + enquiry.getGrade() + ".\n" +
        "Our team will contact you shortly to schedule your trial session.\n\n" +
        "Warm Regards,\n" +
        "Online Tutors Team\n" +
        "📞 +91-8585895058"
    );

    mailSender.send(message);
}

}
