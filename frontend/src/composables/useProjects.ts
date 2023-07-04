import {Task} from '@/model/task';
import {onMounted, ref, UnwrapRef} from 'vue';
import {Project} from "@/model/project";
import {getAllProjects} from "@/api/projects";

export function useProjects() {

    const projects = ref<Project[]>([]);

    const newProject = ref<Project>({});

    const getProjects = async () => {
        try {
            projects.value = await getAllProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
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

    onMounted(getProjects);

    return {
        newProject,
        projects,
        getProjects,
        addProject
    }
}