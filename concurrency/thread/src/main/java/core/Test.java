package core;

public class Test {
  static Boolean b = false;

  synchronized static void print(int x) {
    System.out.println(x);
    b = !b;
  }

  public static void main(String[] args) {

    new Thread(() -> {
      try {
        if (b) {
          print(1);
        }
        b.notify();
        b.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        if (!b) {
          print(2);
        }
        b.notify();
        b.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}