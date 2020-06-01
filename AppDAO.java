package com.voidmain.fm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.voidmain.fm.form.Bank;
import com.voidmain.fm.form.Customer;
import com.voidmain.fm.form.Employee;
import com.voidmain.fm.form.Loan;
import com.voidmain.fm.form.Message;
import com.voidmain.fm.form.Plan;

public class AppDAO {

	public static int isValidUser(String username,String password)
	{
		int roleId=0;

		String status="activated";

		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select role_id from login where username='"+username+"' and password='"+password+"' and status='"+status+"'");

			while (rs.next()) {

				roleId=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roleId;
	}

	public static int getUserType(String userName)
	{
		int role_id=0;

		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select role_id from login where username='"+userName+"'");

			while(rs.next())
			{
				role_id=rs.getInt("role_id");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return role_id;
	}

	public static int updatePassword(String username,String oldPassword,String newPassword)
	{
		int result=0;

		try {
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update login set password=? where username=? and password=?");
			ps.setString(1,newPassword);
			ps.setString(2,username);
			ps.setString(3,oldPassword);

			result=ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static int isUserRegistred(String username)
	{
		int result=0;

		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select count(*) from login where username='"+username+"'");

			while (rs.next()) {

				result=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static int activateUser(String username,String status)
	{
		int result=0;

		try {

			result=MyConnection.getConnection().createStatement().executeUpdate("update login set status='"+status+"' where username='"+username+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static int addCustomer(Customer userForm)
	{
		int result=0; 

		try {

			if(isUserRegistred(userForm.getUserName())==0)
			{
				PreparedStatement ps2=MyConnection.getConnection().prepareStatement("insert into login value(?,?,?,?)");

				ps2.setString(1,userForm.getUserName());
				ps2.setString(2,userForm.getPassword());
				ps2.setString(3,"3");
				ps2.setString(4,"activated");

				if(ps2.executeUpdate()==1)
				{
					PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into customers values(?,?,?,?)");

					ps.setString(1,userForm.getUserName());
					ps.setString(2,userForm.getAddress());
					ps.setString(3,userForm.getMobile());
					ps.setString(4,userForm.getEmail());

					if(ps.executeUpdate()==1)
					{
						result=2;
					}
					else
					{
						MyConnection.getConnection().createStatement().executeUpdate("delete from login where username='"+userForm.getUserName()+"'");
					}
				}
			}
			else
			{
				result=1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static List<Customer> getCustomers()
	{
		List<Customer> usersList=new ArrayList<Customer>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from customers");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Customer user=new Customer();

				user.setUserName(rs.getString("username"));
				user.setAddress(rs.getString("address"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));

				usersList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usersList;
	}

	public static Customer getUserByName(String userName)
	{
		Customer user=new Customer();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from customers where username='"+userName+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				user.setUserName(rs.getString("username"));
				user.setAddress(rs.getString("address"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public static int addBank(Bank bank)
	{
		int result=0; 

		try {

			if(isUserRegistred(bank.getUserName())==0)
			{
				PreparedStatement ps2=MyConnection.getConnection().prepareStatement("insert into login value(?,?,?,?)");

				ps2.setString(1,bank.getUserName());
				ps2.setString(2,bank.getPassword());
				ps2.setInt(3,1);
				ps2.setString(4,"activated");

				if(ps2.executeUpdate()==1)
				{
					PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into banks values(?,?,?,?,?)");

					ps.setString(1,bank.getUserName());
					ps.setString(2,bank.getName());
					ps.setString(3,bank.getAddress());
					ps.setString(4,bank.getMobile());
					ps.setString(5,bank.getEmail());

					if(ps.executeUpdate()==1)
					{
						result=2;
					}
					else
					{
						MyConnection.getConnection().createStatement().executeUpdate("delete from login where username='"+bank.getUserName()+"'");
					}
				}
			}
			else
			{
				result=1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static List<Bank> getBanks()
	{
		List<Bank> banksList=new ArrayList<Bank>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from banks");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Bank bank=new Bank();

				bank.setName(rs.getString("name"));
				bank.setUserName(rs.getString("bankname"));
				bank.setAddress(rs.getString("address"));
				bank.setMobile(rs.getString("mobile"));
				bank.setEmail(rs.getString("email"));

				banksList.add(bank);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return banksList;
	}

	public static Bank getBankByName(String bankName)
	{
		Bank bank=new Bank();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from banks where username='"+bankName+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				bank.setName(rs.getString("name"));
				bank.setUserName(rs.getString("bankname"));
				bank.setAddress(rs.getString("address"));
				bank.setMobile(rs.getString("mobile"));
				bank.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bank;
	}

	public static int addEmployee(Employee employee)
	{
		int result=0; 

		try {

			if(isUserRegistred(employee.getUserName())==0)
			{
				PreparedStatement ps2=MyConnection.getConnection().prepareStatement("insert into login value(?,?,?,?)");

				ps2.setString(1,employee.getUserName());
				ps2.setString(2,employee.getPassword());
				ps2.setInt(3,2);
				ps2.setString(4,"activated");

				if(ps2.executeUpdate()==1)
				{
					PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?,?)");

					ps.setString(1,employee.getUserName());
					ps.setString(2,employee.getAddress());
					ps.setString(3,employee.getMobile());
					ps.setString(4,employee.getEmail());
					ps.setString(5,employee.getType());

					if(ps.executeUpdate()==1)
					{
						result=2;
					}
					else
					{
						MyConnection.getConnection().createStatement().executeUpdate("delete from login where username='"+employee.getUserName()+"'");
					}
				}
			}
			else
			{
				result=1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static List<Employee> getEmployees()
	{
		List<Employee> employeesList=new ArrayList<Employee>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from employees");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Employee employee=new Employee();

				employee.setUserName(rs.getString("username"));
				employee.setAddress(rs.getString("address"));
				employee.setMobile(rs.getString("mobile"));
				employee.setEmail(rs.getString("email"));
				employee.setType(rs.getString("type"));

				employeesList.add(employee);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeesList;
	}

	public static Employee getEmployeeByName(String employeeName)
	{
		Employee employee=new Employee();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from employees where username='"+employeeName+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				employee.setUserName(rs.getString("username"));
				employee.setAddress(rs.getString("address"));
				employee.setMobile(rs.getString("mobile"));
				employee.setEmail(rs.getString("email"));
				employee.setType(rs.getString("type"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employee;
	}

	public static int addLoan(Loan loan)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into loans (customername,bankname,status1,status2,status3,planid) values(?,?,?,?,?,?)");
		
//			ps.setInt(1,0);
			ps.setString(1,loan.getCustomername());
			ps.setString(2,loan.getBankname());
//			ps.setString(4,loan.getDocument());
			ps.setString(3,loan.getStatus1());
			ps.setString(4,loan.getStatus2());
			ps.setString(5,loan.getStatus3());
			ps.setInt(6,Integer.parseInt(loan.getPlanId()));

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int deleteLoan(int id)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("delete from loans where id='"+id+"'");
		
			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int deleteBank(String bankname)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("delete from banks where bankname='"+bankname+"'");
		
			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	public static List<Loan> getLoans()
	{
		List<Loan> loansList=new ArrayList<Loan>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from loans");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Loan loan=new Loan();
				
				loan.setLid(rs.getInt("id"));
				loan.setCustomername(rs.getString("customername"));
				loan.setBankname(rs.getString("bankname"));
//				loan.setDocument(rs.getString("document"));
				loan.setStatus1(rs.getString("status1"));
				loan.setStatus2(rs.getString("status2"));
				loan.setStatus3(rs.getString("status3"));
				loan.setPlanId(rs.getString("planid"));

				loansList.add(loan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loansList;
	}
	
	public static List<Loan> getLoansByType(String type)
	{
		List<Loan> loansList=new ArrayList<Loan>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from loans where type='"+type+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Loan loan=new Loan();
				
				loan.setLid(rs.getInt("id"));
				loan.setCustomername(rs.getString("customername"));
				loan.setBankname(rs.getString("bankname"));
				loan.setDocument(rs.getString("document"));
				loan.setStatus1(rs.getString("status1"));
				loan.setStatus2(rs.getString("status2"));
				loan.setStatus3(rs.getString("status3"));
				loan.setPlanId(rs.getString("planid"));

				loansList.add(loan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loansList;
	}
	
	public static List<Loan> getUserLoans(String username)
	{
		List<Loan> loansList=new ArrayList<Loan>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from loans where customername='"+username+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Loan loan=new Loan();
				
				loan.setLid(rs.getInt("id"));
				loan.setCustomername(rs.getString("customername"));
				loan.setBankname(rs.getString("bankname"));
//				loan.setDocument(rs.getString("document"));
				loan.setStatus1(rs.getString("status1"));
				loan.setStatus2(rs.getString("status2"));
				loan.setStatus3(rs.getString("status3"));
				loan.setPlanId(rs.getString("planid"));

				loansList.add(loan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loansList;
	}

	public static Loan getLoanById(int id)
	{
		Loan loan=new Loan();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from loans where id='"+id+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				loan.setLid(rs.getInt("id"));
				loan.setCustomername(rs.getString("customername"));
				loan.setBankname(rs.getString("bankname"));
				loan.setDocument(rs.getString("document"));
				loan.setStatus1(rs.getString("status1"));
				loan.setStatus2(rs.getString("status2"));
				loan.setStatus3(rs.getString("status3"));
				loan.setPlanId(rs.getString("planid"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loan;
	}
	
	public static int updateLoan3(int lid,String status)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update loans set status3=? where id=?");
			
			ps.setString(1,status);
			ps.setInt(2,lid);

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int updateLoan1(int lid,String status)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update loans set status1=? where id=?");
			
			ps.setString(1,status);
			ps.setInt(2,lid);

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int updateLoan2(int lid,String status)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update loans set status2=? where id=?");
			
			ps.setString(1,status);
			ps.setInt(2,lid);

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int addMessage(Message message)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into messages(username,message) values(?,?)");
//			ps.setInt(1,0);
			ps.setString(1,message.getUsername());
			ps.setString(2,message.getMessage());

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Message> getMessages()
	{
		List<Message> messagesList=new ArrayList<Message>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from messages");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Message message=new Message();
				
				message.setId(rs.getInt("id"));
				message.setUsername(rs.getString("username"));
				message.setMessage(rs.getString("message"));

				messagesList.add(message);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messagesList;
	}
	
	public static List<Message> getMessagesByUserName(String username)
	{
		List<Message> messagesList=new ArrayList<Message>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from messages where username='"+username+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Message message=new Message();
				
				message.setId(rs.getInt("id"));
				message.setUsername(rs.getString("username"));
				message.setMessage(rs.getString("message"));

				messagesList.add(message);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messagesList;
	}
	
	public static int addPlan(Plan plan)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into plans values(?,?,?,?)");

			ps.setInt(1,0);
			ps.setString(2,plan.getBankName());
			ps.setString(3,plan.getLoanType());
			ps.setFloat(4,plan.getInterestRate());

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int updatePlan(int planid,float irate)
	{
		int result=0; 

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update plans set irate=? where planid=?'");

			ps.setFloat(1,irate);
			ps.setInt(2,planid);

			result=ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Plan> getPlans()
	{
		List<Plan> plansList=new ArrayList<Plan>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from plans");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Plan plan=new Plan();
				
				plan.setPlanId(rs.getInt("planid"));
				plan.setBankName(rs.getString("bankname"));
				plan.setLoanType(rs.getString("loantype"));
				plan.setInterestRate(rs.getFloat("irate"));

				plansList.add(plan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plansList;
	}
	
	public static Plan getPlanByID(int id)
	{
		Plan plan=new Plan();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from plans where planid='"+id+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				plan.setPlanId(rs.getInt("planid"));
				plan.setBankName(rs.getString("bankname"));
				plan.setLoanType(rs.getString("loantype"));
				plan.setInterestRate(rs.getFloat("irate"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plan;
	}
	
	public static List<Plan> getPlansByUserName(String bankname)
	{
		List<Plan> plansList=new ArrayList<Plan>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from plans where bankname='"+bankname+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Plan plan=new Plan();
				
				plan.setPlanId(rs.getInt("planid"));
				plan.setBankName(rs.getString("bankname"));
				plan.setLoanType(rs.getString("loantype"));
				plan.setInterestRate(rs.getFloat("irate"));

				plansList.add(plan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plansList;
	}
}
