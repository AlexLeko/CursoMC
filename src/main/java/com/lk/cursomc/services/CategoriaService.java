package com.lk.cursomc.services;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.repositories.CategoriaRepository;
import com.lk.cursomc.services.exceptions.DataIntegrityException;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository _repository;

    public Categoria find(Integer id){
        Optional<Categoria> categoria = _repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Categoria.class.getName()));
    }


    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return _repository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        find(categoria.getId());
        return _repository.save(categoria);
    }

    public void delete(Integer id){
        find(id);

        try{
            _repository.deleteById(id);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

    public List<Categoria> findAll(){
        return _repository.findAll();
    }

}
