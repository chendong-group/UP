package com.lx.curriculum_vitae.model;

public class User {

    public int id;
    public String account;
    public String nick_name;
    public String user_name;
    public String password;
    public String birthday;
    public String id_card;
    public String personal_profile;
    public String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getPersonal_profile() {
        return personal_profile;
    }

    public void setPersonal_profile(String personal_profile) {
        this.personal_profile = personal_profile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User() {
    }

    public User(int id, String account, String nick_name, String user_name, String password, String birthday, String id_card, String personal_profile, String state) {
        this.id = id;
        this.account = account;
        this.nick_name = nick_name;
        this.user_name = user_name;
        this.password = password;
        this.birthday = birthday;
        this.id_card = id_card;
        this.personal_profile = personal_profile;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", id_card='" + id_card + '\'' +
                ", personal_profile='" + personal_profile + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
