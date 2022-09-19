package com.ecggh.userapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("emile"))
        {
            if(password.equals("ecggh123"))
            {

                request.setAttribute("username",username);
                // send the user to dashboard

                 RequestDispatcher view =  request.getRequestDispatcher("dashboard.jsp");
                 view.forward(request, response);
            }
            else
            {
                request.setAttribute("errorMsg","incorrect password");
                // send the user back to LandingPage
            }
        }
        else {

            request.setAttribute("errorMsg","invalid username");
            // send the user back to LandingPage

        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }
}
