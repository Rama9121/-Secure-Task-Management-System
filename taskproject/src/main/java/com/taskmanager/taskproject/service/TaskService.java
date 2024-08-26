package com.taskmanager.taskproject.service;

import java.util.List;

import com.taskmanager.taskproject.payload.TaskDto;

public interface TaskService {
	public TaskDto  SaveTask(long userid,TaskDto taskDto);
	
	public List<TaskDto> getALLTasks(long userid);
	
    public TaskDto getTask(long userid, long taskid);
   
	public void deleteTask(long userid, long taskid);

}
