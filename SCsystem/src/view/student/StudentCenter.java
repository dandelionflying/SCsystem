package view.student;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import action.StudentRequest;
import model.Student;
import view.TestLogin;

//**********************************//
//            ѧ����������                       //
//**********************************//
public class StudentCenter extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isMoved;
	private Point pre_point;
	private Point end_point;
	private JButton elect_button = null;// ѡ��button
	private JButton allCource_button = null;// ȫ���γ�button
	private JButton Smodify_button = null;// �޸�����button
	private JButton audit_button = null;// ���button
	private JButton exit_button = null;// �˳�button
	private JButton relogin_button = null;// ע��button
	private JLabel time_label = null;// ϵͳʱ���ǩ
	private JLabel background = null;// ����
	private Date date = new Date();
	private String year = String.format("%tY", date);
	private String month = String.format("%tb", date);
	private String day = String.format("%td", date);
	private Student Stud;
	private StudentRequest studentRequest = null;

	public StudentCenter(StudentRequest Request, Student student) {
		Stud = student;
		studentRequest = Request;
		Container Stu = getContentPane();
		Stu.setLayout(null);
		ImageIcon image = new ImageIcon("studentcenter.png");
		background = new JLabel(image);
		background.setSize(900, 550);
		image.setImage(image.getImage().getScaledInstance(900, 550, Image.SCALE_DEFAULT));// ͼ���С����Ӧ
		setTitle("ѧ����������");
		setUndecorated(true);// ȥ�߿�

		time_label = new JLabel(year + "��" + month + day + "��");
		time_label.setBounds(250, 500, 100, 30);
		elect_button = new JButton("ѧ��ѡ������");
		allCource_button = new JButton("��ʾȫ���γ�");
		Smodify_button = new JButton("�޸ĸ�������");
		audit_button = new JButton("�������/�鿴�����n��");
		relogin_button = new JButton("ע��");
		exit_button = new JButton("�˳�");
		Font f = new Font("����", Font.BOLD, 16);
		elect_button.setFont(f);
		allCource_button.setFont(f);
		Smodify_button.setFont(f);
		audit_button.setFont(f);
		relogin_button.setFont(f);
		exit_button.setFont(f);
		elect_button.setPreferredSize(new Dimension(80, 20));
		/*****************************************************/
		Color c = new Color(144, 238, 144);// ��Ӱ��ɫֵֻ��ռλ���á�
		elect_button.setBackground(c);
		// elect_button.setOpaque(false); // ���ñ���͸��
		/*****************************************************/
//		ImageIcon icon = new ImageIcon("tab.jpg");

		// elect_button.setIcon(icon);
		elect_button.addActionListener(new ButtonListener());
		elect_button.setBounds(530, 60, 220, 30);

		audit_button.setPreferredSize(new Dimension(80, 20));
		audit_button.setBackground(c);
		audit_button.addActionListener(new ButtonListener());
		audit_button.setBounds(550, 110, 220, 30);

		allCource_button.setPreferredSize(new Dimension(80, 20));
		allCource_button.setBackground(c);
		allCource_button.addActionListener(new ButtonListener());
		allCource_button.setBounds(570, 160, 220, 30);

		Smodify_button.setPreferredSize(new Dimension(80, 20));
		Smodify_button.setBackground(c);
		Smodify_button.addActionListener(new ButtonListener());
		Smodify_button.setBounds(590, 210, 220, 30);

		relogin_button.setPreferredSize(new Dimension(80, 20));
		relogin_button.setBackground(c);
		relogin_button.addActionListener(new ButtonListener());
		relogin_button.setBounds(610, 260, 220, 30);

		exit_button.setPreferredSize(new Dimension(80, 20));
		exit_button.setBackground(c);
		exit_button.addActionListener(new ButtonListener());
		exit_button.setBounds(630, 310, 220, 30);

		move();

		Stu.add(audit_button);
		Stu.add(Smodify_button);
		Stu.add(allCource_button);
		Stu.add(elect_button);
		Stu.add(relogin_button);
		Stu.add(exit_button);
		Stu.add(time_label);
		Stu.add(background);
		// setDragable(this);
		setSize(900, 550);
		setLocationRelativeTo(Stu);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public void close() {
		this.dispose();
	}

	public void CenterUnvisible() {
		setVisible(false);
	}

	public void setStud(Student Stud) {
		this.Stud = Stud;
	}

	public Student getStud() {
		return Stud;
	}

	public String getStudentID() {
		return Stud.getUserID();
	}

	public String getStudentPSW() {
		return Stud.getUserPSW();
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getText().equals("ѧ��ѡ������")) {
				try {
					new ElectCource(getStudentID(), studentRequest);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (button.getText().equals("��ʾȫ���γ�")) {
				try {
					new AllCource(studentRequest);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (button.getText().equals("�޸ĸ�������")) {
				new Modifypasswrd(getStudentID(), studentRequest, Stud);
				CenterUnvisible();
				
			}
			if (button.getText().equals("�������/�鿴�����n��")) {
				try {
					new AuditCourse(getStudentID(), studentRequest);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (button.getText().equals("�˳�")) {
				new OuterFrame("�˳�");
			}
			if (button.getText().equals("ע��")) {
				new OuterFrame("ע��");
			}
		}
	}

	public void move() {
		Point origin = new Point();
		// ʵ�ִ����϶�
		// ��갴��
		super.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		// ���º��϶�
		super.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point newp = getLocation();
				setLocation(newp.x + e.getX() - origin.x, newp.y + e.getY() - origin.y);
			}
		});
	}

	class OuterFrame extends JFrame {

		private static final long serialVersionUID = 1L;
		private int isClose;

		public int getIsClose() {
			return isClose;
		}

		public void OuterClose() {
			this.dispose();
		}

		public OuterFrame(String str) {
			Container outercon = getContentPane();
			outercon.setLayout(null);
			JButton button1 = new JButton("ȷ��");
			JButton button2 = new JButton("ȡ��");
			JLabel label = new JLabel("ȷ��" + str + "?");
			Font font = new Font("������κ", Font.BOLD, 15);
			label.setFont(font);
			button1.setBackground(new Color(160, 220, 200));
			button2.setBackground(new Color(160, 220, 200));
			button1.setBounds(30, 70, 60, 20);
			button2.setBounds(110, 70, 60, 20);
			label.setBounds(60, 20, 80, 40);

			outercon.add(label);
			outercon.add(button1);
			outercon.add(button2);

			if (str.equals("ע��")) {
				button1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isClose = 1;
						setVisible(false);
						CenterUnvisible();
						new TestLogin();
						close();
						OuterClose();
					}
				});
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isClose = 0;
						OuterClose();
					}
				});
			} else if (str.equals("�˳�")) {
				button1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isClose = 0;
						OuterClose();
					}
				});
			}
			setBounds(585, 250, 200, 150);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setVisible(true);
		}

	}

	@SuppressWarnings("unused")
	private void setDragable(final StudentCenter st) {
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isMoved = false;
				st.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePress(MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());
				st.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isMoved) {
					end_point = new Point(st.getLocation().x + e.getX() - pre_point.x,
							st.getLocation().y + e.getY() - pre_point.y);
					st.setLocation(end_point);
				}
			}
		});
	}
}