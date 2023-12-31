import axios from 'axios';
import {API_ROOT} from "@/config/development";
import {Project} from "@/model/project";

export async function getAllProjects(): Promise<Project[]> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/project', config);
        return response.data;
    } catch (error) {
        return <any>error;
    }
}

export async function saveProject(project: Project): Promise<any> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT + '/api/project', project, config);
        return response.data;
    } catch (error) {
        return error;
    }
}

export async function deleteProject(project: Project): Promise<any> {
    const config = {
        withCredentials: true
    }
    try {
        await axios.delete(API_ROOT + '/api/project/' + project.id, config);
    } catch (error) {
        return error;
    }
}
