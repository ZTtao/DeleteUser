package com.amazonaws.lambda.demo.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.amazonaws.lambda.demo.service.IUserService;
import com.amazonaws.lambda.demo.util.Constant;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class UserServiceImpl implements IUserService {

	@Override
	public boolean deleteUserById(List<Integer> idList,Context context) throws Exception {
		LambdaLogger logger = context.getLogger();
		if (idList != null && !idList.isEmpty()) {
			Class.forName(Constant.MYSQL_DRIVER);
			Connection conn = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USER,
					Constant.MYSQL_PASSWORD);
			if (!conn.isClosed()) {
				logger.log("[" + new Date() + "]connect to database success.");
			}
			Statement statement = conn.createStatement();
			String sql = "delete from user where user_id in (";
			for (int i = 0; i < idList.size(); i++) {
				sql += "'" + idList.get(i) + "',";
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			logger.log("[" + new Date() + "]sql:" + sql);
			statement.execute(sql);
			conn.close();
			return true;
		}
		return true;
	}

}
