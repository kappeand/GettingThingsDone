import {onMounted, ref, UnwrapRef} from 'vue';
import {Project} from "@/model/project";
import {getAllProjects, saveProject} from "@/api/projects";

export function useProjects() {

    const projects = ref<Project[]>([]);

    const addProject = async (value: UnwrapRef<Project>) => {
        try {
            await saveProject(ref<Project>({}).value);
            await loadProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    async function getInboxId() {
        if (projects.value.length === 0) { //has at least 1 project (Inbox)
            await loadProjects();
        }
        return projects.value.findIndex(p => p.name === "Inbox");
    }

    async function loadProjects() {
        try {
            projects.value = await getAllProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(async () => {
        await loadProjects()
    });

    return {
        getInboxId,
        projects,
        addProject
    }
}