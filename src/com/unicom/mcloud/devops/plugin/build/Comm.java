package com.unicom.mcloud.devops.plugin.build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Comm {
	
	public static String host = "http://10.124.133.190:8080/api/v1";
	
	public static String loginUrl() {
		String loginUrl = host + "/tenant/login";
		return loginUrl;
	}
	
	public static String projectListUrl() {
		String projectListUrl = host + "/projects";
		return projectListUrl;
	}
	
	public static String repoListUrl(String projectId) {
		String repoListUrl = host + "/project/" + projectId + "/coderepos";
		return repoListUrl;
	}
	
	public static String tagListUrl(String repoId) {
		String tagListUrl = host + "/coderepo/coderepos/"+repoId+"/tags";
		return tagListUrl;
	}
	
	public static String imageListUrl() {
		String tagListUrl = host + "/image/images?imageType=BASE_IMAGE";
		return tagListUrl;
	}
	
	public static String buildUrl() {
		String buildUrl = host+"/build/builddefs";
		return buildUrl;
	}
	
	public static String buildDefinitionListUrl(String projectId) {
		String buildDefinitionListUrl = host + "/build/builddefs?projectId=" + projectId;
		return buildDefinitionListUrl;
	}
	
	public static String executeUrl(String buildDefinitionId) {
		String executeUrl = host + "/build/builddefs/" + buildDefinitionId + "/actions/execute";
		return executeUrl;
	}
	
	public static String buildInstanceUrl(String buildInstanceId) {
		String buildInstanceUrl = host + "/build/builddefs/" + buildInstanceId;
		return buildInstanceUrl;
	}
	
	/**
	 * 获取构建实例
	 */
	
	public static String buildinstancesUrl(String buildInstanceId) {
		String buildinstancesListUrl = host + "/build/buildinstances/"+buildInstanceId;
		return buildinstancesListUrl;
	}
	
	/**
	 * 查询构建任务实例列表
	 */
	public static String stageinstancesListUrl(String buildInstanceId) {
		
		String stageinstancesListUrl = host + "/build/buildinstances/"+buildInstanceId+"/stageinstances";
		
		return stageinstancesListUrl;
	}
	
	
	/**
	 * 查询构建实例日志
	 */
	public static String stageinstancesListLogsUrl(String buildInstanceId,int start){
		String stageinstancesListLogsUrl = host + "/build/buildinstances/"+buildInstanceId+"/logs?start="+start;
	
		return stageinstancesListLogsUrl;
		
	}
	
	public static String[] jsonArrayToString(JSONArray object , String objectName) throws JSONException {
		String[] str = new String[object.length() + 1];
		str[0] = "请选择";
		for (int i = 0; i < object.length(); i++) {
			JSONObject project = object.getJSONObject(i);
			str[i + 1] = project.get(objectName).toString();
		}
		return str;
	}
	
	public static JSONObject[] jsonArrayToString(JSONArray object) throws JSONException {
		JSONObject[] str = new JSONObject[object.length() + 1];
		for (int i = 0; i < object.length(); i++) {
			JSONObject project = object.getJSONObject(i);
			str[i + 1] = project;
		}
		return str;
	}
	
	public static String getIdByName(JSONArray object , String objectName ,String keyName ,String keyId) throws JSONException {
		String repoId = null;
		for (int i = 0; i < object.length(); i++) {
			JSONObject repo = null;
				repo = object.getJSONObject(i);
				if (objectName.equals(repo.get(keyName).toString())) {
					repoId = repo.get(keyId).toString();				
			} 
		}
		return repoId;
	}

}
