package other;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenu implements IBaseData{

	private static final int jframeWidth = 400;//������
	private static final int jframeHeight = 600;//������
	private JFrame jframeMain;
	private JLabel jlblTitle;
	
	public GameMenu() {
		init();
	}
	
	//��Ϸ���򴰿�
	public static void RuleWindow(JFrame relativeWindow) {
        // ����һ���´���
        JFrame newJFrame = new JFrame("2048����Ϸ����");
        newJFrame.setSize(300, 250);
        // ���´��ڵ�λ�����õ� relativeWindow ���ڵ�����
        newJFrame.setLocationRelativeTo(relativeWindow);
        // ������ڹرհ�ť, ִ�����ٴ��ڲ������������Ϊ EXIT_ON_CLOSE, �����´��ڹرհ�ť��, �������̽�������
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // ��������Ϊ���ɸı��С
        newJFrame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(1, 1));

        // ���´�������ʾһ����ǩ
        JLabel label = new JLabel("<html><body>����: �� �� �� ��,<br> ��esc�����¿�ʼ</body></html>",JLabel.CENTER);
        
        label.setFont(RuleFont);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);

        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);
	}
	
	//�û�ע�ᴰ��
	public static void UserWindow(JFrame relativeWindow) {
		JFrame newJFrame = new JFrame("2048���û�ע��");
	    newJFrame.setSize(300, 500);
	    newJFrame.setLocationRelativeTo(relativeWindow);
	    newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    newJFrame.setResizable(true);
	    
	    newJFrame.setLayout(new GridLayout(3,1));
	    JPanel panel1,panel2,panel3;
	    
	    //����
	    panel1=new JPanel();
	    JLabel label=new JLabel("�û�ע��");
	    label.setFont(topicFont);
	    panel1.add(label);
	    newJFrame.add(panel1);
	    
	    //���岿��
	    panel2=new JPanel();
	    JLabel user=new JLabel("�û���");
	    user.setFont(normalFont);
	    panel2.add(user);
	    JTextField input=new JTextField(10);
	    panel2.add(input);
	    newJFrame.add(panel2);
	    
	    //��ť����
	    panel3=new JPanel();
	    JButton submit=new JButton("�ύ");
	    submit.setFont(normalFont);
	    submit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
				new User_SQL();
				User_SQL.nameUpdate(input.getText());
			}
	    });
	    panel3.add(submit);
	    newJFrame.add(panel3);
	    
        newJFrame.setVisible(true);
	}
	@Override
	public void init() {
		//��
		jframeMain = new JFrame("2048����Ϸ�˵�");
		jframeMain.setSize(jframeWidth, jframeHeight);
		jframeMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMain.setLocationRelativeTo(null);
		jframeMain.setResizable(false);
		jframeMain.getContentPane().setLayout(null);
		
		//����
		jlblTitle = new JLabel("2048С��Ϸ", JLabel.CENTER);
		jlblTitle.setFont(topicFont);
		jlblTitle.setForeground(Color.BLACK);
		jlblTitle.setBounds(30, 27, 295, 60);
		jframeMain.getContentPane().add(jlblTitle);
		
		//��ť����
		//��ť1
		JButton btnNewButton1 = new JButton("��ʼ����Ϸ");
		btnNewButton1.setFont(normalFont);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameView().showView();
			}
		});
		btnNewButton1.setBounds(100, 138, 170, 60);
		jframeMain.getContentPane().add(btnNewButton1);
		//��ť2
		JButton btnNewButton2 = new JButton("��Ϸ����");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame jf = new JFrame("��Ϸ����");
		        jf.setSize(250, 250);
		        jf.setLocationRelativeTo(null);
		        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        RuleWindow(jf);
			}
		});
		btnNewButton2.setFont(normalFont);
		btnNewButton2.setBounds(100, 254, 170, 60);
		jframeMain.getContentPane().add(btnNewButton2);
		//��ť3
		JButton btnNewButton3 = new JButton("�û�ע��");
		btnNewButton3.setFont(normalFont);
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame User = new JFrame("�û�ע��");
		        User.setSize(300, 500);
		        User.setLocationRelativeTo(null);
		        User.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        UserWindow(User);
			}
		});
		btnNewButton3.setBounds(100, 370, 170, 60);
		jframeMain.getContentPane().add(btnNewButton3);
	}
	
	public void showMenu() {
		jframeMain.setVisible(true);
	}
}
