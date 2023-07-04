import {getAllTasks, updateTask, addTask} from '@/api/tasks';
import {Task} from '@/model/task';
import {Project} from '@/model/project';
import {onMounted, ref, UnwrapRef} from 'vue';
import {getAllProjects} from "@/api/projects";

export function useTasks() {

    const tasks = ref<Task[]>([]);
    const newTask = ref<Task>({});

    const projects = ref<Project[]>([]);
    const inboxId = ref<number>();

    const getTasks = async () => {

        //TODO only do first time
        try {
            projects.value = await getAllProjects();
            inboxId.value = projects.value[0].id;
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
        inboxId,
        tasks,
        getTasks,
        addTask,
        finishTask
    }
}