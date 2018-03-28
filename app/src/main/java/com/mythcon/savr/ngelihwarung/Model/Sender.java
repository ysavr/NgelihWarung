package com.mythcon.savr.ngelihwarung.Model;

/**
 * Created by SAVR on 27/03/2018.
 * Class ini dibuat Setelah membuat Class MyFirebaseMesaging
 * Selanjutnya buat Class Notification
 * selanjutnya buat Class MyResponse
 * Selanjutnya buat Class Result
 */

public class Sender {
    public String to;
    public Notification notification;

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
