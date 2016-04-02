package com.sensedia.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sensedia.model.Model;
import com.sensedia.service.HazelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renan on 01/04/16.
 */
public class HazelServlet extends HttpServlet {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String APPLICATION_JSON = "application/json";
	GsonBuilder builder = new GsonBuilder();
	Gson gson;

	@Override
	public void init() throws ServletException {
		System.out.println("Init HazelServlet");

		HazelService.init();
		gson = builder.create();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String body = CharStreams.toString(request.getReader());

		Object result = new Object();
		if (request.getMethod().equals(GET)) {
			result = HazelService.getAll();
		} else if (request.getMethod().equals(POST)) {
			Model model = gson.fromJson(body, Model.class);
			HazelService.put(model);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("RESULT", "SUCCESS");
			result = map;
		}

		response.setContentType(APPLICATION_JSON);
		String json = gson.toJson(result);
		response.getWriter().print(json);
	}
}
