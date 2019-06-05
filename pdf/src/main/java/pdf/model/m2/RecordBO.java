package pdf.model.m2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordBO {

  private List<EvaluationResultBO> evaluationResults = new ArrayList<>();
  private List<QuestionBO> questionsAnswered = new ArrayList<>();
  private List<QuestionBO> questionsNotAnswered = new ArrayList<>();
  private List<RecordBO> linkedRecords = new ArrayList<>();
  private List<OwnerBO> owners = new ArrayList<>();
  private String name;
  private Integer businessId;
  private Integer version;
  private String recordTypeName;
  private String template;
  private String description;
  private String createdBy;
  private String evaluationResult;

  private Date createdAt;

  private String updatedBy;

  private Date updatedAt;
  private String category;
  private String status;

  private String iTextUrl = "https://itextpdf.com/en";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getiTextUrl() {
    return iTextUrl;
  }

  public void setiTextUrl(String iTextUrl) {
    this.iTextUrl = iTextUrl;
  }

  public List<EvaluationResultBO> getEvaluationResults() {
    return evaluationResults;
  }

  public void setEvaluationResults(List<EvaluationResultBO> evaluationResults) {
    this.evaluationResults = evaluationResults;
  }

  public List<QuestionBO> getQuestionsAnswered() {
    return questionsAnswered;
  }

  public void setQuestionsAnswered(List<QuestionBO> questionsAnswered) {
    this.questionsAnswered = questionsAnswered;
  }

  public List<QuestionBO> getQuestionsNotAnswered() {
    return questionsNotAnswered;
  }

  public void setQuestionsNotAnswered(List<QuestionBO> questionsNotAnswered) {
    this.questionsNotAnswered = questionsNotAnswered;
  }

  public List<RecordBO> getLinkedRecords() {
    return linkedRecords;
  }

  public void setLinkedRecords(List<RecordBO> linkedRecords) {
    this.linkedRecords = linkedRecords;
  }

  public List<OwnerBO> getOwners() {
    return owners;
  }

  public void setOwners(List<OwnerBO> owners) {
    this.owners = owners;
  }

  public Integer getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Integer businessId) {
    this.businessId = businessId;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getRecordTypeName() {
    return recordTypeName;
  }

  public void setRecordTypeName(String recordTypeName) {
    this.recordTypeName = recordTypeName;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getEvaluationResult() {
    return evaluationResult;
  }

  public void setEvaluationResult(String evaluationResult) {
    this.evaluationResult = evaluationResult;
  }
}
