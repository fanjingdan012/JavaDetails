/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-4
 */
package core;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;

import javax.swing.JFrame;

/**
 * ScreenManager�� 
 * ��Ļ������
 * @version 0.2
 */
public class ScreenManager {
	
	public static void getDisplayMode(GraphicsDevice _device)
	{
		windowScreenMode = _device.getDisplayMode();
		fullScreenMode = new DisplayMode(800,600,windowScreenMode.getBitDepth(),windowScreenMode.getRefreshRate());
	}
	
	/**
	 * ����ȫ��
	 * @param _frame ��Ҫȫ���Ĵ���
	 * @param _device �豸
	 */
	public static void setFullScreen(JFrame _frame, GraphicsDevice _device)
	{
		_device.setFullScreenWindow(_frame);
		_device.setDisplayMode(fullScreenMode);	
		//_frame.setAlwaysOnTop(true);
		fullScreen = true;
	}

	/**
	 * ���ô��ڻ�
	 * @param _frame ��Ҫ���ڻ��Ĵ���
	 * @param _device �豸
	 */
	public static void setWindow(JFrame _frame, GraphicsDevice _device)
	{
		_frame.setAlwaysOnTop(false);
		if (fullScreen) _device.setDisplayMode(windowScreenMode);
		_device.setFullScreenWindow(null);
		fullScreen = false;
	}
	
	private static DisplayMode windowScreenMode = null;
	private static DisplayMode fullScreenMode = null;
	private static boolean fullScreen = false;
}
