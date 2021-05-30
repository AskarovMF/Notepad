package ru.askarov.notepad.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.askarov.notepad.config.SpringConfig;
import ru.askarov.notepad.dao.RecordDao;
import ru.askarov.notepad.model.Record;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class RecordControllerTest {

    @Test
    void index() {
        List<Record> records = Arrays.asList(new Record(), new Record(), new Record());
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);
        Model model = new ExtendedModelMap();

        Mockito.when(dao.index()).thenReturn(records);

        String viewName = controller.index(model);

        assertEquals("records/index", viewName);
        assertSame(records, model.getAttribute("records"));
        Mockito.verify(dao).index();
    }

    @Test
    void show() {
        Record record = new Record();
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);
        Model model = new ExtendedModelMap();

        Mockito.when(dao.show(Mockito.anyLong())).thenReturn(record);

        String viewName = controller.show(Mockito.anyLong(), model);

        assertEquals("records/show", viewName);
        assertSame(record, model.getAttribute("record"));
        Mockito.verify(dao).show(Mockito.anyLong());
    }

    @Test
    void newRecord() {
        Record record = new Record();
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);

        String viewName = controller.newRecord(record);

        assertEquals("records/new", viewName);
    }

    @Test
    void create() {
        Record record = new Record();
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);

        String viewName = controller.create(record);

        assertEquals("redirect:/records", viewName);
        Mockito.verify(dao).save(record);
    }

    @Test
    void edit() {
        Record record = new Record();
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);
        Model model = new ExtendedModelMap();

        Mockito.when(dao.show(Mockito.anyLong())).thenReturn(record);

        String viewName = controller.edit(model, Mockito.anyLong());

        assertEquals("records/edit", viewName);
        assertSame(record, model.getAttribute("record"));
        Mockito.verify(dao).show(Mockito.anyLong());
    }

    @Test
    void update() {
        Record record = new Record();
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);

        Mockito.when(dao.show(Mockito.anyLong())).thenReturn(record);

        String viewName = controller.update("Test title", "Test article", Mockito.anyLong());

        assertEquals("redirect:/records", viewName);
        assertEquals("Test title", record.getTitle());
        assertEquals("Test article", record.getArticle());

        Mockito.verify(dao).show(Mockito.anyLong());
        Mockito.verify(dao).update((Record) Mockito.any());
    }

    @Test
    void delete() {
        RecordDao dao = Mockito.mock(RecordDao.class);
        RecordController controller = new RecordController(dao);

        String viewName = controller.delete(Mockito.anyLong());

        assertEquals("redirect:/records", viewName);
        Mockito.verify(dao).delete(Mockito.anyLong());
    }
}