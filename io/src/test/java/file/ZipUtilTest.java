package file;
import org.junit.Test;
import java.util.List;
public class ZipUtilTest {

    @Test
    public void testUnzip() throws Exception {
        ZipUtil.unzip("zipFile", "dest", "passwd");
    }

    @Test
    public void testUnzip1() {
        //00075235
        for (int i = 75235; i < 11111111; i++) {
            String password = "" + i;
            if (password.length() == 7) {
                password = "0" + password;
            }
            if (password.length() == 6) {
                password = "00" + password;
            }
            if (password.length() == 5) {
                password = "000" + password;
            }
            if (password.length() == 4) {
                password = "0000" + password;
            }
            if (password.length() == 3) {
                password = "00000" + password;
            }
            if (password.length() == 2) {
                password = "000000" + password;
            }
            if (password.length() == 1) {
                password = "0000000" + password;
            }
            try {
                ZipUtil.unzip("D:\\githubP\\JavaDetails\\io\\1.zip", "zip", password);
                System.out.println(password);
                break;
            } catch (Exception e) {
                System.out.println(password + "wrong");
            }
        }
    }

    @Test
    public void checkZipPassword() {
        List<String> passwords = FileUtil.readTxtFileIntoStringArrList("io/pwds.txt");
        for (String password : passwords) {
            try {
                ZipUtil.unzip("io/1.zip", "zip", password);
                System.out.println(password);
                break;
            } catch (Exception e) {
                System.out.println(password + "wrong");
            }
        }
    }
}
