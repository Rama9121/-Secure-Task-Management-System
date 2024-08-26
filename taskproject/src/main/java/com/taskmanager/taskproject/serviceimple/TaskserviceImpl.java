package com.taskmanager.taskproject.serviceimple;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.taskproject.entity.Task;
import com.taskmanager.taskproject.entity.Users;
import com.taskmanager.taskproject.exception.ApiException;
import com.taskmanager.taskproject.exception.Usernotfound;
import com.taskmanager.taskproject.exception.tasknotfound;
import com.taskmanager.taskproject.payload.TaskDto;
import com.taskmanager.taskproject.repository.TaskRepository;
import com.taskmanager.taskproject.repository.UserRepository;
import com.taskmanager.taskproject.service.TaskService;
@Service
public class TaskserviceImpl implements TaskService{
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto SaveTask(long userid, TaskDto taskDto) {
		Users user=userRepository.findById(userid).orElseThrow(
				()-> new Usernotfound(String.format("user id %d not found", userid)));
		Task task =modelMapper.map(taskDto, Task.class);
		task.setUsers(user);
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getALLTasks(long userid) {
		userRepository.findById(userid).orElseThrow(
				()-> new Usernotfound(String.format("user id %d not found", userid)));
		List<Task> tasks= taskRepository.findAllByusersId(userid);		
		return tasks.stream().map(
				task -> modelMapper.map(task, TaskDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TaskDto getTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
				()-> new Usernotfound(String.format("user id %d not found", userid)));
		Task task =  taskRepository.findById(taskid).orElseThrow(
				()-> new tasknotfound(String.format("Task id %d not found", taskid)));
		if(users.getId() != task.getUsers().getId()) {
			throw new ApiException(String.format("task id %d not belongs to user id %d", taskid,userid));
			
		}
		
		return modelMapper.map(task, TaskDto.class);
	}

	public void deleteTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
				()-> new Usernotfound(String.format("user id %d not found", userid)));
		Task task =  taskRepository.findById(taskid).orElseThrow(
				()-> new tasknotfound(String.format("Task id %d not found", taskid)));
		if(users.getId() != task.getUsers().getId()) {
			throw new ApiException(String.format("task id %d not belongs to user id %d", taskid,userid));
			
		}
		taskRepository.deleteById(taskid);;
	}
}



