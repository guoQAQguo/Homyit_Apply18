package apply.dao;

import java.sql.ResultSet;

import apply.bean.Students;

public interface StudentDao {
	public int addStudent(Students stu);
	public ResultSet checkStudent();
	public int checkSid(Students stu);
}
