package view.student;

/*********************
 * 
 *   ��μ��鿴���˿α�
 * 
 *********************/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import action.StudentRequest;
import model.Course;

public class AuditCourse extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton enter_b;
	private JTextField textfield;
	protected Container Audit_CourceCon;
	private CourceJPanel mm;
	private StudentRequest studentRequest = null;
	private Vector<String> columnNameV = new Vector<>();
	private Vector<Vector<Object>> tableValueV = new Vector<>();
	private List<Course> newlist = new ArrayList<Course>();
	private Course[] courses = null;
	private JLabel background = null;

	public StudentRequest getStudentRequest() {
		return studentRequest;
	}

	public AuditCourse(String studentid, StudentRequest Request) throws SQLException {
		ImageIcon icon = new ImageIcon("156.png");
		background = new JLabel(icon);
		background.setSize(1000, 600);
		icon.setImage(icon.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));

		studentRequest = Request;
		columnNameV.add("date");
		columnNameV.add("����һ");
		columnNameV.add("���ڶ�");
		columnNameV.add("������");
		columnNameV.add("������");
		columnNameV.add("������");
		columnNameV.add("������");
		columnNameV.add("������");

		courses = studentRequest.searchCourse(studentid);
		for (Course course : courses) {
			newlist.add(course);
		}
		int i = newlist.size() - 1;
		boolean flag = false;
		for (int row = 1; row <= 5; row++) {
			Vector<Object> rowV = new Vector<>();
			rowV.add("��" + row + "���");
			for (int col = 1; col <= 7; col++) {
				i = newlist.size() - 1;
				while (i >= 0) {
					Course course = newlist.get(i);
					String name = course.getCourseName();
					String time = course.getCourseTime();
					String address = course.getCourseAddress();
					if (time.endsWith((String) rowV.get(0)) && time.startsWith(columnNameV.get(col))) {
						rowV.add(name + "  " + address);
						newlist.remove(i);
						flag = true;
					}
					i--;
				}
				if (!flag || i < 0)
					rowV.add("");
				;
			}
			tableValueV.add(rowV);
		}

		SpringLayout springLayout = new SpringLayout();
		Audit_CourceCon = getContentPane();
		Audit_CourceCon.setLayout(springLayout);

		setTitle("ѧ�����˿α�");

		title = new JLabel("��������ѡ�γ̱�ţ�");
		enter_b = new JButton("����ģ��α�");
		textfield = new JTextField();
		enter_b.setBackground(new Color(135, 206, 235));

		springLayout.putConstraint(SpringLayout.NORTH, title, 450, SpringLayout.NORTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.WEST, title, 150, SpringLayout.WEST, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.SOUTH, title, -100, SpringLayout.SOUTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.EAST, title, -300, SpringLayout.EAST, Audit_CourceCon);

		springLayout.putConstraint(SpringLayout.NORTH, enter_b, 450, SpringLayout.NORTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.WEST, enter_b, 550, SpringLayout.WEST, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.SOUTH, enter_b, -100, SpringLayout.SOUTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.EAST, enter_b, -300, SpringLayout.EAST, Audit_CourceCon);

		springLayout.putConstraint(SpringLayout.NORTH, textfield, 450, SpringLayout.NORTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.WEST, textfield, 320, SpringLayout.WEST, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.SOUTH, textfield, -100, SpringLayout.SOUTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.EAST, textfield, -500, SpringLayout.EAST, Audit_CourceCon);

		mm = new CourceJPanel(columnNameV, tableValueV, 1, 60, 120, 66);
		springLayout.putConstraint(SpringLayout.NORTH, mm, 50, SpringLayout.NORTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.WEST, mm, 100, SpringLayout.WEST, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.SOUTH, mm, -150, SpringLayout.SOUTH, Audit_CourceCon);
		springLayout.putConstraint(SpringLayout.EAST, mm, -250, SpringLayout.EAST, Audit_CourceCon);

		enter_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (studentRequest.isCourse(textfield.getText())) {

						// ��ȡ��ογ�
						Course auditcourse = studentRequest.getCourse(textfield.getText());
						boolean flag = false;
						String name = auditcourse.getCourseName();
						String time = auditcourse.getCourseTime();
						String address = auditcourse.getCourseAddress();
						for (int row = 1; row <= 5; row++) {
							Vector<Object> rowV = new Vector<>();
							rowV = tableValueV.get(row - 1);
							for (int col = 1; col <= 7; col++) {
								// ��ʱ���޿γ̣������ģ��α�
								if (rowV.get(col).equals("") && time.endsWith((String) rowV.get(0))
										&& time.startsWith(columnNameV.get(col))) {
									rowV.set(col, name + "  " + address);
									flag = true;
									reflush(); // ˢ�����
									JOptionPane.showMessageDialog(null, "�Ѽ���ģ��α�");
									System.out.println(col + " " + row);
									break;
								}
							}
							if (flag)
								break;
						}
					} else
						JOptionPane.showMessageDialog(null, "���޴˿γ̣�������ѡ��");
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} finally {
				}
			}
		});

		Audit_CourceCon.add(enter_b);
		Audit_CourceCon.add(title);
		Audit_CourceCon.add(textfield);
		Audit_CourceCon.add(mm);
		Audit_CourceCon.add(background);

		this.setSize(1000, 600);
		setLocationRelativeTo(Audit_CourceCon);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void reflush() {
		this.validate();// ˢ�����
	}
}
