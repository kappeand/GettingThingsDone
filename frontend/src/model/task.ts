import {Project} from "@/model/project";

enum priority {
    High,
    Middle,
    Low,
}

export interface Task {
    id?: number;
    name?: string;
    done?: boolean;
    description?: string;
    dueDate?: Date;
    priority?: priority;
    project?: Project;

}

