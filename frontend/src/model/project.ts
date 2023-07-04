import {Task} from "@/model/task";
import {UserAuthResponse} from "@/model/userAuthResponse";

export interface Project {
    id?: number;
    name?: string;
    tasks?: Task[];
    owner?: UserAuthResponse;

}
