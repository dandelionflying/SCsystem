package view.student;

/*********************
 * 
 *   显示全部课程
 * 
 *********************/
import java.awt.Container;
import java.awt.Image;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import action.StudentRequest;
import model.Course;

public class AllCource extends JFrame {
	private static final long serialVersionUID = 1L;
	private final CourceJPanel mm;
	private StudentRequest studentRequest = null;
	private JLabel background = null;

	public AllCource(StudentRequest Request) throws SQLException {
		studentRequest = Request;
		
		ImageIcon icon = new ImageIcon("156.png");
		background = new JLabel(icon);
		background.setSize(1000, 600);
		icon.setImage(icon.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));
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
		
		// 从服务器获取所有课程，添加到表格
		Course[] courses = studentRequest.allCourse();

		for (int row = 0; row < courses.length; row++) {
			Vector<Object> rowV = new Vector<>();
			Course c = courses[row];
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
		SpringLayout springLayout = new SpringLayout();

		Container All_CourceCon = getContentPane();
		All_CourceCon.setLayout(springLayout);

		setTitle("全部课程");
		mm = new CourceJPanel(detail, tableValueV, 1, 60, 120, 30);
		springLayout.putConstraint(SpringLayout.NORTH, mm, 50, SpringLayout.NORTH, All_CourceCon);
		springLayout.putConstraint(SpringLayout.WEST, mm, 100, SpringLayout.WEST, All_CourceCon);
		springLayout.putConstraint(SpringLayout.SOUTH, mm, -100, SpringLayout.SOUTH, All_CourceCon);
		springLayout.putConstraint(SpringLayout.EAST, mm, -100, SpringLayout.EAST, All_CourceCon);

		All_CourceCon.add(mm);
		All_CourceCon.add(background);
		this.setSize(1000, 600);
		setLocationRelativeTo(All_CourceCon);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
