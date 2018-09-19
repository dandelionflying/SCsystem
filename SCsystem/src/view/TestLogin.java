package view;

/**************
 * 登录类
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import action.StudentRequest;
import model.Student;
import view.student.StudentCenter;

public class TestLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel JL_user = null;// 
	private JLabel JL_password = null;
	private JLabel JL_icon = null;
	private JTextField JT = null;
	private JPasswordField JP = null;
	private JButton JB_enter = null;

	private StudentRequest studentRequest = new StudentRequest("localhost", 7200);

	public StudentRequest getStudentRequest() {
		return studentRequest;
	}

	public TestLogin() {
		Container c = getContentPane();
		setTitle("学生选课系统V1.0");
		setBounds(535, 200, 300, 250);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("华文行楷", Font.BOLD, 25);
		JB_enter = new JButton("登  录");
		JP = new JPasswordField();
		JT = new JTextField();
		JL_icon = new JLabel("学生选课系统");
		JL_password = new JLabel("Password：");
		JL_user = new JLabel("Account ：");
		JL_icon.setHorizontalAlignment(SwingConstants.CENTER);
		JL_icon.setOpaque(true);
		JL_icon.setFont(font);

		JB_enter.setBackground(new Color(0, 205, 0));
		JB_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char pass[] = JP.getPassword();
				Student student = studentRequest.login(JT.getText());
				//从若在服务器端密码匹配成功则允许登录
				if (student != null && student.getUserPSW().equals(new String(pass))) {
					TClose(false);
					new Outer("登录成功^_^", 11, student);
				} else
					new Outer("登录失败T_T", 0, null);
			}
		});
		JP.setEchoChar('*');

		JL_icon.setBounds(70, 30, 160, 40);
		JL_user.setBounds(20, 80, 75, 20);
		JL_password.setBounds(20, 120, 75, 20);
		JT.setBounds(90, 80, 130, 20);
		JP.setBounds(90, 120, 130, 20);
		JB_enter.setBounds(110, 160, 80, 20);
		c.add(JL_icon);
		c.add(JT);
		c.add(JP);
		c.add(JL_user);
		c.add(JL_password);
		c.add(JB_enter);
		setVisible(true);
	}

	public void TClose(Boolean i) {
		this.dispose();
	}
	// 自定义弹出框
	class Outer extends JFrame {
		private static final long serialVersionUID = 1L;
		private Student student = null;

		public Outer(String str, int len, Student user) {
			Container c2 = getContentPane();
			Font f = new Font("隶书", Font.BOLD, 18);
			JLabel JL2 = new JLabel(str);
			JButton jb = new JButton("确  定");
			JL2.setFont(f);
			ImageIcon icon = new ImageIcon("outer2.png");
			JLabel back = new JLabel(icon);
			jb.setBackground(new Color(135, 206, 235));
			back.setSize(200, 150);
			icon.setImage(icon.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT));

			this.student = user;
			setLayout(null);
			JL2.setBounds(45, 30, 110, 40);
			jb.setBounds(65, 80, 75, 20);
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (str.equals("登录失败 ~ "))
						dispose();
					else {
						dispose();
						if (len == 11)
							new StudentCenter(studentRequest, student);
					}
				}
			});
			c2.add(JL2);
			c2.add(jb);
			c2.add(back);
			setBounds(585, 250, 200, 150);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
	}
}
