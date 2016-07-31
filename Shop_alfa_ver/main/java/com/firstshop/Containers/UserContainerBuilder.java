package com.firstshop.Containers;

public class UserContainerBuilder {
    private String name;
    private String password;
    private String location;
    public UserContainerBuilder name(String name){
        this.name = name;
        return this;
    }
    public UserContainerBuilder password(String password){
        this.password = password;
        return this;
    }
    public UserContainerBuilder location(String location){
        this.location = location;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getLocation() {
        return this.location;
    }
    public UserContainer buid(){
        return new UserContainer(this);
    }
}
