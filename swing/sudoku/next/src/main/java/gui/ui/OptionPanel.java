/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import gui.MenuButton;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.Timer;
import core.ScreenManager;
import core.SudokuNext;
import core.SudokuSettings;

import util.data.ImageManager;

/**
 * OptionPanel类 
 * 设置窗口
 * @version 0.2
 */
public class OptionPanel extends JPanel {

	
	/**
	 * 设置窗口
	 */
	public OptionPanel()
	{
		showOn = 0;
		this.setLayout(null);
		this.setBounds(0, 0, 800, 600);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 800, 600);
		
		
		spinbgm_bg = new MenuButton(SudokuNext.getResources().ospinbg1);
		spinbgm_bg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinbgm_bg.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				spinbgm.setSize(e.getPoint().x, spinbgm.getHeight());
				SudokuNext.getSettings().setBGMVolume((e.getPoint().x)*100/spinbgm_bg.getWidth());
				SudokuSettings.update();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		panel.add (spinbgm_bg);
		spinbgm = new MenuButton(SudokuNext.getResources().ospinner1);
		spinbgm.setSize(spinbgm_bg.getWidth()*(SudokuNext.getSettings().getBGMVolume())/100, spinbgm.getHeight());
		panel.add (spinbgm);

		spinse_bg = new MenuButton(SudokuNext.getResources().ospinbg2);
		spinse_bg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinse_bg.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				spinse.setSize(e.getPoint().x, spinse.getHeight());
				SudokuNext.getSettings().setSEVolume((e.getPoint().x)*100/spinse_bg.getWidth());
				SudokuSettings.update();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		panel.add (spinse_bg);
		spinse = new MenuButton(SudokuNext.getResources().ospinner2);
		spinse.setSize(spinse_bg.getWidth()*(SudokuNext.getSettings().getSEVolume())/100, spinse.getHeight());
		panel.add (spinse);
		
		
		oticker = new MenuButton(SudokuNext.getResources().oticker);
		oticker.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		oticker.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuNext.getSettings().setFullScreen(!SudokuNext.getSettings().isFullScreen());
				SudokuSettings.update();
				oticker.setVisible(!oticker.isVisible());
				otickeroff.setVisible(!otickeroff.isVisible());
				if (oticker.isVisible())
					ScreenManager.setFullScreen(SudokuNext.frame, SudokuNext.device);
				else
					ScreenManager.setWindow(SudokuNext.frame, SudokuNext.device);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		panel.add (oticker);
		otickeroff = new MenuButton(SudokuNext.getResources().otickeroff);
		otickeroff.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		otickeroff.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuNext.getSettings().setFullScreen(!SudokuNext.getSettings().isFullScreen());
				SudokuSettings.update();
				oticker.setVisible(!oticker.isVisible());
				otickeroff.setVisible(!otickeroff.isVisible());
				if (oticker.isVisible())
					ScreenManager.setFullScreen(SudokuNext.frame, SudokuNext.device);
				else
					ScreenManager.setWindow(SudokuNext.frame, SudokuNext.device);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		panel.add (otickeroff);
		if (SudokuNext.getSettings().isFullScreen())
		{
			oticker.setVisible(true);
			otickeroff.setVisible(false);
		}else
		{
			otickeroff.setVisible(true);
			oticker.setVisible(false);
		}
		othemes = new MenuButton(SudokuNext.getResources().othemes);
		othemes.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{
				themeMenu.removeAll();
				File file = new File("./theme/");
				File[] x = file.listFiles();
				for (File q:x)
					if (!q.getName().equals("public"))
					{
						JMenuItem temp = new JMenuItem(q.getName());
						temp.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								SudokuNext.getSettings().setThemepackFilename(((JMenuItem)(e.getSource())).getText());
								SudokuNext.getSettings();
								SudokuSettings.update();
								SudokuNext.switchLoadGame();
							}
						});
						themeMenu.insert(temp, themeMenu.getComponentCount());
					}
				themeMenu.show(othemes, e.getX(), e.getY());
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		themeMenu = new JPopupMenu();
		panel.add (othemes);
		oreset = new MenuButton(SudokuNext.getResources().oreset);
		oreset.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				File file = new File("./settings/HighScore.dat");
				file.delete();
				SudokuNext.switchLoadGame();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		panel.add (oreset);
		oreturn = new MenuButton(SudokuNext.getResources().oreturn);
		oreturn.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuNext.switchMainMenu();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		panel.add (oreturn);
		mainLabel = new JLabel(new ImageIcon(ImageManager.getImage(SudokuNext.getTheme(),"background_opt")));
		mainLabel.setBounds(0, 0, 800, 600);
		panel.add (mainLabel);
		add(panel);
	}
	
	private JPopupMenu themeMenu = null;
	private JLabel mainLabel = null;
	private JPanel panel;
	private MenuButton spinbgm;
	private MenuButton spinbgm_bg;
	private MenuButton spinse;
	private MenuButton spinse_bg;
	private MenuButton oticker;
	private MenuButton otickeroff;
	private MenuButton oreset;
	private MenuButton oreturn;
	private MenuButton othemes;
	private JSlider bgmControl;
	private JSlider seControl;
	private int showOn=0;
	private Timer gTimer;
}
