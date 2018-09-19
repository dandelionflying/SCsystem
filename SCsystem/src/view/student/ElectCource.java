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
//            ѧ��ѡ������                       //
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
		setTitle("ѧ��ѡ������");
		SpringLayout springLayout = new SpringLayout();
		ElectCon.setLayout(springLayout);
		ImageIcon icon = new ImageIcon("156.png");
		background = new JLabel(icon);
		background.setSize(1000, 600);
		icon.setImage(icon.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));
		title = new JLabel("��������ѡ�γ̱�ţ�");
		enter_b = new JButton("ѡ  ��");
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
		detail.add("�γ̱��");
		detail.add("�γ�����");
		detail.add("�ڿ���ʦ");
		detail.add("�ڿ�ʱ��");
		detail.add("�ڿεص�");
		detail.add("�γ�ѧ��");
		detail.add("�γ�����");
		detail.add("�Ƿ�Уѡ");
		detail.add("�γ�����");
		detail.add("�γ�����");
		detail.add("���ε�λ");
		detail.add("����רҵ");
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
								JOptionPane.showMessageDialog(null, "ѡ��ʧ��");
								break;
							case 1:
								JOptionPane.showMessageDialog(null, "ѡ�γɹ�");
								break;
							case 2:
								JOptionPane.showMessageDialog(null, "�γ�����");
								break;
							default:
								break;
							}

						} else {
							JOptionPane.showMessageDialog(null, "��ѡ�ÿγ�,�޷��ظ�ѡ��");
						}

					} else
						JOptionPane.showMessageDialog(null, "���޴˿γ̣�������ѡ��");
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
