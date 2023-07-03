enum priority {
    High,
    Middle,
    Low,
}

export interface Task {
    id?: number;
    title?: string;
    done?: boolean;
    description?: string;
    dueDate?: Date;
    priority?: priority.High;


}

