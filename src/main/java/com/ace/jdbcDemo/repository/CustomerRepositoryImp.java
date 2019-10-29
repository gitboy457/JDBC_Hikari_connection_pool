package com.ace.jdbcDemo.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import com.ace.jdbcDemo.entity.Customer;

public class CustomerRepositoryImp implements CustomerRepository {


private HikariDBConfig hikariDBConfig;
	public CustomerRepositoryImp() {
		this .hikariDBConfig= new HikariDBConfig();
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		//object of connection is provided by hikari
		//hikari has pool of connection every thread request for connection object 
		//if pool has connection object free then it will provide that connection object
		//if pool is not free then the thread should be in waiting state
		Connection con = hikariDBConfig.getNewDBConnection();
		Savepoint sp=null;
		try {
			System.out.println("connection object :-  "+con);
			PreparedStatement pstm = con.prepareStatement(CustomerConstants.INSERT_CUSTOMER);
			pstm.setString(1, customer.getCustId());
			pstm.setString(2, customer.getCust_name());
			pstm.setString(3, customer.getEmail());
			pstm.setString(4, customer.getMobile());
			pstm.setDate(5, customer.getCreateDate());
			//create savepoint
			//savepoints should be created before calling execute statement
		 sp=con.setSavepoint(customer.getCustId()+"Savepoint1");
			int i = pstm.executeUpdate();
			if (i == 1) {
				
				con.commit();
				con.close();
				
				return true;
			}
			
		} catch (SQLException e) {
			try {
				//if any exception occure in the insersation of data then rollback all the un commited changes
				con.rollback(sp);
				//con.rollback();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}

	@Override
	public Customer getByCustId(String CustId) {
		Connection con = hikariDBConfig.getNewDBConnection();
		Customer customer = null;
		try {
			customer = new Customer();
			System.out.println("connection object :-  "+con);
			PreparedStatement pstm = con.prepareStatement(CustomerConstants.FIND_BY_CUSTID);
			pstm.setString(1, CustId);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				customer.setCustId(rs.getString("cust_id"));
				customer.setCust_name(rs.getString("cust_name"));
				customer.setEmail(rs.getString("email"));
				customer.setMobile("mobile");
				customer.setCreateDate(rs.getDate("create_date"));
				customer.setUpdateDate(rs.getDate("update_date"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return customer;
	}

}
