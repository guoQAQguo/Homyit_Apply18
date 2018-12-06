package apply.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import apply.bean.Mess;

import java.io.UnsupportedEncodingException;

public class Message {
	public void sendMess(Mess mess) throws HttpException, IOException {
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.api.smschinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", "guoQAQguo")
			,new NameValuePair("Key", "d41d8cd98f00b204e980")
			,new NameValuePair("smsMob",mess.getPhone())
			,new NameValuePair("smsText","恭喜你！"+mess.getName()+",你已经成功报名，后续我们会在通知群中公布分组安排，希望你在后期的培训当中坚持下来，我们与你同在!")};
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:"+statusCode);
		for(Header h : headers)
		{
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
		System.out.println(result); //打印返回消息状态
		post.releaseConnection();
	}

}
