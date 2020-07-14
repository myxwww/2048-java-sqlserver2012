//将一些常用的基本信息和方法写成一个接口

package other;

import java.awt.Font;

public interface IBaseData {
	Font topicFont = new Font("微软雅黑", Font.BOLD, 50);
	Font scoreFont = new Font("微软雅黑", Font.BOLD, 28);
	Font normalFont = new Font("宋体", Font.PLAIN, 20);
	Font RuleFont=new Font("宋体",Font.PLAIN,18);
	Font font1 = new Font("宋体", Font.BOLD, 46);
	Font font2 = new Font("宋体", Font.BOLD, 40);
	Font font3 = new Font("宋体", Font.BOLD, 34);
	Font font4 = new Font("宋体", Font.BOLD, 28);
	Font font5 = new Font("宋体", Font.BOLD, 22);
	int normalFontdata = 20;
	int topicFontdata = 30;
	
	void init();
	//void showMenu();
	//void showView();
}
