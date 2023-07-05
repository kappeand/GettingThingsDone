<template>
  <ion-content class="ion-padding">
    <ion-item>
      <ion-input type="text" placeholder="Title" v-model="task.name"></ion-input>
    </ion-item>
    <ion-item>
      <ion-textarea type="text" placeholder="Description" v-model="task.description"></ion-textarea>
    </ion-item>
    <ion-item>
      <ion-select v-model="task.projectId" aria-label="project" class="always-flip"
                  toggleIcon="caret-down-sharp" interface="popover" label="Project">
        <ion-select-option :value="project.id" :key="project.id" v-for="project in projects">
          {{ project.name }}
        </ion-select-option>
      </ion-select>
    </ion-item>
    <ion-item>
      <ion-select v-model="task.priority" aria-label="priority" class="always-flip"
                  toggleIcon="caret-down-sharp" interface="popover" label="Priority">
        <ion-select-option value="HIGH">High</ion-select-option>
        <ion-select-option value="MEDIUM">Medium</ion-select-option>
        <ion-select-option value="LOW">Low</ion-select-option>
      </ion-select>
    </ion-item>
    <ion-button v-if="isNewTask" @click="handleButton(task)" expand="full">Create Task</ion-button>
    <ion-button v-if="!isNewTask" @click="handleButton(task)" expand="full">Update Task</ion-button>
  </ion-content>
</template>
<script setup lang="ts">
import {
  IonButton,
  IonContent,
  IonInput,
  IonItem,
  IonSelect,
  IonSelectOption,
  IonTextarea,
  modalController
} from "@ionic/vue";
import {useProjects} from "@/composables/useProjects";
import {useTasks} from "@/composables/useTasks";
import {onMounted, ref} from "vue";
import {Priority, Task} from "@/model/task";

const {projects, getInboxId} = useProjects();
const {addOrUpdateTask} = useTasks();
const props = defineProps(['modalTask']);

async function handleButton(modalTask: Task) {
  await addOrUpdateTask(modalTask);
  await modalController.dismiss();
}

let isNewTask = false;
let task = ref<Task>({});
onMounted(async () => {
  if (props.modalTask != null) {
    task = props.modalTask;
  } else {
    isNewTask = true;
    task.value.projectId = await getInboxId();
    task.value.priority = Priority.LOW
  }
});
</script>