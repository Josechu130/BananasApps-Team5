package es.netmind.banana_invoices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.persistence.IPropietarioRepo;
import lombok.Getter;
import lombok.Setter;

public class InventarioImpl implements IInventario {
    @Getter
    @Setter
    @Autowired
    private IPropietarioRepo propietariosRepo;

    @Override
    public List<Propietario> findAll() {
        return propietariosRepo.findAll();
    }

    @Override
    public Propietario save(Propietario prop) {
        return propietariosRepo.save(prop);
    }
    
    
}
