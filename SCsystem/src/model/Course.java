package model;
/*
 *    �γ���
 * */
public class Course {
	private String courseID;// �γ̱��
	private String courseName;// �γ�����
	private double courseCredit;// �γ�ѧ��
	private String courseAddress;// �γ̵�ַ
	private String insitituteOf;// ����ѧԺ
	private String majorOf;// ����רҵ
	private String courseTime;// �Ͽ�ʱ��
	private String courseTeacherId; // һ����ʦ����
	private int stuMaxNum;// �������
	private int remaindingQuantity;// �γ�����
	private boolean courseUniversity;// �Ƿ�Уѡ
	private String courseType;//�γ�����

	public int getRemaindingQuantity() {
		return remaindingQuantity;
	}

	public void setRemaindingQuantity(int remaindingQuantity) {
		this.remaindingQuantity = remaindingQuantity;
	}


	public void setCourseUniversity(boolean courseUniversity) {
		this.courseUniversity = courseUniversity;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(double courseCredit) {
		this.courseCredit = courseCredit;
	}

	public String getCourseAddress() {
		return courseAddress;
	}

	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}

	public String getInsitituteOf() {
		return insitituteOf;
	}

	public void setInsitituteOf(String insitituteOf) {
		this.insitituteOf = insitituteOf;
	}

	public String getMajorOf() {
		return majorOf;
	}

	public void setMajorOf(String majorOf) {
		this.majorOf = majorOf;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getTea() {
		return courseTeacherId;
	}

	public void setTea(String courseTeacherId) {
		this.courseTeacherId = courseTeacherId;
	}

	public int getStuMaxNum() {
		return stuMaxNum;
	}

	public void setStuMaxNum(int stuMaxNum) {
		this.stuMaxNum = stuMaxNum;
	}

	public String getCourseTeacherId() {
		return courseTeacherId;
	}

	public void setCourseTeacherId(String courseTeacherId) {
		this.courseTeacherId = courseTeacherId;
	}

	public boolean isCourseUniversity() {
		return courseUniversity;
	}

}
