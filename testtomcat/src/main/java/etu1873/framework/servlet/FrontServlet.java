package etu1873.framework.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> MappingUrls;

    @Override
    public void init() throws ServletException {
        this.MappingUrls=new HashMap<>();
        File fichier= new File("../bob");
        File[] files= fichier.listFiles();
        for(File file:files)
        {
            Class classtemp=null;
            String fileName=file.getName().split(".java")[0];
            try {
                classtemp=Class.forName("etu1873.framework.bob."+fileName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Object obj=null;
            try {
                obj=classtemp.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            Method[] list_method=obj.getClass().getDeclaredMethods();
            for(Method method:list_method)
            {
                if (method.isAnnotationPresent(annota1.class))
                {
                    String classname=obj.getClass().getSimpleName();
                    String url=method.getAnnotation(annota1.class).url();
                    String methodname=method.getName();
                    MappingUrls.put(url,new Mapping(classname,methodname));

                    
                }
            }




        }



    }

    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.println(request.getRequestURL());

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