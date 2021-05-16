package ru.askarov.notepad.dao;

import org.springframework.stereotype.Repository;
import ru.askarov.notepad.model.Record;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Record> index() {
        Query query = entityManager.createQuery("SELECT r FROM Record r");
        return query.getResultList();
    }

    public Record show(long id) {
        return entityManager.find(Record.class, id);
    }

    public void save(Record record) {
        entityManager.persist(record);
    }

    public void update(long id, Record record) {
        entityManager.merge(record);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.find(Record.class, id));
    }
}
