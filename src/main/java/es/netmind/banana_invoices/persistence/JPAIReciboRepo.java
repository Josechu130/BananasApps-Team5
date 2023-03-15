package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.models.Recibo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

public class JPAIReciboRepo implements IReciboRepo{

    private EntityManager em;

    @Override
    public List<Recibo> findAll() {
        return null;
    }
    
    @Override
    //@Transactional
    public Recibo save(Recibo entity) {
    	em.persist(entity);
    	return entity;
    }
    
}
