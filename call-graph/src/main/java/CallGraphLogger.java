import java.util.HashSet;
import java.util.Set;

public class CallGraphLogger {
  private static final Set<String> methodCalls = new HashSet<>();

  public static void logMethodEntry(String methodName) {
    methodCalls.add(methodName);
    System.out.println("Method called: " + methodName);
  }

  public static Set<String> getMethodCalls() {
    return methodCalls;
  }
}