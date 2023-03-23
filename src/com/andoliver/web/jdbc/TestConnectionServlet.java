package com.andoliver.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestConnectionServlet
 */
@WebServlet("/TestConnectionServlet")
public class TestConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Resource name that we defined in context.xml
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM student");
			rs = ps.executeQuery();

			while (rs.next()) {
				String email = rs.getString("email");
				out.println(email);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
