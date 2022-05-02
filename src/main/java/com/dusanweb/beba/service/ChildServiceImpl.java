package com.dusanweb.beba.service;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChildServiceImpl implements BebaService<Child>{

    @Autowired
    private ChildRepository childRepository;

    @Override
    public List<Child> findAll() {
        return childRepository.findAll();
    }

    @Override
    public Optional<Child> findById(Long id) {
        return childRepository.findById(id);
    }

    @Override
    public Child save(Child child) {
        return childRepository.save(child);
    }

    @Override
    public void delete(Child child) {
        childRepository.delete(child);
    }

    @Override
    public void deleteById(Long id) {
        childRepository.deleteById(id);
    }

    public List<Child> findAllOrderByAllergyTypeAsc() {
        return childRepository.findAllOrderByAllergyTypeAsc();
    }

    public void deleteAll() {
        childRepository.deleteAll();
    }

    public List<Child> findByDateOfBirth(LocalDate date) {
        return childRepository.findByDateOfBirth(date);
    }

    public Optional<Child> findByFirstName(String firstName) {
        return childRepository.findByFirstName(firstName);
    }

    public List<Child> findAllOrderByWeightDesc() {
        return childRepository.findAllOrderByWeightDesc();
    }

    public List<Child> findBySex(char sex) {
        return childRepository.findBySex(sex);
    }
}
