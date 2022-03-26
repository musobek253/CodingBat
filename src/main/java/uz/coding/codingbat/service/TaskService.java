package uz.coding.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Language;
import uz.coding.codingbat.entity.Task;
import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.payload.TaskDto;
import uz.coding.codingbat.repository.LanguageRepository;
import uz.coding.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    LanguageRepository languageRepository;

    public ApiResponse addTask(TaskDto taskDto){
        boolean exists = taskRepository.existsByName(taskDto.getName());
        if (exists)
            return new ApiResponse("bunday nomli task mavjud", false);
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setInfoText(taskDto.getInfoText());
        task.setSolution(taskDto.getSolution());
        task.setMethod(taskDto.getMethod());
        task.setHint(taskDto.getHint());
        task.setActive(taskDto.getActive());
        boolean existsById = languageRepository.existsById(taskDto.getLanguageId());
        if (!existsById)
            return new ApiResponse("bunday idli language mavjud emas", false);
        task.setLanguage(languageRepository.getById(taskDto.getLanguageId()));
        taskRepository.save(task);
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    public ApiResponse editTask(TaskDto taskDto, Integer id){
        Optional<Task> optional = taskRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("bunday idli task mavjud emas", false);
        Task editingTask = optional.get();
        editingTask.setName(taskDto.getName());
        editingTask.setInfoText(taskDto.getInfoText());
        editingTask.setSolution(taskDto.getSolution());
        editingTask.setMethod(taskDto.getMethod());
        editingTask.setActive(taskDto.getActive());
        editingTask.setHint(taskDto.getHint());
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("bunday idli language mavjud emas", false);
        Language editingLanguage = optionalLanguage.get();
        editingLanguage.setId(taskDto.getLanguageId());
        languageRepository.save(editingLanguage);
        taskRepository.save(editingTask);
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Task getById(Integer id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public ApiResponse deleteTask(Integer id){
        boolean exists = taskRepository.existsById(id);
        if (!exists)
            return new ApiResponse("Bunday idli task mavjud emas", false);
        taskRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
    }
}
