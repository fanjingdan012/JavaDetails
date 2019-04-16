
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Main {
    
    private static final String TEMPLATE_PATH = "src/main/resources/templates";
    
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = getDataMap();
            // step4 加载模版文件
            Template template = configuration.getTemplate("stringFreeMarker.ftl");
            // step5 生成数据
            out = new OutputStreamWriter(System.out);
            // step6 输出文件
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static Map<String, Object> getDataMap() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", "itdragon博客");
        dataMap.put("dateTime", new Date());

        List<User> users = new ArrayList<User>();
        users.add(new User(1, "ITDragon 博客"));
        users.add(new User(2, "欢迎"));
        users.add(new User(3, "You！"));
        dataMap.put("users", users);
        return dataMap;
    }
}