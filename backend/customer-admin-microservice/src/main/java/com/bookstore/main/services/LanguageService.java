package com.bookstore.main.services;

import com.bookstore.main.models.Language;
import com.bookstore.main.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public void save(String language) {
        Language lang = new Language();
        lang.setLanguage(language);
        languageRepository.save(lang);
    }

    public Language getLanguage(Long id) {
        return languageRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Language not found")
        );
    }

    public Language updateLanguage(Long id, String language) {
        if (languageRepository.existsById(id)) {
            Language lang = getLanguage(id);
            lang.setLanguage(language);
            return languageRepository.save(lang);
        } else {
            throw new RuntimeException("Language not found");
        }
    }

    public void deleteLanguage(Long id) {
        if(languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Language not found");
        }
    }

    public int count(){
        return (int) languageRepository.count();
    }

    public List<Language> getAllLanguage(){
        return languageRepository.findAll();
    }
}
