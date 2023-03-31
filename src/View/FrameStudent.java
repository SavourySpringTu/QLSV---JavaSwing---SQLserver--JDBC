package View;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrameStudent extends JFrame {

	private JPanel contentPane;
	private JTextField tfId_sv;
	private JTextField tfName_sv;
	private JTextField tfAge_sv;
	private JTextField tfPassword_sv;
	private JTextField tfId_class;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnAlter;
	private JTable table;
	private About about;
	
	private FrameStudent frameStudent;
	private FrameHome frameHome;
	
	private PreparedStatement pstmt=null;
	private Connection connection;

	public FrameStudent(Connection connection) throws SQLException {
		about = new About();
		this.connection=connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfId_sv = new JTextField();
		tfId_sv.setBounds(98, 96, 134, 19);
		contentPane.add(tfId_sv);
		tfId_sv.setColumns(10);
		
		tfName_sv = new JTextField();
		tfName_sv.setBounds(98, 141, 134, 19);
		contentPane.add(tfName_sv);
		tfName_sv.setColumns(10);
		
		tfAge_sv = new JTextField();
		tfAge_sv.setColumns(10);
		tfAge_sv.setBounds(98, 187, 134, 19);
		contentPane.add(tfAge_sv);
		
		tfPassword_sv = new JTextField();
		tfPassword_sv.setColumns(10);
		tfPassword_sv.setBounds(98, 233, 134, 19);
		contentPane.add(tfPassword_sv);
		
		tfId_class = new JTextField();
		tfId_class.setColumns(10);
		tfId_class.setBounds(98, 280, 134, 19);
		contentPane.add(tfId_class);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameHome = new FrameHome(connection);
							frameHome.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(10, 10, 85, 21);
		contentPane.add(btnNewButton);
// ========= BUTTON INSERT EVENT =============		
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean kt= true;
				String id = tfId_sv.getText();
				String name = tfName_sv.getText();
				String age = tfAge_sv.getText();
				String password = tfPassword_sv.getText();
				String id_class = tfId_class.getText();
				
//===================== Xử lý ngoại lệ tuổi =====================
				kt=about.ktAge(kt,age);
				if(kt==false)
				{
					about.showTitle("Tuổi không hợp lệ!",true);
					kt=true;
					return;
				}
//======================== Xử lý ngoại lệ chuỗi ===========================
				kt=about.ktName(kt,name);
				if(kt==false)
				{
					kt=true;
					about.showTitle("Tên không hợp lê!",true);
					return;
				}
// ==========================================================================
				String sql="INSERT INTO Student(id_sv,name_sv,age,password_sv,id_class) VALUES(?,?,?,?,?)";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,id);
					pstmt.setString(2,name);
					pstmt.setString(3,age);
					pstmt.setString(4,password);
					pstmt.setString(5,id_class);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
					about.showTitle("Thêm sinh viên thât bại!",true);
				}
				about.showTitle("Thêm sinh viên thành công!",false);
				System.out.println("Thêm sinh viên thành công! " +"ID: "+ id+" "+"Name: "+name+" "+"Age: "+age+" "+"Password: "+password+" "+"ID_class: "+id_class);
			}
		});
		btnInsert.setBounds(310, 95, 85, 21);
		contentPane.add(btnInsert);

// =============== BUTTON DELETE ===============
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId_sv.getText();
				String sql="DELETE FROM Student WHERE id_sv="+"'"+id+"'";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				about.showTitle("Xóa sinh viên thành công!",false);
				System.out.print("Xóa sinh viên thành công! " +"ID: "+ id);
			}
		});		
		btnDelete.setBounds(310, 140, 85, 21);
		contentPane.add(btnDelete);

// ============== BUTTON ALTER ===============
		
		btnAlter = new JButton("Alter");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean kt= true;
				String id = tfId_sv.getText();
				String name = tfName_sv.getText();
				String age = tfAge_sv.getText();
				String password = tfPassword_sv.getText();
				String id_class = tfId_class.getText();
//===================== Xử lý ngoại lệ tuổi =====================
				kt=about.ktAge(kt,age);
				if(kt==false)
				{
					about.showTitle("Tuổi không hợp lệ!",true);
					kt=true;
					return;
				}
//======================== Xử lý ngoại lệ chuỗi ===========================
				kt=about.ktName(kt,name);
				if(kt==false)
				{
					kt=true;
					about.showTitle("Tên không hợp lê!",true);
					return;
				}
// ==========================================================================
				String sql ="UPDATE Student SET name_sv="+"'"+name+"'"+",age="+"'"+age+"'"+",password_sv="+"'"+password+"'"+",id_class="+"'"+id_class+"'"+"WHERE id_sv="+"'"+id+"'";
				System.out.println(sql);
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				about.showTitle("Sửa sinh viên thành công!",false);
				System.out.print("Sửa sinh viên thành công! " +"ID: "+ id);
			}
		});
		
// =============================================
		
		btnAlter.setBounds(310, 186, 85, 21);
		contentPane.add(btnAlter);
		
		Label label = new Label("Id");
		label.setAlignment(Label.RIGHT);
		label.setBounds(12, 96, 59, 21);
		contentPane.add(label);
		
		Label label_1 = new Label("Name");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(12, 139, 59, 21);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Age");
		label_2.setAlignment(Label.RIGHT);
		label_2.setBounds(12, 185, 59, 21);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Password");
		label_3.setAlignment(Label.RIGHT);
		label_3.setBounds(12, 231, 59, 21);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Id class");
		label_4.setAlignment(Label.RIGHT);
		label_4.setBounds(12, 278, 59, 21);
		contentPane.add(label_4);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Name", "Age", "Password", "Id class"
			}
		));
		table.setBounds(10, 332, 766, 160);
		contentPane.add(table);
		System.out.println("toi list");
		showListStudent();
	}
	
// =========================== SHOW LIST ==========================================		
	
	public void showListStudent() throws SQLException
	{
		System.out.println("================ Danh sách sinh viên ======================");
		System.out.println("connection: "+connection);
		pstmt = connection.prepareStatement("SELECT * FROM Student");
		ResultSet rs = pstmt.executeQuery();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(rs.next())
		{
			Object objList[] = {rs.getString("id_sv"),rs.getString("name_sv"),rs.getInt("age"),rs.getString("password_sv"),rs.getString("id_class")};
			System.out.println(rs.getString("id_sv"));
			model.addRow(objList);
			table.setModel(model);
		}
		System.out.println("===========================================================");
	}
}
