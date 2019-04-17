package pdf.util;

import pdf.model.ChooseBO;
import pdf.model.QuestionBO;
import pdf.model.RegulationBO;
import pdf.model.RequirementBO;
import pdf.model.TextBO;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {
  public static RegulationBO prepareData() {
    RegulationBO bo = new RegulationBO();
    //    FreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
    //    ITEXTUrl("http://developers.itextpdf.com/examples-itext5");
    //    JFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
    bo.setCategory("GDPR");
    bo.setStatus("Draft");
    bo.setName("SOX");
    bo.setDescription(
        "The regulation applies if the data controller (an organisation that collects data from "
            + "EU residents), or processor (an organisation that processes data on behalf of a "
            + "data controller like cloud service providers), or the data subject (person) is "
            + "based in the EU.");
    bo.setValidPeriod("Dec 9, 2019 - Dec 19, 2019");
    List<QuestionBO> questionBOS = new ArrayList<QuestionBO>();
    ChooseBO q1 = new ChooseBO();
    q1.setQuestion("Question1");
    q1.setAnswerNum(1);
    q1.setAnswerText("Yes");
    questionBOS.add(q1);
    TextBO q2 = new TextBO();
    q2.setQuestion("Question1");
    q2.setAnswerText("案件是的骄傲的凯撒角度看就撒活动课教案肯德基萨科技的凯撒觉得");
    questionBOS.add(q2);
    bo.setQuestions(questionBOS);

    List<RequirementBO> requirements = new ArrayList<RequirementBO>();
    for (int i = 0; i < 10; i++) {
      RequirementBO r = new RequirementBO();
      r.setCreator("judy.fan@sap.com");
      r.setName("requirement "+i);
      r.setDescription1("somelooooooooooooooooooooooooooooooglongemail@somecompany.com"
          + " very long text very long text very long text very long text very long text very long"
          + " text very long text very long text very long text very long text very long text");
      r.setStatus("Draft");
      requirements.add(r);
    }
    bo.setRequirements(requirements);
    return bo;
  }
}
