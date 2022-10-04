package com.thinking.machines.hr.dl;
import java.util.*;
import java.sql.*;
public class AdministratorDAO
{
public AdministratorDTO getByUsername(String username)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from administrator where username=?");
preparedStatement.setString(1,username.trim());
ResultSet resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Username: "+username);
}
String password=resultSet.getString("password").trim();
AdministratorDTO administratorDTO=new AdministratorDTO();
administratorDTO.setUsername(username);
administratorDTO.setPassword(password);
resultSet.close();
preparedStatement.close();
connection.close();
return administratorDTO;
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
}
}