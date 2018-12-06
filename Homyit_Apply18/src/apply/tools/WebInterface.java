package apply.tools;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.main.Student;

/**
 * Servlet implementation class WebInterface
 */
@WebServlet("/*")
public class WebInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Mail mail = new Mail();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebInterface() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setHeader("Access-Control-Allow-Origin", "*");   // 改动了*
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				
				// 获取请求的URI地址信息
		        String url = request.getRequestURI();
		        // 截取方法名
		        String methodName = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));//返回"/"之后，"."之前的字符串，
//		        System.out.println(url.lastIndexOf("/"));
//		        System.out.println(url.lastIndexOf("."));
//		        System.out.println(url.lastIndexOf(".h"));
		        System.out.println(methodName);
		        Method method = null;
		        
		            // 使用反射机制获取在本类中声明了的方法
		        try {
		            // 使用反射机制获取在本类中声明了的方法
		            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		            // 执行方法
		            method.invoke(this, request, response);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
	}
	
	Student stud = new Student();
	
	//报名操作
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		stud.addStudent(request, response);
	}
	private void checkStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		stud.checkStudent(request, response);
	}

}
