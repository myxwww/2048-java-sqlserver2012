package other;

import java.awt.Color;
import java.awt.Font;
 
// 方格类
public class Check {
	public int value;
 
	public Check() {
		clear();
	}
	
	public void clear() {
		value = 0;
	}
	
	public Color getForeground() {
		switch (value) {
		case 0:
			return new Color(245,255,250);
		case 2:
		case 4:
			return Color.BLACK;
		default:
			return Color.WHITE;
		}
	}
	//不同分数不同的格子颜色
	public Color getBackground() {
		 switch (value) {
         case 0:
             return new Color(240,248,255);
         case 2:
             return new Color(250,235,215);
         case 4:
             return new Color(255,228,225);
         case 8:
             return new Color(255,250,205);
         case 16:
             return new Color(211,211,211);
         case 32:
             return new Color(245,245,245);
         case 64:
             return new Color(143,188,143);
         case 128:
             return new Color(255,222,173);
         case 256:
             return new Color(238,232,170);
         case 512:
             return new Color(219,112,147);
         case 1024:
             return new Color(176,224,230);
         case 2048:
             return new Color(189,183,107);
         case 4096:
             return new Color(205,133,63);
         case 8192:
             return new Color(192,192,192);
         case 16384:
             return new Color(135,206,235);
         default:
             return new Color(70,130,180);
         }
	}
	//不同分数不同的字体
	public Font getCheckFont() {
		if (value < 10) {
			return IBaseData.font1;
		}
		if (value < 100) {
			return IBaseData.font2;
		}
		if (value < 1000) {
			return IBaseData.font3;
		}
		if (value < 10000) {
			return IBaseData.font4;
		}
		
		return IBaseData.font5;
	}
 
}