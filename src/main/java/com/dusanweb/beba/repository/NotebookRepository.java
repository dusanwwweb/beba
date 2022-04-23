package com.dusanweb.beba.repository;

import com.dusanweb.beba.dto.NotebookPostsResponse;
import com.dusanweb.beba.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    @Query(value = "SELECT new com.dusanweb.beba.dto.NotebookPostsResponse(n.name, p.observation) FROM Notebook n JOIN n.posts p")
    List<NotebookPostsResponse> getPostObservations();
}
