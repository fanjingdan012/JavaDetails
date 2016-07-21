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
 * ScreenManager类 
 * 屏幕管理器
 * @version 0.2
 */
public class ScreenManager {
	
	public static void getDisplayMode(GraphicsDevice _device)
	{
		windowScreenMode = _device.getDisplayMode();
		fullScreenMode = new DisplayMode(800,600,windowScreenMode.getBitDepth(),windowScreenMode.getRefreshRate());
	}
	
	/**
	 * 设置全屏
	 * @param _frame 需要全屏的窗体
	 * @param _device 设备
	 */
	public static void setFullScreen(JFrame _frame, GraphicsDevice _device)
	{
		_device.setFullScreenWindow(_frame);
		_device.setDisplayMode(fullScreenMode);	
		//_frame.setAlwaysOnTop(true);
		fullScreen = true;
	}

	/**
	 * 设置窗口化
	 * @param _frame 需要窗口化的窗体
	 * @param _device 设备
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
