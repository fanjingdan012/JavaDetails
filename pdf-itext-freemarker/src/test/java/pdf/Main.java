package pdf;

import pdf.component.PDFHeaderFooter;
import pdf.component.PDFKit;
import pdf.util.FreeMarkerUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static RegulationBO prepareData() {
    RegulationBO bo = new RegulationBO();
    bo.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
    bo.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
    bo.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
    bo.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
    bo.setImageUrl(
        "http://mss.vip.sankuai.com/v1/mss_74e5b6ab17f44f799a524fa86b6faebf/360report/logo_1.png");
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
    for (int i = 0; i < 100; i++) {
      RequirementBO r = new RequirementBO();
      r.setName("requirement" + i);
      r.setStatus("Draft");
      requirements.add(r);
    }
    bo.setRequirements(requirements);
    return bo;
  }



  public static void main(String[] args) {

    RegulationBO bo = prepareData();
    for (int i = 0; i < bo.getQuestions().size(); i++) {
      if (bo.getQuestions().get(i) instanceof ChooseBO) {
        String htmlData = FreeMarkerUtil.getContent("choose.ftl", bo.getQuestions().get(i));
        bo.getQas().add(htmlData);
      } else if (bo.getQuestions().get(i) instanceof TextBO) {
        String htmlData = FreeMarkerUtil.getContent("text.ftl", bo.getQuestions().get(i));
        bo.getQas().add(htmlData);
      }
    }

    try {

      PDFHeaderFooter headerFooter = new PDFHeaderFooter();
      PDFKit pdfkit = new PDFKit();
      pdfkit.setHeaderFooterBuilder(headerFooter);
      String htmlData = FreeMarkerUtil.getContent("record.ftl", bo);
      String saveFilePath = pdfkit.exportToFile("record.pdf", bo, htmlData);
      System.out.println(saveFilePath);
    } catch (Exception e) {
      System.out.println("PDF generate failed{}");
      e.printStackTrace();

    }


  }
}
