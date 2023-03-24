package com.andoliver.web.jdbc.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.andoliver.web.jdbc.model.Student;
import com.andoliver.web.jdbc.model.dao.StudentDbUtil;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	private StudentDbUtil studentDbUtil;

	@Override
	public void init() throws ServletException {
		studentDbUtil = new StudentDbUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String command = request.getParameter("command");

			if (command == null) {
				listStudents(request, response);
			} else if (command.equals("ADD")) {
				addStudent(request, response);
			} else if (command.equals("LOAD")) {
				loadStudent(request, response);
			} else if (command.equals("UPDATE")) {
				updateStudent(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(id, firstName, lastName, email);
		
		studentDbUtil.updateStudent(student);
		
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		if (studentId != null) {
			Integer id = Integer.parseInt(studentId);
			Student student = new Student(id, null, null, null);
			student = studentDbUtil.loadStudent(student);

			request.setAttribute("studentLoaded", student);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(null, firstName, lastName, email);

		studentDbUtil.newStudent(student);

		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students = studentDbUtil.getStudents();

		request.setAttribute("STUDENTS_LIST", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

}
