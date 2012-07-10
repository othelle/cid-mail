package com.othelle.cig.email

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 10.07.12
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}
