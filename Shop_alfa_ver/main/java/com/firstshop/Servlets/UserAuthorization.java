package com.firstshop.Servlets;

import com.worldmanager.dbhelper.old_file_location.controller.database.Connector3;
import com.worldmanager.dbhelper.old_file_location.controller.database.SimpleCommands;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by TH-221 on 25.06.2016.
 */
@WebServlet("/loginAuthorization.do")
public class UserAuthorization extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uid");
        String userPassword = request.getParameter("psw");
        SimpleCommands com = new SimpleCommands();
        int authLevel=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connector3 con =  new Connector3();
                if (con.existenceCheck(com.checkUserForExist("user_name", userName), "user_name").equals(userName)
                        && con.existenceCheck(com.checkUserForExist("user_pass", userPassword), "user_pass").equals(userPassword)){
                    authLevel = 1;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String uid = "authorizeduser";
        HttpSession s = request.getSession();
        s.setAttribute("userName", uid);
        s.setAttribute("userNameLevel", authLevel);

        if(authLevel < 1 || uid == null || uid ==""){
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("/destinationpage.do");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
