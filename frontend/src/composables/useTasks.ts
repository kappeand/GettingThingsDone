import {getAllTasks, updateTask, addTask} from '@/api/tasks';
import {Task} from '@/model/task';
import {onMounted, ref, UnwrapRef} from 'vue';

export function useTasks() {

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
            await updateTask(task);
            console.log("ahhhh");
            getTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addTask = async (value: UnwrapRef<Task>) => {
        try {
            // add the new todo and update the list of all todos afterwards
            await addTask(newTask.value);
            getTasks();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getTasks);

    return {
        newTask,
        tasks,
        getTasks,
        addTask,
        finishTask
    }
}