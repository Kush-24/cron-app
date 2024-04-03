package com.example.cron.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.example.cron.entity.CronRequest;
import com.example.cron.entity.ParseCronExpressionResponse;
import com.example.cron.handler.FieldHandlerRegistory;
import com.example.cron.utility.CronUtility;
import com.example.cron.utility.InvalidExpressionException;

import java.util.Map;


@Service
public class CronServiceImpl implements CronService{

	@Override
	public ParseCronExpressionResponse generateCronInReadableForm(CronRequest request)
			throws InvalidExpressionException {
		ParseCronExpressionResponse response = new ParseCronExpressionResponse();

		String cronExpression = request.getCronExpression();

		System.out.println("cronExpression:: " + cronExpression);

		if (!CronUtility.validateCronExpression(cronExpression)) {
			System.out.println("Invalid cron expression. Please provide a valid cron expression.");
			throw new InvalidExpressionException("Invalid cron expression.");
		}
		String[] fields = cronExpression.split("\\s+");
		String[] labels = CronUtility.labels;
		Map<String, String> map = new LinkedHashMap<>();
		// fetch all fields one by one and make the result
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			String label = labels[i];
			String result=FieldHandlerRegistory.runHandlers(field, label);
			map.put(labels[i], result);
		}
		response.setMap(map);
		System.out.println(response.getMap().toString());
		
		return response;
	}

}
