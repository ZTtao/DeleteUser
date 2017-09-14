package com.amazonaws.lambda.demo.service;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;

public interface IUserService {
	boolean deleteUserById(List<Integer> idList,Context context) throws Exception;
}
