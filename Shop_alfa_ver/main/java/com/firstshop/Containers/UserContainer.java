package com.firstshop.Containers;

public class UserContainer {
    private String name;
    private String password;
    private String location;

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getLocation() {
        return this.location;
    }

    UserContainer (final UserContainerBuilder userContainerBuilder){
        this.name = userContainerBuilder.getName();
        this.password = userContainerBuilder.getPassword();
        this.location = userContainerBuilder.getLocation();
    }
}
