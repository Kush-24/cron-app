package com.example.cron.service;

import java.util.List;

import com.example.cron.entity.CronRequest;
import com.example.cron.entity.ParseCronExpressionResponse;
import com.example.cron.utility.InvalidExpressionException;


public interface CronService {
	ParseCronExpressionResponse generateCronInReadableForm(CronRequest request) throws InvalidExpressionException;
}
