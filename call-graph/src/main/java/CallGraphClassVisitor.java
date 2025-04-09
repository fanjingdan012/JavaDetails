import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class CallGraphClassVisitor extends ClassVisitor {
  public CallGraphClassVisitor(int api, ClassVisitor classVisitor) {
    super(api, classVisitor);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
    return new CallGraphMethodVisitor(api, mv, name);
  }
}