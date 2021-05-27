package ru.askarov.notepad.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.askarov.notepad.config.SpringConfigTest;
import ru.askarov.notepad.dao.RecordDao;
import ru.askarov.notepad.model.Record;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {SpringConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class RecordDaoTest {

    @Autowired
    private RecordDao dao;

    @Test
    @Rollback
    @DisplayName("Добавление записей")
    void testAddRecord(){
        Record first = new Record();
        first.setTitle("First");
        first.setArticle("Test note record");

        Record second = new Record();
        second.setTitle("Second");
        second.setArticle("Test note second");

        dao.save(first);
        dao.save(second);

        List<Record> list = dao.index();
        assertEquals(2, list.size());
    }

    @Test
    @Rollback
    @DisplayName("Содержание записи")
    void testContentRecord(){
        Record record = new Record();
        record.setTitle("Test");
        record.setArticle("Test note");

        dao.save(record);

        Record expected = dao.show(record.getId());

        assertEquals(record, expected);
    }

    @Test
    @Rollback
    @DisplayName("Обновление записи")
    void testUpdateRecord(){
        Record record = new Record();
        record.setTitle("Test");
        record.setArticle("Test note");

        dao.save(record);

        record.setTitle("New Title");
        record.setArticle("New Article");

        dao.update(record);

        Record expected = dao.show(record.getId());
        assertEquals(record, expected);
    }

    @Test
    @Rollback
    @DisplayName("Удаление записи")
    void testDeleteRecord(){
        Record record = new Record();
        record.setTitle("Test");
        record.setArticle("Test note");

        dao.save(record);
        dao.delete(record.getId());

        Record expected = dao.show(record.getId());

        assertNull(expected);
    }

}