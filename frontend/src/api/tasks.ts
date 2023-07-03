import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Task } from '@/model/task';

export async function getAllTasks(): Promise<Task[]>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/task', config);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
}

export async function addTask(task: Task): Promise<any>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT + '/api/task', task, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}

export async function updateTask(task: Task): Promise<any>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.put(API_ROOT + '/api/task', task, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}