package uz.coding.codingbat.service;

import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Language;
import uz.coding.codingbat.payload.ApiResponse;

import uz.coding.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    /**
     * Language qo'shuvchi method
     * avval bazada borligini tekshiradi, keyin bazaga qo'shadi.
     * @param language Language
     * @return APIRESPONSE
     */
    public ApiResponse addLanguage(Language language){
        boolean existsByName = languageRepository.existsByName(language.getName());
        if (existsByName)
            return new ApiResponse("bunday nomli til mavjud", false);
        Language language1 = new Language();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * Barcha Language larni listda qaytaradi
     * @return ResponseEntity
     */
    public List<Language> getAllLanguage(){
        return languageRepository.findAll();
    }

    /**
     * Id bo'yicha delete methode.
     *
     * @param id Integer
     * @return Language
     */
    public Language getByIdLanguage(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    /**
     * Update methode
     * @param id Integer
     * @param language Language
     * @return ResponseEntity
     */
    public ApiResponse editLanguage(Integer id, Language language){
        boolean exists = languageRepository.existsByName(language.getName());
        if (exists)
            return new ApiResponse("tahrirlanmadi", false);
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday idli til mavjud emas", false);
        Language editingLanguage = optionalLanguage.get();
        editingLanguage.setName(language.getName());
        languageRepository.save(editingLanguage);
        return new ApiResponse("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * Delete methode
     * @param id Integer
     * @return ResponseEntity
     */
    public ApiResponse deleteLanguage(Integer id){
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik", false);
        }
    }
}
