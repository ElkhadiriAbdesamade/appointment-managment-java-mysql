import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Register extends JFrame {
	
	
	JLabel showpsw;
	JLabel hidepsw;
	
	JTextField txtcin=new JTextField();
	JTextField txtnom=new JTextField();
	JTextField txtprenom=new JTextField();
	JTextField txtadrs=new JTextField();
	JTextField txttele=new JTextField();
	JTextField txtemail=new JTextField();
	JPasswordField txtpsw=new JPasswordField();
	JPasswordField txtcpsw=new JPasswordField();
	public Register()
	{
		//declaration des compenent
		
				JPanel pnl=new JPanel();
				JLabel close;
				JLabel minus;
				JLabel lbtr = new JLabel("Entrez vos informations pour obtenir votre compte :");
				JLabel lblogin1 = new JLabel("Si vous avez un compte ");
				JLabel lblogin2 = new JLabel("Log In");				
				JLabel lbtitre=new JLabel("Register Form");
				JLabel lbcin=new JLabel("Cin :");
				JLabel lbnom=new JLabel("Nom :");
				JLabel lbprenom=new JLabel("Prenom :");
				JLabel lbadrss=new JLabel("Adresse :");
				JLabel lbtele=new JLabel("Téléphon :");
				JLabel lbemail=new JLabel("Email :");
				JLabel lbpsw=new JLabel("PassWord :");
				JLabel lbcpsw=new JLabel("Confirm PassWord :");
				
				
				JButton btnregister =new JButton("Register");				
				JButton btncancel =new JButton("Annuler");				
				
				
		
		
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
			showpsw.setBackground(Color.decode("#A9F6FE"));
			hidepsw.setBackground(Color.decode("#A9F6FE"));
			pnl.setBackground(Color.decode("#A9F6FE"));
			getContentPane().setBackground(Color.decode("#0F0F33"));
			lbtitre.setOpaque(true);
			close.setOpaque(true);
			minus.setOpaque(true);
			showpsw.setOpaque(true);
			hidepsw.setOpaque(true);
			close.setBackground(Color.decode("#A9F6FE"));
			minus.setBackground(Color.decode("#A9F6FE"));
			lbtitre.setForeground(Color.decode("#0F0F33"));
			lbtitre.setBackground(Color.decode("#A9F6FE"));
			lbtr.setForeground(Color.decode("#A9F6FE"));
			lbcin.setForeground(Color.decode("#A9F6FE"));
			lbnom.setForeground(Color.decode("#A9F6FE"));
			lbprenom.setForeground(Color.decode("#A9F6FE"));
			lbadrss.setForeground(Color.decode("#A9F6FE"));
			lbtele.setForeground(Color.decode("#A9F6FE"));
			lbemail.setForeground(Color.decode("#A9F6FE"));
			lbpsw.setForeground(Color.decode("#A9F6FE"));
			lbcpsw.setForeground(Color.decode("#A9F6FE"));
			lblogin1.setForeground(Color.decode("#A9F6FE"));		
			lblogin2.setForeground(Color.decode("#A9F6FE"));
			btnregister.setBackground(Color.decode("#A9F6FE"));
			btncancel.setBackground(Color.decode("#A9F6FE"));
			btnregister.setForeground(Color.decode("#0F0F33"));
			btncancel.setForeground(Color.decode("#0F0F33"));
		 
		  ///Font
						 
			lbtitre.setFont(new Font("Serif", Font.BOLD, 25));
			lbtr.setFont(new Font("Serif", Font.BOLD, 25));
			lbcin.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbnom.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbprenom.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbemail.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbtele.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbadrss.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbpsw.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lbcpsw.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
			lblogin1.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		
			
			Font font = lblogin2.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			lblogin2.setFont(font.deriveFont(attributes));
			
			btncancel.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
			btnregister.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		 ///Cursor
			Cursor c = new Cursor(Cursor.HAND_CURSOR);
			close.setCursor(c);	
			minus.setCursor(c);
			btnregister.setCursor(c);
			btncancel.setCursor(c);
			lblogin2.setCursor(c);
			showpsw.setCursor(c);
			hidepsw.setCursor(c);
		//x,y
		lbtitre .setBounds(10,5,250,30);
		
		lbtr   .setBounds(60,50,600,30);
		lbcin   .setBounds(200,100,115,30);
		lbnom   .setBounds(200,150,115,30);
		lbprenom.setBounds(200,200,115,30);
		lbadrss .setBounds(200,250,115,30);
		lbtele  .setBounds(200,300,115,30);
		lbemail .setBounds(200,350,115,30);
		lbpsw   .setBounds(200,400,115,30);
		lbcpsw  .setBounds(200,450,135,30);
		
		txtcin   .setBounds(340,100,250,30);
		txtnom   .setBounds(340,150,250,30);
		txtprenom.setBounds(340,200,250,30);
		txtadrs  .setBounds(340,250,250,30);
		txttele  .setBounds(340,300,250,30);
		txtemail .setBounds(340,350,250,30);
		txtpsw   .setBounds(340,400,250,30);
		txtcpsw  .setBounds(340,450,250,30);
		
		btncancel.setBounds(400,530,130,40);
		btnregister.setBounds(200,530,130,40);	
		
		lblogin1  .setBounds(200,580,170,40);
		lblogin2  .setBounds(370,580,40,40);
	
		showpsw. setBounds(620,425,40,30);
		hidepsw. setBounds(620,425,40,30);
		
		close. setBounds(760,0,40,40);		
		minus. setBounds(720,0,40,40);
		
		pnl.setBounds(0,0,800,40);
		
		//add Les Composent

		getContentPane().add(lbtitre);
		getContentPane().add(lbtr);
		getContentPane().add(lbcin);
		getContentPane().add(lbnom);
		getContentPane().add(lbprenom);
		getContentPane().add(lbadrss);
		getContentPane().add(lbtele);
		getContentPane().add(lbemail);
		getContentPane().add(lbpsw);
		getContentPane().add(lbcpsw);
		
		getContentPane().add(txtcin);
		getContentPane().add(txtnom);
		getContentPane().add(txtprenom);
		getContentPane().add(txtadrs);
		getContentPane().add(txttele);
		getContentPane().add(txtemail);
		getContentPane().add(txtpsw);
		getContentPane().add(txtcpsw);
		
		
		getContentPane().add(btnregister);
		getContentPane().add(btncancel);
		
		getContentPane().add(showpsw);
		getContentPane().add(hidepsw); 
		
		getContentPane().add(close);
		getContentPane().add(minus);
		getContentPane().add(lblogin1);
		getContentPane().add(lblogin2);
		getContentPane().add(pnl);
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		showpsw.setVisible(false);
		setUndecorated(true);				
		setBounds(300, 300, 800, 650);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		//Les Evenment
		
				btncancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Authentification at =new Authentification();
						setVisible(false);
					}
				});
				lblogin2.addMouseListener(new MouseListener() {
					
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
						Authentification at =new Authentification();
						setVisible(false);
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
						setVisible(false);
						Authentification a=new Authentification();
						
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
						txtcpsw.setEchoChar('•');
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
							txtcpsw.setEchoChar((char) 0);
							hidepsw.setVisible(false);
							showpsw.setVisible(true);						
					}
				});
				
				btnregister.addMouseListener(new MouseListener() {
					
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
						btnregister.setBackground(Color.decode("#A9F6FE"));
						btnregister.setForeground(Color.decode("#0F0F33"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnregister.setBackground(Color.decode("#0F0F33"));
						btnregister.setForeground(Color.decode("#A9F6FE"));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
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
						
						try 
							{
							  String cin=txtcin.getText();
							  String nom=txtnom.getText();
							  String prenom=txtprenom.getText();
							  String adrss=txtadrs.getText();
							  String tele=txttele.getText();
							  String email=txtemail.getText();
							  String psw=new String(txtpsw.getPassword());
							  String cpsw=new String(txtcpsw.getPassword());
							  
							  
							  
							  if(cin.equals("") || Character.isAlphabetic(cin.charAt(0))==false )
							  {
								  	System.out.println("Le CIN non valide !!");
									JOptionPane.showMessageDialog(null, "Le CIN non valide !!");
									return;
							  }
							  if(tele.length()!=10 || onlyDigits(tele, tele.length())==false)
							  {
								  	System.out.println("Le Nomber de Telephon non valide !!");
									JOptionPane.showMessageDialog(null, "Le Nomber de Telephon non valide !!");
									return;
							  }
							  
							  if(psw.equals("") || cpsw.equals("") || cin.equals("") || nom.equals("") || prenom.equals("") || adrss.equals("") || tele.equals("") || email.equals(""))
								{
									System.out.println("Les champs non valide !!");
									JOptionPane.showMessageDialog(null, "Les champs non valide !!");
									return;
								}
							  							  							  							  
							  if(exist(cin)==false)
								{
									  if(psw.equals(cpsw))
									  {
										    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
											System.out.println("successfully Connected To DataBase ");
											java.sql.Statement stmt = cnx.createStatement();
											String cmd1="insert into patient values ('"+cin+"','"+nom+"','"+prenom+"','"+adrss+"','"+tele+"','"+email+"')";
											String cmd2="insert into users(login,psw,type_cmp) values ('"+cin+"','"+psw+"','User')";
											int n1= stmt.executeUpdate(cmd1);
											int n2= stmt.executeUpdate(cmd2);
											if(n1==1 && n2==1)
											{
												JOptionPane.showMessageDialog(null,"Compte créé avec succès \n Votre CIN est Le Login");
												System.out.println("Compte créé avec succès");
												clear();
											}
											else {
												JOptionPane.showConfirmDialog(null,"Error Data !!");
												System.out.println("Error Data !!");
												return;
												
											}
											stmt.close();
									  }
									  else {
										  JOptionPane.showMessageDialog(null,"Confirme password !!");
											System.out.println("Confirme password !!");
											return;
											
										} 
								  	
								}
							  else {
								    JOptionPane.showMessageDialog(null,"Cin est Exist déja !!");
									System.out.println("Cin est Exist déja !!");
									return;
									
								} 
								
								
							}
						catch (SQLException e1) 
							{
								System.out.println("problem on database !!");
								e1.printStackTrace();
							}
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
		
	}
	
	boolean exist(String cin) {
		boolean t=false;
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
		
		try 
			{
				Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
				System.out.println("successfully Connected To DataBase ");
				java.sql.Statement stmt = cnx.createStatement();  
				String cmd="select * from patient where cin='"+cin+"'";
				ResultSet rs= stmt.executeQuery(cmd);
				
				while(rs.next())
				{
					System.out.println(rs.getRow());
					if(rs.getRow()!=0) {
						t=true;
					}	
				}		
				stmt.close();			
				
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
		return t;
	}
	public void clear() 
	{
		txtcin.setText("");
		txtnom.setText("");
		txtprenom.setText("");
		txtadrs.setText("");
		txtemail.setText("");
		txttele.setText("");
		txtpsw.setText("");
		txtcpsw.setText("");
		txtcin.requestFocus();		
	}

	public static boolean onlyDigits(String str, int n) 
    {        
		boolean t=false;
       for (int i = 0; i < n; i++) {         	
        	if (Character.isDigit(str.charAt(i))) { 
        		
                t= true; 
            } 
            else { 
                return false; 
            } 
        } 
        return t; 
    } 
}
