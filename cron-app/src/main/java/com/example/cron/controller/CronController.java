package com.example.cron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cron.entity.CronRequest;
import com.example.cron.entity.ParseCronExpressionResponse;
import com.example.cron.service.CronService;

/**
 * Parses crontab expression and create a Schedule object representing that
 * expression.
 *
 * The expression string can be 5 fields expression for minutes resolution.
 *
 * <pre>
 *  ┌───────────── minute (0 - 59)
 *  │ ┌───────────── hour (0 - 23)
 *  │ │ ┌───────────── day of the month (1 - 31) or L for last day of the month
 *  │ │ │ ┌───────────── month (1 - 12 or Jan/January - Dec/December)
 *  │ │ │ │ ┌───────────── day of the week (0 - 6 or Sun/Sunday - Sat/Saturday)
 *  │ │ │ │ │
 *  │ │ │ │ │
 *  │ │ │ │ │
 * "* * * * *"
 * </pre>
 */

@RestController
@RequestMapping("/cronexpression")
public class CronController {
	
	@Autowired
	CronService cronService;

	
	 @PostMapping("/parser")
	    public ResponseEntity<ParseCronExpressionResponse> parseCronExpression(@RequestBody CronRequest cron) {
	        try {
	            ParseCronExpressionResponse response = cronService.generateCronInReadableForm(cron);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

}
