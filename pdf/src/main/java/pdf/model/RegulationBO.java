package pdf.model;

import java.util.ArrayList;
import java.util.List;

public class RegulationBO {

  private List<String> qas = new ArrayList<String>();
  private List<QuestionBO> questions;
  private List<RequirementBO> requirements;

  private String imageUrl;
  private String picUrl;
  private String name;
  private String description;
  private String validPeriod;
  private String category;
  private String status;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public List<String> getQas() {
    return qas;
  }

  public void setQas(List<String> qas) {
    this.qas = qas;
  }

  public List<QuestionBO> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionBO> questions) {
    this.questions = questions;
  }

  public List<RequirementBO> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<RequirementBO> requirements) {
    this.requirements = requirements;
  }

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

  public String getValidPeriod() {
    return validPeriod;
  }

  public void setValidPeriod(String validPeriod) {
    this.validPeriod = validPeriod;
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
}
