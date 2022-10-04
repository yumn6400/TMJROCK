package com.thinking.machines.hr.dl;
import java.util.*;
import java.sql.*;
import java.math.*;
public class EmployeeDAO
{
public List<EmployeeDTO> getAll() throws DAOException
{
List<EmployeeDTO> employees;
try
{
employees=new LinkedList<>();
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.is_indian,employee.gender,employee.basic_salary,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code order by employee.name ");
EmployeeDTO employeeDTO=null;
int id;
String name;
int designationCode;
String title;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
while(resultSet.next())
{
id=resultSet.getInt("id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
title=resultSet.getString("title").trim();
dateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender").trim();
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setTitle(title);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employees.add(employeeDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return employees;
}


public void add(EmployeeDTO employee) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();
preparedStatement=connection.prepareStatement("select id from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN number: "+panNumber+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select id from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar card number: "+aadharCardNumber+" exists.");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("insert into employee(name,designation_code,date_of_birth,is_indian,gender,basic_salary,pan_number,aadhar_card_number)values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,employee.getName());
preparedStatement.setInt(2,employee.getDesignationCode());
java.util.Date dateOfBirth=employee.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,sqlDate);
preparedStatement.setBoolean(4,employee.getIsIndian());
preparedStatement.setString(5,employee.getGender());
preparedStatement.setBigDecimal(6,employee.getBasicSalary());
preparedStatement.setString(7,employee.getPANNumber());
preparedStatement.setString(8,employee.getAadharCardNumber());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
employee.setEmployeeId("A"+resultSet.getInt(1));
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public boolean panNumberExists(String panNumber)throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public boolean aadharCardNumberExists(String aadharCardNumber)throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public void deleteByEmployeeId(String employeeId)throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception e)
{
System.out.println(e.getMessage());
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
preparedStatement.close();
resultSet.close();
connection.close();
throw new DAOException("Invalid employee Id: "+employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
}

public EmployeeDTO getByEmployeeId(String employeeId)throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception e)
{
System.out.println(e.getMessage());
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_card_number from employee inner join designation where employee.designation_code=designation.code and id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid employee Id: "+employeeId);
}
int id=resultSet.getInt("id");
String name=resultSet.getString("name");
int designationCode=resultSet.getInt("designation_code");
String title=resultSet.getString("title");
java.util.Date dateOfBirth=resultSet.getDate("date_of_birth");
String gender=resultSet.getString("gender");
Boolean isIndian=resultSet.getBoolean("is_indian");
BigDecimal basicSalary=resultSet.getBigDecimal("basic_salary");
String panNumber=resultSet.getString("pan_number");
String aadharCardNumber=resultSet.getString("aadhar_card_number");
resultSet.close();
preparedStatement.close();
connection.close();
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setTitle(title);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setGender(gender);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void update(EmployeeDTO employee) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
String employeeId=employee.getEmployeeId();
int actualEmployeeId=Integer.parseInt(employeeId.substring(1));

preparedStatement=connection.prepareStatement("select gender from employee where  id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid employee id: "+employeeId);
}
resultSet.close();
preparedStatement.close();
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();
preparedStatement=connection.prepareStatement("select gender from employee where  pan_number=? and id<>?");
preparedStatement.setString(1,panNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN number: "+panNumber+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select id from employee where aadhar_card_number=? and id<>?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar card number: "+aadharCardNumber+" exists.");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,is_indian=?,gender=?,basic_salary=?,pan_number=?,aadhar_card_number=? where id=?");
preparedStatement.setString(1,employee.getName());
preparedStatement.setInt(2,employee.getDesignationCode());
java.util.Date dateOfBirth=employee.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,sqlDate);
preparedStatement.setBoolean(4,employee.getIsIndian());
preparedStatement.setString(5,employee.getGender());
preparedStatement.setBigDecimal(6,employee.getBasicSalary());
preparedStatement.setString(7,employee.getPANNumber());
preparedStatement.setString(8,employee.getAadharCardNumber());
preparedStatement.setInt(9,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public boolean employeeIdExists(String employeeId)throws DAOException
{
int actualEmployeeId=0;
boolean exists=false;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception e)
{
return false;
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public EmployeeDTO getByPANNumber(String panNumber)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_card_number from employee inner join designation where employee.designation_code=designation.code and pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid PAN number: "+panNumber);
}
int id=resultSet.getInt("id");
String name=resultSet.getString("name");
int designationCode=resultSet.getInt("designation_code");
String title=resultSet.getString("title");
java.util.Date dateOfBirth=resultSet.getDate("date_of_birth");
String gender=resultSet.getString("gender");
Boolean isIndian=resultSet.getBoolean("is_indian");
BigDecimal basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number");
String aadharCardNumber=resultSet.getString("aadhar_card_number");
resultSet.close();
preparedStatement.close();
connection.close();
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setTitle(title);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setGender(gender);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public EmployeeDTO getByAadharCardNumber(String aadharCardNumber)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_card_number from employee inner join designation where employee.designation_code=designation.code and aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Aadhar card number: "+aadharCardNumber);
}
int id=resultSet.getInt("id");
String name=resultSet.getString("name");
int designationCode=resultSet.getInt("designation_code");
String title=resultSet.getString("title");
java.util.Date dateOfBirth=resultSet.getDate("date_of_birth");
String gender=resultSet.getString("gender");
Boolean isIndian=resultSet.getBoolean("is_indian");
BigDecimal basicSalary=resultSet.getBigDecimal("basic_salary");
String panNumber=resultSet.getString("pan_number");
aadharCardNumber=resultSet.getString("aadhar_card_number");
resultSet.close();
preparedStatement.close();
connection.close();
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setTitle(title);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setGender(gender);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

}