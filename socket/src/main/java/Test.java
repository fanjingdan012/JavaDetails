import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputListener;
//import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
/*import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
 import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;*/
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Test extends JFrame implements MouseInputListener, ActionListener {
    JLabel guanggao, beijing, wenzi, shezhi, zhanghaowb, qq1, dengluzhuangtai;
    // JTextField zhanghao;

    JPopupMenu haoma;
    JComboBox zhanghao;
    JPasswordField mima;
    JCheckBox jizhumima, zidongdenglu;
    JButton denglu, chashamuma;
    JProgressBar jpb;
    SimThread activity;
    Timer activityMonitor;
    String name, qq;
    Socket s;

    public Test() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        }
        chashamuma = new JButton("查杀木马");
        chashamuma.setBounds(240, 155, 85, 20);
        this.add(chashamuma);
        jpb = new JProgressBar();
        jpb.setStringPainted(true);
        jpb.setBounds(100, 240, 200, 15);
        this.add(jpb);
        chashamuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpb.setMaximum(1000);// 设置进度栏的最大值
                activity = new SimThread(1000);
                activity.start();// 启动线程
                activityMonitor.start();// 启动定时器
                chashamuma.setEnabled(false);// 禁止按钮
            }
        });
        activityMonitor = new Timer(100, new ActionListener() {// 每0.5秒执行一次
                    @Override
                    public void actionPerformed(ActionEvent e) {// 以下动作将在事件调度线程中运行，十分安全
                        int current = activity.getCurrent();// 得到线程的当前进度
                        jpb.setValue(current);// 更新进度栏的值
                        if (current == activity.getTarget()) {// 如果到达目标值
                            activityMonitor.stop();// 终止定时器
                            chashamuma.setEnabled(true);// 激活按钮
                        }
                    }
                });
        dengluzhuangtai = new JLabel(new ImageIcon("zaixianzhuangtai.jpg"));
        dengluzhuangtai.setBounds(75, 145, 35, 30);
        this.add(dengluzhuangtai);
        dengluzhuangtai.addMouseListener(this);
        denglu = new JButton("登录");
        denglu.setBounds(140, 155, 80, 20);
        this.add(denglu);
        this.setAlwaysOnTop(true);
        zidongdenglu = new JCheckBox("自动登录");
        zidongdenglu.setBounds(200, 190, 100, 30);
        this.add(zidongdenglu);
        jizhumima = new JCheckBox("记住密码");
        jizhumima.setBounds(100, 190, 100, 30);
        // jizhumima.setBackground(new Color(228, 244, 255));
        this.add(jizhumima);

        haoma = new JPopupMenu();

        /*
         * zhanghao = new JTextField(20);
         * zhanghao.setBounds(120, 78, 135, 20);
         * zhanghao.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
         * zhanghao.setFont(new Font("宋体",Font.PLAIN,13));
         * this.add(zhanghao);
         */
        // zhanghaowb = new JLabel(new ImageIcon("2.png"));
        // zhanghaowb.setBounds(90, 73, 194, 31);
        // jiantou = new JLabel(new ImageIcon("baijiantou.png"));
        // jiantou.setBounds(256, 78, 23, 21);
        // jiantou.addMouseListener(this);
        // this.add(jiantou);
        // this.add(zhanghaowb);
        chashamuma.addActionListener(this);
        mima = new JPasswordField();
        mima.setEchoChar('*');
        mima.setFont(new Font("宋体", Font.PLAIN, 13));
        mima.setBounds(100, 113, 150, 20);
        this.add(mima);

        zhanghao = new JComboBox();
        zhanghao.setEditable(true);
        zhanghao.setBounds(100, 78, 150, 20);
        zhanghao.setFont(new Font("宋体", Font.PLAIN, 13));
        this.add(zhanghao);
        guanggao = new JLabel(new ImageIcon("guanggao.gif"));
        guanggao.setBounds(0, 0, 334, 64);
        beijing = new JLabel(new ImageIcon("beijing.jpg"));
        beijing.setBounds(0, 64, 334, 154);
        wenzi = new JLabel(new ImageIcon("wenzi.jpg"));
        wenzi.setBounds(30, 75, 50, 100);

        denglu.addActionListener(this);
        // zhanghaowb.addMouseListener(this);
        // zhanghao.addMouseListener(this);
        this.add(wenzi);
        this.add(beijing);
        this.setLayout(null);
        this.add(guanggao);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setSize(340, 250);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        /*
         * JFrame.setDefaultLookAndFeelDecorated(true);
         * try {
         * UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel()) ;
         * UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
         * 
         * } catch (Exception e) {
         * System.out.println("Substance Raven Graphite failed to initialize");
         * }
         * SwingUtilities.invokeLater(new Runnable() {
         * public void run() {
         * QQLogin w = new QQLogin();
         * w.setVisible(true);
         * }
         * });
         */
        new Test();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == dengluzhuangtai) {
            dengluzhuangtai.setIcon(new ImageIcon("zaixianzhuangtaidian.jpg"));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == dengluzhuangtai) {
            dengluzhuangtai.setIcon(new ImageIcon("zaixianzhuangtai.jpg"));

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO 自动生成方法存根

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO 自动生成方法存根

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO 自动生成方法存根

    }

    public class liaotianchuangkou {

    }

    class SimThread extends Thread {// 线程类
        private int current;// 进度栏的当前值
        private final int target;// 进度栏的最大值

        public SimThread(int t) {
            current = 0;
            target = t;
        }

        public int getTarget() {
            return target;
        }

        public int getCurrent() {
            return current;
        }

        @Override
        public void run() {// 线程体
            try {
                while (current < target && !interrupted()) {// 如果进度栏的当前值小于目标值并且线程没有被中断
                    sleep(10);
                    current++;
                    if (current == 700) {
                        sleep(3000);
                    } else if (current == 730) {
                        sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chashamuma) {
            this.setBounds(300, 300, 340, 300);
        } else if (e.getSource() == denglu) {
            String zh = (String) zhanghao.getSelectedItem();
            System.out.println(zhanghao.getSelectedItem());
            // System.out.println(zhanghao.getItemAt(0));
            char[] str = mima.getPassword();
            String mima = String.valueOf(str);
            ;
            System.out.println(mima);
            // Sql login = new Sql();

            // if(login.login(zh,mima))
            // {
            try {
                s = new Socket("127.0.0.1", 8888);
                System.out.println(s);
                PrintWriter pw;
                Scanner sc;
                pw = new PrintWriter(s.getOutputStream(), true);
                sc = new Scanner(s.getInputStream());
                String str2 = "login#289872400198724#" + zh + "#289872400198724#" + mima;
                System.out.println(str2);
                pw.println(str2);

                String str3 = sc.nextLine();
                String yanzheng[] = str3.split("#");
                System.out.println(str3);
                if (yanzheng[0].equals("true")) {
                    System.out.println("登陆成功!");
                    name = yanzheng[1];
                    qq = yanzheng[2];

                    // this.setVisible(false);

                    // Thread.sleep(5000);
                    System.out.println("woao" + name);
                    System.out.println("woai" + qq);

                    // Logined logined = new Logined(name,qq);
                    this.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "用户名或密码错误!", "用户名或密码错误!", 0);
                }

            } catch (UnknownHostException e2) {
                // TODO 自动生成 catch 块
                e2.printStackTrace();
            } catch (IOException e2) {
                // TODO 自动生成 catch 块
                e2.printStackTrace();
            }

            /*
             * try {
             * login.rs =
             * login.stat.executeQuery("select * from qquser where username='"+zh+"' and password = '"+mima+"'");
             * boolean flag = login.rs.next();
             * if(flag == true)
             * {
             * name = login.rs.getString("name");
             * qq = login.rs.getString("username");
             * }
             * else
             * {
             * 
             * }
             */

            // } catch (SQLException e1) {
            // TODO 自动生成 catch 块
            // e1.printStackTrace();
            // }

        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误", "输入错误", 0);
        }
        // this.setVisible(false);
        // new Logined();

    }

}