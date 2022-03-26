package uz.coding.codingbat.service;

import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Category;

import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.payload.CategoryDto;
import uz.coding.codingbat.repository.CategoryRepository;
import uz.coding.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;

    public CategoryService(CategoryRepository categoryRepository, LanguageRepository languageRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists)
            return new ApiResponse("Already exist Category Name", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setClassification(categoryDto.getClassification());
        if (!languageRepository.existsAllByIdIn(categoryDto.getLanguageId()))
            return new ApiResponse("Language not found",false);
        category.setLanguages(languageRepository.getAllByIdIn(categoryDto.getLanguageId()));
        categoryRepository.save(category);
        return new ApiResponse("Successfully added",true);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getByIdCategory(Integer id){
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.orElse(null);
    }

    public ApiResponse editCategory(CategoryDto categoryDto, Integer id){
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday idli category mavjud emas", false);
        Category editingCategory = optional.get();
        editingCategory.setName(categoryDto.getName());
        editingCategory.setClassification(categoryDto.getClassification());
        editingCategory.setLanguages(languageRepository.getAllByIdIn(categoryDto.getLanguageId()));
        return new ApiResponse("Muvaffaqiyatli qo'shildi", true);
    }

    public ApiResponse deleteCategory(Integer id){
        try{
            categoryRepository.deleteById(id);
            return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik", false);
        }

    }





}
