package com.ecggh.userapp;

import com.ecggh.model.Credential;
import com.ecggh.model.UserInfo;

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

@WebServlet(name = "UpdateProfileServlet", value = "/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    private Path fileUserInfoPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //add your own code here
        Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\ecggh\\userapp\\src\\main\\resources\\data\\userinfo.csv");
        //String pathString = p1.toString();
        this.fileUserInfoPath = p1.toAbsolutePath();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserInfo> userInfoList = new ArrayList<>();

        BufferedReader in_userinfo;
        in_userinfo = Files.newBufferedReader(this.fileUserInfoPath);

        String line = "";

        while( ( line = in_userinfo.readLine()) != null )
        {
            String[] tempUIData = line.split(",");
            UserInfo tempUserInfo  = new UserInfo(tempUIData[0],tempUIData[1], tempUIData[2]);
            userInfoList.add(tempUserInfo);
        }

        // The List of UserInfo is populated

        String fullname = request.getParameter("fullname");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");

        UserInfo newUserInfo = new UserInfo(fullname, phonenumber, email);
        userInfoList.add(newUserInfo);

        BufferedWriter out_userinfo;
        out_userinfo = Files.newBufferedWriter(this.fileUserInfoPath);

        for(UserInfo userInfo : userInfoList)
        {
            out_userinfo.write(userInfo.getFullname()+","+userInfo.getPhonenumber()+","+userInfo.getEmail()+"\n");
        }

        out_userinfo.flush();

        request.setAttribute("profileUpdateMessage","Profile Updated Successfully");
        RequestDispatcher view =  request.getRequestDispatcher("dashboard.jsp");
        view.forward(request, response);



    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
