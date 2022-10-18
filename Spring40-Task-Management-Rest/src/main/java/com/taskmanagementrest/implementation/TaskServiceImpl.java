package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.entity.Project;
import com.taskmanagementrest.entity.Task;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.enums.Status;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.util.MapperUtil;
import com.taskmanagementrest.repository.TaskRepository;
import com.taskmanagementrest.repository.UserRepository;
import com.taskmanagementrest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Lazy
    @Autowired
    TaskRepository taskRepository;
    @Lazy
    @Autowired
    MapperUtil mapperUtil;

    @Lazy
    @Autowired
    UserRepository userRepository;


    @Override
    public TaskDTO findById(Long id) throws TaskManagementException {
        Task task = taskRepository.findById(id).orElseThrow(()->new TaskManagementException("Task Does not exist"));

        return mapperUtil.convert(task, new TaskDTO());

    }

    @Override
    public List<TaskDTO> listAllTasks() {

        List<Task>list = taskRepository.findAll();

        return list.stream().map(obj->{return mapperUtil.convert(obj, new TaskDTO());}).collect(Collectors.toList());

    }

    @Override
    public TaskDTO save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task = mapperUtil.convert(dto, new Task());
        Task savedTask = taskRepository.save(task);

        return mapperUtil.convert(savedTask, new TaskDTO());
    }

    @Override
    public TaskDTO update(TaskDTO dto) throws TaskManagementException {

        Task task = taskRepository.findById(dto.getId()).orElseThrow(()->new TaskManagementException("Task does not exist"));
        Task convertedTask = mapperUtil.convert(dto, new Task());
        Task savedTask = taskRepository.save(convertedTask);

       return mapperUtil.convert(savedTask, new TaskDTO());
    }

    @Override
    public void delete(Long id) throws TaskManagementException {
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new TaskManagementException("Task does not exist"));
        foundTask.setDeleted(true);
        taskRepository.save(foundTask);


    }

    @Override
    public void deleteByProject(ProjectDTO project) {
     List<TaskDTO>taskDTOS = listAllByProject(project);
     taskDTOS.forEach(taskDTO -> {
         try {
             delete(taskDTO.getId());
         } catch (TaskManagementException e) {
             throw new RuntimeException(e);
         }
     });
    }

    @Override
    public int totalNonCompletedTasks(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTasks(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);

    }

    public List<TaskDTO>listAllByProject(ProjectDTO project){
        List<Task>list = taskRepository.findAllByProject(mapperUtil.convert(project, new Project()));
        return list.stream().map(obj->{return mapperUtil.convert(obj, new TaskDTO());
        }).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) throws TaskManagementException {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(()->new TaskManagementException("User Does not exist"));
        List<Task>list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);

        return list.stream().map(obj->mapperUtil.convert(obj, new TaskDTO())).collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateStatus(TaskDTO taskDTO) throws TaskManagementException {
        Task task = taskRepository.findById(taskDTO.getId()).orElseThrow(()->new TaskManagementException("Task Does not exist"));

        task.setTaskStatus(taskDTO.getTaskStatus());
        Task savedTask = taskRepository.save(task);

        return mapperUtil.convert(savedTask, new TaskDTO());
    }

    @Override
    public List<TaskDTO> listAllTaskByStatus(Status status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(username);
        List<Task>list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);
        return list.stream().map(obj->mapperUtil.convert(obj, new TaskDTO())).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> readAllByEmployee(User assignedEmployee) {
        List<Task>task = taskRepository.findAllByAssignedEmployee(assignedEmployee);
        return task.stream().map(obj->mapperUtil.convert(obj, new TaskDTO())).collect(Collectors.toList());
    }


    public List<TaskDTO> listAllTasksByProjectManager() throws TaskManagementException {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();


        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(()->new TaskManagementException("This user does not exist"));

        List<Task>tasks = taskRepository.findAllByProjectAssignedManager(user);

        return tasks.stream().map(obj->mapperUtil.convert(obj,new TaskDTO())).collect(Collectors.toList());
    }


}
