package com.ecggh.userappspring;


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

    // Dependency Injection in action
    @Autowired
    public CredentialRepository credentialRepository;

    @Autowired
    public UserinfoRepository userinfoRepository;

    @GetMapping("/test1")
    public String test1handler()
    {
        return "testview1";
    }

    @GetMapping("/test2")
    public String test2handler()
    {
        return "testview2";
    }

    @PostMapping("/login")
    public String loginHandler(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session)
    {

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
                session.setAttribute("username", username);

                if (tempUserInfo.get().getType().equals("seller"))
                {
                    return "sellerdashboard";
                }
                else if (tempUserInfo.get().getType().equals("buyer"))
                {
                    return "buyerdashboard";
                }
                else if (tempUserInfo.get().getType().equals("admin"))
                {
                    return "dashboard";
                }
                else
                {
                    return "updateprofile";
                }


            }
            else
            {

                model.addAttribute("errMsg", "Incorrect password");
                return "landingpage";

            }



        }
        else // usernamne is invalid
        {
            model.addAttribute("errMsg", "Invalid username");
            return "landingpage";

        }

    }

    @GetMapping("/logout")
    public String logoutHandler( Model model, HttpSession session) // Dependency Injection without annotation with HttpSession and Model
    {
        session.invalidate();
        model.addAttribute("errMsg", "You have successfully logged out");
        return "landingpage";
    }

    @PostMapping("/signup")
    public String signupHandler(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
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






}
