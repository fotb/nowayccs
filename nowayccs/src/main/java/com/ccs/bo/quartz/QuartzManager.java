package com.ccs.bo.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;



public class QuartzManager {

    private Scheduler scheduler;

    /**
     * @Description: 添加一个定时任务
     * 
     * @param jobName
     *            任务名
     * @param jobGroupName
     *            任务组名
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param jobClass
     *            任务
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
            String cron) {
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder =   TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
            String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job */
                // JobDetail jobDetail =
                // scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                // Class<? extends Job> jobClass = jobDetail.getJobClass();
                // removeJob(jobName, jobGroupName, triggerName,
                // triggerGroupName);
                // addJob(jobName, jobGroupName, triggerName, triggerGroupName,
                // jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 修改 一个job的 时间表达式
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     */
    public void updateJob(String jobName,String jobGroupName,String jobTime){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);  
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime))
                    .build();
            //重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    /**
     * 删除任务一个job
     * @param jobName 任务名称
     * @param jobGroupName 任务组名
     */
    public  void deleteJob(String jobName,String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停一个job
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName,String jobGroupName){
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);  
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * 恢复一个job
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName,String jobGroupName){
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行一个job
     * @param jobName
     * @param jobGroupName
     */
    public void runAJobNow(String jobName,String jobGroupName){
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * @Description:启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public String transCron(String time){
        String seconds = StringUtils.substringAfterLast(time, ":");
        String minute = StringUtils.substringAfter(time, ":").substring(0, 2);
        String hour = StringUtils.substringAfter(time, " ").substring(0, 2);
        String day = StringUtils.substringAfterLast(time, "-").substring(0, 2);
        String month =  StringUtils.substringAfter(time, "-").substring(0, 2);
        return seconds + " " + minute + " " + hour + " " + day + " " + month + " ?";
    }
    
    
    /**
     * 获取所有计划中的任务列表
     * @return
     */
    public List<Map<String,Object>> queryAllJob(){
        List<Map<String,Object>> jobList=null;
        try {
             GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();  
             Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
             jobList = new ArrayList<Map<String,Object>>();  
             for (JobKey jobKey : jobKeys) {  
                  List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);  
                  for (Trigger trigger : triggers) {  
                      Map<String,Object> map=new HashMap<>();
                      map.put("jobName",jobKey.getName());
                      map.put("jobGroupName",jobKey.getGroup());
                      map.put("description","触发器:" + trigger.getKey());
                      Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
                      map.put("jobStatus",triggerState.name());
                      if (trigger instanceof CronTrigger) {  
                          CronTrigger cronTrigger = (CronTrigger) trigger;  
                          String cronExpression = cronTrigger.getCronExpression();  
                          map.put("jobTime",cronExpression);
                      }  
                      jobList.add(map);  
                  }  
              }  
        } catch (SchedulerException e) {
            e.printStackTrace();
        }  
        return jobList;  
    }

    /**
     * 获取所有正在运行的job
     * @return
     */
    public List<Map<String,Object>> queryRunJob(){
        List<Map<String,Object>> jobList=null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String,Object>>(executingJobs.size());  
            for (JobExecutionContext executingJob : executingJobs) {  
                Map<String,Object> map=new HashMap<String, Object>();  
                JobDetail jobDetail = executingJob.getJobDetail();  
                JobKey jobKey = jobDetail.getKey();  
                Trigger trigger = executingJob.getTrigger(); 
                map.put("jobName",jobKey.getName());
                map.put("jobGroupName",jobKey.getGroup());
                map.put("description","触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
                map.put("jobStatus",triggerState.name());
                if (trigger instanceof CronTrigger) {  
                    CronTrigger cronTrigger = (CronTrigger) trigger;  
                    String cronExpression = cronTrigger.getCronExpression();  
                    map.put("jobTime",cronExpression);
                }  
                jobList.add(map);  
            }  
        } catch (SchedulerException e) {
            e.printStackTrace();
        }  
        return jobList;  
    }
}