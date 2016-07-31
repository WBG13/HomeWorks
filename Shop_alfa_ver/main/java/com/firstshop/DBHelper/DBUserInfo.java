package com.firstshop.dbhelper;

public abstract class DBUserInfo {
    private String uid;
    private String pwd;
    private String cat;
    public DBUserInfo(){
    }
    public DBUserInfo(String userName, String password ){
        this.uid = userName;
        this.pwd = password;
    }

    public String getUserId(){
        return this.uid;
    }

    public String getCat() {
        return cat;
    }

    public String getPassword() {
        return pwd;
    }

    public void setUserId(String uid) {
        this.uid = uid;
    }

    public void setPassword(String pwd) {
        this.pwd = pwd;
    }

    public void setCatalog(String cat) {
        this.cat = cat;
    }
}
