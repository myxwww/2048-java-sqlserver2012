//��һЩ���õĻ�����Ϣ�ͷ���д��һ���ӿ�

package other;

import java.awt.Font;

public interface IBaseData {
	Font topicFont = new Font("΢���ź�", Font.BOLD, 50);
	Font scoreFont = new Font("΢���ź�", Font.BOLD, 28);
	Font normalFont = new Font("����", Font.PLAIN, 20);
	Font RuleFont=new Font("����",Font.PLAIN,18);
	Font font1 = new Font("����", Font.BOLD, 46);
	Font font2 = new Font("����", Font.BOLD, 40);
	Font font3 = new Font("����", Font.BOLD, 34);
	Font font4 = new Font("����", Font.BOLD, 28);
	Font font5 = new Font("����", Font.BOLD, 22);
	int normalFontdata = 20;
	int topicFontdata = 30;
	
	void init();
	//void showMenu();
	//void showView();
}
