package uz.coding.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Example;
import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.payload.ExampleDto;
import uz.coding.codingbat.repository.ExampleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;

    public ApiResponse addExample(ExampleDto exampleDto){
        try {
            Example example = new Example();
            example.setText(exampleDto.getText());
            example.setTask(exampleDto.getTaskId());
            exampleRepository.save(example);
            return new ApiResponse("Muvaffaqiyatli qo'shildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik", false);
        }
    }

    public ApiResponse editExample(Integer id, ExampleDto exampleDto){
        boolean exists = exampleRepository.existsById(id);
        if (!exists)
            return new ApiResponse("Bunday idli example mavjud emas", false);
        Example editingExample = new Example();
        editingExample.setText(exampleDto.getText());
        editingExample.setTask(exampleDto.getTaskId());
        exampleRepository.save(editingExample);
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    public List<Example> getAllExample(){
       return exampleRepository.findAll();
    }

    public Example getByIdExample(Integer id){
        Optional<Example> optional = exampleRepository.findById(id);
        return optional.orElse(null);
    }

    public ApiResponse deleteById(Integer id){
        if (!exampleRepository.existsById(id))
            return new ApiResponse("Bunday idli example mavjud emas", false);
        exampleRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
    }

}
