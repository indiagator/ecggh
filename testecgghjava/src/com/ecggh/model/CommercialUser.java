package com.ecggh.model;

public class CommercialUser extends User
{

    public CommercialUser(CommercialUser commercialUser, ResidentialUser residentialUser) {
        super(commercialUser, residentialUser);
    }
}
