package com.app.res.mailling;

import com.app.res.property.propertyClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    @Autowired
    private SendMailService sendMailService;

     @Autowired
     private MailRepository mailRepository;

    @PostMapping(path = "/mails")
    public Long save(@RequestBody MailClass mailClass) throws Exception {
        System.out.println("send mail");
        return sendMailService.sendMail(mailClass);
    }
    @GetMapping(path = "/mails")
    public List<MailClass> getMails(){
        return sendMailService.getAllMails();
    }
    @GetMapping(path = "/mails/{mailId}")
    public ResponseEntity<MailClass> getMailById(@PathVariable Long mailId) {
        MailClass mailClass = mailRepository.findById(mailId)
                .orElseThrow(() -> new IllegalStateException("property not exist with id:" + mailId));
        return ResponseEntity.ok(mailClass);
    }
}
