import {onMounted, ref, UnwrapRef} from 'vue';
import {Project} from "@/model/project";
import {getAllProjects} from "@/api/projects";

export function useProjects() {

    const projects = ref<Project[]>([]);

    const newProject = ref<Project>({});

    const inboxId = ref<number>();

    const getProjects = async () => {
        try {
            projects.value = await getAllProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getInboxProjectId = async () =>{
        await getProjects();
        inboxId.value = projects.value[0].id;
        return inboxId.value;
    }

    const addProject = async (value: UnwrapRef<Project>) => {
        try {
            // add the new todo and update the list of all todos afterwards
            await addProject(newProject.value);
            getProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getInboxProjectId);

    return {
        newProject,
        inboxId,
        projects,
        getInboxProjectId,
        getProjects,
        addProject
    }
}