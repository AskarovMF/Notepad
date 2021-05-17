package ru.askarov.notepad.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Record {

    @Id
    @GeneratedValue
    protected long id;

    protected String title;
    protected String article;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date lastModified;

    public Record() {
    }

    public Record(String article) {
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String getLastModified() {
        return new SimpleDateFormat("dd/MM/yyyy HH:hh:mm").format(lastModified);
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && Objects.equals(title, record.title) && Objects.equals(article, record.article) && Objects.equals(lastModified, record.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, article, lastModified);
    }
}
