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
//            �޸ĸ�������                       //
//**********************************//
public class Modifypasswrd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel background;
	private JLabel prePasswordLabel;
	private JLabel newPasswordLabel;
	private JLabel newPasswordLabel2;
	private JPasswordField prePF;// ԭ����������
	private JPasswordField newPF;// ������������
	private JPasswordField newPF2;// ������ȷ����
	private JButton enter;
	private JButton cansel;
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	private boolean flag = false;
	int count = 0;// �����������

	public Modifypasswrd(String userid, StudentRequest studentRequest, Student student) {
		Container mfp = getContentPane();
		setTitle("�޸ĸ�������");
		setBounds(535, 200, 300, 250);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// ʵ�������������
		background = new JLabel();
		ImageIcon image = new ImageIcon("modifypassword.jpg");
		background = new JLabel(image);
		background.setSize(300, 250);
		image.setImage(image.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT));// ͼ���С����Ӧ
		prePasswordLabel = new JLabel("ԭ���룺");
		newPasswordLabel = new JLabel("�����룺");
		newPasswordLabel2 = new JLabel("�ٴ����룺");
		prePF = new JPasswordField();
		newPF = new JPasswordField();
		newPF2 = new JPasswordField();
		enter = new JButton("ȷ��");
		cansel = new JButton("ȡ��");
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
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�,�����µ�¼��");
						setUnvisible();
						new TestLogin();
						close();
					} else {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
						count++;
						if (count > 3) {
							JOptionPane.showMessageDialog(null, "�޸Ĵ�������,�����µ�¼��");
							setUnvisible();
							new TestLogin();
							close();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "ԭ�����������벻һ�£�");
					count++;
					if (count > 3) {
						JOptionPane.showMessageDialog(null, "�޸Ĵ�������,�����µ�¼��");
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
		// �������������
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
