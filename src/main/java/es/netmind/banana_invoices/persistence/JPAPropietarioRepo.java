package es.netmind.banana_invoices.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.netmind.banana_invoices.models.Propietario;

public class JPAPropietarioRepo implements IPropietarioRepo {
    
	@PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<Propietario> findAll() {
        Query query = em.createQuery("SELECT e FROM Propietario e");
        return (List<Propietario>) query.getResultList();
    }

    @Override
    @Transactional
    public Propietario save(Propietario prop) {
        em.persist(prop);
        return prop;
    }
}
