package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrameHome extends JFrame {

	private JPanel contentPane;
	private FrameStudent frameStudent;
	private FrameClass frameClass;
	private FrameTeacher frameTeacher;
	
	private Connection connection;

	public FrameHome(Connection connection) throws SQLException {
		this.connection=connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("STUDENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameStudent = new FrameStudent(connection);
							frameStudent.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(304, 174, 155, 51);
		contentPane.add(btnNewButton);
		
		JButton btnTeacher = new JButton("TEACHER");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frameTeacher = new FrameTeacher(connection);
					frameTeacher.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
		});
		btnTeacher.setBounds(304, 252, 155, 51);
		contentPane.add(btnTeacher);
		
		JButton btnClass = new JButton("CLASS");
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frameClass = new FrameClass(connection);
					frameClass.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClass.setBounds(304, 337, 155, 51);
		contentPane.add(btnClass);
		
		JButton btnNewButton_3 = new JButton("Thoát");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(334, 453, 97, 31);
		contentPane.add(btnNewButton_3);
		
		Label label = new Label("QUAN LY SINH VIEN");
		label.setAlignment(Label.CENTER);
		label.setBounds(206, 66, 351, 51);
		contentPane.add(label);
	}
}
