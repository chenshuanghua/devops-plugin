package com.unicom.mcloud.devops.plugin.build;

import java.util.Date;
import java.util.Map;

public class BuildDefinition {
    private String definitionId;

    private String definitionName;

    private String projectId;

    private String codeRepoId;

    private String pipilineTpId;

    private String pipelineTpType;

    private String triggerType;

    private String notificationType;

    private Boolean building;

    private Integer estimatedDuration = 0;

    private String lastBuildId;

    private Date lastSuccessful;

    private Date lastFailed;

    private Integer lastDuration = 0;

    private Integer totalSuccessful = 0;

    private Integer totalFailed = 0;

    private Integer totalRecovery = 0;

    private Integer averageDuration = 0;

    private Integer averageRecovery = 0;

    private String delFlag;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String tenantId;

    private String definitionParams;

    private String description;

    private String triggerRegex;

    private String retention;

    private String variableDefine;

    private String notificationRegex;
    
    private Map<String, Object> pipelineParamMap;

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId == null ? null : definitionId.trim();
    }

    public String getDefinitionName() {
        return definitionName;
    }

    public void setDefinitionName(String definitionName) {
        this.definitionName = definitionName == null ? null : definitionName.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getCodeRepoId() {
        return codeRepoId;
    }

    public void setCodeRepoId(String codeRepoId) {
        this.codeRepoId = codeRepoId == null ? null : codeRepoId.trim();
    }

    public String getPipilineTpId() {
        return pipilineTpId;
    }

    public void setPipilineTpId(String pipilineTpId) {
        this.pipilineTpId = pipilineTpId == null ? null : pipilineTpId.trim();
    }

    public String getPipelineTpType() {
        return pipelineTpType;
    }

    public void setPipelineTpType(String pipelineTpType) {
        this.pipelineTpType = pipelineTpType == null ? null : pipelineTpType.trim();
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType == null ? null : triggerType.trim();
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType == null ? null : notificationType.trim();
    }

    public Boolean getBuilding() {
        return building;
    }

    public void setBuilding(Boolean building) {
        this.building = building;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getLastBuildId() {
        return lastBuildId;
    }

    public void setLastBuildId(String lastBuildId) {
        this.lastBuildId = lastBuildId == null ? null : lastBuildId.trim();
    }

    public Date getLastSuccessful() {
        return lastSuccessful;
    }

    public void setLastSuccessful(Date lastSuccessful) {
        this.lastSuccessful = lastSuccessful;
    }

    public Date getLastFailed() {
        return lastFailed;
    }

    public void setLastFailed(Date lastFailed) {
        this.lastFailed = lastFailed;
    }

    public Integer getLastDuration() {
        return lastDuration;
    }

    public void setLastDuration(Integer lastDuration) {
        this.lastDuration = lastDuration;
    }

    public Integer getTotalSuccessful() {
        return totalSuccessful;
    }

    public void setTotalSuccessful(Integer totalSuccessful) {
        this.totalSuccessful = totalSuccessful;
    }

    public Integer getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(Integer totalFailed) {
        this.totalFailed = totalFailed;
    }

    public Integer getTotalRecovery() {
        return totalRecovery;
    }

    public void setTotalRecovery(Integer totalRecovery) {
        this.totalRecovery = totalRecovery;
    }

    public Integer getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Integer averageDuration) {
        this.averageDuration = averageDuration;
    }

    public Integer getAverageRecovery() {
        return averageRecovery;
    }

    public void setAverageRecovery(Integer averageRecovery) {
        this.averageRecovery = averageRecovery;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getDefinitionParams() {
        return definitionParams;
    }

    public void setDefinitionParams(String definitionParams) {
        this.definitionParams = definitionParams == null ? null : definitionParams.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTriggerRegex() {
        return triggerRegex;
    }

    public void setTriggerRegex(String triggerRegex) {
        this.triggerRegex = triggerRegex == null ? null : triggerRegex.trim();
    }

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention == null ? null : retention.trim();
    }

    public String getVariableDefine() {
        return variableDefine;
    }

    public void setVariableDefine(String variableDefine) {
        this.variableDefine = variableDefine == null ? null : variableDefine.trim();
    }

    public String getNotificationRegex() {
        return notificationRegex;
    }

    public void setNotificationRegex(String notificationRegex) {
        this.notificationRegex = notificationRegex == null ? null : notificationRegex.trim();
    }

    public Map<String, Object> getPipelineParamMap() {
        return pipelineParamMap;
    }

    public void setPipelineParamMap(Map<String, Object> pipelineParamMap) {
        this.pipelineParamMap = pipelineParamMap;
    }
}