import com.sun.tools.attach.VirtualMachine;

public class A {
  public static void main(String[] args) throws Exception {

    // attach方法参数为目标应用程序的进程号
    VirtualMachine vm = VirtualMachine.attach("4988");
    // 请用你自己的agent绝对地址，替换这个
    vm.loadAgent(
        "/Users/i312177/githubP/JavaDetails/call-graph/target/call-graph-1.0-SNAPSHOT-jar-with-dependencies.jar");

  }
}