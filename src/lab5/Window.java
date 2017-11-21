package lab5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Window implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private Timer timer;
	
	Character character; 
	Integer clicks=0;
	JButton lastButton=null;
	String currText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		timer = new Timer(1000,this);
		timer.setRepeats(false);
		currText="";
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 358, 288);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 305, 92);
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);
		
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==lastButton&&timer.isRunning())
				{
					clicks+=1;					
					String text = ((JButton)(arg0.getSource())).getText();
					character = new Character(text.charAt(clicks%text.length()));
				}
				else if(!timer.isRunning())
				{
					clicks=0;		
					String text = ((JButton)(arg0.getSource())).getText();
					character = new Character(text.charAt(clicks%text.length()));
					timer.start();
					lastButton = (JButton)arg0.getSource();
				}
				else if(arg0.getSource()!=lastButton&&timer.isRunning())
				{
					currText+=character;
					clicks=0;
					String text = ((JButton)(arg0.getSource())).getText();
					character = new Character(text.charAt(clicks%text.length()));
					timer.start();
					lastButton = (JButton)arg0.getSource();
				}
			}
		};
		
		
		JButton btnNewButton = new JButton("abc");
		btnNewButton.addActionListener(listener);
		btnNewButton.setBounds(28, 114, 67, 23);
		panel.add(btnNewButton);
		
		JButton btnDef = new JButton("def");
		btnDef.setBounds(104, 114, 67, 23);
		btnDef.addActionListener(listener);
		panel.add(btnDef);
		
		JButton btnGhi = new JButton("ghi");
		btnGhi.setBounds(181, 114, 67, 23);
		btnGhi.addActionListener(listener);
		panel.add(btnGhi);
		
		JButton btnJkl = new JButton("jkl");
		btnJkl.setBounds(258, 114, 67, 23);
		btnJkl.addActionListener(listener);
		panel.add(btnJkl);
		
		JButton btnMno = new JButton("mno");
		btnMno.setBounds(28, 148, 67, 23);
		btnMno.addActionListener(listener);
		panel.add(btnMno);
		
		JButton btnPqr = new JButton("pqr");
		btnPqr.setBounds(104, 148, 67, 23);
		btnPqr.addActionListener(listener);
		panel.add(btnPqr);
		
		JButton btnStuv = new JButton("stuv");
		btnStuv.setBounds(181, 148, 67, 23);
		btnStuv.addActionListener(listener);
		panel.add(btnStuv);
		
		JButton btnWxyz = new JButton("wxyz");
		btnWxyz.setBounds(258, 148, 67, 23);
		btnWxyz.addActionListener(listener);
		panel.add(btnWxyz);
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setBounds(28, 182, 67, 23);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("-");
		button.setBounds(104, 182, 67, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("/");
		button_1.setBounds(181, 182, 67, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("=");
		button_2.setBounds(258, 182, 67, 23);
		panel.add(button_2);
		
		JButton btnC = new JButton("C");
		btnC.setBounds(258, 215, 67, 23);
		panel.add(btnC);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
		currText+=character;
		System.out.println(currText);
	}
}
