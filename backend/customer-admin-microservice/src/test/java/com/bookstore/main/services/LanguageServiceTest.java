package com.bookstore.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Language;
import com.bookstore.main.repository.LanguageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LanguageService.class})
@ExtendWith(SpringExtension.class)
class LanguageServiceTest {
    @MockBean
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageService languageService;

    @Test
    void testSave() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.save((Language) any())).thenReturn(language);
        this.languageService.save("en");
        verify(this.languageRepository).save((Language) any());
        assertTrue(this.languageService.getAllLanguage().isEmpty());
    }

    @Test
    void testGetLanguage() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        Optional<Language> ofResult = Optional.of(language);
        when(this.languageRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(language, this.languageService.getLanguage(123L));
        verify(this.languageRepository).findById((Long) any());
        assertTrue(this.languageService.getAllLanguage().isEmpty());
    }

    @Test
    void testGetLanguage2() {
        when(this.languageRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.languageService.getLanguage(123L));
        verify(this.languageRepository).findById((Long) any());
    }

    @Test
    void testUpdateLanguage() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        Optional<Language> ofResult = Optional.of(language);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);
        when(this.languageRepository.save((Language) any())).thenReturn(language1);
        when(this.languageRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.languageRepository.existsById((Long) any())).thenReturn(true);
        assertSame(language1, this.languageService.updateLanguage(123L, "en"));
        verify(this.languageRepository).existsById((Long) any());
        verify(this.languageRepository).findById((Long) any());
        verify(this.languageRepository).save((Language) any());
        assertTrue(this.languageService.getAllLanguage().isEmpty());
    }

    @Test
    void testUpdateLanguage2() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        when(this.languageRepository.save((Language) any())).thenReturn(language);
        when(this.languageRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(this.languageRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> this.languageService.updateLanguage(123L, "en"));
        verify(this.languageRepository).existsById((Long) any());
        verify(this.languageRepository).findById((Long) any());
    }

    @Test
    void testUpdateLanguage3() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);
        Optional<Language> ofResult = Optional.of(language);

        Language language1 = new Language();
        language1.setLanguage("en");
        language1.setId(123L);
        when(this.languageRepository.save((Language) any())).thenReturn(language1);
        when(this.languageRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.languageRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> this.languageService.updateLanguage(123L, "en"));
        verify(this.languageRepository).existsById((Long) any());
    }

    @Test
    void testDeleteLanguage() {
        doNothing().when(this.languageRepository).deleteById((Long) any());
        when(this.languageRepository.existsById((Long) any())).thenReturn(true);
        this.languageService.deleteLanguage(123L);
        verify(this.languageRepository).deleteById((Long) any());
        verify(this.languageRepository).existsById((Long) any());
        assertTrue(this.languageService.getAllLanguage().isEmpty());
    }

    @Test
    void testDeleteLanguage2() {
        doNothing().when(this.languageRepository).deleteById((Long) any());
        when(this.languageRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> this.languageService.deleteLanguage(123L));
        verify(this.languageRepository).existsById((Long) any());
    }

    @Test
    void testCount() {
        when(this.languageRepository.count()).thenReturn(3L);
        assertEquals(3, this.languageService.count());
        verify(this.languageRepository).count();
        assertTrue(this.languageService.getAllLanguage().isEmpty());
    }

    @Test
    void testGetAllLanguage() {
        ArrayList<Language> languageList = new ArrayList<>();
        when(this.languageRepository.findAll()).thenReturn(languageList);
        List<Language> actualAllLanguage = this.languageService.getAllLanguage();
        assertSame(languageList, actualAllLanguage);
        assertTrue(actualAllLanguage.isEmpty());
        verify(this.languageRepository).findAll();
    }
}

