package uz.coding.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Answer;
import uz.coding.codingbat.entity.Task;
import uz.coding.codingbat.entity.Users;
import uz.coding.codingbat.payload.AnswerDto;
import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.repository.AnswerRepository;
import uz.coding.codingbat.repository.TaskRepository;
import uz.coding.codingbat.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UsersRepository usersRepository;

    /**
     * Answer qo'shuvchi method
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse addAnswer(AnswerDto answerDto){
        if (answerRepository.existsByText(answerDto.getText()))
            return new ApiResponse("Bunday nomli Answer mavjud emas", false);
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setCorrect(answerDto.getCorrect());
        if (!taskRepository.existsById(answerDto.getTaskId()))
            return new ApiResponse("Bunday task mavjud emas", false);
        if (!usersRepository.existsById(answerDto.getUsersId()))
            return new ApiResponse("Bunday user mavjud emas", false);
        answer.setTask(taskRepository.getById(answerDto.getTaskId()));
        answer.setUsers(usersRepository.getById(answerDto.getUsersId()));
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * Answerni taxrirlovchi method
     * @param id Integer
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse editAnswer(Integer id,AnswerDto answerDto){
        if (answerRepository.existsById(id)) {
            Optional<Answer> optional = answerRepository.findById(id);
            if (!optional.isPresent())
                return new ApiResponse("Answer Not Found", false);
            Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
            if (!optionalTask.isPresent())
                return new ApiResponse("Task Not Found", false);
            Optional<Users> optionalUsers = usersRepository.findById(answerDto.getUsersId());
            if (!optionalUsers.isPresent())
                return new ApiResponse("User Not Found", false);
            Answer editingAnswer = new Answer();
            editingAnswer.setText(answerDto.getText());
            editingAnswer.setCorrect(answerDto.getCorrect());
            editingAnswer.setTask(taskRepository.getById(answerDto.getTaskId()));
            editingAnswer.setUsers(usersRepository.getById(answerDto.getUsersId()));
            answerRepository.save(editingAnswer);
            return new ApiResponse("Successfully saved", true);
        }
        return new ApiResponse("Answer Not Found", false);
    }

    public List<Answer> getAllAnswer(){
        return answerRepository.findAll();
    }

    public Answer getByIdAnswer(Integer id){
        Optional<Answer> optional = answerRepository.findById(id);
        return optional.orElse(null);
    }

    public ApiResponse deleteAnswer(Integer id){
        if(!answerRepository.existsById(id))
            return new ApiResponse("Xatolik", false);
        answerRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
    }

}
