package com.andoliver.web.jdbc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.andoliver.web.jdbc.model.Student;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM student");
			rs = ps.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				Student student = new Student(id, firstName, lastName, email);
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}

		return students;
	}

	public void newStudent(Student student) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement("INSERT INTO student(first_name, last_name, email) VALUES (?, ?, ?)");
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, ps, conn);
		}
	}

	public Student loadStudent(Student student) {

		Student newStudent = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement("SELECT * FROM student WHERE id = ?");
			ps.setInt(1, student.getId());
			rs = ps.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				newStudent = new Student(id, firstName, lastName, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}

		return newStudent;
	}

	public void updateStudent(Student student) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement(
					"UPDATE student " + "SET first_name = ?, last_name = ?, email = ? " + "WHERE id = ?");
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setInt(4, student.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, ps, conn);
		}
	}

	public void deleteStudent(Integer id) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement("DELETE FROM student WHERE id = ?");
			ps.setInt(1, id);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, ps, conn);
		}

	}

	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
