package org.example.atmUI;

import org.example.atm.Database;
import org.example.atm.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class UserLogin extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
        Enumeration<String> parameterNames = req.getParameterNames();
        if (!parameterNames.hasMoreElements()) {
            out.println("You are not authorised to operate this ATM.");
            return;
        }
        String paramName = parameterNames.nextElement();
        String token = req.getParameterValues(paramName)[0];
        User user = Database.findUser(token);
        if (user==null) {
            out.println("You are not authorised to operate this ATM.");
            return;
        }
       //res.sendRedirect("user-menu?token=" + token);
        req.setAttribute("username", user.name);
        req.setAttribute("balance", user.account.balance);
        req.getRequestDispatcher("/operation.jsp").forward(req, res);
    }
}

