import { getAllTasks, saveTask} from '@/api/tasks';
import {Priority, Task} from '@/model/task';
import {onMounted, ref, UnwrapRef} from 'vue';
import {useProjects} from "@/composables/useProjects";

export function useTasks() {

    const {getInboxProjectId} = useProjects()

    const tasks = ref<Task[]>([]);
    const newTask = ref<Task>({});

    const getTasks = async () => {
        try {
            tasks.value = await getAllTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const finishTask = async (task: Task) => {
        //wait for checkbox animation to finish
        await new Promise(f => setTimeout(f, 500));
        try {
            task.done = true;
            await saveTask(task);
            getTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addTask = async (task: Task) => {
        try{
            await saveTask(task);
            getTasks();
        }  catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getTasks);
    return {
        newTask,
        tasks,
        getTasks,
        addTask,
        updateExistingTask: addTask,
        finishTask
    }


}