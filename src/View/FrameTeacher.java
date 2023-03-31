package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.Button;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameTeacher extends JFrame {

	private JPanel contentPane;
	private JTextField tfId_teacher;
	private JTextField tfName_teacher;
	private JTextField textField_2;
	private Label label;
	private Label label_1;
	private Label tfPassword_teacher;
	private Button btnInsert;
	private Button btnDelete;
	private Button btnAlter;
	private JTable table;
	
	private Connection connection;
	private FrameHome frameHome;
	private PreparedStatement pstmt;
	private About about;

	public FrameTeacher(Connection connection) throws SQLException {
		about = new About();
		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton tfThoat = new JButton("Thoát");
		tfThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frameHome = new FrameHome(connection);
					frameHome.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tfThoat.setBounds(10, 10, 85, 21);
		contentPane.add(tfThoat);
		
		tfId_teacher = new JTextField();
		tfId_teacher.setColumns(10);
		tfId_teacher.setBounds(119, 131, 134, 19);
		contentPane.add(tfId_teacher);
		
		tfName_teacher = new JTextField();
		tfName_teacher.setColumns(10);
		tfName_teacher.setBounds(119, 182, 134, 19);
		contentPane.add(tfName_teacher);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 241, 134, 19);
		contentPane.add(textField_2);
		
		label = new Label("Id");
		label.setAlignment(Label.RIGHT);
		label.setBounds(36, 129, 59, 21);
		contentPane.add(label);
		
		label_1 = new Label("Name ");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(36, 180, 59, 21);
		contentPane.add(label_1);
		
		tfPassword_teacher = new Label("Password");
		tfPassword_teacher.setAlignment(Label.RIGHT);
		tfPassword_teacher.setBounds(36, 239, 59, 21);
		contentPane.add(tfPassword_teacher);

// ================== BUTTON INSERT =========================================
		btnInsert = new Button("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean kt= true;
				String id = tfId_teacher.getText();
				String name = tfName_teacher.getText();
				String password = tfPassword_teacher.getText();
				
//======================== Xử lý ngoại lệ chuỗi ===========================
				kt=about.ktName(kt,name);
				if(kt==false)
				{
					kt=true;
					about.showTitle("Tên không hợp lê!",true);
					return;
				}
// ==========================================================================
				String sql="INSERT INTO Teacher(id_teacher,name_teacher,password_teacher) VALUES(?,?,?)";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,id);
					pstmt.setString(2,name);;
					pstmt.setString(3,password);
					pstmt.executeUpdate();
					showListTeacher();
				} catch (SQLException e1) {
					e1.printStackTrace();
					about.showTitle("Thêm giáo viên thât bại!",true);
				}
				about.showTitle("Thêm giáo viên thành công!",false);
				System.out.println("Thêm giáo viên thành công! " +"ID: "+ id+" "+"Name: "+name+" "+"Password: "+password);
			}
		});
		btnInsert.setBounds(358, 129, 85, 21);
		contentPane.add(btnInsert);
		
// ============================= BUTTON DELET =======================================
		
		btnDelete = new Button("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId_teacher.getText();
				String sql="DELETE FROM Teacher WHERE id_teacher="+"'"+id+"'";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				about.showTitle("Xóa giáo viên thành công!",false);
				System.out.print("Xóa giáo viên thành công! " +"ID: "+ id);
			}
		});
		btnDelete.setBounds(358, 180, 85, 21);
		contentPane.add(btnDelete);

// ========================== BUTTON ALTER ==================================
		
		btnAlter = new Button("Alter");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId_teacher.getText();
				String name = tfName_teacher.getText();
				String password = tfPassword_teacher.getText();
				String sql ="UPDATE Teacher SET name_teacher="+"'"+name+"'"+",password_teacher="+"'"+password+"'"+"WHERE id_teacher="+"'"+id+"'";
				try {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					about.showTitle("Sửa giáo viên thất bại!", true);
				}
				System.out.println("Sửa giáo viên thành công: Id= "+id+", Name= "+name+", Id teacher= "+password);
				about.showTitle("Sửa giáo viên thành công!", false);
			}
		});
		btnAlter.setBounds(358, 239, 85, 21);
		contentPane.add(btnAlter);

// ==========================================================================		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setSurrendersFocusOnKeystroke(true);
		table.setForeground(Color.BLACK);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(Color.WHITE);
		table.setBounds(10, 350, 766, 160);
		contentPane.add(table);
		showListTeacher();
	}
	
// =========================== SHOW LIST ==========================================	
	
	public void showListTeacher() throws SQLException
	{
		System.out.println("================ Danh sách sinh viên ======================");
		System.out.println("connection: "+connection);
		pstmt = connection.prepareStatement("SELECT * FROM Teacher");
		ResultSet rs = pstmt.executeQuery();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(rs.next())
		{
			Object objList[] = {rs.getString("id_teacher"),rs.getString("name_teacher"),rs.getString("password_teacher")};
			System.out.println(rs.getString("id_teacher"));
			model.addRow(objList);
			table.setModel(model);
		}
		System.out.println("===========================================================");
	}
}
