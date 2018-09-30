package mainFrame;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
/**
 * 
 * @author Christian J. Lorenzo
 *This class creates a Graphical Use Interface with a Calendar, Alarms and a small frame when the alarms are activated.
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = -9150977283619795205L;
	//class Variables
	JPanel notificationPnl;
	static JLabel lblMonth, lblYear;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox<String> cmbYear;
	static Container pane;
	static DefaultTableModel mtblCalendar; 
	static JScrollPane stblCalendar; 
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	static JLabel alarmLabel = new JLabel("Alarm Name: ");  
	static JTextField name = new JTextField("Alarm");
	static JLabel startLabel=new JLabel("Start Time: ");
	static JLabel startDate=new JLabel("Date (MM/dd/yyyy): ");
	static JLabel endDate=new JLabel("Date (MM/dd/yyyy): ");
	static Calendar calendar;
	static SimpleDateFormat sdf;
	static String actualTime;
	static JLabel intLabel=new JLabel("Repeat Interval(Min): ");
	static JButton save= new JButton();
	static JTextField hourText= new JTextField();
	static JTextField dayText= new JTextField();
	static Timer timer;
	static JPanel contentPane;
	static JSpinner startTime;
	static JSpinner endTime;
	static JTextField txtAlarm;
	static JTextField txtclock;
	private SoundManager soundManager= new SoundManager();
	private JComboBox<?> interval;
	private JSpinner dateSpinner1;
	private JSpinner dateSpinner2;
	private String startDateChanged;
	private String endDateChanged;
	private String startTimeChanged;
	private String endTimeChanged;
	private String snooze="";
	private String snooze2="";
	private int counter=0;
	private int counter2=0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton nbtn1;
	private JButton nbtn2;
	private JButton nbtn3;
	private JFrame pop= new JFrame("Notification");
	private Alarms[] alarms= new Alarms[30];
	private String currentTime="";
	private String currentDate="";
	private String password;
	private String passwordVer;
	private String[]soundsName= {"redAlert","tornadoSiren","laserSound","explosionSound","nyanCat"};
	private JComboBox soundBox;
	private JTextField txtDisplay;
	@SuppressWarnings("unchecked")
	public GUI() {

		//Creacion de las alarmas
		for(int i=0;i<30;i++){
			alarms[i]= new Alarms();
		}
		//Theme 
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		do{
			Object[] options1 = { "Save", "Continue Without Password"};

			JPanel panel = new JPanel();
			panel.add(new JLabel("Enter a Password"));
			JTextField textField = new JTextField(10);
			panel.add(textField);

			int result = JOptionPane.showOptionDialog(null, panel, "Password Selection",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options1, null);
			if (result == JOptionPane.YES_OPTION){
				password=textField.getText();
			}
			if(result==JOptionPane.NO_OPTION){
				password="";
			}
		}while(password==null);
		//Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 554);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Alarm Name Label
		JLabel lblAlarmName = new JLabel("Alarm Name:");
		lblAlarmName.setBounds(410, 22, 106, 20);
		contentPane.add(lblAlarmName);

		txtAlarm = new JTextField();
		txtAlarm.setText("");
		txtAlarm.setBounds(500, 22, 86, 20);
		contentPane.add(txtAlarm);
		txtAlarm.setColumns(10);

		//Start Date Label
		JLabel lblStartDate = new JLabel("Start Date (dd/MM/yyyy):");
		lblStartDate.setBounds(354, 53, 162, 14);
		contentPane.add(lblStartDate);

		//End Date Label
		JLabel lblNewLabel = new JLabel("End Date (dd/MM/yyyy):");
		lblNewLabel.setBounds(354, 78, 162, 14);
		contentPane.add(lblNewLabel);

		//Start Time Label
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setBounds(410, 114, 106, 14);
		contentPane.add(lblStartTime);

		//End Time Label
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(410, 139, 106, 14);
		contentPane.add(lblEndTime);

		//Interval Label
		JLabel lblRepeatInterval = new JLabel("Repeat Interval (Min):");
		lblRepeatInterval.setBounds(410, 192, 132, 14);
		contentPane.add(lblRepeatInterval);

		//Sound Label
		JLabel lblSound = new JLabel("Sound:");
		lblSound.setBounds(420, 219, 80, 14);
		contentPane.add(lblSound);

		//Snooze Label
		JLabel Snooze = new JLabel("Snooze Password:");
		Snooze.setBounds(410, 260, 106, 14);
		contentPane.add(Snooze);



		//Save Button
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.BLACK);
		btnSave.setBounds(573, 354, 89, 23);
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent a) {
				do{
					Object[] options1 = { "Confirm", "Confirm(Only Works If you do not have Password)"};

					JPanel panel = new JPanel();
					panel.add(new JLabel("Please enter Your Password"));
					JTextField textField = new JTextField(10);
					panel.add(textField);

					int result = JOptionPane.showOptionDialog(null, panel, "Password Confirmation",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options1, null);
					if (result == JOptionPane.YES_OPTION){
						passwordVer=textField.getText();
					}
					if(result==JOptionPane.NO_OPTION){
						passwordVer="";
					}

				}while(!passwordVer.equals(password));
				counter=0;
				btn1.setVisible(true);
				btn2.setVisible(true);
				btn3.setVisible(true);

				for(int i =0; i<alarms.length-1;i++){
					if(alarms[i].getName().equals("Empty")){
						alarms[i].setName(txtAlarm.getText());
						if(startDateChanged==null){
							Calendar cal=Calendar.getInstance();
							SimpleDateFormat sdf= new SimpleDateFormat("MMM dd yyyy");
							startDateChanged=""+sdf.format(cal.getTimeInMillis());}
						alarms[i].setStartDate(startDateChanged);

						if(endDateChanged==null){
							Calendar cal=Calendar.getInstance();
							SimpleDateFormat sdf= new SimpleDateFormat("MMM dd yyyy");
							endDateChanged=""+sdf.format(cal.getTimeInMillis());
						}
						alarms[i].setEndDate(endDateChanged);

						if(startTimeChanged==null){
							Calendar cal=Calendar.getInstance();
							SimpleDateFormat sdf= new SimpleDateFormat("kk:mm");
							startTimeChanged=""+sdf.format(cal.getTimeInMillis());
						}

						alarms[i].setStartTime(startTimeChanged);

						if(endTimeChanged==null){
							Calendar cal=Calendar.getInstance();
							SimpleDateFormat sdf= new SimpleDateFormat("kk:mm");
							endTimeChanged=""+sdf.format(cal.getTimeInMillis());
						}
						alarms[i].setEndTime(endTimeChanged);
						if(interval.getSelectedIndex()==0){
							alarms[i].setInterval(00);
						}
						else
							alarms[i].setInterval(interval.getSelectedIndex()*5);

						if(soundBox.getSelectedIndex()==0){
							alarms[i].setSound("redAlert");
						}
						if(soundBox.getSelectedIndex()==1){
							alarms[i].setSound("tornadoSiren");
						}
						if(soundBox.getSelectedIndex()==2){
							alarms[i].setSound("laserSound");
						}
						if(soundBox.getSelectedIndex()==3){
							alarms[i].setSound("explosionSound");
						}	
						if(soundBox.getSelectedIndex()==4){
							alarms[i].setSound("nyanCat");
						}
						break;
					}

				}//End For
				pop.setBounds(900, 400, 400, 400);
				pop.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				notificationPnl = new JPanel();
				pop.setContentPane(notificationPnl);
				notificationPnl.setLayout(null);
				alarmLabel.setBounds(10, 0, 90, 50);
				JLabel name=new JLabel(txtAlarm.getText());
				name.setBounds(10, 13, 50, 50);
				JLabel displayNot=new JLabel(txtDisplay.getText());
				displayNot.setBounds(50, 100, 100, 100);
				notificationPnl.add(alarmLabel);
				notificationPnl.add(name);
				notificationPnl.add(displayNot);
				pop.validate();

				nbtn1 = new JButton("1");
				nbtn1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent m) {
						snooze2=snooze2+1;
						counter2=counter2+1;
						for(int i=0;i<alarms.length-1;i++){
							if(snooze2.equals(alarms[i].getSnooze())){
								pop.dispose();
								soundManager.stopTornadoSiren();
								soundManager.stopRedAlert();
								soundManager.stopNyan();
								soundManager.stopExplosion();
								soundManager.stopLaser();
								break;
							}
							if(counter2%3==0){

								snooze2="";
								counter2=0;
							}}

					}
				});
				nbtn1.setBackground(Color.BLACK);
				nbtn1.setForeground(Color.RED);
				nbtn1.setBounds(10, 50, 63, 23);
				notificationPnl.add(nbtn1);

				nbtn2 = new JButton("2");
				nbtn2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent m) {
						snooze2=snooze2+1;
						counter2=counter2+1;
						for(int i=0;i<alarms.length-1;i++){
							if(snooze2.equals(alarms[i].getSnooze())){
								pop.dispose();
								soundManager.stopTornadoSiren();
								soundManager.stopRedAlert();
								soundManager.stopNyan();
								soundManager.stopExplosion();
								soundManager.stopLaser();
								break;
							}
							if(counter2%3==0){

								snooze2="";
								counter2=0;
							}}

					}
				});
				nbtn2.setForeground(Color.RED);
				nbtn2.setBackground(Color.BLACK);
				nbtn2.setBounds(100, 50, 63, 23);
				notificationPnl.add(nbtn2);

				nbtn3 = new JButton("3");
				nbtn3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent m) {
						snooze2=snooze2+1;
						counter2=counter2+1;
						for(int i=0;i<alarms.length-1;i++){
							if(snooze2.equals(alarms[i].getSnooze())){
								pop.dispose();
								soundManager.stopTornadoSiren();
								soundManager.stopRedAlert();
								soundManager.stopNyan();
								soundManager.stopExplosion();
								soundManager.stopLaser();
								break;
							}
							if(counter2%3==0){

								snooze2="";
								counter2=0;
							}}

					}


				});
				nbtn3.setForeground(Color.RED);
				nbtn3.setBackground(Color.BLACK);
				nbtn3.setBounds(200, 50, 63, 23);
				notificationPnl.add(nbtn3);

				if(snooze==""){
					nbtn1.setVisible(false);
					nbtn2.setVisible(false);
					nbtn3.setVisible(false);
					JButton snzbtn= new JButton("Snooze");
					snzbtn.setBackground(Color.BLACK);
					snzbtn.setForeground(Color.RED);
					snzbtn.setBounds(120, 50, 100, 40);
					snzbtn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent m) {
							pop.dispose();
							soundManager.stopTornadoSiren();
							soundManager.stopRedAlert();
							soundManager.stopNyan();
							soundManager.stopExplosion();
							soundManager.stopLaser();
						}
					});
					snzbtn.setVisible(true);
					notificationPnl.add(snzbtn);

				}
				snooze="";
				//Activate the alarms
				timer= new Timer(1000,new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						Calendar cal1=Calendar.getInstance();
						SimpleDateFormat sdf= new SimpleDateFormat("kk:mm:ss");	
						SimpleDateFormat sdf2= new SimpleDateFormat("MMM dd yyyy");	
						currentDate=sdf2.format(cal1.getTimeInMillis());
						currentTime = sdf.format(cal1.getTimeInMillis());

						for(int k=0;k<alarms.length-1;k++){	
							if(startDateChanged.equals(endDateChanged)&&startTimeChanged.equals(endDateChanged)){
								JOptionPane.showMessageDialog(null, "The alarm ends at the same time it starts. Please Delete.");	

							}
							if(currentTime.equals(alarms[k].getEndTime())&&currentDate.equals(alarms[k].getEndDate())){
								pop.dispose();
								soundManager.stopTornadoSiren();
								soundManager.stopRedAlert();
								soundManager.stopNyan();
								soundManager.stopExplosion();
								soundManager.stopLaser();

							}
							if(currentTime.equals(alarms[k].getStartTime())&&currentDate.equals(alarms[k].getStartDate())){
								pop.setVisible(true);
								if(alarms[k].getSound()=="redAlert")
								{soundManager.playRed();}
								else 	if(alarms[k].getSound()=="tornadoSiren")
								{soundManager.playTornado();}
								else if(alarms[k].getSound()=="laserSound")
								{soundManager.playLaser();}
								else if(alarms[k].getSound()=="explosionSound")
								{soundManager.playExplosionSound();}
								else if(alarms[k].getSound()=="nyanCat")
								{soundManager.playNyan();}
								counter2=0;

								k=29;

							}

						}
					}


				});
				timer.start();
			}
		});			
		contentPane.add(btnSave);

		for(int k=0;k<alarms.length-1;k++){	
			if(currentTime.equals(alarms[k].getEndTime())&&currentDate.equals(alarms[k].getEndDate())){
				pop.dispose();
				soundManager.stopTornadoSiren();
				soundManager.stopRedAlert();
				soundManager.stopNyan();
				soundManager.stopExplosion();
				soundManager.stopLaser();
			}
			if(currentTime.equals(alarms[k].getStartTime())&&currentDate.equals(alarms[k].getStartDate())){
				pop.setVisible(true);
				if(alarms[k].getSound()=="redAlert")
				{soundManager.playRed();}
				else 	if(alarms[k].getSound()=="tornadoSiren")
				{soundManager.playTornado();}
				else if(alarms[k].getSound()=="laserSound")
				{soundManager.playLaser();}
				else if(alarms[k].getSound()=="explosionSound")
				{soundManager.playExplosionSound();}
				else if(alarms[k].getSound()=="nyanCat")
				{soundManager.playNyan();}
				counter2=0;

				k=29;

			}

		}

		//Interval for
		int k=0;
		String[] interv= new String[13];
		for(int i=0;i<13;i++){
			interv[i]=""+k;
			k+=5;
		}
		//Interval
		interval = new JComboBox(interv);
		interval.setBounds(552, 189, 66, 20);
		contentPane.add(interval);
		//Separator
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(354, 102, 308, 14);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(410, 164, 253, 14);
		contentPane.add(separator_3);




		//Calendar

		//Controllers
		lblMonth = new JLabel ("January");
		lblMonth.setBackground(Color.BLACK);
		lblYear = new JLabel ("Change year:");
		cmbYear = new JComboBox<String>();
		btnPrev = new JButton ("<<");
		btnPrev.setBackground(Color.BLACK);
		btnNext = new JButton (">>");
		btnNext.setBackground(Color.BLACK);
		mtblCalendar = new DefaultTableModel(){/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
			tblCalendar = new JTable(mtblCalendar);
			stblCalendar = new JScrollPane(tblCalendar);
			pnlCalendar = new JPanel(null);
			pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

			//Register action listeners
			pane=this.getContentPane();

			btnPrev.addActionListener(new btnPrev_Action());
			btnNext.addActionListener(new btnNext_Action());
			cmbYear.addActionListener(new cmbYear_Action());

			//Add controls to pane
			pane.add(pnlCalendar);
			pnlCalendar.add(lblMonth);
			pnlCalendar.add(lblYear);
			pnlCalendar.add(cmbYear);
			pnlCalendar.add(btnPrev);
			pnlCalendar.add(btnNext);
			pnlCalendar.add(stblCalendar);

			pnlCalendar.setBounds(0, 0, 320, 335);
			lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
			lblYear.setBounds(10, 305, 80, 20);
			cmbYear.setBounds(230, 305, 80, 20);
			btnPrev.setBounds(10, 25, 50, 25);
			btnNext.setBounds(260, 25, 50, 25);
			stblCalendar.setBounds(10, 50, 300, 250);
			//Get real month/year
			GregorianCalendar cal = new GregorianCalendar(); //Create calendar
			realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
			realMonth = cal.get(GregorianCalendar.MONTH); //Get month
			realYear = cal.get(GregorianCalendar.YEAR); //Get year
			currentMonth = realMonth; //Match month and year
			currentYear = realYear;

			//Add headers
			String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
			for (int i=0; i<7; i++){
				mtblCalendar.addColumn(headers[i]);
			}


			//No resize/reorder
			tblCalendar.getTableHeader().setResizingAllowed(false);
			tblCalendar.getTableHeader().setReorderingAllowed(false);

			//Single cell selection
			tblCalendar.setColumnSelectionAllowed(true);
			tblCalendar.setRowSelectionAllowed(true);
			tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//Set row/column count
			tblCalendar.setRowHeight(38);
			mtblCalendar.setColumnCount(7);
			mtblCalendar.setRowCount(6);

			//Populate table
			for (int i=realYear-100; i<=realYear+100; i++){
				cmbYear.addItem(String.valueOf(i));
			}

			//Refresh calendar
			refreshCalendar (realMonth, realYear);
			//End Calendar


			//Time
			JLabel lblTime = new JLabel("Time:");
			lblTime.setBounds(25, 352, 37, 36);
			contentPane.add(lblTime);

			//Snooze Buttons
			btn1 = new JButton("1");
			btn1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent m) {
					snooze=snooze+1;
					counter=counter+1;
					if(counter>=3){
						btn1.setVisible(false);
						btn2.setVisible(false);
						btn3.setVisible(false);
					}
				}
			});
			btn1.setBackground(Color.BLACK);
			btn1.setForeground(Color.RED);
			btn1.setBounds(500, 256, 63, 23);
			contentPane.add(btn1);

			btn2 = new JButton("2");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btn2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent m) {
					snooze=snooze+2;
					counter=counter+1;
					if(counter>=3){
						btn1.setVisible(false);
						btn2.setVisible(false);
						btn3.setVisible(false);
					}
				}
			});
			btn2.setForeground(Color.RED);
			btn2.setBackground(Color.BLACK);
			btn2.setBounds(575, 256, 63, 23);
			contentPane.add(btn2);

			btn3 = new JButton("3");
			btn3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent m) {
					snooze=snooze+3;
					counter=counter+1;
					if(counter>=3){
						btn1.setVisible(false);
						btn2.setVisible(false);
						btn3.setVisible(false);
					}
				}
			});
			btn3.setForeground(Color.RED);
			btn3.setBackground(Color.BLACK);
			btn3.setBounds(649, 256, 63, 23);
			contentPane.add(btn3);

			// My Alarms
			JButton btnList = new JButton("My Alarms");
			btnList.setBackground(Color.BLACK);
			btnList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					JFrame frame= new JFrame("My Alarms");
					frame.setSize(500,680);
					frame.setLocation(800,200);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JPanel panel = new JPanel();
					frame.setContentPane(panel);
					panel.setLayout(null);
					JTextField[] nameAlarm= new JTextField[alarms.length];
					JTextField[] startTime= new JTextField[alarms.length];
					JTextField[] endTime= new JTextField[alarms.length];
					JButton deletebtn= new JButton("Clear");
					deletebtn.setBounds(225, 25, 100, 20);

					JLabel nameLabel=new JLabel("Alarm Name");
					nameLabel.setBounds(15, 5, 70, 20);
					panel.add(nameLabel);
					JLabel StartTimelbl=new JLabel("Start Time");
					StartTimelbl.setBounds(85, 5, 70, 20);
					panel.add(StartTimelbl);
					JLabel EndTimelbl=new JLabel("End Time");
					EndTimelbl.setBounds(155, 5, 70, 20);
					panel.add(EndTimelbl);
					int pos=25;

					for(int i =0;i<alarms.length-1;i++){
						nameAlarm[i]= new JTextField(alarms[i].getName());
						startTime[i]=new JTextField(alarms[i].getStartTime());
						endTime[i]=new JTextField(alarms[i].getEndTime());
						nameAlarm[i].setBounds(15, pos, 50, 20);
						nameAlarm[i].setEditable(false);
						startTime[i].setBounds(85, pos, 60, 20);
						startTime[i].setEditable(false);
						endTime[i].setBounds(155, pos, 60, 20);
						endTime[i].setEditable(false);
						pos+=20;
						panel.add(nameAlarm[i]);	
						panel.add(startTime[i]);	
						panel.add(endTime[i]);	
						panel.add(deletebtn);	
					}
					deletebtn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent m) {
							do{
								Object[] options1 = { "Clear", "Clear (Only Works If you do not have Password)"};

								JPanel panel = new JPanel();
								panel.add(new JLabel("Please enter Your Password"));
								JTextField textField = new JTextField(10);
								panel.add(textField);

								int result = JOptionPane.showOptionDialog(null, panel, "Password Confirmation",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
										null, options1, null);
								if (result == JOptionPane.YES_OPTION){
									passwordVer=textField.getText();
								}
								if(result==JOptionPane.NO_OPTION){
									passwordVer="";
								}

							}while(!passwordVer.equals(password));
							for(int i=0;i<alarms.length-1;i++){
								alarms[i].setName("Empty");
								alarms[i].setStartTime("Empty");
								alarms[i].setEndTime("Empty");
								alarms[i].setStartDate("Empty");
								alarms[i].setSnooze("");
								alarms[i].setInterval(00);

							}}
					});
					frame.setVisible(true);
				}
			});
			btnList.setBounds(549, 388, 132, 46);
			contentPane.add(btnList);

			//Clock 12 Hour
			JButton btn12Hour = new JButton("12-Hour Clock");
			btn12Hour.setBackground(Color.BLACK);
			btn12Hour.setBounds(10, 411, 106, 23);
			btn12Hour.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					//Clock
					timer= new Timer(1000,new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							Calendar calendar=Calendar.getInstance();
							txtclock = new JTextField();
							txtclock.setEditable(false);
							txtclock.setBounds(70, 360, 80, 20);
							contentPane.add(txtclock);
							txtclock.setColumns(10);
							SimpleDateFormat sdf = new SimpleDateFormat("KK:mm:ss a");
							actualTime=sdf.format(calendar.getTimeInMillis());
							txtclock.setText(actualTime);
							contentPane.add(txtclock);

						}

					});
					timer.start();
				}

			});
			contentPane.add(btn12Hour);

			//Clock 24 Hour
			JButton btn24Hour = new JButton("24-Hour Clock");
			btn24Hour.setBackground(Color.BLACK);
			btn24Hour.setBounds(126, 411, 106, 23);
			btn24Hour.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {

					//Clock
					timer= new Timer(1000,new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							Calendar calendar=Calendar.getInstance();
							JTextField txtclock24 = new JTextField();
							txtclock24.setEditable(false);
							txtclock24.setBounds(70, 382, 80, 20);
							txtclock24.setColumns(10);
							SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm:ss a");
							actualTime=sdf2.format(calendar.getTimeInMillis());
							txtclock24.setText(actualTime);
							contentPane.add(txtclock24);

						}

					});
					timer.start();
				}

			});
			contentPane.add(btn24Hour);

			//Start & End Time
			Calendar calendar1 = Calendar.getInstance();
			Date initDate1 = calendar1.getTime();
			SpinnerDateModel timeModel1 = new SpinnerDateModel(initDate1, null, null, Calendar.MINUTE);
			SpinnerDateModel timeModel2 = new SpinnerDateModel(initDate1, null, null, Calendar.MINUTE);

			startTime = new JSpinner(timeModel1);
			startTime.setBounds(500, 110, 96, 22);
			startTime.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					startTimeChanged=startTime.getValue().toString().substring(11, 19);
				}
			});
			startTime.setEditor(new JSpinner.DateEditor(startTime, "hh:mm:ss a"));
			contentPane.add(startTime);

			endTime = new JSpinner(timeModel2);
			endTime.setBounds(500, 136, 96, 20);
			endTime.setEditor(new JSpinner.DateEditor(endTime, "hh:mm:ss a"));
			endTime.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					endTimeChanged=endTime.getValue().toString().substring(11, 19);
				}
			});
			contentPane.add(endTime);

			//Star & End Date
			Calendar calendar2 = Calendar.getInstance();
			Date initDate2 = calendar2.getTime();
			calendar2.set(Calendar.MONTH, calendar2.getActualMinimum(Calendar.MONTH));
			Date earliestDate2 = calendar2.getTime();
			calendar2.set(Calendar.MONTH, calendar2.getActualMaximum(Calendar.MONTH));
			Date latestDate2 = calendar2.getTime();
			SpinnerDateModel dateModel1 = new SpinnerDateModel(initDate2, earliestDate2, latestDate2, Calendar.MONTH);
			SpinnerDateModel dateModel2 = new SpinnerDateModel(initDate2, earliestDate2, latestDate2, Calendar.MONTH);

			dateSpinner1 = new JSpinner(dateModel1);
			dateSpinner1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					startDateChanged=dateSpinner1.getValue().toString().substring(4, 10)+" "+dateSpinner1.getValue().toString().substring(24, 28);
				}
			});
			dateSpinner2 = new JSpinner(dateModel2);
			dateSpinner1.setEditor(new JSpinner.DateEditor(dateSpinner1, "dd/MM/yyyy"));
			dateSpinner2.setEditor(new JSpinner.DateEditor(dateSpinner2, "dd/MM/yyyy"));
			dateSpinner2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					endDateChanged=dateSpinner2.getValue().toString().substring(4, 10)+" "+dateSpinner2.getValue().toString().substring(24, 28);				}
			});
			dateSpinner1.setBounds(500, 50, 96, 20);
			dateSpinner2.setBounds(500, 78, 96, 20);

			contentPane.add(dateSpinner1);
			contentPane.add(dateSpinner2);

			JLabel lblMessageForNotification = new JLabel("Message for Notification");
			lblMessageForNotification.setBounds(410, 300, 132, 14);
			contentPane.add(lblMessageForNotification);

			txtDisplay = new JTextField();
			txtDisplay.setBounds(549, 297, 132, 36);
			contentPane.add(txtDisplay);
			txtDisplay.setColumns(10);



			soundBox = new JComboBox(soundsName);
			soundBox.setForeground(Color.BLACK);
			soundBox.setBackground(Color.BLACK);
			soundBox.setBounds(500, 217, 167, 20);
			contentPane.add(soundBox);
	}



	/**
	 * This method refreshes the calendar in order to keep it updated when an action is received on the buttons
	 * @param month Integer number that represents the number of the month. Expected from 1 to 12
	 * @param year Integer number that represents the year.Expected from -100 from current year to 100.
	 */
	public static void refreshCalendar(int month, int year){
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month

		//Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
		if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
		lblMonth.setText(months[month]); //Refresh the month label (at the top)
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}

		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}
	/**
	 * 
	 * @author Christian
	 *This abstract class is the one that paints the weekend and the current Day in the Calendar
	 */
	static class tblCalendarRenderer extends DefaultTableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			if (column == 0 || column == 6){ //Week-end
				setBackground(new Color(255, 240, 220));
			}
			else{ //Week
				setBackground(new Color(255, 255, 255));
			}
			if (value != null){
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
					setBackground(Color.red);
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;  
		}
	}/**
	 * 
	 * @author Christian
	 *This abstract class defines the action PrevButton does. In this case the PrevButton
	 *is set to get the past month and store it in a variable in order to refresh the Calendar in the past month.
	 */

	static class btnPrev_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 0){ //Back one year
				currentMonth = 11;
				currentYear -= 1;
			}
			else{ //Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	/**
	 * 
	 * @author Christian
	 *This abstract class defines the action NextButton does. In this case the NextButton
	 *is set to get the next coming month and store it in a variable in order to refresh the Calendar in the upcoming month.
	 */
	static class btnNext_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 11){ //Foward one year
				currentMonth = 0;
				currentYear += 1;
			}
			else{ //Foward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	/**
	 * 
	 * @author Christian
	 *This abstract class implements Action Listener and is used to pass the year selected in the Combo Box to antoher variable in order to refresh the calendar in the selected year.
	 */
	static class cmbYear_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (cmbYear.getSelectedItem() != null){
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}

		}}	
}
