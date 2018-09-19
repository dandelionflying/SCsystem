package action;

/**************
 *
 *  向服务器端发送请求
 *
 **/
import java.io.IOException;

import model.Course;
import model.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentRequest extends Request {

	private int result = -1;// 结果
	private String str = null;// 接收数据

	public StudentRequest(String host, int port) {
		super(host, port);
	}
	// 添加课程请求
	public int addCourse(String sid, String cid) {
		result = -1;
		try {
			bw.write(0);
			bw.write(sid + "\n");
			bw.write(cid + "\n");
			bw.flush();
			result = br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询个人课程
	public Course[] searchCourse(String sid) {
		Course[] courses = null;
		try {
			bw.write(3);
			bw.write(sid + "\n");
			bw.flush();
			String coursesStr = br.readLine();
			JSONArray jsonArray = JSONArray.fromObject(coursesStr);

			courses = (Course[]) JSONArray.toArray(jsonArray, Course.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courses;
	}
	// 获得全部课程请求
	public Course[] allCourse() {
		Course[] courses = null;
		try {
			bw.write(1);
			bw.flush();
			String listStr = br.readLine();
			JSONArray jsonArray = JSONArray.fromObject(listStr);
			courses = (Course[]) JSONArray.toArray(jsonArray, Course.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return courses;
	}
	// 判断是否存在该课程请求
	public boolean isCourse(String courseid) {
		try {
			bw.write(2);
			bw.write(courseid + "\n");
			bw.flush();
			result = br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return true;
		else
			return false;
	}
	// 获得该课程请求
	public Course getCourse(String courseid) {
		Course course = null;
		try {

			bw.write(4);
			bw.write(courseid + "\n");
			bw.flush();
			JSONObject jsonObject = JSONObject.fromObject(br.readLine());
			course = (Course) JSONObject.toBean(jsonObject, Course.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return course;
	}
	// 登录请求
	public Student login(String studentid) {
		Student student = null;

		JSONObject jsonObject;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					bw.write(5);
					bw.write(studentid + "\n");
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		t.interrupt();
		try {
			str = br.readLine();
			jsonObject = JSONObject.fromObject(str);
			student = (Student) JSONObject.toBean(jsonObject, Student.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return student;
	}
	// 重置密码请求
	public boolean resetPasswordRequest(String studentid, String newPassword) {

		try {
			bw.write(6);
			bw.write(studentid + "\n");
			bw.write(newPassword + "\n");
			bw.flush();
			result = br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return true;
		else
			return false;
	}
	// 判断是否已选该课请求
	public boolean isElectedRequest(String studentid, String courseid) {
		try {
			bw.write(7);
			bw.write(studentid + "\n");
			bw.write(courseid + "\n");
			bw.flush();
			result = br.read();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return true;
		else
			return false;
	}
	// 关闭资源
	public void close() {
		try {
			bw.close();
			outwriter.close();
			out.close();
			br.close();
			inreader.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
