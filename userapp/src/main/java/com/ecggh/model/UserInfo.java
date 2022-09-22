package com.ecggh.model;


//POJO
public class UserInfo {


   private String fullname;
   private String phonenumber;
   private String email;

   public UserInfo(String fullname, String phonenumber, String email)
   {
       this.fullname = fullname;
       this.phonenumber = phonenumber;
       this.email = email;
   }

    public String getFullname() {
        return fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }
}
