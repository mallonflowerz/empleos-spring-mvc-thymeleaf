package com.employ.services.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employ.controller.copy.model.model.Vacantes;
import com.employ.repository.repository.VacantesRepository;
import com.employ.services.IVacantesService;

@Service
@Primary
public class VacantesServicesJpa implements IVacantesService {

    @Autowired
    private VacantesRepository vacantesRepository;

    @Override
    public List<Vacantes> buscarTodas() {
        return vacantesRepository.findAll();
    }

    @Override
    public Vacantes buscarPorId(Integer idVacante) {
        Optional<Vacantes> optional = vacantesRepository.findById(idVacante);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardar(Vacantes vacantes) {
        vacantesRepository.save(vacantes);
    }

    @Override
    public List<Vacantes> buscarDestacadas() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {
        vacantesRepository.deleteById(idVacante);
    }

    @Override
    public List<Vacantes> buscarByExample(Example<Vacantes> examples) {
        return vacantesRepository.findAll(examples);
    }

    @Override
    public Page<Vacantes> buscarTodas(Pageable page) {
        return vacantesRepository.findAll(page);
    }

}
