import {getAllTasks, saveTask} from '@/api/tasks';
import {Task} from '@/model/task';
import {onMounted, ref} from 'vue';

const tasks = ref<Task[]>([]);

export function useTasks() {

    const loadTasks = async () => {
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
            await loadTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addOrUpdateTask = async (task: Task) => {
        try {
            await saveTask(task);
            await loadTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(loadTasks);
    return {
        tasks,
        addOrUpdateTask,
        finishTask
    }


}