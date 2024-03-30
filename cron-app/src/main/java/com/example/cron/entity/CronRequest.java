package com.example.cron.entity;

public class CronRequest {

	private String cronExpression;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CronRequest [cronExpression=");
		builder.append(cronExpression);
		builder.append("]");
		return builder.toString();
	}

}
