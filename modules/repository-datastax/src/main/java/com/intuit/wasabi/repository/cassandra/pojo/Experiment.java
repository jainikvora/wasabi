package com.intuit.wasabi.repository.cassandra.pojo;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Date;
import java.util.UUID;

/*****
 * Watchouts:
 * 1) all getters and setters must be public
 * 2) must have a public default constructor
 * 3) for boolean method, uses setXXXX for set value, but isXXXX for get value
 */

@Table(name="experiment")
public final class Experiment {

    @PartitionKey
    private UUID id;

    private String description;

    @Column(name="sample_percent")
    private double samplePercent;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    private String state;

    @Column(name="app_name")
    private String appName;

    private Date created;

    private Date modified;

    private String rule;

    @Column(name="model_name")
    private String modelName;

    @Column(name="model_version")
    private String modelVersion;

    @Column(name="is_personalized")
    private boolean personalized;

    @Column(name="is_rapid_experiment")
    private boolean rapidExperiment;

    @Column(name="user_cap")
    private int userCap;

    @Column(name="creatorid")
    private String creatorId;

    public Experiment() {} //require this by com.datastax.driver.mapping.ReflectionMapper.newEntity

    public Experiment(UUID id, String description, double samplePercent, Date startTime, Date endTime, String state, String appName, Date created, Date modified, String rule, String modelName, String modelVersion, boolean personalized, boolean rapidExperiment, int userCap, String creatorId) {
        this.id = id;
        this.description = description;
        this.samplePercent = samplePercent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.appName = appName;
        this.created = created;
        this.modified = modified;
        this.rule = rule;
        this.modelName = modelName;
        this.modelVersion = modelVersion;
        this.personalized = personalized;
        this.rapidExperiment = rapidExperiment;
        this.userCap = userCap;
        this.creatorId = creatorId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getSamplePercent() {
        return samplePercent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getState() {
        return state;
    }

    public String getAppName() {
        return appName;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public String getRule() {
        return rule;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public boolean isPersonalized() {
        return personalized;
    }

    public boolean isRapidExperiment() {
        return rapidExperiment;
    }

    public int getUserCap() {
        return userCap;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSamplePercent(double samplePercent) {
        this.samplePercent = samplePercent;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public void setPersonalized(boolean personalized) {
        this.personalized = personalized;
    }

    public void setRapidExperiment(boolean rapidExperiment) {
        this.rapidExperiment = rapidExperiment;
    }

    public void setUserCap(int userCap) {
        this.userCap = userCap;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experiment that = (Experiment) o;

        if (Double.compare(that.samplePercent, samplePercent) != 0) return false;
        if (personalized != that.personalized) return false;
        if (rapidExperiment != that.rapidExperiment) return false;
        if (userCap != that.userCap) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (rule != null ? !rule.equals(that.rule) : that.rule != null) return false;
        if (modelName != null ? !modelName.equals(that.modelName) : that.modelName != null) return false;
        if (modelVersion != null ? !modelVersion.equals(that.modelVersion) : that.modelVersion != null) return false;
        return creatorId != null ? creatorId.equals(that.creatorId) : that.creatorId == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(samplePercent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + appName.hashCode();
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        result = 31 * result + (modelVersion != null ? modelVersion.hashCode() : 0);
        result = 31 * result + (personalized ? 1 : 0);
        result = 31 * result + (rapidExperiment ? 1 : 0);
        result = 31 * result + userCap;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", samplePercent=" + samplePercent +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state='" + state + '\'' +
                ", appName='" + appName + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", rule='" + rule + '\'' +
                ", modelName='" + modelName + '\'' +
                ", modelVersion='" + modelVersion + '\'' +
                ", personalized=" + personalized +
                ", rapidExperiment=" + rapidExperiment +
                ", userCap=" + userCap +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }
}