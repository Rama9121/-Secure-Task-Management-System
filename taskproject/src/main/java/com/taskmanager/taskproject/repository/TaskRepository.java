package com.taskmanager.taskproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.taskproject.entity.Task;

 public interface TaskRepository  extends JpaRepository<Task, Long>{



	List<Task> findAllByusersId(long userid);
	
}


