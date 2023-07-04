import {getAllTasks, saveTask} from '@/api/tasks';
import {Task} from '@/model/task';
import {onMounted, ref} from 'vue';
import {modalController} from "@ionic/vue";
import TaskModal from "@/components/TaskModal.vue";

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

    const openModal = async (task: any) => {
        const modal = await modalController.create({
            component: TaskModal,
            componentProps: {
                modalTask: task
            }
        });
        await modal.present();
    }


    onMounted(loadTasks);
    return {
        openModal,
        tasks,
        addOrUpdateTask,
        finishTask
    }
}