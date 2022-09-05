package com.etask.implementation;

import com.etask.dto.TaskDTO;
import com.etask.entity.Task;
import com.etask.enums.Status;
import com.etask.mapper.TaskMapper;
import com.etask.repository.TaskRepository;
import com.etask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
}
