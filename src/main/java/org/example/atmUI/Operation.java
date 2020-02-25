package org.example.atmUI;

import org.example.atm.Database;
import org.example.atm.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class Operation extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        Enumeration<String> parameterNames = req.getParameterNames();
        if (!parameterNames.hasMoreElements()) {
            out.println("ATM encountered an error.");
            return;
        }
        String paramName = parameterNames.nextElement();
        String token = req.getParameterValues(paramName)[0];
        User user = Database.findUser(token);
        if (user==null) {
            out.println("You are not authorised to operate this ATM.");
            return;
        }
        if (!parameterNames.hasMoreElements()) {
            out.println("ATM encountered an error.");
            return;
        }
        paramName = parameterNames.nextElement();
        String svalue = req.getParameterValues(paramName)[0];
        int delta = Integer.parseInt(svalue);
        Database.alterBalance(token, delta);
        req.setAttribute("username", user.name);
        req.setAttribute("balance", user.account.balance);
        req.getRequestDispatcher("/operation.jsp").forward(req, res);
    }
}

