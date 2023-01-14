import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;

import net.proteanit.sql.DbUtils;

import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Admin_form extends JFrame {
	/*Les Compossent De La 1er Panel*/
	JLabel lbtp = new JLabel("Information De Patient :");
	JLabel lbcin=new JLabel("Cin :");
	JLabel lbnom=new JLabel("Nom :");
	JLabel lbprenom=new JLabel("Prenom :");
	JLabel lbadrss=new JLabel("Adresse :");
	JLabel lbtele=new JLabel("Telephon :");
	JLabel lbemail=new JLabel("Email :");
	JLabel lbpsw=new JLabel("PassWord :");
	JLabel lbcher=new JLabel("Tapez Le CIN que vous Recherches :");
	
	JTextField txtcin=new JTextField();
	JTextField txtnom=new JTextField();
	JTextField txtprenom=new JTextField();
	JTextField txtadrs=new JTextField();
	JTextField txttele=new JTextField();
	JTextField txtemail=new JTextField();
	JTextField txtcher=new JTextField();
	JPasswordField txtpsw=new JPasswordField();
	JButton btnAjouter =new JButton("Ajouter");
	JButton btnSupprimer =new JButton("Supprimer");
	JButton btnModiffier =new JButton("Modiffier");
	JButton btnChercher =new JButton("Chercher");
	
	JTable tablep=new JTable(){public boolean editCellAt(int row, int column, java.util.EventObject e) { return false;}} ;
	/*Les Compossent De La 2eme Panel*/
	JLabel lbtu = new JLabel("Information De User :");
	JLabel lblogin=new JLabel("Login :");
	JLabel lbpswu=new JLabel("PassWord :");
	JLabel lbtype=new JLabel("Type_compte :");
	JLabel lbcheru=new JLabel("Tapez Le Login que vous Recherches :");
	
	JTextField txtlogin=new JTextField();
	JTextField txtpswu=new JTextField();	
	JComboBox cbtype=new JComboBox();
	JTextField txtcheru=new JTextField();
	JButton btnAjouteru =new JButton("Ajouter");
	JButton btnSupprimeru =new JButton("Supprimer");
	JButton btnModiffieru =new JButton("Modiffier");
	JButton btnChercheru =new JButton("Chercher");
	JTable tableu=new JTable(){public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}} ;
	/*Les Compossent De La 3eme Panel*/
	JLabel lbdate=new JLabel("choisissez une date :");
	JLabel lbtr=new JLabel("Voir les Rendez-vous :");
	JLabel lbcinr=new JLabel("choisissez une CIN :");
	JTable table=new JTable(){public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}} ;
	JButton btnAffrnd = new JButton("Tout Les Rendez_Vous");
	JButton btnSupprnd = new JButton("Supprimer Rendez_Vous");
	JComboBox cbcin=new JComboBox();
	
	JPanel pnlgr = new JPanel();
	JPanel pnlgp = new JPanel();	
	JPanel pnlgu = new JPanel();
	public Admin_form() throws IOException
	{
		
		/**Data Time Piker*/
		UtilDateModel model = new UtilDateModel();
		Date currentdate= Date.from(Instant.now());
		model.setValue(currentdate);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());			
		/**********************************************************************************************/
		/****rendez_vous table****/
		 JScrollPane js=new JScrollPane(table);					
	   	 js.setBounds(300,200,600,300);		     
	     pnlgr.add(js);
	     /****PATIENT table****/
	     JScrollPane js1=new JScrollPane(tablep);					
	   	 js1.setBounds(500,70,600,280);		     
	     pnlgp.add(js1);
	     /****USER table****/
	     JScrollPane js2=new JScrollPane(tableu);					
	   	 js2.setBounds(500,60,600,300);		     
	     pnlgu.add(js2);
	     
	     
		JLabel lbadmin;
		JLabel lbadmin1=new JLabel("Administrateur");
		JPanel pnlmove = new JPanel();
		JPanel pnl=new JPanel();
		JPanel pnl1=new JPanel();			
		

		JLabel showpsw;
		JLabel hidepsw;
		JLabel close;
		JLabel minus;
		JLabel logout;
		JLabel lbtitre = new JLabel("Admin Form"); 		
		JButton btngp = new JButton("Gestion Des Patient");
		JButton btngr = new JButton("Consulter Les Rendez_vous");
		JButton btngu = new JButton("Gestion Des User");
		
		cbtype.addItem("Admin");
		cbtype.addItem("User");
		cbtype.setSelectedItem(null);
		
		
		
		//declaration de container
		getContentPane().setLayout(null);
		pnlgp.setLayout(null);
		pnlgr.setLayout(null);
		pnlgu.setLayout(null);
		
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
		ImageIcon icon2 = new ImageIcon("img/admin.png");
		lbadmin =new JLabel(icon2,JLabel.CENTER);		
		ImageIcon icon4 = new ImageIcon("img/show_16.png");
		hidepsw =new JLabel(icon4,JLabel.CENTER);
		ImageIcon icon5 = new ImageIcon("img/hide_16.png");
		showpsw =new JLabel(icon5,JLabel.CENTER);
		 ///color
		
		pnl.setBackground(Color.decode("#A9F6FE"));
		pnlmove.setBackground(Color.decode("#A9F6FE"));
		pnl1.setBackground(Color.decode("#A9F6FE"));
		pnlgp.setBackground(Color.decode("#0F0F33"));
		pnlgr.setBackground(Color.decode("#0F0F33"));
		pnlgu.setBackground(Color.decode("#0F0F33"));
		getContentPane().setBackground(Color.decode("#0F0F33"));
		lbtitre.setOpaque(true);
		close.setOpaque(true);
		minus.setOpaque(true);
		lbadmin.setOpaque(true);
		logout.setOpaque(true);
		showpsw.setOpaque(true);
		hidepsw.setOpaque(true);
		close.setBackground(Color.decode("#A9F6FE"));
		minus.setBackground(Color.decode("#A9F6FE"));
		showpsw.setBackground(Color.decode("#A9F6FE"));
		hidepsw.setBackground(Color.decode("#A9F6FE"));
		lbtitre.setForeground(Color.decode("#0F0F33"));
		lbtitre.setBackground(Color.decode("#A9F6FE"));
		lbadmin.setBackground(Color.decode("#0F0F33"));
		logout.setBackground(Color.decode("#0F0F33"));
		lbadmin1.setForeground(Color.decode("#A9F6FE"));
		
		lbtp.setForeground(Color.decode("#A9F6FE"));
		lbcin.setForeground(Color.decode("#A9F6FE"));
		lbnom.setForeground(Color.decode("#A9F6FE"));
		lbprenom.setForeground(Color.decode("#A9F6FE"));
		lbadrss.setForeground(Color.decode("#A9F6FE"));
		lbtele.setForeground(Color.decode("#A9F6FE"));
		lbemail.setForeground(Color.decode("#A9F6FE"));
		lbpsw.setForeground(Color.decode("#A9F6FE"));
		lbcher.setForeground(Color.decode("#A9F6FE"));	
		
		lbtu.setForeground(Color.decode("#A9F6FE"));
		lblogin.setForeground(Color.decode("#A9F6FE"));
		lbpswu.setForeground(Color.decode("#A9F6FE"));
		lbtype.setForeground(Color.decode("#A9F6FE"));
		lbcheru.setForeground(Color.decode("#A9F6FE"));
		
		btngp.setBackground(Color.decode("#0F0F33"));
		btngp.setForeground(Color.decode("#A9F6FE"));		
		btngr.setBackground(Color.decode("#0F0F33"));
		btngr.setForeground(Color.decode("#A9F6FE"));
		btngu.setBackground(Color.decode("#0F0F33"));
		btngu.setForeground(Color.decode("#A9F6FE"));
		
		btnAjouter.setBackground(Color.decode("#A9F6FE"));
		btnAjouter.setForeground(Color.decode("#0F0F33"));		
		btnSupprimer.setBackground(Color.decode("#A9F6FE"));
		btnSupprimer.setForeground(Color.decode("#0F0F33"));		
		btnModiffier.setBackground(Color.decode("#A9F6FE"));
		btnModiffier.setForeground(Color.decode("#0F0F33"));
		btnChercher.setBackground(Color.decode("#A9F6FE"));
		btnChercher.setForeground(Color.decode("#0F0F33"));
		
		btnAjouteru.setBackground(Color.decode("#A9F6FE"));
		btnAjouteru.setForeground(Color.decode("#0F0F33"));		
		btnSupprimeru.setBackground(Color.decode("#A9F6FE"));
		btnSupprimeru.setForeground(Color.decode("#0F0F33"));		
		btnModiffieru.setBackground(Color.decode("#A9F6FE"));
		btnModiffieru.setForeground(Color.decode("#0F0F33"));
		btnChercheru.setBackground(Color.decode("#A9F6FE"));
		btnChercheru.setForeground(Color.decode("#0F0F33"));
		
		lbtr.setForeground(Color.decode("#A9F6FE"));
		lbdate.setForeground(Color.decode("#A9F6FE"));
		lbcinr.setForeground(Color.decode("#A9F6FE"));
		table.setForeground(Color.decode("#0F0F33"));
		table.setBackground(Color.decode("#A9F6FE"));
		tableu.setForeground(Color.decode("#0F0F33"));
		tableu.setBackground(Color.decode("#A9F6FE"));
		tablep.setForeground(Color.decode("#0F0F33"));
		tablep.setBackground(Color.decode("#A9F6FE"));
		
		 ///Font
		lbtitre.setFont(new Font("Serif", Font.BOLD, 25));
		lbtp.setFont(new Font("Serif", Font.BOLD, 20));
		lbcin.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbnom.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbprenom.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbemail.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbtele.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbadrss.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbpsw.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbcher.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		
		lbadmin1.setFont(new Font("Serif", Font.BOLD, 20));
		
		lbtu.setFont(new Font("Serif", Font.BOLD, 20));
		lblogin.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbpswu.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbtype.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbcheru.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		
		btnAjouter.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnModiffier.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnSupprimer.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnChercher.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		
		btnAjouteru.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnModiffieru.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnSupprimeru.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btnChercheru.setFont(new Font("Roundo-SemiBold", Font.BOLD, 20));
		btngp.setFont(new Font("Roundo-SemiBold", Font.BOLD,16 ));
		btngr.setFont(new Font("Roundo-SemiBold", Font.BOLD, 15));
		btngu.setFont(new Font("Roundo-SemiBold", Font.BOLD, 16));
		
		lbtr.setFont(new Font("Serif", Font.BOLD, 20));
		lbdate.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		lbcinr.setFont(new Font("Roundo-SemiBold", Font.PLAIN, 15));
		btnAffrnd.setBackground(Color.decode("#A9F6FE"));
		btnAffrnd.setForeground(Color.decode("#0F0F33"));
		btnSupprnd.setBackground(Color.decode("#A9F6FE"));
		btnSupprnd.setForeground(Color.decode("#0F0F33"));
		 ///Cursor
		Cursor c = new Cursor(Cursor.HAND_CURSOR);
		close.setCursor(c);	
		minus.setCursor(c);
		showpsw.setCursor(c);
		hidepsw.setCursor(c);
		btngr.setCursor(c);
		btngp.setCursor(c);
		btngu.setCursor(c);
		btnAjouter.setCursor(c);
		btnModiffier.setCursor(c);
		btnSupprimer.setCursor(c);
		btnChercher.setCursor(c);
		
		btnAjouteru.setCursor(c);
		btnModiffieru.setCursor(c);
		btnSupprimeru.setCursor(c);
		btnChercheru.setCursor(c);
		
		btnAffrnd.setCursor(c);
		btnSupprnd.setCursor(c);
		
		logout.setCursor(c);
		//X , Y
		lbtitre .setBounds(10,5,250,30);
		
		btngp .setBounds(5,250,245,50);
		btngr .setBounds(5,310,245,50);
		btngu .setBounds(5,370,245,50);
		
		close. setBounds(1360,0,40,40);		
		minus. setBounds(1320,0,40,40);
		lbadmin. setBounds(80,50,100,100);
		lbadmin1   .setBounds(65,170,150,30);
		logout.setBounds(95,500,70,70);
		showpsw. setBounds(450,370,40,30);
		hidepsw. setBounds(450,370,40,30);
		
		
		pnlgp .setBounds(253,40,1147,660);
		pnlgr .setBounds(253,40,1147,660);
		pnlgu .setBounds(253,40,1147,660);
		pnl1  .setBounds(250,40,3,660);
		pnl   .setBounds(0,0,1400,40);
		
		lbtp    .setBounds(50,5,300,30);
		lbcin   .setBounds(50,70,115,30);
		lbnom   .setBounds(50,120,115,30);
		lbprenom.setBounds(50,170,115,30);
		lbadrss .setBounds(50,220,115,30);
		lbtele  .setBounds(50,270,115,30);
		lbemail .setBounds(50,320,115,30);
		lbpsw   .setBounds(50,370,115,30);
		lbcher   .setBounds(50,550,250,30);
		txtcin   .setBounds(200,70,250,30);
		txtnom   .setBounds(200,120,250,30);
		txtprenom.setBounds(200,170,250,30);
		txtadrs  .setBounds(200,220,250,30);
		txttele  .setBounds(200,270,250,30);
		txtemail .setBounds(200,320,250,30);
		txtpsw   .setBounds(200,370,250,30);
		txtcher  .setBounds(300,550,230,30);
		btnAjouter  .setBounds(50,450,150,40);
		btnModiffier.setBounds(250,450,150,40);
		btnSupprimer.setBounds(450,450,150,40);
		btnChercher .setBounds(550,550,150,30);
		
		lbtu      .setBounds(50,20,300,30);
		lblogin   .setBounds(50,100,115,30);
		lbpswu    .setBounds(50,150,115,30);
		lbtype    .setBounds(50,200,115,30);
		lbcheru   .setBounds(50,500,260,30);
		txtlogin  .setBounds(200,100,250,30);
		txtpswu   .setBounds(200,150,250,30);
		cbtype    .setBounds(200,200,250,30);
		txtcheru  .setBounds(320,500,220,30);
		//btnAjouteru  .setBounds(50,400,150,40);
		btnModiffieru.setBounds(70,400,150,40);
		btnSupprimeru.setBounds(270,400,150,40);
		btnChercheru .setBounds(550,500,150,30);
		
		
		lbtr      .setBounds(300,20,300,30);
		lbdate    .setBounds(300,100,150,30);
		lbcinr    .setBounds(300,150,150,30);
		datePicker.setBounds(450,100,200,30);
		cbcin     .setBounds(450,150,100,30);
		btnAffrnd .setBounds(300,550,200,40);
		btnSupprnd .setBounds(550,550,200,40);
		//add Les Composent
		
		getContentPane().add(lbtitre);
		getContentPane().add(btngp);
		getContentPane().add(btngr);
		getContentPane().add(btngu);
		
		getContentPane().add(close);
		getContentPane().add(minus);
		getContentPane().add(lbadmin);
		getContentPane().add(lbadmin1);
		getContentPane().add(logout);
		
		getContentPane().add(pnlmove);
		getContentPane().add(pnlgu);
		getContentPane().add(pnlgp);
		getContentPane().add(pnlgr);
		getContentPane().add(pnl1);
		getContentPane().add(pnl);
		
		pnlgp.add(lbtp);
		pnlgp.add(lbcin);
		pnlgp.add(lbnom);
		pnlgp.add(lbprenom);
		pnlgp.add(lbadrss);
		pnlgp.add(lbtele);
		pnlgp.add(lbemail);
		pnlgp.add(lbpsw);
		pnlgp.add(lbcher);
		pnlgp.add(txtcin);
		pnlgp.add(txtnom);
		pnlgp.add(txtprenom);
		pnlgp.add(txtadrs);
		pnlgp.add(txttele);
		pnlgp.add(txtemail);
		pnlgp.add(txtpsw);
		pnlgp.add(showpsw);
		pnlgp.add(hidepsw);
		pnlgp.add(txtcher);
		pnlgp.add(btnAjouter);
		pnlgp.add(btnModiffier);
		pnlgp.add(btnSupprimer);
		pnlgp.add(btnChercher);
		
		
		pnlgu.add(lbtu);
		pnlgu.add(lblogin);
		pnlgu.add(lbpswu);
		pnlgu.add(lbtype);
		pnlgu.add(lbcheru);
		pnlgu.add(txtlogin);
		pnlgu.add(txtpswu);
		pnlgu.add(cbtype);
		pnlgu.add(txtcheru);
		pnlgu.add(btnModiffieru);
		pnlgu.add(btnSupprimeru);
		pnlgu.add(btnChercheru);
		//pnlgu.add(btnAjouteru);
		
		pnlgr.add(lbtr);
		pnlgr.add(lbdate);
		pnlgr.add(lbcinr);
		pnlgr.add(cbcin);
		pnlgr.add(datePicker);
		pnlgr.add(btnAffrnd);
		pnlgr.add(btnSupprnd);
		
		showpsw.setVisible(false);
		pnlgu.setVisible(false);
		pnlgr.setVisible(false);
		
		getAllcin();
		showAllpatient();
		pnlgp.setVisible(true);
		pnlmove.setBounds(0,250,5,50);
		setBounds(300, 300, 1400, 700);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
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
		
		cbcin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showRendezVousbyCin(datePicker.getJFormattedTextField().getText(), (String)cbcin.getSelectedItem());
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
					  String nom=txtnom.getText();
					  String prenom=txtprenom.getText();
					  String adrss=txtadrs.getText();
					  String tele=txttele.getText();
					  String email=txtemail.getText();
					  String psw=new String(txtpsw.getPassword());
					  
					  
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
					  
					  if(psw.equals("") || cin.equals("") || nom.equals("") || prenom.equals("") || adrss.equals("") || tele.equals("") || email.equals(""))
						{
							System.out.println("Les champs non valide !!");
							JOptionPane.showMessageDialog(null,"Les champs non valide !!");
							return;
						}
					 
					  
					  if(exist(cin)==false)
						{
							 
								    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
									System.out.println("successfully Connected To DataBase ");
									java.sql.Statement stmt = cnx.createStatement();
									String cmd1="insert into patient values ('"+cin+"','"+nom+"','"+prenom+"','"+adrss+"','"+tele+"','"+email+"')";
									String cmd2="insert into users(login,psw,type_cmp) values ('"+cin+"','"+psw+"','user')";
									int n1= stmt.executeUpdate(cmd1);
									int n2= stmt.executeUpdate(cmd2);
									if(n1==1 && n2==1)
									{
										JOptionPane.showMessageDialog(null,"Donneé est Ajouter Avec succès");
										System.out.println("Donneé est Ajouter Avec succès");
										showAllpatient();
										clear();
									}
									else {
										JOptionPane.showMessageDialog(null,"Error Data !!");
										System.out.println("Error Data !!");
										return;
										
									}
									stmt.close();
							 
						  	
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
					
					  
					  if(cin.equals(""))
						{
							System.out.println("Tapez Le Cin que vous Supprimes !!");
							JOptionPane.showMessageDialog(null,"Tapez Le Cin que vous Supprimes !!");
							return;
						}
					  
					  if(exist(cin)==true )
						{
						  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr?", "Confirm Dialog Box", 
	                                JOptionPane.YES_NO_OPTION);
	                        if (option == JOptionPane.YES_OPTION)
	                        {
	                        	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
								System.out.println("successfully Connected To DataBase ");
								java.sql.Statement stmt = cnx.createStatement();
								String cmd1="delete from patient where cin='"+cin+"'";
								String cmd2="delete from users where login='"+cin+"'";
								int n1= stmt.executeUpdate(cmd1);
								int n2= stmt.executeUpdate(cmd2);
								if(n1==1 && n2==1)
								{
									JOptionPane.showMessageDialog(null,"Donneé est Supprimer Avec succès");
									System.out.println("Donneé est Supprimer Avec succès");
									showAllpatient();
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
						System.out.println("problem on database !!");
						e1.printStackTrace();
					}
			}
		
		});
		btnModiffier.addMouseListener(new MouseListener() {
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
				btnModiffier.setBackground(Color.decode("#A9F6FE"));				
				btnModiffier.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnModiffier.setBackground(Color.decode("#0F0F33"));				
				btnModiffier.setForeground(Color.decode("#A9F6FE"));
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
					  if(cin.equals("") || nom.equals("") || prenom.equals("") || adrss.equals("") || tele.equals("") || email.equals(""))
						{
							System.out.println("Les champs non valide !!");
							JOptionPane.showMessageDialog(null,"Les champs non valide !!");
							return;
						}
					  
					  
					  
					  if(exist(cin)==true)
						{
							 
						  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr?", "Confirm Dialog Box", 
	                                JOptionPane.YES_NO_OPTION);
	                        if (option == JOptionPane.YES_OPTION)
	                        {
	                        	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
								System.out.println("successfully Connected To DataBase ");
								java.sql.Statement stmt = cnx.createStatement();
								String cmd="update patient set nom='"+nom+"',prenom='"+prenom+"',adresse='"+adrss+"',tele='"+tele+"',email='"+email+"' where cin='"+cin+"'";								
								int n= stmt.executeUpdate(cmd);								
								if(n==1)
								{
									JOptionPane.showMessageDialog(null,"Donneé est Modiffier Avec succès");
									System.out.println("Donneé est Modiffier Avec succès");
									showAllpatient();
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
						System.out.println("problem on database !!");
						e1.printStackTrace();
					}
			}
		
		});
		btnChercher.addMouseListener(new MouseListener() {
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
				btnChercher.setBackground(Color.decode("#A9F6FE"));				
				btnChercher.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnChercher.setBackground(Color.decode("#0F0F33"));				
				btnChercher.setForeground(Color.decode("#A9F6FE"));
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
					clear();
					String cin=txtcher.getText();					  					 
					  if(cin.equals(""))
						{
							System.out.println("Tapez Le Cin que vous recherches !!");
							JOptionPane.showMessageDialog(null,"Tapez Le Cin que vous recherches !!");
							return;
						}					  
					  if(exist(cin)==true)
						{
							
						    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
							System.out.println("successfully Connected To DataBase ");
							java.sql.Statement stmt = cnx.createStatement();
							String cmd="select * from patient where cin='"+cin+"'";							
							ResultSet rs = stmt.executeQuery(cmd);							
							while(rs.next())
							{
								txtcin.setText(rs.getString("cin"));
								txtnom.setText(rs.getString("nom"));
								txtprenom.setText(rs.getString("prenom"));
								txtadrs.setText(rs.getString("adresse"));
								txttele.setText(rs.getString("tele"));
								txtemail.setText(rs.getString("email"));																							
							}
							
																																												
							stmt.close();							 						  	
						}
					  else {
						  JOptionPane.showMessageDialog(null,"Cin est ne Exist pas !!");
							System.out.println("Cin est ne Exist pas !!");
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
		
		btnSupprimeru.addMouseListener(new MouseListener() {
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
				btnSupprimeru.setBackground(Color.decode("#A9F6FE"));				
				btnSupprimeru.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSupprimeru.setBackground(Color.decode("#0F0F33"));				
				btnSupprimeru.setForeground(Color.decode("#A9F6FE"));
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
					  String login=txtlogin.getText();										  
					  if(login.equals(""))
						{
							System.out.println("Tapez Le login que vous Supprimes !!");
							JOptionPane.showMessageDialog(null,"Tapez Le Login que vous Supprimes !!");
							return;
						}					  
					  if(existu(login)==true )
						{
						  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr?", "Confirm Dialog Box", 
	                                JOptionPane.YES_NO_OPTION);
	                        if (option == JOptionPane.YES_OPTION)
	                        {
	                        	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
								System.out.println("successfully Connected To DataBase ");
								java.sql.Statement stmt = cnx.createStatement();
								String cmd1="delete from patient where cin='"+login+"'";
								String cmd2="delete from users where login='"+login+"'";
								int n1= stmt.executeUpdate(cmd1);
								int n2= stmt.executeUpdate(cmd2);
								if(n1==1 && n2==1)
								{
									JOptionPane.showMessageDialog(null,"Donneé est Supprimer");
									System.out.println("Donneé est Supprimer");
									showAlluser();
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
						System.out.println("problem on database !!");
						e1.printStackTrace();
					}
			}
		
		});
		btnModiffieru.addMouseListener(new MouseListener() {
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
				btnModiffieru.setBackground(Color.decode("#A9F6FE"));				
				btnModiffieru.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnModiffieru.setBackground(Color.decode("#0F0F33"));				
				btnModiffieru.setForeground(Color.decode("#A9F6FE"));
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
					  String login=txtlogin.getText();
					  String psw=txtpswu.getText();
					  String type_cmp=(String)cbtype.getSelectedItem();					  
					  
					  
					  if(psw.equals("") || login.equals("") || type_cmp.equals("") )
						{
							System.out.println("Les champs non valide !!");
							JOptionPane.showMessageDialog(null,"Les champs non valide !!");
							return;
						}
					  
					  if(existu(login)==true)
						{
							 
						  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr?", "Confirm Dialog Box", 
	                                JOptionPane.YES_NO_OPTION);
	                        if (option == JOptionPane.YES_OPTION)
	                        {
	                        	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
								System.out.println("successfully Connected To DataBase ");
								java.sql.Statement stmt = cnx.createStatement();								
								String cmd="update users set psw='"+psw+"',type_cmp='"+type_cmp+"' where login='"+login+"'";								
								int n= stmt.executeUpdate(cmd);
								if(n==1)
								{
									JOptionPane.showMessageDialog(null,"Donneé est Modiffier");
									System.out.println("Donneé est Modiffier");
									showAlluser();
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
						  JOptionPane.showMessageDialog(null,"Login est ne Exist pas !!");
							System.out.println("Login est ne Exist pas !!");
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
		btnChercheru.addMouseListener(new MouseListener() {
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
				btnChercheru.setBackground(Color.decode("#A9F6FE"));				
				btnChercheru.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnChercheru.setBackground(Color.decode("#0F0F33"));				
				btnChercheru.setForeground(Color.decode("#A9F6FE"));
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
					clear();
					String login=txtcheru.getText();					  					 
					  if(login.equals(""))
						{
							System.out.println("Tapez Le Login que vous recherches !!");
							JOptionPane.showMessageDialog(null,"Tapez Le Login que vous recherches !!");
							return;
						}					  
					  if(existu(login)==true)
						{
							
						    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
							System.out.println("successfully Connected To DataBase ");
							java.sql.Statement stmt = cnx.createStatement();
							String cmd="select * from users where login='"+login+"'";							
							ResultSet rs = stmt.executeQuery(cmd);							
							while(rs.next())
							{
								txtlogin.setText(rs.getString("login"));
								txtpswu.setText(rs.getString("psw"));
								System.out.println(rs.getString("type_cmp"));
								if(rs.getString("type_cmp").equals("Admin"))
									{
										cbtype.setSelectedIndex(0);
									}
								else {
									cbtype.setSelectedIndex(1);
								}
								
																														
							}
							
																																												
							stmt.close();							 						  	
						}
					  else {
						  JOptionPane.showMessageDialog(null,"Login est ne Exist pas !!");
							System.out.println("Login est ne Exist pas !!");
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
		/*btnAjouteru.addMouseListener(new MouseListener() {
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
				btnAjouteru.setBackground(Color.decode("#A9F6FE"));				
				btnAjouteru.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAjouteru.setBackground(Color.decode("#0F0F33"));				
				btnAjouteru.setForeground(Color.decode("#A9F6FE"));
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
					  String login=txtlogin.getText();
					  String psw=txtpswu.getText();
					  String type_cmp=(String)cbtype.getSelectedItem();					  
					  
					  
					  if(psw.equals("") || login.equals("") || type_cmp.equals("") )
						{
							System.out.println("Les champs non valide !!");
							JOptionPane.showMessageDialog(null,"Les champs non valide !!");
							return;
						}
					  
					  if(existu(login)==false)
						{
							 
								    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
									System.out.println("successfully Connected To DataBase ");
									java.sql.Statement stmt = cnx.createStatement();									
									String cmd="insert into users(login,psw,type_cmp) values ('"+login+"','"+psw+"','"+type_cmp+"')";									
									int n= stmt.executeUpdate(cmd);
									if(n==1)
									{
										JOptionPane.showMessageDialog(null,"Donneé est Ajouter");
										System.out.println("Donneé est Ajouter");
										clear();
									}
									else {
										JOptionPane.showMessageDialog(null,"Error Data !!");
										System.out.println("Error Data !!");
										return;
										
									}
									stmt.close();							 						  
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
		
		});*/

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
					cbcin.setSelectedIndex(0);
					showAllRendezVous();
					
					}
			});
		
		btnSupprnd.addMouseListener(new MouseListener() {
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
				btnSupprnd.setBackground(Color.decode("#A9F6FE"));				
				btnSupprnd.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSupprnd.setBackground(Color.decode("#0F0F33"));				
				btnSupprnd.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
					  String cin=(String)cbcin.getSelectedItem();
					  String date=datePicker.getJFormattedTextField().getText();
					  if(cin.equals(""))
						{
							System.out.println("Choisissez le Cin que vous souhaitez supprimer !!");
							JOptionPane.showMessageDialog(null,"Choisissez le Cin que vous souhaitez supprimer !!");
							return;
						}					  
					  if(table.getRowCount() !=0 )
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
									JOptionPane.showMessageDialog(null,"rendez_vous est Supprimer");
									System.out.println("rendez_vous est Supprimer");
									cbcin.setSelectedItem("");
									showAllRendezVous();
																		
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
							  JOptionPane.showMessageDialog(null,"rendez_vous est ne Exist pas !!");
								System.out.println("rendez_vous est ne Exist pas !!");
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
		
		datePicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbcin.setSelectedItem("");
				showRendezVous(datePicker.getJFormattedTextField().getText());
				
				//System.out.println(datePicker.getJFormattedTextField().getText());
			}
		});
		
		btngp.addMouseListener(new MouseListener() {
			
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
				
				btngp.setBackground(Color.decode("#0F0F33"));				
				btngp.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btngp.setBackground(Color.decode("#A9F6FE"));				
				btngp.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlmove.setBounds(0,250,5,50);
				pnlgp.setVisible(true);
				pnlgr.setVisible(false);
				pnlgu.setVisible(false);
				showAllpatient();
				
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
				pnlmove.setBounds(0,310,5,50);
				pnlgp.setVisible(false);
				pnlgu.setVisible(false);
				pnlgr.setVisible(true);
				//showAllRendezVous();
				
			}
		});
		
		btngu.addMouseListener(new MouseListener() {
			
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
				
				btngu.setBackground(Color.decode("#0F0F33"));				
				btngu.setForeground(Color.decode("#A9F6FE"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btngu.setBackground(Color.decode("#A9F6FE"));				
				btngu.setForeground(Color.decode("#0F0F33"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlmove.setBounds(0,370,5,50);
				pnlgu.setVisible(true);
				pnlgr.setVisible(false);
				pnlgp.setVisible(false);
				showAlluser();
				
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
	
	boolean existu(String login) {
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
				String cmd="select * from users where login='"+login+"'";
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
		txtcin.requestFocus();	
		
		txtlogin.setText("");
		txtpswu.setText("");
		cbtype.setSelectedItem(null);
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
				 String cmd="SELECT patient.cin,nom,prenom,date_rnd,heure,libelle_rnv FROM rendez_vous,patient WHERE rendez_vous.cin=patient.cin";
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
					 String cmd="SELECT patient.cin,nom,prenom,date_rnd,heure,libelle_rnv FROM rendez_vous,patient WHERE rendez_vous.cin=patient.cin and date_rnd='"+date+"'";
				     ResultSet rs= stmt.executeQuery(cmd);					     				  
					 table.setModel(DbUtils.resultSetToTableModel(rs));
					 
				}
			catch (SQLException e1) 
				{
					System.out.println("problem on database !!");
					e1.printStackTrace();
				}											
			} 

	public void showRendezVousbyCin(String date,String cin) 
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
				 String cmd="SELECT patient.cin,nom,prenom,date_rnd,heure,libelle_rnv FROM rendez_vous,patient WHERE rendez_vous.cin=patient.cin and date_rnd='"+date+"' and patient.cin='"+cin+"'";
			     ResultSet rs= stmt.executeQuery(cmd);					     				  
				 table.setModel(DbUtils.resultSetToTableModel(rs));
				 
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
										
		}
	
	public void showAllpatient() 
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
				 String cmd="select * from patient";
			     ResultSet rs= stmt.executeQuery(cmd);					     				  
				 tablep.setModel(DbUtils.resultSetToTableModel(rs));	   
				 	
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
										
		} 
		
	public void showAlluser() 
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
				 String cmd="select * from users";
			     ResultSet rs= stmt.executeQuery(cmd);					     				  
				 tableu.setModel(DbUtils.resultSetToTableModel(rs));	   
				 	
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
										
		} 
		
	public void getAllcin() 
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
			cbcin.addItem("");
			try 
			{
				
				 Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pnt_Rnd","root","1234");
			     System.out.println("cnx succes");
				 java.sql.Statement stmt = cnx.createStatement();  
				 String cmd="select cin from patient";
			     ResultSet rs= stmt.executeQuery(cmd);	
			     while(rs.next())
			     {
			    	 cbcin.addItem(rs.getString("cin"));
			     }
				 	   
				 	
			}
		catch (SQLException e1) 
			{
				System.out.println("problem on database !!");
				e1.printStackTrace();
			}
										
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

