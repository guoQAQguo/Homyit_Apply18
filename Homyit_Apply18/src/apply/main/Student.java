package apply.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.bean.Mess;
import apply.bean.Students;
import apply.daoImpl.StudentDaoImpl;
import apply.tools.Mail;
import apply.tools.Message;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Student {
	
	StudentDaoImpl sdi = new StudentDaoImpl();
	Message mes = new Message();
	
	public void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		int sex = Integer.parseInt(request.getParameter("sex"));
		
		//实例化一个Students对象
		Students stu = new Students();
		stu.setSex(sex);
		stu.setName(request.getParameter("name"));
		stu.setSid(request.getParameter("s_id"));
		stu.setCla(request.getParameter("cla"));
		stu.setQQ(request.getParameter("QQ"));
		stu.setTel(request.getParameter("tel"));
		stu.setOther(request.getParameter("other"));
		
		
		int rs = sdi.checkSid(stu); //检查数据库里是否有这个学生的学号
		if(rs == 0) {
			//传入数据库
			int i = sdi.addStudent(stu);
			if(i != 0) {
				json.put("res", "success");
//				Mess mess = new Mess();   //实例化一个短信发送方的对象
//				mess.setName(request.getParameter("name"));
//				mess.setPhone(request.getParameter("tel"));
//				mes.sendMess(mess);   //发送短信
			}else {
				json.put("res", "failed");
			}		
		}else {
			json.put("res", "have");
		}
		
		
		response.getWriter().write(json.toString());
	}
	
	public void checkStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONArray array = new JSONArray();
		
		try {
			ResultSet rs = sdi.checkStudent();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount;
			columnCount = metaData.getColumnCount();
			while(rs.next()) {
				JSONObject json = new JSONObject();
				for(int a=1;a<=columnCount;a++) {
					String columnName = metaData.getColumnName(a);
					String value = rs.getString(columnName);
					json.put(columnName, value);
				}
				array.put(json);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(array.toString());
	}
}
