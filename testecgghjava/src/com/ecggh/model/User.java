package com.ecggh.model;



public class User
{

    // class body
    private String username;

    private CommercialUser commercialUser;
    private ResidentialUser residentialUser;

    public User(CommercialUser commercialUser, ResidentialUser residentialUser)
    {
        this.commercialUser = commercialUser;
        this.residentialUser = residentialUser;
    }
}
