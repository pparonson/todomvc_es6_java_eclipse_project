package com.pparonson.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource for resource injection
	@Resource(name="jdbc/todomvc_es6_java_eclipse_project")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set up printWriter
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/plain");
    	
    	// Get connection to DB
    	Connection connection = null;
    	Statement statement = null;
    	ResultSet resultSet = null;
    	
    	try {
    		connection = dataSource.getConnection();
    	// Create SQL statement
    		String sqlStatement = "SELECT * FROM todoList";
    		statement = connection.createStatement();
    	// Execute SQL statement
    		resultSet = statement.executeQuery(sqlStatement);
    	// Process ResultSet
    		while(resultSet.next()) {
    			String todoText = resultSet.getString("todotext");
    			out.println(todoText);
    		}
    	} catch(Exception exception) {
    		exception.printStackTrace();
    	}
	}

}
