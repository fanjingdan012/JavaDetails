/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package pariticles;


import java.awt.Image;

import util.data.ImageManager;


/**
 * Particle类 
 * 粒子
 * @version 0.1
 */

public class Particle {

	private Vector position;

	private Vector velocity;

	private int life;

	private Image stuff;

	/**
	 *  构造函数
	 * @param pos 位置
	 * @param velocity 速度
	 * @param _stuffname 图片
	 * @param string 
	 * @param life 生命
	 */
	public Particle(Vector pos, Vector velocity,  String string, String _stuffname, int life) {
		
		this.position = pos;
		
		this.velocity = velocity;
		
		this.stuff = ImageManager.getRandomScaledImage(string, _stuffname,50);
		
		if (life > 0)
			this.life = life;
	}
	
	/**
	 * 更新粒子
	 * @param elapsedTime
	 */
	public  void update(long elapsedTime)
	{
		position = position.add(velocity);
		life += elapsedTime;
	}

	public int getLife() {
		return life;
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Image getStuff() {
		return stuff;
	}

	public void setStuff(Image _stuff) {
		this.stuff = _stuff;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
}