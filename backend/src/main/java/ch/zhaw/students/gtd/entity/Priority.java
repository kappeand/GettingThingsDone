package ch.zhaw.students.gtd.entity;

public enum Priority {
    LOW, MEDIUM, HIGH;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
