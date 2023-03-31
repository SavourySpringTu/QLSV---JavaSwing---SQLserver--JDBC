package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.net.httpserver.Authenticator.Result;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPassword;
	
	private FrameHome frameHome;
	private static FrameLogin frameLogin;
	
	private static Connection connection=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameLogin = new FrameLogin();
					frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FrameLogin() throws SQLException {
		About about = new About();
		try {
	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	String connectionURL="jdbc:sqlserver://DESKTOP-9U8SQD3\\SQLSERVER01:1433;encrypt=true;databaseName=QLSV;integratedSecurity=true;trustServerCertificate=true";
	    	connection = DriverManager.getConnection(connectionURL, "sa", "123");
	    	System.out.println("Kết nối sql thành công!");
	    }catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    	about.showTitle("Không thể kết nối SQL!",true);
	    }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUser = new JTextField();
		tfUser.setBounds(186, 199, 151, 35);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(186, 275, 151, 35);
		contentPane.add(tfPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tfUser.getText();
				String password = tfPassword.getText();
				String sql = "EXEC Login "+"'"+user+"'"+","+"'"+password+"'";
				try {
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()==false)
					{
						about.showTitle("Mật khẩu hoặc tên tài khoản không đúng!", true);
					}
					else
					{
						try {
							frameHome = new FrameHome(connection);
							frameHome.setVisible(true);
							dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(202, 363, 124, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(425, 476, 101, 27);
		contentPane.add(btnNewButton_1);
		
		Label label = new Label("User name");
		label.setBounds(186, 170, 59, 21);
		contentPane.add(label);
		
		Label label_1 = new Label("Password");
		label_1.setBounds(186, 248, 59, 21);
		contentPane.add(label_1);
		
		Label label_2 = new Label("QUAN LY SINH VIEN");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 25));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(83, 43, 364, 46);
		contentPane.add(label_2);
	}
}
