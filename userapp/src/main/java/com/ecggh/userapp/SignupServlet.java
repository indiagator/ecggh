package com.ecggh.userapp;

import com.ecggh.model.Credential;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        Credential signupCredential = new Credential(username, password);
        credentials.add(signupCredential);

        BufferedWriter out_credentials;
        out_credentials = Files.newBufferedWriter(this.filCredentialsPath);

        for(Credential credential : credentials)
        {
            out_credentials.write(credential.getUsername()+","+credential.getPassword()+"\n");
        }

        out_credentials.flush();

        request.setAttribute("signupMessage","Signup Successful, Login with your Credentials");

        RequestDispatcher view =  request.getRequestDispatcher("index.jsp");
        view.forward(request, response);


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
