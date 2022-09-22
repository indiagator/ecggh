package com.ecggh.userapp;

import com.ecggh.model.Credential;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Not a POJO
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private Path filCredentialsPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //add your own code here
        Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\ecggh\\userapp\\src\\main\\resources\\data\\credentials.csv");
        //String pathString = p1.toString();
        this.filCredentialsPath = p1.toAbsolutePath();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        List<Credential> credentials = new ArrayList<>();

        BufferedReader in_credentials;
        in_credentials = Files.newBufferedReader(this.filCredentialsPath);

        String line = "";

        while( ( line = in_credentials.readLine()) != null )
        {
            String[] tempCredData = line.split(",");
            Credential tempCredential  = new Credential(tempCredData[0],tempCredData[1]);
            credentials.add(tempCredential);
        }

        // The List of Credentials is populated


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<Credential> userCredential ; // just declaration

        if( (userCredential = credentials.stream().filter(credential -> credential.getUsername().equals(username)).findAny()).isPresent() )
        {
            if(userCredential.get().getPassword().equals(password))
            {

                request.setAttribute("username",username);

                HttpSession session = request.getSession(true);
                session.setAttribute("username",username);

                // send the user to dashboard

                 RequestDispatcher view =  request.getRequestDispatcher("dashboard.jsp");
                 view.forward(request, response);
            }
            else
            {
                request.setAttribute("errorMsg","incorrect password");
                // send the user back to LandingPage

                RequestDispatcher view =  request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            }
        }
        else {

            request.setAttribute("errorMsg","invalid username");
            // send the user back to LandingPage

            RequestDispatcher view =  request.getRequestDispatcher("index.jsp");
            view.forward(request, response);

        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();

        //add your own code here
    }
}
