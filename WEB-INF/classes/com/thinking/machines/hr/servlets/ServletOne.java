package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class ServletOne extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
try
{
List<DesignationDTO> designations=new DesignationDAO().getAll();
Gson gson=new Gson();
String gsonString=gson.toJson(designations);
System.out.println(gsonString);
pw.print(gsonString);
pw.flush();
}catch(DAOException daoException)
{
pw.print("Some problem : "+daoException.getMessage());
}
}catch(Exception ee)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e){}
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e){}
}
}