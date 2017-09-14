package com.amazonaws.lambda.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.lambda.demo.dto.RequestModel;
import com.amazonaws.lambda.demo.dto.User;
import com.amazonaws.lambda.demo.service.IUserService;
import com.amazonaws.lambda.demo.service.impl.UserServiceImpl;
import com.amazonaws.lambda.demo.util.BaseInfoRecorder;
import com.amazonaws.lambda.demo.util.TokenUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<RequestModel, String> {

	@Override
	public String handleRequest(RequestModel input, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("[" + new Date() + "]Input: " + JSONObject.toJSONString(input));
		String token = input.getBaseInfo().getToken();
		logger.log("[" + new Date() + "]token: " + token);
		User user = TokenUtil.validateToken(token);
		Map<String, Object> map = new HashMap<>();
		if (user == null) {
			map.put("success", false);
			map.put("userName", "");
		} else {
			IUserService userService = new UserServiceImpl();
			try {
				userService.deleteUserById(input.getIds(), context);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("message", "exception.");
			}
		}
		logger.log("[" + new Date() + "]deleteUser:" + map);
		// 访问信息记录
		BaseInfoRecorder.record(user == null ? "unknow" : user.getUserId().toString(), input.getBaseInfo(),
				"[" + new Date() + "]deleteUser:" + JSONObject.toJSONString(map));
		return JSONObject.toJSONString(map);
	}

}
