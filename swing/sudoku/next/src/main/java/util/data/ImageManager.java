/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package util.data;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * ImageManager类 
 * 图像处理类
 * @version 0.1
 */
public class ImageManager {
	
	public static Image getImage(String theme ,String _filename)
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return kit.getImage("./theme/"+theme+"/"+_filename+".png");
	
	}
	
	/**
	 * 获取缩放过的图像
	 * @param _theme 主题
	 * @param _filename 文件名
	 * @param _width 宽度
	 * @return 缩放后的图像
	 */
	public static Image getRandomScaledImage(String _theme , String _filename , int _width)
	{
		Image x = getImage(_theme, _filename);
		Random rand = new Random();
		float r = rand.nextFloat()*0.5f;
		return x.getScaledInstance((int)(_width*(0.5+r)), (int)(_width*(0.5+r)), Image.SCALE_SMOOTH);
	}
}
