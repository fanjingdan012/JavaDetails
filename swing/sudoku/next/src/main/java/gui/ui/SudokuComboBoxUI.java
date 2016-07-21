/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package gui.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.MenuButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import core.SudokuNext;

import util.data.ImageButtonRes;
import util.data.ImageManager;

/**
 * SudokuComboBoxUI类 
 * 下拉列表、用于显示主题列表
 * @version 0.1
 */
public class SudokuComboBoxUI extends BasicComboBoxUI{

	ImageButtonRes res;
	Image onImage;
	Image offImage;
		
	@Override
	protected JButton createArrowButton() {

		JButton button = new JButton();
		button.setBorder(null);
		res = SudokuNext.getResources().othemes;
		onImage = ImageManager.getImage(res.getTheme(), res.getOn());
		offImage = ImageManager.getImage(res.getTheme(), res.getDef());
		button.setLocation((int)res.getPos().getWidth(), (int)res.getPos().getHeight());
		button.setSize(ImageManager.getImage(res.getTheme(), res.getDef()).getWidth(null),ImageManager.getImage(SudokuNext.getTheme(), res.getDef()).getHeight(null));
		button.addMouseListener(new MouseListener(){
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
		return button;
	}	

}
