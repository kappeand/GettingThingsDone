import {onMounted, ref, UnwrapRef} from 'vue';
import {Project} from "@/model/project";
import {getAllProjects, saveProject} from "@/api/projects";

export function useProjects() {

    const projects = ref<Project[]>([]);
    const inboxId = ref<number>(-1);

    const addProject = async (value: UnwrapRef<Project>) => {
        try {
            await saveProject(ref<Project>({}).value);
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

    onMounted(async () => {
        await loadProjects();
        inboxId.value = projects.value.find((project) => project.name == "Inbox")?.id!;
    });

    return {
        inboxId,
        projects,
        addProject
    }
}