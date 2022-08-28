package com.etask.service;

import com.etask.dto.TaskDTO;
import com.etask.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{

    List<TaskDTO> findTaskByManager(UserDTO manager);
}
