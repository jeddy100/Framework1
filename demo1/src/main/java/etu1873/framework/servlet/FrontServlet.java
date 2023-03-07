package etu1873.framework.servlet;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> MappingUrls;

    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrintWriter out= response.getWriter();
        out.println(request.getRequestURI());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            ProcessRequest(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            ProcessRequest(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void destroy() {
    }
}