package action;

/**************
 *
 *  ��������˷�������
 *
 **/
import java.io.IOException;

import model.Course;
import model.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentRequest extends Request {

	private int result = -1;// ���
	private String str = null;// ��������

	public StudentRequest(String host, int port) {
		super(host, port);
	}
	// ��ӿγ�����
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

	// ��ѯ���˿γ�
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
	// ���ȫ���γ�����
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
	// �ж��Ƿ���ڸÿγ�����
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
	// ��øÿγ�����
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
	// ��¼����
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
	// ������������
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
	// �ж��Ƿ���ѡ�ÿ�����
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
	// �ر���Դ
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
