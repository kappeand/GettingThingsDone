<template>
  <ion-content class="ion-padding">
    <ion-grid>
      <ion-row>
        <ion-col size="1" v-if="!isNewTask" style="padding-top: 4%">
          <ion-checkbox v-if="modalTask.priority == Priority.HIGH"
                        style="--checkmark-color: background ;--border-color: red;--border-color-checked: red;--checkbox-background-checked: red"
                        mode="ios" color="red"
                        @click="finishTask(modalTask);modalController.dismiss();"></ion-checkbox>
          <ion-checkbox v-if="modalTask.priority == Priority.MEDIUM"
                        style="--checkmark-color: background ;--border-color: yellow;--border-color-checked: yellow;--checkbox-background-checked: yellow"
                        mode="ios" color="yellow"
                        @click="finishTask(modalTask);modalController.dismiss();"></ion-checkbox>
          <ion-checkbox v-if="modalTask.priority == Priority.LOW"
                        style="--border-color: green;--border-color-checked: green;--checkbox-background-checked: green"
                        mode="ios" color="green"
                        @click="finishTask(modalTask);modalController.dismiss();"></ion-checkbox>
        </ion-col>
        <ion-col>
          <ion-row>
            <ion-input v-model="modalTask.name" placeholder="Title" type="text"></ion-input>
          </ion-row>
          <ion-row>
            <ion-textarea rows="5" label="Description" v-model="modalTask.description" labelPlacement="stacked"
                          placeholder="Enter description here"></ion-textarea>
          </ion-row>
          <ion-row>
            <p>Due date</p>
            <ion-datetime-button v-if="modalTask.dueDate != undefined" datetime="datetime"></ion-datetime-button>
            <ion-datetime-button v-if="modalTask.dueDate == undefined" datetime="datetime"></ion-datetime-button>
          </ion-row>
          <ion-modal :keep-contents-mounted="true">
            <ion-datetime locale="de-CH" first-day-of-week="1" v-model="modalTask.dueDate" id="datetime"></ion-datetime>
          </ion-modal>
          <ion-row>
            <ion-select v-model="modalTask.projectId" aria-label="project" class="always-flip"
                        toggleIcon="caret-down-sharp" interface="popover" label="Project">
              <ion-select-option :value="project.id" :key="project.id" v-for="project in projects">
                {{ project.name }}
              </ion-select-option>
            </ion-select>
          </ion-row>
          <ion-row>
            <ion-select v-model="modalTask.priority" aria-label="priority" class="always-flip"
                        toggleIcon="caret-down-sharp" interface="popover" label="Priority">
              <ion-select-option value="HIGH">High</ion-select-option>
              <ion-select-option value="MEDIUM">Medium</ion-select-option>
              <ion-select-option value="LOW">Low</ion-select-option>
            </ion-select>
          </ion-row>
        </ion-col>
      </ion-row>
    </ion-grid>
    <ion-button v-if="isNewTask" @click="handleButton" expand="full">Create Task</ion-button>
    <ion-button v-if="!isNewTask" @click="handleButton" expand="full">Update Task</ion-button>
  </ion-content>

</template>
<script setup lang="ts">
import {
  IonButton,
  IonCheckbox,
  IonCol,
  IonContent,
  IonDatetime,
  IonDatetimeButton,
  IonGrid,
  IonInput,
  IonModal,
  IonRow,
  IonSelect,
  IonSelectOption,
  modalController
} from "@ionic/vue";
import {useProjects} from "@/composables/useProjects";
import {useTasksOfProjectId} from "@/composables/useTasksOfProjectId";
import {Priority} from "@/model/task";

const {projects} = useProjects();
const props = defineProps(['modalTask', 'isNewTask']);
const {addOrUpdateTask, finishTask} = useTasksOfProjectId(props.modalTask.projectId);

async function handleButton() {
  await addOrUpdateTask(props.modalTask);
  await modalController.dismiss();
}
</script>