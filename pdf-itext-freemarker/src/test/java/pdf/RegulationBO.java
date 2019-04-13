package pdf;

import java.util.ArrayList;
import java.util.List;

public class RegulationBO {

    private String templateName;

    private String freeMarkerUrl;

    private String ITEXTUrl;

    private String JFreeChartUrl;

    private List<String> scores;
    private List<String> qas = new ArrayList<String>();
    private List<QuestionBO> questions;
    private List<RequirementBO> requirements;

    private String imageUrl;

    private String picUrl;

    private String scatterUrl;
    private String name;
    private String description;
    private String validPeriod;
    private String category;
    private String status;
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFreeMarkerUrl() {
        return freeMarkerUrl;
    }

    public void setFreeMarkerUrl(String freeMarkerUrl) {
        this.freeMarkerUrl = freeMarkerUrl;
    }

    public String getITEXTUrl() {
        return ITEXTUrl;
    }

    public void setITEXTUrl(String ITEXTUrl) {
        this.ITEXTUrl = ITEXTUrl;
    }

    public String getJFreeChartUrl() {
        return JFreeChartUrl;
    }

    public void setJFreeChartUrl(String JFreeChartUrl) {
        this.JFreeChartUrl = JFreeChartUrl;
    }

    public List<String> getScores() {
        return scores;
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }

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

    public String getScatterUrl() {
        return scatterUrl;
    }

    public void setScatterUrl(String scatterUrl) {
        this.scatterUrl = scatterUrl;
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
