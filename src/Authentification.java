import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Authentification extends JFrame {
	
	JLabel showpsw;
	JLabel hidepsw;
	public static String Currentlogin;
	public Authentification() {
		
		//declaration des compenent
		
		JPanel pnl=new JPanel();
		JLabel close;
		JLabel minus;
		
		JLabel lbregister1 = new JLabel("Si vous n'avez pas un compte:");
		JLabel lbregister2 = new JLabel("cliquez ici");
		
		JLabel lbtitre=new JLabel("Athentification Form");
		JLabel lblogin=new JLabel("Login :");
		JLabel lbpsw=new JLabel("PassWord :");
		 
		JButton btnlogin =new JButton("Login");
		
		JButton btncancel =new JButton("Annuler");
		
		JTextField txtlogin=new JTextField();
		JPasswordField txtpsw=new JPasswordField();
		
		//declaration de container
		getContentPane().setLayout(null);
		
		
		
		//styles
		  ///img		
		ImageIcon icon = new ImageIcon("img/close_16.png");
		close =new JLabel(icon,JLabel.CENTER);
		ImageIcon icon1 = new ImageIcon("img/minus_16.png");
		minus =new JLabel(icon1,JLabel.CENTER);
		ImageIcon icon2 = new ImageIcon("img/show_16.png");
		hidepsw =new JLabel(icon2,JLabel.CENTER);
		ImageIcon icon3 = new ImageIcon("img/hide_16.png");
		showpsw =new JLabel(icon3,JLabel.CENTER);
		  ///color
		pnl.setBackground(Color.decode("#A9F6FE"));
		showpsw.setBackground(Color.decode("#A9F6FE"));
		hidepsw.setBackground(Color.decode("#A9F6FE"));
		getContentPane().setBackground(Color.decode("#0F0F33"));
		lbregister1.setForeground(Color.decode("#A9F6FE"));
		lbregister2.setForeground(Color.decode("#A9F6FE"));
		lbtitre.setForeground(Color.decode("#0F0F33"));
		lbtitre.setBackground(Color.decode("#A9F6FE"));
		close.setBackground(Color.decode("#A9F6FE"));
		minus.setBackground(Color.decode("#A9F6FE"));
		
		lbtitre.setOpaque(true);
		close.setOpaque(true);
		minus.setOpaque(true);
		showpsw.setOpaque(true);
		hidepsw.setOpaque(true);
		lblogin.setForeground(Color.decode("#A9F6FE"));
		lbpsw.setForeground(Color.decode("#A9F6FE"));
		btnlogin.setBackground(Color.decode("#A9F6FE"));
		btncancel.setBackground(Color.decode("#A9F6FE"));
		btnlogin.setForeground(Color.decode("#0F0F33"));
		btncancel.setForeground(Color.decode("#0F0F33"));
		  ///cursor
		Cursor c = new Cursor(Cursor.HAND_CURSOR);
		close.setCursor(c);	
		minus.setCursor(c);
		btnlogin.setCursor(c);
		btncancel.setCursor(c);
		lbregister2.setCursor(c); 
		showpsw.setCursor(c);
		hidepsw.setCursor(c);
				
		  ///font
		lbtitre.setFont(new Font("Serif", Font.BOLD, 25));
		lblogin.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 20));
		lbpsw.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 20));
		lbregister1.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
	
		Font font = lbregister2.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbregister2.setFont(font.deriveFont(attributes));
		
		btncancel.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnlogin.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		
		//X,Y
		
		lbtitre.  setBounds(10,5,250,30);		
		lblogin.  setBounds(80,90,100,30);
		lbpsw.    setBounds(80,130,110,30);
		txtlogin. setBounds(230,90,250,30);
		txtpsw.   setBounds(230,130,250,30);
		btncancel.setBounds(320,200,130,40);
		btnlogin. setBounds(130,200,130,40);	
		pnl.setBounds(0,0,600,40);
		close. setBounds(560,0,40,40);		
		minus. setBounds(520,0,40,40);
		showpsw. setBounds(500,130,40,30);
		hidepsw. setBounds(500,130,40,30);
		lbregister1.setBounds(130,250,200,40);
		lbregister2.setBounds(340,250,60,40);
		
		
		
		//add Les Composent
		
		getContentPane().add(lbtitre);
		getContentPane().add(lblogin);
		getContentPane().add(lbpsw);
		getContentPane().add(txtlogin);
		getContentPane().add(txtpsw);
		getContentPane().add(btnlogin);
		getContentPane().add(btncancel);		
		getContentPane().add(close);
		getContentPane().add(minus);
		getContentPane().add(showpsw);
		getContentPane().add(hidepsw);
		getContentPane().add(pnl);
		getContentPane().add(lbregister1);
		getContentPane().add(lbregister2);
		
		
		
	    setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		showpsw.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 600, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		//Les Evenment
		
		
		
		btncancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
				
			}
		});
		btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("driver loaded successfully");
				}
				catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					System.out.println("Driver error !!");
					e1.printStackTrace();
				}
				String psw = new String(txtpsw.getPassword());
				String login = txtlogin.getText(); 
				
				if(psw.equals("") || login.equals(""))
				{
					System.out.println("Remplire Les champs !!");
					JOptionPane.showMessageDialog(null, "Remplire Les champs !!");
					return;
				}
				try 
					{
						Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
						System.out.println("successfully Connected To DataBase ");
						java.sql.Statement stmt = cnx.createStatement();  
						ResultSet rs=stmt.executeQuery("select * from users where login='"+login+"'");
						while(rs.next()) 
						{														 
							if(psw.equals(rs.getString("psw"))) 
							{
								switch (rs.getString("type_cmp")) {
								case "Admin":									
									System.out.println("Admin");
									Admin_form gp = new Admin_form();
									setVisible(false);
									break;
								case "User":
									Currentlogin=login;
									User_form gr=new User_form();
									System.out.println("User");
									setVisible(false);
									break;
								default:
									break;
								}
								System.out.println("Authentification successfully");	
							}																											
							else {
								System.out.println("Login Or PassWord is Incorect !!");
								JOptionPane.showMessageDialog(null, "Login Ou PassWord is Incorect !!");
								return;
							}																									
						  }
						
						}
					catch (SQLException e1) 
						{
							System.out.println("problem on database !!");
							e1.printStackTrace();
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				//setVisible(false);
			}				
		});
		
		close.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				close.setBackground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				close.setBackground(Color.decode("#FD1A2D"));
						
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});
		minus.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				minus.setBackground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				minus.setBackground(Color.decode("#FAFAFA"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setState(JFrame.ICONIFIED);
			}
		});
		
		showpsw.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				showpsw.setBackground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				showpsw.setBackground(Color.decode("#FAFAFA"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
						
				txtpsw.setEchoChar('•');
				hidepsw.setVisible(true);
				showpsw.setVisible(false);
			}
		});
		hidepsw.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				hidepsw.setBackground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				hidepsw.setBackground(Color.decode("#FAFAFA"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub							
					txtpsw.setEchoChar((char) 0);
					hidepsw.setVisible(false);
					showpsw.setVisible(true);						
			}
		});
		
		btnlogin.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnlogin.setBackground(Color.decode("#A9F6FE"));
				btnlogin.setForeground(Color.decode("#0F0F33"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnlogin.setBackground(Color.decode("#0F0F33"));
				btnlogin.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btncancel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btncancel.setBackground(Color.decode("#A9F6FE"));				
				btncancel.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btncancel.setBackground(Color.decode("#0F0F33"));				
				btncancel.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lbregister2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Register rg =new Register();
				setVisible(false);
			}
		});
	}
}
