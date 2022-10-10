package com.ecggh.userappspring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MainController
{

    Logger logger = LoggerFactory.getLogger(MainController.class);
    // Dependency Injection in action
    @Autowired
    public CredentialRepository credentialRepository;

    @Autowired
    public UserinfoRepository userinfoRepository;

    @Autowired
    public ProductofferRepository productofferRepositoryJpa;

    @GetMapping("/test1")
    public String test1handler()
    {

        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code

        return "testview1";
    }

    @GetMapping("/test2")
    public String test2handler()
    {

        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code

        return "testview2";
    }

    @PostMapping("/login")
    public String loginHandler(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session)
    {

        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code


        Optional<Userinfo> tempUserInfo;
        Optional<Credential> userCredential = credentialRepository.findById(username);

        //****************************

        if (userCredential.isPresent()) // if username is valid
        {
            // username is valid code

            Credential tempCredential = userCredential.get();
            tempUserInfo = userinfoRepository.findById(tempCredential.getId());

            if (tempCredential.getPassword().equals(password))
            {
                session.setAttribute("username", username); // Authentication Mechanism (Token) can be implemented here

                if (tempUserInfo.get().getType().equals("seller"))
                {
                    logger.info("A Seller Logged in with username "+session.getAttribute("username"));
                    return "sellerdashboard";
                }
                else if (tempUserInfo.get().getType().equals("buyer"))
                {
                    logger.info("A Buyer Logged in with username "+session.getAttribute("username"));
                    return "buyerdashboard";
                }
                else if (tempUserInfo.get().getType().equals("admin"))
                {
                    logger.info("An Admin Logged in with username "+session.getAttribute("username"));
                    return "dashboard";
                }
                else
                {
                    logger.info("A New User Logged in");

                    return "updateprofile";
                }


            }
            else
            {

                logger.info("Incorrect Password Entered");


                model.addAttribute("errMsg", "Incorrect password");
                return "landingpage";

            }



        }
        else // usernamne is invalid
        {
            logger.info("Invalid Username entered");



            model.addAttribute("errMsg", "Invalid username");
            return "landingpage";

        }

    }

    @GetMapping("/logout")
    public String logoutHandler( Model model, HttpSession session) // Dependency Injection without annotation with HttpSession and Model
    {
        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code

        logger.info("a user logged out with username:"+session.getAttribute("username"));

        session.invalidate();
        model.addAttribute("errMsg", "You have successfully logged out");
        return "landingpage";
    }

    @PostMapping("/signup")
    public String signupHandler(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code


        Credential newCredential = new Credential();
        newCredential.setId(username);
        newCredential.setPassword(password);

        credentialRepository.save(newCredential);

        model.addAttribute("errMsg","New Signup Completed");

        return "landingpage";
    }

    @PostMapping("/updateprofile")
    public String updateProfileHandler(@RequestParam("fullname") String fullname, @RequestParam("phonenumber") String phonenumber, @RequestParam("type") String type, HttpSession session )
    {

        //verify the security token --> execute the following code if the token is valid, otherwise send a 404 or some other relevant error code

        String username = (String) session.getAttribute("username");

        Userinfo newUserInfo = new Userinfo();
        newUserInfo.setId(username);
        newUserInfo.setFullname(fullname);
        newUserInfo.setPhonenumber(phonenumber);
        newUserInfo.setType(type);

        userinfoRepository.save(newUserInfo);

        if (newUserInfo.getType().equals("seller"))
        {
            return "sellerdashboard";
        }
        else if (newUserInfo.getType().equals("buyer"))
        {
            return "buyerdashboard";
        }
        else if (newUserInfo.getType().equals("admin"))
        {
            return "dashboard";
        }
        else
        {
            return "updateprofile";
        }
    }


    @PostMapping("/saveproductoffer")
    public String saveProductOffer( @RequestParam("hscode") String hscode, @RequestParam("qty") int qty, @RequestParam("price") int price, @RequestParam("currency") String currency, HttpSession session)
    {

        String offerid = (new Integer(((Double.valueOf(Math.random()*100000)).intValue()))).toString();



        Productoffer productoffer = new Productoffer();
        productoffer.setId(offerid);
        productoffer.setUsername((String) session.getAttribute("username"));
        productoffer.setHscode(hscode);
        productoffer.setQty(qty);
        productoffer.setPrice(price);
        productoffer.setCurrency(currency);

        //productofferRepository.
        productofferRepositoryJpa.save(productoffer);


        return "sellerdashboard";
    }






}
