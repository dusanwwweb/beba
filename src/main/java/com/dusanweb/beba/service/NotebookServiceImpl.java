package com.dusanweb.beba.service;

import com.dusanweb.beba.dto.NotebookPostsResponse;
import com.dusanweb.beba.model.Notebook;
import com.dusanweb.beba.model.Post;
import com.dusanweb.beba.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class NotebookServiceImpl implements BebaService<Notebook>{

    @Autowired
    private NotebookRepository notebookRepository;

    @Override
    public List<Notebook> findAll() {
        return notebookRepository.findAll();
    }

    @Override
    public Optional<Notebook> findById(Long id) {
        return notebookRepository.findById(id);
    }

    @Override
    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    @Override
    public void delete(Notebook notebook) {
        notebookRepository.delete(notebook);
    }

    @Override
    public void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }

    public Collection<? extends NotebookPostsResponse> getPostObservations() {
        return notebookRepository.getPostObservations();
    }
/*
    public void removePost(Post post){
        Notebook notebook = new Notebook();
        notebook.removePost(post);
    }

 */

}
