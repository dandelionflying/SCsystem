package view.student;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import action.StudentRequest;
import model.Course;

//**********************************//
//            学生选课中心                       //
//**********************************//
public class ElectCource extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton enter_b;
	private JTextField textfield;
	private CourceJPanel mm;
	private JLabel background = null;
	private StudentRequest studentRequest = null;

	public ElectCource(String studentid, StudentRequest Request) throws SQLException {
		studentRequest = Request;
		Container ElectCon = getContentPane();
		setTitle("学生选课中心");
		SpringLayout springLayout = new SpringLayout();
		ElectCon.setLayout(springLayout);
		ImageIcon icon = new ImageIcon("156.png");
		background = new JLabel(icon);
		background.setSize(1000, 600);
		icon.setImage(icon.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));
		title = new JLabel("请输入所选课程编号：");
		enter_b = new JButton("选  课");
		textfield = new JTextField();
		enter_b.setBackground(new Color(135, 206, 235));
		springLayout.putConstraint(SpringLayout.NORTH, title, 450, SpringLayout.NORTH, ElectCon);
		springLayout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, ElectCon);
		springLayout.putConstraint(SpringLayout.SOUTH, title, -100, SpringLayout.SOUTH, ElectCon);
		springLayout.putConstraint(SpringLayout.EAST, title, -500, SpringLayout.EAST, ElectCon);

		springLayout.putConstraint(SpringLayout.NORTH, enter_b, 450, SpringLayout.NORTH, ElectCon);
		springLayout.putConstraint(SpringLayout.WEST, enter_b, 600, SpringLayout.WEST, ElectCon);
		springLayout.putConstraint(SpringLayout.SOUTH, enter_b, -100, SpringLayout.SOUTH, ElectCon);
		springLayout.putConstraint(SpringLayout.EAST, enter_b, -300, SpringLayout.EAST, ElectCon);

		springLayout.putConstraint(SpringLayout.NORTH, textfield, 450, SpringLayout.NORTH, ElectCon);
		springLayout.putConstraint(SpringLayout.WEST, textfield, 350, SpringLayout.WEST, ElectCon);
		springLayout.putConstraint(SpringLayout.SOUTH, textfield, -100, SpringLayout.SOUTH, ElectCon);
		springLayout.putConstraint(SpringLayout.EAST, textfield, -450, SpringLayout.EAST, ElectCon);

		title.setHorizontalAlignment(SwingConstants.CENTER);
		Vector<String> detail = new Vector<>();
		Vector<Vector<Object>> tableValueV = new Vector<>();
		detail.add("课程编号");
		detail.add("课程名称");
		detail.add("授课老师");
		detail.add("授课时间");
		detail.add("授课地点");
		detail.add("课程学分");
		detail.add("课程类型");
		detail.add("是否校选");
		detail.add("课程容量");
		detail.add("课程余量");
		detail.add("开课单位");
		detail.add("所属专业");
		Course[] newlist2 = studentRequest.allCourse();

		for (int row = 0; row < newlist2.length; row++) {
			Course c = newlist2[row];
			if (c.isCourseUniversity()) {
				Vector<Object> rowV = new Vector<>();
				rowV.add(c.getCourseID());
				rowV.add(c.getCourseName());
				rowV.add(c.getCourseTeacherId());
				rowV.add(c.getCourseTime());
				rowV.add(c.getCourseAddress());
				rowV.add(c.getCourseCredit());
				rowV.add(c.getCourseType());
				rowV.add(c.isCourseUniversity());
				rowV.add(c.getStuMaxNum());
				rowV.add(c.getRemaindingQuantity());
				rowV.add(c.getInsitituteOf());
				rowV.add(c.getMajorOf());
				tableValueV.add(rowV);
			}
		}
		mm = new CourceJPanel(detail, tableValueV, 1, 60, 120, 30);
		springLayout.putConstraint(SpringLayout.NORTH, mm, 50, SpringLayout.NORTH, ElectCon);
		springLayout.putConstraint(SpringLayout.WEST, mm, 100, SpringLayout.WEST, ElectCon);
		springLayout.putConstraint(SpringLayout.SOUTH, mm, -150, SpringLayout.SOUTH, ElectCon);
		springLayout.putConstraint(SpringLayout.EAST, mm, -100, SpringLayout.EAST, ElectCon);

		enter_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (studentRequest.isCourse(textfield.getText())) {

						if (!studentRequest.isElectedRequest(studentid, textfield.getText())) {

							int f = studentRequest.addCourse(studentid, textfield.getText());
							switch (f) {
							case 0:
								JOptionPane.showMessageDialog(null, "选课失败");
								break;
							case 1:
								JOptionPane.showMessageDialog(null, "选课成功");
								break;
							case 2:
								JOptionPane.showMessageDialog(null, "课程已满");
								break;
							default:
								break;
							}

						} else {
							JOptionPane.showMessageDialog(null, "已选该课程,无法重复选择！");
						}

					} else
						JOptionPane.showMessageDialog(null, "暂无此课程，请重新选择");
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});

		ElectCon.add(enter_b);
		ElectCon.add(title);
		ElectCon.add(textfield);
		ElectCon.add(mm);
		ElectCon.add(background);
		this.setSize(1000, 600);
		setLocationRelativeTo(ElectCon);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
