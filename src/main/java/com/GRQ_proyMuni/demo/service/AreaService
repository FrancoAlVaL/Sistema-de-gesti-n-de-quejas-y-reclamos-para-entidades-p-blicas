package com.GRQ_proyMuni.demo.service;

import com.GRQ_proyMuni.demo.entity.Area;
import com.GRQ_proyMuni.demo.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<Area> listarTodos() {
        return areaRepository.findAll();
    }

    public Optional<Area> buscarPorId(Integer id) {
        return areaRepository.findById(id);
    }

    public Area guardar(Area area) {
        return areaRepository.save(area);
    }

    public void eliminar(Integer id) {
        areaRepository.deleteById(id);
    }
}
