package other;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class GameView implements IBaseData{
	private static final int jframeWidth = 400;//容器宽
	private static final int jframeHeight = 600;//容器长
	private static int score = 0;
	
	private JFrame jframeMain;
	private JLabel jlblTitle;
	private JLabel jlblScoreName;
	private JLabel jlblScore;
	private GameBoard gameBoard;
 
	public GameView() {
		init();
	}
 
	@Override
	public void init() {
		jframeMain = new JFrame("2048");
		jframeMain.setSize(jframeWidth, jframeHeight);
		jframeMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMain.setLocationRelativeTo(null);
		jframeMain.setResizable(false);
		jframeMain.setLayout(null);
		
		jlblTitle = new JLabel("2048", JLabel.CENTER);
		jlblTitle.setFont(topicFont);
		jlblTitle.setForeground(Color.BLACK);
		jlblTitle.setBounds(50, 0, 150, 60);
		jframeMain.add(jlblTitle);
		
		
		// 分数区
		jlblScoreName = new JLabel("得 分", JLabel.CENTER);
		jlblScoreName.setFont(scoreFont);
		jlblScoreName.setForeground(Color.WHITE);
		jlblScoreName.setOpaque(true);
		jlblScoreName.setBackground(Color.GRAY);
		jlblScoreName.setBounds(250, 0, 120, 30);
		jframeMain.add(jlblScoreName);
		
		jlblScore = new JLabel("0", JLabel.CENTER);
		jlblScore.setFont(scoreFont);
		jlblScore.setForeground(Color.WHITE);
		jlblScore.setOpaque(true);
		jlblScore.setBackground(Color.GRAY);
		jlblScore.setBounds(250, 30, 120, 30);
		jframeMain.add(jlblScore);
		
		gameBoard = new GameBoard();
		gameBoard.setBounds(0, 100, 400, 400);
		gameBoard.setBackground(Color.GRAY);
		gameBoard.setFocusable(true);
		gameBoard.setLayout(new FlowLayout());
		jframeMain.add(gameBoard);
	}
	
	// 游戏面板需要对键值实现侦听，
	// 这里采用内部类来继承 JPanel 类，
	// 并实现接口 KeyListener 中的 keyPressed 方法，
	@SuppressWarnings("serial")
	class GameBoard extends JPanel implements KeyListener {
		private static final int CHECK_GAP = 10;
		private static final int CHECK_ARC = 20;
		private static final int CHECK_SIZE = 86;
 
		private Check[][] checks = new Check[4][4];
		private boolean isadd = true;
		
		public GameBoard() {
			initGame();
			addKeyListener(this);
		}
		//操作
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			//退出游戏
			case KeyEvent.VK_ESCAPE:
				new GameMenu().showMenu();
				break;
			//左移
			case KeyEvent.VK_LEFT:
				moveLeft();
				createCheck();
				judgeGameOver();
				break;
			//右移
			case KeyEvent.VK_RIGHT:
				moveRight();
				createCheck();
				judgeGameOver();
				break;
			//上移
			case KeyEvent.VK_UP:
				moveUp();
				createCheck();
				judgeGameOver();
				break;
			//下移
			case KeyEvent.VK_DOWN:
				moveDown();
				createCheck();
				judgeGameOver();
				break;
			default:
				break;
			}
			repaint();
		}
		//游戏开始
		private void initGame() {
            score = 0;
			for (int indexRow = 0; indexRow < 4; indexRow++) {
				for (int indexCol = 0; indexCol < 4; indexCol++) {
					checks[indexRow][indexCol] = new Check();
				}
			}
			// 生成两个数
			isadd = true;
			createCheck();
			isadd = true;
			createCheck();
		}
		
		private void createCheck() {
			List<Check> list = getEmptyChecks();
			
			if (!list.isEmpty() && isadd) {
				Random random = new Random();
				int index = random.nextInt(list.size());
				Check check = list.get(index);
				// 2, 4出现概率3:1
				check.value = (random.nextInt(4) % 3 == 0) ? 2 : 4;
				isadd = false;
			} 
		}
		
		// 获取空白方格
		private List<Check> getEmptyChecks() {
			List<Check> checkList = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (checks[i][j].value == 0) {
                    	checkList.add(checks[i][j]);
                    }
                }
            }
			
			return checkList;
		}
		
		private boolean judgeGameOver() {
			jlblScore.setText(score + "");
			
			if (!getEmptyChecks().isEmpty()) {
				return false;
			}
			
			for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                //判断是否存在可合并的方格
	                if (checks[i][j].value == checks[i][j + 1].value || checks[i][j].value == checks[i + 1][j].value) {
	                    return false;
	                }
	            }
	        }
			
			
		    return true;
		}
		
		private boolean moveLeft() {
			for (int i = 0; i < 4; i++) {
				for (int j = 1, index = 0; j < 4; j++) {
					if (checks[i][j].value > 0) {
						if (checks[i][j].value == checks[i][index].value) {
							score += checks[i][index++].value <<= 1;
							checks[i][j].value = 0;
							isadd = true;
						} else if (checks[i][index].value == 0) {
							checks[i][index].value = checks[i][j].value;
							checks[i][j].value = 0;
							isadd = true;
						} else if (checks[i][++index].value == 0) {
							checks[i][index].value = checks[i][j].value;
							checks[i][j].value = 0;
							isadd = true;
						}
					} 
				}
			}
			return isadd;
		}
		
		private boolean moveRight() {
			for (int i = 0; i < 4; i++) {
				for (int j = 2, index = 3; j >= 0; j--) {
					if (checks[i][j].value > 0) {
						if (checks[i][j].value == checks[i][index].value) {
							score += checks[i][index--].value <<= 1;
							checks[i][j].value = 0;
							isadd = true;
						} else if (checks[i][index].value == 0) {
							checks[i][index].value = checks[i][j].value;
							checks[i][j].value = 0;
							isadd = true;
						} else if (checks[i][--index].value == 0) {
							checks[i][index].value = checks[i][j].value;
							checks[i][j].value = 0;
							isadd = true;
						}
					}
				}
			}
 
			return isadd;
		}
		
		private boolean moveUp() {
			for (int i = 0; i < 4; i++) {
				for (int j = 1, index = 0; j < 4; j++) {
					if (checks[j][i].value > 0) {
						if (checks[j][i].value == checks[index][i].value) {
							score += checks[index++][i].value <<= 1;
							checks[j][i].value = 0;
							isadd = true;
						} else if (checks[index][i].value == 0) {
							checks[index][i].value = checks[j][i].value;
							checks[j][i].value = 0;
							isadd = true;
						} else if (checks[++index][i].value == 0){
							checks[index][i].value = checks[j][i].value;
								checks[j][i].value = 0;
								isadd = true;
						}
					} 
				}
			}
 
			return isadd;
		}
		
		private boolean moveDown() {
			for (int i = 0; i < 4; i++) {
				for (int j = 2, index = 3; j >= 0; j--) {
					if (checks[j][i].value > 0) {
						if (checks[j][i].value == checks[index][i].value) {
							score += checks[index--][i].value <<= 1;
							checks[j][i].value = 0;
							isadd = true;
						} else if (checks[index][i].value == 0) {
							checks[index][i].value = checks[j][i].value;
							checks[j][i].value = 0;
							isadd = true;
						} else if (checks[--index][i].value == 0) {
							checks[index][i].value = checks[j][i].value;
							checks[j][i].value = 0;
							isadd = true;
						}
					} 
				}
			}
 
			return isadd;
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					drawCheck(g, i, j);
				}
			}
			
			// GameOver
			if (judgeGameOver()) {
				g.setColor(new Color(64, 64, 64, 150));
				g.fillRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.WHITE);
				g.setFont(topicFont);
				FontMetrics fms = getFontMetrics(topicFont);
				String value = "游戏结束";
				g.drawString(value,
						(getWidth()-fms.stringWidth(value)) / 2,
						getHeight() / 2);
				User_SQL.ScoreQuery();
				User_SQL.executeUpdate(score,User_SQL.executeQuery());
				/*bug继续左右输入会存入n个数据*/
			}
		}
		
		// 绘制方格
		// Graphics2D 类扩展了 Graphics 类，
		// 提供了对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
		private void drawCheck(Graphics g, int i, int j) {
			Graphics2D gg = (Graphics2D) g;
			gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE);
			Check check = checks[i][j];
			gg.setColor(check.getBackground());
			// 绘制圆角
			// x - 要填充矩形的 x 坐标。
			// y - 要填充矩形的 y 坐标。
			// width - 要填充矩形的宽度。
			// height - 要填充矩形的高度。
			// arcwidth - 4 个角弧度的水平直径。
			// archeight - 4 个角弧度的垂直直径。
			gg.fillRoundRect(CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * j,
					CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * i,
					CHECK_SIZE, CHECK_SIZE, CHECK_ARC, CHECK_ARC);
			gg.setColor(check.getForeground());
			gg.setFont(check.getCheckFont());
			
			// 对文字的长宽高测量。
			FontMetrics fms = getFontMetrics(check.getCheckFont());
			String value = String.valueOf(check.value);
			//使用此图形上下文的当前颜色绘制由指定迭代器给定的文本。
			//getAscent()是FontMetrics中的一个方法，
			//它返回某字体的基线(baseline)到该字体中大多数字符的升部(ascender)之间的距离
			//getDescent 为降部
			gg.drawString(value,
					CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * j +
					(CHECK_SIZE - fms.stringWidth(value)) / 2,
					CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * i +
					(CHECK_SIZE - fms.getAscent() - fms.getDescent()) / 2
					+ fms.getAscent());
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
		}
 
		@Override
		public void keyTyped(KeyEvent e) {
		}
		
	}
 
	public void showView() {
		jframeMain.setVisible(true);
	}
	
}