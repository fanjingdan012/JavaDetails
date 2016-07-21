package pariticles;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;


public class ParticlesSnow {

	private static int DEFAULT_NUM_PARTICLES = 30;
	private static int PARTICLES_MAX_LIFE = 150;
	private Random rand = new Random();
	protected ArrayList<Particle> particles = new ArrayList<Particle>();
	protected Vector pos1, pos2; // pos1表示显示飘落效果的左上角坐标，pos2为右下角坐标

	public ParticlesSnow(float x1, float y1, float x2, float y2) {
		this.pos1 = new Vector(x1, y1, 0);
		this.pos2 = new Vector(x2, y2, 0);
		for (int i = 0; i < DEFAULT_NUM_PARTICLES; i++) {
			particles.add(generateParticle());
		}
	}

	protected Particle generateParticle() {
		Vector volicity = new Vector(rand.nextFloat()*3-1.5f, 2f, 0);
		Vector pos = this.pos1.add(new Vector(rand.nextInt((int)pos2.getX()-(int)pos1.getX())+pos1.getX(), pos1.getY()+rand.nextInt((int)pos2.getY()-(int)pos1.getY())*0.6f,0));
		Particle part = new Particle(pos, volicity, "public", "particle" , rand.nextInt(80)); //通过设定不同粒子的已逝去生命达到随时生成/消失的效果
		return part;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < DEFAULT_NUM_PARTICLES; i++) {
			Particle part = particles.get(i);
			if (part.getLife()>=140)
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
			else if (part.getLife()>=145)
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f));
			else
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
			g.drawImage(part.getStuff(), (int)part.getPosition().getX(), (int)part.getPosition().getY(), null);
		}
	}

	public void update(long elapsedTime) {
		Particle part;
		for (int i = 0; i < DEFAULT_NUM_PARTICLES; i++) {
			part = particles.get(i);
			part.update(elapsedTime);
			if (part.getLife() > PARTICLES_MAX_LIFE) {
				particles.remove(i);
				particles.add(i, generateParticle());
			}
		}
	}

}
