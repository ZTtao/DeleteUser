package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.lambda.demo.dto.RequestModel;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

	private static RequestModel input;

	@BeforeClass
	public static void createInput() throws IOException {
		// TODO: set up your sample input object here.
		input = null;
	}

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	@Test
	public void testLambdaFunctionHandler() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Context ctx = createContext();
		input = new RequestModel();
		List<Integer> list = new ArrayList<>();
		list.add(8);
		list.add(9);
		input.setIds(list);
//		input.setToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDUyMzA3NzMsInN1YiI6IntcInVzZXJOYW1lXCI6XCJhZG1pblwiLFwidXNlcklkXCI6NH0iLCJleHAiOjE1MDUyMzQzNzN9.jbhdguKXBV25whw4HRW81JUkXd4pDgy8wge8Olcywlc");
		String output = handler.handleRequest(input, ctx);
		System.out.println(output);
		// TODO: validate output here if needed.
		// Assert.assertEquals("Hello from Lambda!", output);
	}
}
