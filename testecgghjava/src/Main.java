import com.ecggh.model.*;

public class Main
{

    // 8 + 1 ?? primitive data types in Java - 4 types for Integer , 2 Types for Decimal, 1 for Char, 1 for Boolean
    // String - an array of Characters

    private int num;
    private double numDouble;
    private String name;

    User user; //scope is relevant to the whole class


    public static void main(String[] args) // method entry point
    {

        // sequence of statements
        // sequence of statements
        // sequence of statements

        System.out.println("Hello ECGGH!");

        ResidentialUser residentialUser  = new ResidentialUser();
        CommercialUser commercialUser = new CommercialUser();

        User user1 = new User(commercialUser, residentialUser);

        PowerPlant powerPlant = new PowerPlant();

        ThermalPP thermalPP = new ThermalPP();
        ThermalPP thermalPP1 = new ThermalPP();
        ThermalPP thermalPP2 = new ThermalPP();


    }

    public void someMethod()
    {

        CommercialUser commercialUser = new CommercialUser();
        ResidentialUser residentialUser = new ResidentialUser();

        user = new User(commercialUser, residentialUser); // dependency

    }


}


