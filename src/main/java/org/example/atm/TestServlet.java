package org.example.atm;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Enumeration;

public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] value = req.getParameterValues(paramName);
            byte[] decodedBytes = Base64.getDecoder().decode(value[0]);
            String[] decodedStrings = new String(decodedBytes).split(":");
            String card = decodedStrings[0];
            String pin = decodedStrings[1];
            out.write("card:"+card);
            out.write("\n");
            out.write("pin:"+pin);
            out.write("\n");
        }
        out.write("Servlet");
    }
}

