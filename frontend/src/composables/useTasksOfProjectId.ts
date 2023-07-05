import {getTasks, saveTask} from '@/api/tasks';
import {Task} from '@/model/task';
import {onMounted, ref} from 'vue';
import {modalController} from "@ionic/vue";
import TaskModal from "@/components/TaskModal.vue";

const tasksOfProjectId = ref<Task[]>([]);

export function useTasksOfProjectId(projectId: number) {

    const loadTasks = async () => {
        try {
            tasksOfProjectId.value = await getTasks(projectId);
        } catch (error) {
            console.log(error);
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
            console.log(error);
        }
    }

    const addOrUpdateTask = async (task: Task) => {
        try {
            await saveTask(task);
            await loadTasks();
        } catch (error) {
            console.log(error);
        }
    }

    const openModal = async (task: any, isNewTask: boolean) => {
        const modal = await modalController.create({
            component: TaskModal,
            breakpoints: [0, 0.3, 0.5, 0.8],
            initialBreakpoint: 0.5,
            componentProps: {
                modalTask: task,
                isNewTask: isNewTask
            }
        });
        await modal.present();
    }

    onMounted(async () => {
        await loadTasks();
    });
    return {
        openModal,
        tasksOfProjectId,
        addOrUpdateTask,
        finishTask
    }
}