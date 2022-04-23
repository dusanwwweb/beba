package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
