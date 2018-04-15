package pl.setblack.kotlinspring;

import java.io.Serializable;
import java.util.Objects;

public class DocumentJ implements Serializable {
    public final int id;
    public final String path;
    public final long type;

    public DocumentJ(int id, String path, long type) {
        this.id = id;
        this.path = path;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentJ documentJ = (DocumentJ) o;
        return id == documentJ.id &&
                type == documentJ.type &&
                Objects.equals(path, documentJ.path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, path, type);
    }

    @Override
    public String toString() {
        return "DocumentJ{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", type=" + type +
                '}';
    }

    public DocumentJ withId(int newId) {
        return new DocumentJ(newId, this.path, this.type);
    }

    public DocumentJ withId(String newPath) {
        return new DocumentJ(this.id, newPath, this.type);
    }

    public DocumentJ withType(long newType) {
        return new DocumentJ(this.id, this.path, newType);
    }
}
