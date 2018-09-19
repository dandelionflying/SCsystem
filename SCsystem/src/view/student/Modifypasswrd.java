package view.student;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import action.StudentRequest;
import model.Student;
import view.TestLogin;

//**********************************//
//            修改个人密码                       //
//**********************************//
public class Modifypasswrd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel background;
	private JLabel prePasswordLabel;
	private JLabel newPasswordLabel;
	private JLabel newPasswordLabel2;
	private JPasswordField prePF;// 原密码输入区
	private JPasswordField newPF;// 新密码输入区
	private JPasswordField newPF2;// 新密码确认区
	private JButton enter;
	private JButton cansel;
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	private boolean flag = false;
	int count = 0;// 输入密码次数

	public Modifypasswrd(String userid, StudentRequest studentRequest, Student student) {
		Container mfp = getContentPane();
		setTitle("修改个人密码");
		setBounds(535, 200, 300, 250);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// 实例化各组件对象
		background = new JLabel();
		ImageIcon image = new ImageIcon("modifypassword.jpg");
		background = new JLabel(image);
		background.setSize(300, 250);
		image.setImage(image.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT));// 图标大小自适应
		prePasswordLabel = new JLabel("原密码：");
		newPasswordLabel = new JLabel("新密码：");
		newPasswordLabel2 = new JLabel("再次输入：");
		prePF = new JPasswordField();
		newPF = new JPasswordField();
		newPF2 = new JPasswordField();
		enter = new JButton("确定");
		cansel = new JButton("取消");
		enter.setBackground(new Color(135, 206, 235));
		cansel.setBackground(new Color(135, 206, 235));
		prePasswordLabel.setBounds(30, 40, 75, 25);
		newPasswordLabel.setBounds(30, 80, 75, 25);
		newPasswordLabel2.setBounds(30, 120, 120, 25);
		prePF.setBounds(90, 40, 130, 25);
		newPF.setBounds(90, 80, 130, 25);
		newPF2.setBounds(90, 120, 130, 25);
		enter.setBounds(80, 160, 60, 20);
		cansel.setBounds(160, 160, 60, 20);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				oldPassword = String.valueOf(prePF.getPassword());
				newPassword = String.valueOf(newPF.getPassword());
				newPassword2 = String.valueOf(newPF2.getPassword());
				if (oldPassword.equals(student.getUserPSW()) && newPassword.equals(newPassword2)) {
					flag = studentRequest.resetPasswordRequest(student.getUserID(), newPassword);
					if (flag) {
						JOptionPane.showMessageDialog(null, "修改成功,请重新登录！");
						setUnvisible();
						new TestLogin();
						close();
					} else {
						JOptionPane.showMessageDialog(null, "修改失败");
						count++;
						if (count > 3) {
							JOptionPane.showMessageDialog(null, "修改次数过多,请重新登录！");
							setUnvisible();
							new TestLogin();
							close();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "原密码错误或密码不一致！");
					count++;
					if (count > 3) {
						JOptionPane.showMessageDialog(null, "修改次数过多,请重新登录！");
						setUnvisible();
						new TestLogin();
						close();
					}
				}
			}
		});
		cansel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				new StudentCenter(studentRequest, student);
			}
		});
		// 各组件加入容器
		mfp.add(cansel);
		mfp.add(enter);
		mfp.add(newPF2);
		mfp.add(newPF);
		mfp.add(prePF);
		mfp.add(newPasswordLabel2);
		mfp.add(newPasswordLabel);
		mfp.add(prePasswordLabel);
		mfp.add(background);
		setVisible(true);
	}

	public boolean isResetSucceed() {
		return flag;
	}

	public void close() {
		this.dispose();
	}

	public void setUnvisible() {
		this.setVisible(false);
	}
}
