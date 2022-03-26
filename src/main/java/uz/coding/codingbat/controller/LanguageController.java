package uz.coding.codingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.coding.codingbat.entity.Language;
import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/language")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Language qo'shuvchi method
     * avval bazada borligini tekshiradi, keyin bazaga qo'shadi.
     * @param language LANGUAGE
     * @return RESPONSEENTITY
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody Language language){
        ApiResponse apiResponse = languageService.addLanguage(language);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Barcha Language larni listda qaytaradi
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguage(){
        List<Language> languageList = languageService.getAllLanguage();
        return ResponseEntity.ok(languageList);
    }

    /**
     * Id bo'yicha delete methode.
     *
     * @param id Integer
     * @return Language
     */
    @GetMapping(value = "/{id}")
    public Language getByIdLanguage(@PathVariable Integer id){
        return languageService.getByIdLanguage(id);
    }

    /**
     * Update methode
     * @param id Integer
     * @param language Language
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @RequestBody Language language){
        ApiResponse apiResponse = languageService.editLanguage(id, language);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(202).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Delete methode
     * @param id Integer
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
