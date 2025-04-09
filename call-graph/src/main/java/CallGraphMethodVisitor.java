import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class CallGraphMethodVisitor extends AdviceAdapter {
  private final String methodName;

  protected CallGraphMethodVisitor(int api, MethodVisitor methodVisitor, String methodName) {
    super(api, methodVisitor, 0, methodName, null);

    this.methodName = methodName;
  }

  @Override
  protected void onMethodEnter() {
    mv.visitLdcInsn(methodName);
    mv.visitMethodInsn(INVOKESTATIC, "CallGraphLogger", "logMethodEntry", "(Ljava/lang/String;)V", false);
    // mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    // mv.visitLdcInsn("Calling method: " + owner + "." + name);
    // mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

    // Call the original method

  }
}