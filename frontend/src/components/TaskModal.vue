<template>
  <ion-modal ref="modal" :trigger="trigger" :initial-breakpoint="0.5" :breakpoints="[0, 0.25, 0.5, 0.75]">
    <ion-content class="ion-padding">
      <ion-item>
        <ion-input type="text" placeholder="Title" v-model="modalTask.name"></ion-input>
      </ion-item>
      <ion-item>
        <ion-textarea type="text" placeholder="Description" v-model="modalTask.description"></ion-textarea>
      </ion-item>
      <ion-item>
        <ion-select v-model="modalTask.projectId" aria-label="project" class="always-flip"
                    toggleIcon="caret-down-sharp" interface="popover" label="Project">
          <ion-select-option :value="project.id" :key="project.id" v-for="project in projects">{{ project.name }}
          </ion-select-option>
        </ion-select>
      </ion-item>
      <ion-item>
        <ion-select v-model="modalTask.priority" aria-label="priority" class="always-flip"
                    toggleIcon="caret-down-sharp" interface="popover" label="Priority">
          <ion-select-option value="HIGH">High</ion-select-option>
          <ion-select-option value="MEDIUM">Medium</ion-select-option>
          <ion-select-option value="LOW">Low</ion-select-option>
        </ion-select>
      </ion-item>
      <ion-button @click="async function (){await addTask(await getTask())}" expand="full"> Create Task</ion-button>
    </ion-content>
  </ion-modal>
</template>
<script setup lang="ts">
import {IonButton, IonContent, IonInput, IonItem, IonModal, IonSelect, IonSelectOption, IonTextarea} from "@ionic/vue";
import {useProjects} from "@/composables/useProjects";
import {useTasks} from "@/composables/useTasks";
import {ref} from "vue";
import {Priority, Task} from "@/model/task";

const {projects} = useProjects();
const {addTask} = useTasks();
const props = defineProps(['modalTask', 'trigger']);

const {getInboxProjectId} = useProjects();
const task = ref<Task>({});

async function getTask() {
  if (props.modalTask != null) {
    task.value = props.modalTask;
  } else {
    const newTask = ref<Task>({});
    newTask.value.projectId = await getInboxProjectId();
    newTask.value.priority = Priority.LOW;
  }
  return task.value;
}


</script>