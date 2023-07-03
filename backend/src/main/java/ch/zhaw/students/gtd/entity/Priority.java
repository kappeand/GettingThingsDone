package ch.zhaw.students.gtd.entity;

public enum Priority {
    Low, Medium, High;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
