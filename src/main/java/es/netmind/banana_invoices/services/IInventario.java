package es.netmind.banana_invoices.services;

import java.util.List;

import es.netmind.banana_invoices.models.Propietario;

public interface IInventario {
    public List<Propietario> findAll();

    public Propietario save(Propietario prop);

}
