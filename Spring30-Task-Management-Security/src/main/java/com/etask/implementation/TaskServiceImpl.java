package com.etask.implementation;

import com.etask.dto.ProjectDTO;
import com.etask.dto.TaskDTO;
import com.etask.entity.Task;
import com.etask.entity.User;
import com.etask.enums.Status;
import com.etask.mapper.ProjectMapper;
import com.etask.mapper.TaskMapper;
import com.etask.repository.TaskRepository;
import com.etask.repository.UserRepository;
import com.etask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Lazy
    @Autowired
    TaskRepository taskRepository;
    @Lazy
    @Autowired
    TaskMapper taskMapper;

    @Lazy
    @Autowired
    ProjectMapper projectMapper;

    @Lazy
    @Autowired
    UserRepository userRepository;


    @Override
    public TaskDTO findById(Long id) {
        Optional<Task>task = taskRepository.findById(id);
        if(task.isPresent()){
            return taskMapper.convertToDTO(task.get());
        }

        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {

        List<Task>list = taskRepository.findAll();

        return list.stream().map(obj->{return taskMapper.convertToDTO(obj);}).collect(Collectors.toList());

    }

    @Override
    public Task save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(dto);

        return taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO dto) {

        Optional<Task>task = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);
        convertedTask.setTaskStatus(task.get().getTaskStatus());
        convertedTask.setAssignedDate(task.get().getAssignedDate());
        taskRepository.save(convertedTask);

    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isPresent()){
            foundTask.get().setDeleted(true);
            taskRepository.save(foundTask.get());
        }

    }

    @Override
    public void deleteByProject(ProjectDTO project) {
     List<TaskDTO>taskDTOS = listAllByProject(project);
     taskDTOS.forEach(taskDTO -> delete(taskDTO.getId()));
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
        List<Task>list = taskRepository.findAllByProject(projectMapper.convertToEntity(project));
        return list.stream().map(obj->{return taskMapper.convertToDTO(obj);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username);
        List<Task>list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);

        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {
        Optional<Task> task = taskRepository.findById(taskDTO.getId());
        if(task.isPresent()){
            task.get().setTaskStatus(taskDTO.getTaskStatus());
            taskRepository.save(task.get());
        }
    }

    @Override
    public List<TaskDTO> listAllTaskByStatus(Status status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(username);
        List<Task>list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> readAllByEmployee(User assignedEmployee) {
        List<Task>task = taskRepository.findAllByAssignedEmployee(assignedEmployee);
        return task.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void completeTaskById(Long id) {
        taskRepository.completeTaskById(id);
    }



    public List<TaskDTO> listAllTasksByProjectManager() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username);
        List<Task>tasks = taskRepository.findAllByProjectAssignedManager(user);

        return tasks.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }


}
