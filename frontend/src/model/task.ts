import {Project} from "@/model/project";

export enum Priority {
    HIGH = "HIGH", MEDIUM = "MEDIUM", LOW = "LOW"
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

