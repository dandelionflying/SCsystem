package model;


/************
 * ѧ����
 ************/
public class Student extends User {
	
	private String institudeOf;    //ѧ������ѧԺ
	private String majorOf;         //ѧ������רҵ
	private String classOf;       //ѧ�������༶
	private boolean universityCourse; //ѧ���Ƿ�ѡ��Уѡ��
	
	public boolean isUniversityCourse() {
		return universityCourse;
	}

	public void setUniversityCourse(boolean universityCourse) {
		this.universityCourse = universityCourse;
	}

	public String getInstitudeOf() {
		return institudeOf;
	}
	
	public void setInstitudeOf(String institudeOf) {
		this.institudeOf = institudeOf;
	}
	
	public String getMajorOf() {
		return majorOf;
	}
	
	public void setMajorOf(String majorOf) {
		this.majorOf = majorOf;
	}
	
	public String getClassOf() {
		return classOf;
	}
	
	public void setClassOf(String classOf) {
		this.classOf = classOf;
	}
	
}
