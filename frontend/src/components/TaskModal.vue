<template>
  <ion-content class="ion-padding">
    <ion-grid>
      <ion-row>
        <ion-col size="1" v-if="!isNewTask" style="padding-top: 4%">
          <ion-checkbox v-if="task.priority == Priority.HIGH"
                        style="--checkmark-color: background ;--border-color: red;--border-color-checked: red;--checkbox-background-checked: red"
                        mode="ios" color="red"
                        @click="finishTask(task);modalController.dismiss();"></ion-checkbox>
          <ion-checkbox v-if="task.priority == Priority.MEDIUM"
                        style="--checkmark-color: background ;--border-color: yellow;--border-color-checked: yellow;--checkbox-background-checked: yellow"
                        mode="ios" color="yellow"
                        @click="finishTask(task);modalController.dismiss();"></ion-checkbox>
          <ion-checkbox v-if="task.priority == Priority.LOW"
                        style="--border-color: green;--border-color-checked: green;--checkbox-background-checked: green"
                        mode="ios" color="green"
                        @click="finishTask(task);modalController.dismiss();"></ion-checkbox>
        </ion-col>
        <ion-col>
          <ion-row>
            <ion-input v-model="task.name" placeholder="Title" type="text"></ion-input>
          </ion-row>
          <ion-row>
            <ion-datetime-button v-if="task.dueDate != undefined" datetime="datetime"></ion-datetime-button>
            <ion-datetime-button v-if="task.dueDate == undefined" datetime="datetime"
                                 :color="'success'"></ion-datetime-button>
          </ion-row>
          <ion-modal :keep-contents-mounted="true">
            <ion-datetime v-model="task.dueDate" id="datetime"></ion-datetime>
          </ion-modal>
          <ion-row>
            <ion-select v-model="task.projectId" aria-label="project" class="always-flip"
                        toggleIcon="caret-down-sharp" interface="popover" label="Project">
              <ion-select-option :value="project.id" :key="project.id" v-for="project in projects">{{
                  project.name
                }}
              </ion-select-option>
            </ion-select>
          </ion-row>
          <ion-row>
            <ion-select v-model="task.priority" aria-label="priority" class="always-flip"
                        toggleIcon="caret-down-sharp" interface="popover" label="Priority">
              <ion-select-option value="HIGH">High</ion-select-option>
              <ion-select-option value="MEDIUM">Medium</ion-select-option>
              <ion-select-option value="LOW">Low</ion-select-option>
            </ion-select>
          </ion-row>
        </ion-col>
      </ion-row>
    </ion-grid>
    <ion-button v-if="isNewTask" @click="handleButton(task)" expand="full">Create Task</ion-button>
    <ion-button v-if="!isNewTask" @click="handleButton(task)" expand="full">Update Task</ion-button>
  </ion-content>

</template>
<script setup lang="ts">
import {
  IonButton, IonCheckbox, IonCol,
  IonContent,
  IonDatetime,
  IonDatetimeButton, IonGrid,
  IonInput,
  IonItem,
  IonModal, IonRow,
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
const {addOrUpdateTask, finishTask} = useTasks();
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