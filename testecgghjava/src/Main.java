import com.ecggh.model.PowerPlant;
import com.ecggh.model.ThermalPP;
import com.ecggh.model.User;

public class Main
{

    // 8 + 1 ?? primitive data types in Java - 4 types for Integer , 2 Types for Decimal, 1 for Char, 1 for Boolean
    // String - an array of Characters

    private int num;
    private double numDouble;
    private String name;

    User user; //scope is relevant to the whole class


    public static void main(String[] args) // method
    {

        // sequence of statements
        // sequence of statements
        // sequence of statements

        System.out.println("Hello ECGGH!");

        User user1 = new User();

        PowerPlant powerPlant = new PowerPlant();

        ThermalPP thermalPP = new ThermalPP();
        ThermalPP thermalPP1 = new ThermalPP();
        ThermalPP thermalPP2 = new ThermalPP();


    }

    public void someMethod()
    {
        user = new User();

    }


}


