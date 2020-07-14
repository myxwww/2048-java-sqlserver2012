package other;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenu implements IBaseData{

	private static final int jframeWidth = 400;//容器宽
	private static final int jframeHeight = 600;//容器长
	private JFrame jframeMain;
	private JLabel jlblTitle;
	
	public GameMenu() {
		init();
	}
	
	//游戏规则窗口
	public static void RuleWindow(JFrame relativeWindow) {
        // 创建一个新窗口
        JFrame newJFrame = new JFrame("2048の游戏规则");
        newJFrame.setSize(300, 250);
        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setLocationRelativeTo(relativeWindow);
        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // 窗口设置为不可改变大小
        newJFrame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(1, 1));

        // 在新窗口中显示一个标签
        JLabel label = new JLabel("<html><body>操作: ↑ ↓ ← →,<br> 按esc键重新开始</body></html>",JLabel.CENTER);
        
        label.setFont(RuleFont);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);

        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);
	}
	
	//用户注册窗口
	public static void UserWindow(JFrame relativeWindow) {
		JFrame newJFrame = new JFrame("2048の用户注册");
	    newJFrame.setSize(300, 500);
	    newJFrame.setLocationRelativeTo(relativeWindow);
	    newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    newJFrame.setResizable(true);
	    
	    newJFrame.setLayout(new GridLayout(3,1));
	    JPanel panel1,panel2,panel3;
	    
	    //标题
	    panel1=new JPanel();
	    JLabel label=new JLabel("用户注册");
	    label.setFont(topicFont);
	    panel1.add(label);
	    newJFrame.add(panel1);
	    
	    //主体部分
	    panel2=new JPanel();
	    JLabel user=new JLabel("用户名");
	    user.setFont(normalFont);
	    panel2.add(user);
	    JTextField input=new JTextField(10);
	    panel2.add(input);
	    newJFrame.add(panel2);
	    
	    //按钮部分
	    panel3=new JPanel();
	    JButton submit=new JButton("提交");
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
		//框
		jframeMain = new JFrame("2048の游戏菜单");
		jframeMain.setSize(jframeWidth, jframeHeight);
		jframeMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMain.setLocationRelativeTo(null);
		jframeMain.setResizable(false);
		jframeMain.getContentPane().setLayout(null);
		
		//标题
		jlblTitle = new JLabel("2048小游戏", JLabel.CENTER);
		jlblTitle.setFont(topicFont);
		jlblTitle.setForeground(Color.BLACK);
		jlblTitle.setBounds(30, 27, 295, 60);
		jframeMain.getContentPane().add(jlblTitle);
		
		//按钮部分
		//按钮1
		JButton btnNewButton1 = new JButton("开始新游戏");
		btnNewButton1.setFont(normalFont);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameView().showView();
			}
		});
		btnNewButton1.setBounds(100, 138, 170, 60);
		jframeMain.getContentPane().add(btnNewButton1);
		//按钮2
		JButton btnNewButton2 = new JButton("游戏规则");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame jf = new JFrame("游戏规则");
		        jf.setSize(250, 250);
		        jf.setLocationRelativeTo(null);
		        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        RuleWindow(jf);
			}
		});
		btnNewButton2.setFont(normalFont);
		btnNewButton2.setBounds(100, 254, 170, 60);
		jframeMain.getContentPane().add(btnNewButton2);
		//按钮3
		JButton btnNewButton3 = new JButton("用户注册");
		btnNewButton3.setFont(normalFont);
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame User = new JFrame("用户注册");
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
