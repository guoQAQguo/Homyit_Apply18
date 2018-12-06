package apply.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import apply.bean.Students;
import apply.dao.StudentDao;
import apply.tools.Db;

public class StudentDaoImpl implements StudentDao{
	Db db = new Db();
	@Override
	
	//增加一个学生信息
	public int addStudent(Students stu) {
		// TODO Auto-generated method stub
		String sql = "insert into Student_mess18(name,sex,student_id,class,tel,QQ,other)"
				+"values(?,?,?,?,?,?,?)";
		
		try {
			int i=db.update(sql,stu.getName(),stu.getSex(),stu.getSid(),stu.getCla(),stu.getTel(),stu.getQQ(),stu.getOther());
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//查看学生信息
	@Override
	public ResultSet checkStudent() {
		// TODO Auto-generated method stub
		
		String sql = "select * from Student_mess18";
		ResultSet rs = db.query(sql);
		return rs;
	}
	
	//检查学号是否存在
	@Override
	public int checkSid(Students stu) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from Student_mess18 where student_id = ?";
		ResultSet rs = db.query(sql, stu.getSid());
		
		int result;
		try {
			rs.next();
			result = rs.getInt(1);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
