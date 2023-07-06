import {Project} from "@/model/project";

export enum Priority {
    HIGH = 3, MEDIUM = 2, LOW = 1
}

export interface Task {
    id?: number;
    projectId?: number;
    name?: string;
    done?: boolean;
    description?: string;
    dueDate?: Date;
    priority?: Priority;
}

