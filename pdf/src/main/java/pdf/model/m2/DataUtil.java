package pdf.model.m2;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataUtil {
  public static RecordBO prepareData() {
    RecordBO bo = new RecordBO();
    bo.setStatus("Published");
    bo.setName("Hire to retire");
    bo.setDescription("This is the record for the Business process hire to retire");
    bo.setCreatedAt(new Date());
    bo.setCreatedBy("User A");
    bo.setUpdatedAt(new Date());
    bo.setUpdatedBy("User A");
    bo.setVersion(2);
    bo.setBusinessId(1251);
    List<QuestionBO> questionsAnswered = new ArrayList<>();
    QuestionBO q1 = new QuestionBO();
    q1.setQuestion("Outside EU or International Organisation:");
    q1.setAnswer("No(2)");
    questionsAnswered.add(q1);

    QuestionBO q2 = new QuestionBO();
    q2.setQuestion("Process Description:");
    q2.setAnswer(
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.");
    questionsAnswered.add(q2);

    QuestionBO q3 = new QuestionBO();
    q3.setQuestion("Data Subject:");
    q3.setAnswer("All (1), Nothing (2)");
    questionsAnswered.add(q3);

    QuestionBO q4 = new QuestionBO();
    q4.setQuestion("Deletion Period according to Retention Policy:");
    q4.setAnswer("Yes(1)");
    questionsAnswered.add(q4);

    QuestionBO q5 = new QuestionBO();
    q5.setQuestion("Data are evaluted:");
    q5.setAnswer("No(2)");
    questionsAnswered.add(q5);

    QuestionBO q6 = new QuestionBO();
    q6.setQuestion("Legal Basis:");
    q6.setAnswer("Others(05)");
    questionsAnswered.add(q6);

    List<QuestionBO> questionsNotAnswered = new ArrayList<>();
    QuestionBO q7 = new QuestionBO();
    q7.setQuestion("Blocking Concept:");
    questionsNotAnswered.add(q7);

    QuestionBO q8 = new QuestionBO();
    q8.setQuestion("Deletion Period according to Retention Policy:");
    questionsNotAnswered.add(q7);
    bo.setQuestionsAnswered(questionsAnswered);
    bo.setQuestionsNotAnswered(questionsNotAnswered);

    List<EvaluationResultBO> evaluationResults = new ArrayList<>();
    EvaluationResultBO evaluationResult = new EvaluationResultBO();
    evaluationResult.setDate(new Date());
    evaluationResult.setDescription("There are some critical answers in the form. Please provide a DPIA in addition.");
    evaluationResult.setName("This Record");
    evaluationResult.setSummary("DPIA required");
    evaluationResults.add(evaluationResult);
    bo.setEvaluationResults(evaluationResults);


    List<RecordBO> linkedRecords = new ArrayList<>();
    RecordBO linkedRecord = new RecordBO();
    linkedRecord.setRecordTypeName("DPIA");
    linkedRecord.setBusinessId(12354646);
    linkedRecord.setName("Record Name");
    linkedRecords.add(linkedRecord);
    bo.setLinkedRecords(linkedRecords);

    List<OwnerBO> owners = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      OwnerBO r = new OwnerBO();
      r.setId("judy.fan@sap.com");
      r.setType("Business Owner");
      r.setName("Judy");
      owners.add(r);
    }
    bo.setOwners(owners);
    bo.setEvaluationResult("DPIA required");
    bo.setRecordTypeName("Records of Processing Activities");
    bo.setTemplate("Records of Processing Activities Version 1");
    return bo;
  }
}
