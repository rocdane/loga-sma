package com.loga.model;

import com.loga.vendor.Crypto;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date dateOuverture;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "close_at")
    private Date dateFermeture;

    @ManyToOne
    @JoinColumn(name = "users",referencedColumnName = "username")
    private User user;
    
    public Session() {
        try {
            this.setToken(Crypto.getInstance().generateKey(128).toString());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
