package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameClass extends JFrame {

	private JPanel contentPane;
	private JTextField tfId_class;
	private JTextField tfName_class;
	private JTextField tfId_teacher;
	private Label label;
	private Label label_1;
	private Label label_2;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnAlter;
	private JTable table;
	private FrameHome frameHome;
	private FrameClass frameClass;
	
	private Connection connection;
	private About about;

	public FrameClass(Connection connection) throws SQLException {
		this.connection = connection ;
		about = new About();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(10, 10, 85, 21);
		contentPane.add(btnNewButton);
		
		tfId_class = new JTextField();
		tfId_class.setColumns(10);
		tfId_class.setBounds(97, 112, 139, 19);
		contentPane.add(tfId_class);
		
		tfName_class = new JTextField();
		tfName_class.setColumns(10);
		tfName_class.setBounds(97, 164, 139, 19);
		contentPane.add(tfName_class);
		
		tfId_teacher = new JTextField();
		tfId_teacher.setColumns(10);
		tfId_teacher.setBounds(97, 220, 139, 19);
		contentPane.add(tfId_teacher);
		
		label = new Label("Id ");
		label.setAlignment(Label.RIGHT);
		label.setBounds(20, 110, 59, 21);
		contentPane.add(label);
		
		label_1 = new Label("Name");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(20, 162, 59, 21);
		contentPane.add(label_1);
		
		label_2 = new Label("Id teacher");
		label_2.setAlignment(Label.RIGHT);
		label_2.setBounds(20, 218, 59, 21);
		contentPane.add(label_2);

// ====================== BUTTON INSERT ===========================
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName_class.getText();
				String id = tfId_class.getText();
				String id_teacher = tfId_teacher.getText();
				String sql="INSERT INTO Class(id_class,name_class,id_teacher) VALUES(?,?,?)";
				try {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,id);
					pstmt.setString(2,name);
					pstmt.setString(3,id_teacher);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					about.showTitle("Thêm lớp thất bại!",true);
				}
				about.showTitle("Thêm lớp thành công!",false);
				System.out.println("Thêm thành công: Id= "+id+" ,Name= "+name+", Id_teacher= "+id_teacher);
			}
		});
		btnInsert.setBounds(324, 111, 85, 21);
		contentPane.add(btnInsert);

// =========================== BUTTON DELETE =================================
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId_class.getText();
				String sql="DELETE FROM Student WHERE id="+"'"+id+"'";
				try {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					about.showTitle("Xóa lớp thất bại!",true);
				}
				about.showTitle("Xóa lớp thành công!",false);
				System.out.println("Id xoa: "+id);
			}
		});
		btnDelete.setBounds(324, 163, 85, 21);
		contentPane.add(btnDelete);

// ======================= BUTTON ALTER =============================		
		
		btnAlter = new JButton("Alter");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId_class.getText();
				String name=tfName_class.getText();
				String id_teacher=tfId_teacher.getText();
				String sql ="UPDATE Class SET name_class="+"'"+name+"'"+",id_teacher="+"'"+id_teacher+"'"+"WHERE id_class="+"'"+id+"'";
				try {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					about.showTitle("Sửa lớp thất bại!", true);
				}
				System.out.println("Sửa thành công: Id= "+id+", Name= "+name+", Id teacher= "+id_teacher);
				about.showTitle("Sửa lớp thành công!", false);
			}
		});
		btnAlter.setBounds(324, 219, 85, 21);
		contentPane.add(btnAlter);

// ==================================================================		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
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
		table.setBounds(20, 299, 756, 254);
		contentPane.add(table);
		showListClass();
	}

// =========================== SHOW LIST ==========================================	
	
	public void showListClass() throws SQLException
	{
		System.out.println("================ Danh sách lớp ======================");
		System.out.println("connection: "+connection);
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Class");
		ResultSet rs = pstmt.executeQuery();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(rs.next())
		{
			Object objList[] = {rs.getString("id_class"),rs.getString("name_class"),rs.getString("id_teacher")};
			System.out.println(rs.getString("id_class"));
			model.addRow(objList);
			table.setModel(model);
		}
		System.out.println("===========================================================");
	}
}
