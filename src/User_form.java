import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;

import net.proteanit.sql.DbUtils;

import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



public class User_form extends JFrame {
	
	
	String user_nom,user_prenom;
	/*Les Compossent De La 1er Panel*/
	JLabel lbtr = new JLabel("Information De Rendez_Vous :");
	JLabel lbcin=new JLabel("Cin :");
	JLabel lbdaterd=new JLabel("Date Rendez Vous :");
	JLabel lbheur=new JLabel("heur Rendez Vous :");
	JLabel lblibelle=new JLabel("Libelle Rendez Vous :");	
	
	
	JLabel txtcin=new JLabel();	
	JComboBox cbheur=new JComboBox();
	JTextArea txtlibelle=new JTextArea();
	 JScrollPane jsliblle=new JScrollPane(txtlibelle);
	JButton btnAjouter =new JButton("Ajouter");
	JButton btnSupprimer =new JButton("Supprimer");

	
	JLabel lbnom=new JLabel();
	JLabel lbprenom=new JLabel();
	
	
	/*Les Compossent De La 2eme Panel*/
		
	JLabel lbtcr=new JLabel("Voir Votre Rendez-vous :");
	JLabel lbdatecr=new JLabel("choisissez une date :");
	JTable table=new JTable(){public boolean editCellAt(int row, int column, java.util.EventObject e) { return false;}} ;
	JButton btnAffrnd = new JButton("Tout Les Rendez_Vous");

	JPanel pnlgr = new JPanel();
	public User_form() throws IOException
	{
		
		
		/**Data Time Piker 1*/
		UtilDateModel model1 = new UtilDateModel();
		Date currentdate1= Date.from(Instant.now());
		model1.setValue(currentdate1);
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());			
		/**********************************************************************************************/
		/**Data Time Piker 2*/
		UtilDateModel model2 = new UtilDateModel();
		Date currentdate2= Date.from(Instant.now());
		model2.setValue(currentdate2);
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());			
		/**********************************************************************************************/
		
		
		
		JLabel lbuser;
		JPanel pnlmove = new JPanel();
		JPanel pnl=new JPanel();
		JPanel pnl1=new JPanel();					
		
		JPanel pnlgcr = new JPanel();
		
		JLabel close;
		JLabel minus;
		JLabel logout;
		JLabel lbtitre = new JLabel("User Form"); 		
		JButton btngr = new JButton("Gestion Des Rendez_Vous");
		JButton btngcr = new JButton("Consulter Votre Rendez_vous");
		
		 JScrollPane js=new JScrollPane(table);					
	   	 js.setBounds(50,200,600,300);		     
	     pnlgcr.add(js);
		
		cbheur.addItem("09:00");
		cbheur.addItem("10:00");
		cbheur.addItem("11:00");
		cbheur.addItem("12:00");
		cbheur.addItem("13:00");
		cbheur.addItem("14:00");
		cbheur.addItem("15:00");
		cbheur.addItem("16:00");
		//cbheur.setSelectedItem(null);
		
		
		
		//declaration de container
		getContentPane().setLayout(null);		
		pnlgr.setLayout(null);
		pnlgcr.setLayout(null);
		
		//Styles
		 ///img		
		
		/**/
		  BufferedImage image;		
		  image = ImageIO.read(new File("img/logout.png"));
		  Area clip = new Area( new Rectangle(0, 0, image.getWidth(), image.getHeight()) );
		  Area oval = new Area( new Ellipse2D.Double(0, 0, image.getWidth() - 1, image.getHeight() - 1) );
		  clip.subtract( oval );
		  Graphics g2d = image.createGraphics();
		  g2d.setClip( clip );
		  g2d.setColor( Color.decode("#0F0F33") );
		  g2d.fillRect(0, 0, image.getWidth(), image.getHeight());		  
		 /* */
		logout =new JLabel(new ImageIcon(image),JLabel.CENTER);
		ImageIcon icon = new ImageIcon("img/close_16.png");
		close =new JLabel(icon,JLabel.CENTER);
		ImageIcon icon1 = new ImageIcon("img/minus_16.png");
		minus =new JLabel(icon1,JLabel.CENTER);
		ImageIcon icon2 = new ImageIcon("img/user.png");
		lbuser =new JLabel(icon2,JLabel.CENTER);		
		
		
		
		
		 ///color
		
		pnl.setBackground(Color.decode("#A9F6FE"));
		pnlmove.setBackground(Color.decode("#A9F6FE"));
		pnl1.setBackground(Color.decode("#A9F6FE"));
		pnlgcr.setBackground(Color.decode("#0F0F33"));
		pnlgr.setBackground(Color.decode("#0F0F33"));
		
		getContentPane().setBackground(Color.decode("#0F0F33"));
		lbtitre.setOpaque(true);
		close.setOpaque(true);
		minus.setOpaque(true);
		lbuser.setOpaque(true);
		logout.setOpaque(true);
		
		close.setBackground(Color.decode("#A9F6FE"));
		minus.setBackground(Color.decode("#A9F6FE"));
		lbtitre.setForeground(Color.decode("#0F0F33"));
		lbtitre.setBackground(Color.decode("#A9F6FE"));
		lbuser.setBackground(Color.decode("#0F0F33"));
		
		lbtr.setForeground(Color.decode("#A9F6FE"));
		lbcin.setForeground(Color.decode("#A9F6FE"));
		txtcin.setForeground(Color.decode("#A9F6FE"));
		lbdaterd.setForeground(Color.decode("#A9F6FE"));
		lbheur.setForeground(Color.decode("#A9F6FE"));
		lblibelle.setForeground(Color.decode("#A9F6FE"));		
		lbnom.setForeground(Color.decode("#A9F6FE"));
		lbprenom.setForeground(Color.decode("#A9F6FE"));
		logout.setBackground(Color.decode("#0F0F33"));
		
		lbnom.setBackground(Color.red);
		lbprenom.setBackground(Color.pink);
			
		btngr.setBackground(Color.decode("#0F0F33"));
		btngr.setForeground(Color.decode("#A9F6FE"));
		btngcr.setBackground(Color.decode("#0F0F33"));
		btngcr.setForeground(Color.decode("#A9F6FE"));
		
		btnAjouter.setBackground(Color.decode("#A9F6FE"));
		btnAjouter.setForeground(Color.decode("#0F0F33"));		
		btnSupprimer.setBackground(Color.decode("#A9F6FE"));
		btnSupprimer.setForeground(Color.decode("#0F0F33"));		
	
	
		btnAffrnd.setBackground(Color.decode("#A9F6FE"));
		btnAffrnd.setForeground(Color.decode("#0F0F33"));
		
	
		
		
		lbtcr.setForeground(Color.decode("#A9F6FE"));	
		lbdatecr.setForeground(Color.decode("#A9F6FE"));
		table.setForeground(Color.decode("#0F0F33"));
		table.setBackground(Color.decode("#A9F6FE"));
		js.setForeground(Color.decode("#0F0F33"));
		js.setBackground(Color.decode("#A9F6FE"));
		 ///Font
		lbtitre.setFont(new Font("Serif", Font.BOLD, 25));
		lbtr.setFont(new Font("Serif", Font.BOLD, 20));
		lbcin.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		txtcin.setFont(new Font("Serif", Font.BOLD, 15));
		lbdaterd.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbheur.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lblibelle.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));		
		
		lbnom.setFont(new Font("Serif", Font.BOLD, 20));
		lbprenom.setFont(new Font("Serif", Font.BOLD, 20));
		
		lbtcr.setFont(new Font("Serif", Font.BOLD, 20));
		lbdatecr.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		
		btnAjouter.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
	
		btnSupprimer.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
	
	
		
		btngr.setFont(new Font("Roundo-SemiBold", Font.BOLD, 15));
		btngcr.setFont(new Font("Roundo-SemiBold", Font.BOLD, 14));
				
		
		 ///Cursor
		Cursor c = new Cursor(Cursor.HAND_CURSOR);
		close.setCursor(c);	
		minus.setCursor(c);
		btngr.setCursor(c);
		btngcr.setCursor(c);
		
		btnAjouter.setCursor(c);
		btnSupprimer.setCursor(c);
	
		
		btnAffrnd.setCursor(c);
		
		logout.setCursor(c);
		//X , Y
		lbtitre .setBounds(10,5,250,30);
		
		btngr .setBounds(5,250,245,50);
		btngcr .setBounds(5,310,245,50);
		
		
		close. setBounds(960,0,40,40);		
		minus. setBounds(920,0,40,40);
		lbuser. setBounds(80,50,100,100);
		logout.setBounds(95,500,70,70);
		
		lbnom   .setBounds(80,170,150,30);
		lbprenom.setBounds(80,200,150,30);
		
		pnlgr .setBounds(253,40,747,660);
		pnlgcr .setBounds(253,40,747,660);		
		pnl1  .setBounds(250,40,3,660);
		pnl   .setBounds(0,0,1000,40);
		
		lbtr       .setBounds(50,50,300,30);
		lbcin      .setBounds(150,150,115,30);
		lbdaterd   .setBounds(150,200,200,30);
		lbheur     .setBounds(150,250,200,30);
		lblibelle  .setBounds(150,300,200,30);	
		txtcin     .setBounds(300,150,250,30);
		datePicker1.setBounds(300,200,200,30);
		cbheur     .setBounds(300,250,250,30);
		jsliblle   .setBounds(300,300,250,100);
				
		btnAjouter  .setBounds(150,450,150,40);	
		btnSupprimer.setBounds(350,450,150,40);
	
					
		lbtcr      .setBounds(50,20,300,30);
		lbdatecr    .setBounds(50,100,150,30);
		datePicker2.setBounds(250,100,200,30);
		btnAffrnd .setBounds(50,550,200,40);
	
		//add Les Composent
		
		getContentPane().add(lbtitre);
		getContentPane().add(btngr);
		getContentPane().add(btngcr);		
		
		getContentPane().add(close);
		getContentPane().add(minus);
		getContentPane().add(lbuser);
		getContentPane().add(lbnom);
		getContentPane().add(lbprenom);
		getContentPane().add(logout);
		
		getContentPane().add(pnlmove);
		getContentPane().add(pnlgr);
		getContentPane().add(pnlgcr);
		getContentPane().add(pnl1);
		getContentPane().add(pnl);
		
		pnlgr.add(lbtr);
		pnlgr.add(lbcin);
		pnlgr.add(lbdaterd);
		pnlgr.add(lbheur);
		pnlgr.add(lblibelle);
		pnlgr.add(txtcin);
		pnlgr.add(datePicker1);
		pnlgr.add(cbheur);
		pnlgr.add(jsliblle);					
		pnlgr.add(btnAjouter);	
		pnlgr.add(btnSupprimer);	
								
		pnlgcr.add(lbtcr);
		pnlgcr.add(lbdatecr);
		pnlgcr.add(datePicker2);
		pnlgcr.add(btnAffrnd);

		
		
		
		
		getUserInfo();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		pnlmove.setBounds(0,250,5,50);	
		pnlgr.setVisible(true);
		pnlgcr.setVisible(false);
		setBounds(300, 300, 1000, 700);			
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Les evenement
		
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
		logout.addMouseListener(new MouseListener() {
			
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
				logout.setBackground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				logout.setBackground(Color.decode("#FD1A2D"));
						
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Authentification a=new Authentification();
			}
		});
		
		
    	btnAjouter.addMouseListener(new MouseListener() {
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
				btnAjouter.setBackground(Color.decode("#A9F6FE"));				
				btnAjouter.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAjouter.setBackground(Color.decode("#0F0F33"));				
				btnAjouter.setForeground(Color.decode("#A9F6FE"));
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
					  String date=datePicker1.getJFormattedTextField().getText();
					  String heur=(String)cbheur.getSelectedItem();
					  String libelle=txtlibelle.getText();
					  Calendar t=(Calendar)datePicker1.getJFormattedTextField().getValue();
					  
					  
					 System.out.println(t);
					  if(libelle.equals(""))
						{
							System.out.println("Les champs non valide !!");
							JOptionPane.showMessageDialog(null,"Les champs non valide !!");
							return;
						}
					 
					  if(exist(date)==false)
						{
							 
								    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
									System.out.println("successfully Connected To DataBase ");
									CallableStatement stmt  = cnx.prepareCall("{call check_date(?,?,?,?)}");
									
									stmt .setString(1, cin);
									stmt .setString(2, date);
									stmt .setString(3, heur);
									stmt .setString(4, libelle);
									stmt .execute();
									stmt .close();
						 
									JOptionPane.showMessageDialog(null,"Donneé est Ajouter");
									System.out.println("Donneé est Ajouter");
									clear();
									
								
							 
						  	
						}
					  else {
						  JOptionPane.showMessageDialog(null,"Rendez_Vous est Exist déja !!");
							System.out.println("Rendez_Vous est Exist déja !!");
							return;							
						} 
						
						
					}
				catch (SQLException e1) 
					{						
						JOptionPane.showMessageDialog(null,e1);
						return;
						//e1.printStackTrace();
					}
			}
		
		});
		btnSupprimer.addMouseListener(new MouseListener() {
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
				btnSupprimer.setBackground(Color.decode("#A9F6FE"));				
				btnSupprimer.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSupprimer.setBackground(Color.decode("#0F0F33"));				
				btnSupprimer.setForeground(Color.decode("#A9F6FE"));
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
					  String date=datePicker1.getJFormattedTextField().getText();
					  
					  
					  if(cin.equals("") || date.equals(""))
						{
							System.out.println("S.V.P pour Supprimes un Rendez_vouz choisir la date et l'heure  !!");
							JOptionPane.showMessageDialog(null,"S.V.P pour Supprimes un Rendez_vouz choisir la date et l'heure  !!");
							return;
						}
					  
					  if(exist(date)==true )
						{
						  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr?", "Confirm Dialog Box", 
	                                JOptionPane.YES_NO_OPTION);
	                        if (option == JOptionPane.YES_OPTION)
	                        {
	                        	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
								System.out.println("successfully Connected To DataBase ");
								java.sql.Statement stmt = cnx.createStatement();
								String cmd="delete from rendez_vous where cin='"+cin+"' and date_rnd='"+date+"'";								
								int n= stmt.executeUpdate(cmd);								
								if(n==1)
								{
									JOptionPane.showMessageDialog(null,"Donneé est Supprimer");
									System.out.println("Donneé est Supprimer");
									clear();
								}
								else {
									JOptionPane.showMessageDialog(null,"Error Data !!");
									System.out.println("Error Data !!");
									return;										
								}
								stmt.close();							 						  	
	                        }
						}
						  else {
							  JOptionPane.showMessageDialog(null,"Cin est ne Exist pas !!");
								System.out.println("Cin est ne Exist pas !!");
								return;
								
							} 
	                        			
								    
						
						
					}
				catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null,e1);
						System.out.println("problem on database !!");
						e1.printStackTrace();
					}
			}
		
		});
	
		
	
		btnAffrnd.addMouseListener(new MouseListener() {
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
				btnAffrnd.setBackground(Color.decode("#A9F6FE"));				
				btnAffrnd.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAffrnd.setBackground(Color.decode("#0F0F33"));				
				btnAffrnd.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
					showAllRendezVous();
					}
			});
		
	
		
		datePicker2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showRendezVous(datePicker2.getJFormattedTextField().getText());
				
				System.out.println(datePicker2.getJFormattedTextField().getText());
			}
		});
		
		
		btngr.addMouseListener(new MouseListener() {
			
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
				btngr.setBackground(Color.decode("#0F0F33"));				
				btngr.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btngr.setBackground(Color.decode("#A9F6FE"));				
				btngr.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlmove.setBounds(0,250,5,50);	
				pnlgr.setVisible(true);
				pnlgcr.setVisible(false);
				
				//showAllRendezVous();
				
			}
		});
		
		btngcr.addMouseListener(new MouseListener() {
			
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
				
				btngcr.setBackground(Color.decode("#0F0F33"));				
				btngcr.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btngcr.setBackground(Color.decode("#A9F6FE"));				
				btngcr.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlmove.setBounds(0,310,5,50);				
				pnlgr.setVisible(false);
				pnlgcr.setVisible(true);
				
			}
		});
	}
	
	boolean exist(String date ) {
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
				String cmd="select * from rendez_vous where date_rnd='"+date+"' and cin='"+Authentification.Currentlogin+"'";
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
		
		cbheur.setSelectedItem(null);
		txtlibelle.setText("");
		
	}
	
	public void showAllRendezVous() 
	{
		
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
			     System.out.println("cnx succes");
				 java.sql.Statement stmt = cnx.createStatement();  
				 String cmd="SELECT patient.cin,nom,prenom,date_rnd,heure,libelle_rnv FROM rendez_vous,patient WHERE rendez_vous.cin=patient.cin and patient.cin='"+Authentification.Currentlogin+"'";
			     ResultSet rs= stmt.executeQuery(cmd);					     				  
				 table.setModel(DbUtils.resultSetToTableModel(rs));	   
				 	
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
										
		} 
	
	public void showRendezVous(String date) 
		{
			
			
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
				     System.out.println("cnx succes");
					 java.sql.Statement stmt = cnx.createStatement();  
					 String cmd="SELECT patient.cin,nom,prenom,date_rnd,heure,libelle_rnv FROM rendez_vous,patient WHERE rendez_vous.cin=patient.cin and date_rnd='"+date+"' and patient.cin='"+Authentification.Currentlogin+"'";
				     ResultSet rs= stmt.executeQuery(cmd);					     				  
					 table.setModel(DbUtils.resultSetToTableModel(rs));
					 
				}
			catch (SQLException e1) 
				{
					System.out.println("problem on database !!");
					e1.printStackTrace();
				}
											
			} 
		
	public void getUserInfo() {
		txtcin.setText(Authentification.Currentlogin);		
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
				String cmd="select * from patient where cin='"+Authentification.Currentlogin+"'";
				ResultSet rs= stmt.executeQuery(cmd);
				
				while(rs.next())
				{
					System.out.println(Authentification.Currentlogin);
					System.out.println(rs.getRow());					
					lbnom.setText(rs.getString("nom"));
					lbprenom.setText(rs.getString("prenom"));
				}		
				stmt.close();			
				
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
	}
			
		

}

