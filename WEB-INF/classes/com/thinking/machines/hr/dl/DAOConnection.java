package com.thinking.machines.hr.dl;
import java.sql.*;
public class DAOConnection
{
private DAOConnection(){}
private static Connection connection=null;
public static Connection getConnection() throws DAOException
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmdbuser","tmdbuser");
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return connection;
}
}