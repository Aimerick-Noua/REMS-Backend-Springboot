package com.app.res.mailling;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Mail_Table")
public class MailClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String destinataire;
    private String last_send;
    private String objet;
    @Column(length = 500)
    private String message;

    public String getLast_send() {
        return last_send;
    }

    public void setLast_send(Date last_send) {
        this.last_send = String.valueOf(LocalDate.now());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MailClass{" +
                "Id=" + Id +
                ", destinataire='" + destinataire + '\'' +
                ", last_send=" + last_send +
                ", objet='" + objet + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public MailClass(Long id, String destinataire, String last_send, String objet, String message) {
        Id = id;
        this.destinataire = destinataire;
        this.last_send = last_send;
        this.objet = objet;
        this.message = message;
    }

    public MailClass() {
        super();
    }
}
