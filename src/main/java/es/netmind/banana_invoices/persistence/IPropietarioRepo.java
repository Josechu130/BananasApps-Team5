package es.netmind.banana_invoices.persistence;

import java.util.List;

import es.netmind.banana_invoices.models.Propietario;

public interface IPropietarioRepo {
    public List<Propietario> findAll();
    public Propietario save(Propietario prop);
}
