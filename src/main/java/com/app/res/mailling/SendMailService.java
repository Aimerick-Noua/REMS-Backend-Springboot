package com.app.res.mailling;

import com.app.res.property.propertyClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailRepository repository;


    public Long sendMail(MailClass mail)throws Exception{
        System.out.println("Send mail ...");
        SimpleMailMessage msg = new SimpleMailMessage();
        MailClass user = new MailClass();
        msg.setTo(mail.getDestinataire());
        msg.setFrom("aimericknoua1@gmail.com");
        msg.setSubject(mail.getObjet());
        msg.setText(mail.getMessage());
        msg.setSentDate(Date.from(Instant.now()));
        javaMailSender.send(msg);
        return repository.save(mail).getId();
    }

    public List<MailClass> getAllMails() {
        return repository.findAll();
    }
}
