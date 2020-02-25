package org.example.atm;

import javax.servlet.http.*;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException
    {
            httpServletResponse.getWriter().print("Servlet");
    }
}

