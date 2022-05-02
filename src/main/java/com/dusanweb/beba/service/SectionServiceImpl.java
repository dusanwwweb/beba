package com.dusanweb.beba.service;

import com.dusanweb.beba.model.Section;
import com.dusanweb.beba.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements BebaService<Section>{

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<Section> findById(Long id) {
        return sectionRepository.findById(id);
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
    }

    @Override
    public void deleteById(Long id) {
        sectionRepository.deleteById(id);
    }
}
