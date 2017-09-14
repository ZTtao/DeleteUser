package com.amazonaws.lambda.demo.dto;

import java.util.List;

public class RequestModel {
	private BaseInfo baseInfo;
	private List<Integer> ids;

	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
