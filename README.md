# codeclause-

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MyStopWatch extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtMinute, txtSecond1, txtSecond2;
	private JButton btnReset, btnStart, btnAbout;
	private Timer timer;

	public MyStopWatch() {
		super("DS Timer");
		configureView();
		setVisible(true);
	}
	
	//Usually done outside the class
	public static void runStopWatch(){
		java.awt.EventQueue.invokeLater(new Runnable(){
		     public void run(){
		    	 new MyStopWatch();
		     }	
		});
	}

	private void configureView() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel pnlLabels = new Panel(new GridLayout(1, 3));
		pnlLabels.add(new JLabel("Minutes"));
		pnlLabels.add(new JLabel("Seconds"));
		add(pnlLabels, BorderLayout.NORTH);

		Panel pnlTimer = new Panel(new GridLayout(1, 3));
		pnlTimer.add(txtMinute = createNewTextField("0"));
		pnlTimer.add(txtSecond1 = createNewTextField("0"));
		pnlTimer.add(txtSecond2 = createNewTextField("0"));
		add(pnlTimer, BorderLayout.CENTER);

		Panel pnlAction = new Panel(new FlowLayout());
		pnlAction.add(btnReset = new JButton("Reset"));
		pnlAction.add(btnStart = new JButton("Start"));
		pnlAction.add(btnAbout = new JButton("About"));
		add(pnlAction, BorderLayout.SOUTH);

		btnReset.addActionListener(this);
		btnStart.addActionListener(this);
		btnAbout.addActionListener(this);
		setSize(300,120);

	}

	private JTextField createNewTextField(String text) {
		JTextField field = new JTextField(text);
		field.setSize(200, 50);
		field.setHorizontalAlignment(JTextField.CENTER);
		return field;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnAbout){
			JOptionPane.showMessageDialog(this, "Created by GGordon 4.10.2017. \nEvery moment counts.");
		}
		else if (arg0.getSource() == btnReset) {
			resetTimer();
		} else if (arg0.getSource() == btnStart) {
			// get time then start
			btnReset.setText("Stop");
			timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {

					private long currentTime = 0;

					@Override
					public void run() {
						// initialize
						try {
						if (currentTime == 0)
							currentTime = convertInputToTime();
						} catch (NumberFormatException e) {
			                showInvalidNumberEnteredPrompt();
			                currentTime = 0;
			                resetTimer();
			                return;
						}
						// decrement
						currentTime -= 1000;
						updateUITime(currentTime);
						if (currentTime <= 0)
							resetTimer();
					}

				}, 0, 1000);
			

		}

	}
	
	private void showInvalidNumberEnteredPrompt(){
		JOptionPane.showMessageDialog(this, "Please enter only numbers.");
	}

	private synchronized void updateUITime(long newTime) {
		newTime = (long) Math.floor(newTime / 1000.0);
		int minutes = (int) Math.floor(newTime / 60.0);
		int second1 = (int) Math.floor((newTime - (minutes * 60)) / 10.0);
		int second2 = (int) Math
				.floor((newTime - (minutes * 60) - (second1 * 10)) / 1.0);
		txtMinute.setText(minutes + "");
		txtSecond1.setText(second1 + "");
		txtSecond2.setText(second2 + "");
	}

	private void cancelTimer() {
		try {
			timer.cancel();
		} catch (IllegalStateException e) {
			if (!e.getMessage().contains("Timer already cancelled"))
				e.printStackTrace();
		}
	}

	private synchronized void resetTimer() {
		btnReset.setText("Reset");
		cancelTimer();
		txtMinute.setText("0");
		txtSecond1.setText("0");
		txtSecond2.setText("0");
	}

	private long convertInputToTime() throws NumberFormatException {
		int minutes = 0, seconds = 0;
			minutes = Integer.parseInt(txtMinute.getText());
			seconds = Integer.parseInt(txtSecond1.getText()) * 10
					+ Integer.parseInt(txtSecond2.getText());
		seconds = (minutes * 60 + seconds) * 1000;
		// System.out.println("Converted time : "+seconds);
		return seconds;
	}

	@Override
	public void dispose() {
		cancelTimer();
		;
		super.dispose();
	}

}


