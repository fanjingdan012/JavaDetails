/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package gui;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.SudokuNext;

import util.data.ImageButtonRes;
import util.data.ImageManager;

/**
 * MenuButton类 
 * 用于菜单中的按钮
 * @version 0.1
 */
public class MenuButton extends JLabel{
	
	//构造函数
	public MenuButton(ImageButtonRes _res)
	{
		super(new ImageIcon(ImageManager.getImage(_res.getTheme(), _res.getDef())));
		res = _res;
		onImage = ImageManager.getImage(_res.getTheme(), _res.getOn());
		offImage = ImageManager.getImage(_res.getTheme(), _res.getDef());
		this.setLocation((int)res.getPos().getWidth(), (int)res.getPos().getHeight());
		this.setSize(ImageManager.getImage(_res.getTheme(), _res.getDef()).getWidth(null),ImageManager.getImage(SudokuNext.getTheme(), _res.getDef()).getHeight(null));
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
			((MenuButton)e.getSource()).setIcon(new ImageIcon(onImage));
			}
			public void mouseExited(MouseEvent e) {
			((MenuButton)e.getSource()).setIcon(new ImageIcon(offImage));	
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
		});
	}
	
	//设置资源
	public void setImageRes(String _theme, String _defimage, String _onImage)
	{
		res.setTheme(_theme);
		res.setDef(_defimage);
		res.setOn(_onImage);
		onImage = ImageManager.getImage(res.getTheme(), res.getOn());
		offImage = ImageManager.getImage(res.getTheme(), res.getDef());
	}
	
	Image onImage;
	Image offImage;
	ImageButtonRes res;
}
