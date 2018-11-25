package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_QUARTZJOB")
public class QuartzJobVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8212234048424156949L;
	
	@Column(name = "JOBNAME")
	private String jobName;
	
	@Column(name = "JOBGROUPNAME")
	private String jobGroupName;
	
	@Column(name = "TRIGGERNAME")
	private String triggerName;
	
	@Column(name = "TRIGGERGROUPNAME")
	private String triggerGroupName;
	
	@Column(name = "JOBCLASS")
	private String jobClass;
	
	@Column(name = "CRON")
	private String cron;
	
	@Column(name = "STATUS")
	private String status;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
