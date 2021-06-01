package ru.askarov.notepad.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.askarov.notepad.config.SpringConfigTest;
import ru.askarov.notepad.model.Record;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {SpringConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional(Transactional.TxType.REQUIRES_NEW)
@Rollback
public class RecordDaoTest {

    @Autowired
    private RecordDao dao;
    Record record;

    @BeforeEach
    void init(){
        record = new Record();
        record.setTitle("Test");
        record.setArticle("Test note");

        dao.save(record);
    }

    @Test
    @DisplayName("Добавление записей")
    void testAddRecord(){
        Record second = new Record();
        second.setTitle("Second note");
        second.setArticle("Test note second");

        dao.save(second);

        List<Record> list = dao.index();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Содержание записи")
    void testContentRecord(){
        Record expected = dao.show(record.getId());

        assertEquals(record, expected);
    }

    @Test
    @DisplayName("Обновление записи")
    void testUpdateRecord(){
        record.setTitle("New Title");
        record.setArticle("New Article");

        dao.update(record);

        Record expected = dao.show(record.getId());
        assertEquals(record, expected);
    }

    @Test
    @DisplayName("Удаление записи")
    void testDeleteRecord(){
        dao.delete(record.getId());
        Record expected = dao.show(record.getId());

        assertNull(expected);
    }
}