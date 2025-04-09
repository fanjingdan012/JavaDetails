import java.lang.instrument.Instrumentation;

public class CallGraphAgent {
  public static void premain(String agentArgs, Instrumentation inst) {
    inst.addTransformer(new CallGraphTransformer());
  }
}