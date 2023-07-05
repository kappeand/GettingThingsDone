import {onMounted, ref} from 'vue';
import {Project} from "@/model/project";
import {deleteProject, getAllProjects, saveProject} from "@/api/projects";
import {modalController} from "@ionic/vue";
import ProjectModal from "@/components/ProjectModal.vue";
import TaskListModal from "@/components/TaskListModal.vue";


const projects = ref<Project[]>([]);
const inboxId = ref<number>(-1);

export function useProjects() {
    const addOrUpdateProject = async (project: Project) => {
        try {
            await saveProject(project);
            await loadProjects();
        } catch (error) {
            console.log(error);
        }
    }


    async function loadProjects() {
        try {
            projects.value = await getAllProjects();
        } catch (error) {
            console.log(error);
        }
    }

    async function removeProject(project: Project) {
        console.log(project.id);
        try {
            await deleteProject(project);
            await loadProjects();
        } catch (error) {
            console.log(error);
        }
    }

    onMounted(async () => {
        await loadProjects();
        inboxId.value = projects.value.find((project) => project.name == "Inbox")?.id!;
    });

    const openProjectModal = async (project: Project, isNewProject: boolean) => {
        const modal = await modalController.create({
            component: ProjectModal,
            breakpoints: [0, 0.3, 0.5, 0.8],
            initialBreakpoint: 0.5,
            componentProps: {
                modalProject: project,
                isNewProject: isNewProject,
            }
        });
        await modal.present();
    };

    const openTaskListModal = async (project: Project, isArchive: boolean) => {
        const modal = await modalController.create({
            component: TaskListModal,
            componentProps: {
                projectId: project.id,
                isArchive: isArchive
            }
        });
        await modal.present();
    }

    return {
        openProjectModal,
        openTaskListModal,
        removeProject,
        inboxId,
        projects,
        addOrUpdateProject
    }
}