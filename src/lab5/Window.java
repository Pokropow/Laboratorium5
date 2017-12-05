package lab5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
	boolean czyMale=true;
	
	DefaultListModel<String> model;


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


	public Window() {
		timer = new Timer(1000,this);
		timer.setRepeats(false);
		currText="";
		initialize();
	}


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
		
		model = new DefaultListModel<String>();
		list.setModel(model);		
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
					character = new Character((char) (text.charAt(clicks%text.length())+(czyMale?0:'A'-'a')));
					timer.restart();
				}
				else if(!timer.isRunning())
				{
					clicks=0;		
					String text = ((JButton)(arg0.getSource())).getText();
					character = new Character((char) (text.charAt(clicks%text.length())+(czyMale?0:'A'-'a')));
					timer.start();
					lastButton = (JButton)arg0.getSource();
				}
				else if(arg0.getSource()!=lastButton&&timer.isRunning())
				{
					currText+=character;
					clicks=0;
					String text = ((JButton)(arg0.getSource())).getText();
					character = new Character((char) (text.charAt(clicks%text.length())+(czyMale?0:'A'-'a')));
					timer.restart();
					lastButton = (JButton)arg0.getSource();
				}
				textField.setText(currText+character);
			}
		};
		
		ActionListener signListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				if(character!=null)
				{
					currText+=character;
				}
				character =null;
				currText+=((JButton)e.getSource()).getText();
				textField.setText(currText);
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
		btnNewButton_1.addActionListener(signListener);
		btnNewButton_1.setBounds(28, 182, 67, 23);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("-");
		button.addActionListener(signListener);
		button.setBounds(104, 182, 67, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("/");
		button_1.addActionListener(signListener);
		button_1.setBounds(181, 182, 67, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("=");
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				timer.stop();
				if(character!=null)
				{
					currText+=character;
				}
				character = null;
				String s = parse(currText);
				currText +=" = " +s;
				
				//model.addElement(currText);
				model.add(0, currText);
				
				currText="";
				textField.setText(currText);				
			}
			
		});
		button_2.setBounds(258, 182, 67, 23);
		panel.add(button_2);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				timer.stop();
				if(character!=null)
				{
					currText+=character;
				}
				character = null;
				if(currText.length()>0)
				{
					currText = currText.substring(0, currText.length()-1);
					textField.setText(currText);
				}				
			}
		});
		btnC.setBounds(258, 215, 67, 23);
		panel.add(btnC);
		
		JButton MD = new JButton("M/D");
		MD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				if(character!=null)
				{
					currText+=character;
				}
				character = null;
				czyMale=!czyMale;						
			}
		});
		MD.setBounds(181, 216, 67, 23);
		panel.add(MD);
	}

	private String parse(final String input)
	{
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Character>signs = new ArrayList<Character>();
		
		int beginIndex = 0;
		for(int i=0;i<=input.length();i++)
		{
			if(i==input.length())
			{
				words.add(input.substring(beginIndex, i));
			}
			else
			{
				char c = input.charAt(i);
				if(c=='+'||c=='-'||c=='/')
				{
					signs.add(new Character(c));
					words.add(input.substring(beginIndex, i));
					beginIndex=i+1;
				}
			}			
		}
		String s = words.get(0);
		
		for(int i=0;i<signs.size();i++)
		{
			char sign = signs.get(i);
			switch(sign)
			{
			case '+':
				s = s+words.get(i+1);
				break;
			case '-':
				s=s.replaceAll(words.get(i+1), "");
				break;
			case '/':
				s = longestCommonPart(s, words.get(i+1));
				break;				
			}
		}		
		return s;
	}
	
	private String longestCommonPart(final String a, final String b)
	{
		String longest = "";
		int longest_size = 0;
		
		for(int i=0;i<a.length();i++)
		{
			for(int j=0;j<b.length();j++)
			{
				int n=0;
				while(i+n<a.length()&&j+n<b.length()&&a.charAt(i+n)==b.charAt(j+n))
				{
					n++;
				}
				if(n>longest_size)
				{
					longest_size = n;
					longest = a.substring(i, i+n);
				}
			}
		}
		return longest;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
		currText+=character;
		character = null;
		textField.setText(currText);
		System.out.println(currText);
	}
}
