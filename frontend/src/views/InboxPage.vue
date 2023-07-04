<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header mode="ios">
          <ion-label>Inbox</ion-label>
        </ion-list-header>
        <div :key="task.id" v-for="task in tasks">
          <ion-item id="open-modal" v-on:click="setTask(task)" v-if="!task.done && inboxId == task.projectId">
            <ion-grid>
              <ion-row>
                <ion-col size="1">
                  <ion-checkbox v-if="task.priority == Priority.HIGH"
                                style="--border-color: red" mode="ios" color="red"
                                @click="finishTask(task)"></ion-checkbox>
                  <ion-checkbox v-if="task.priority == Priority.MEDIUM"
                                style="--border-color: yellow" mode="ios" color="yellow"
                                @click="finishTask(task)"></ion-checkbox>
                  <ion-checkbox v-if="task.priority == Priority.LOW"
                                style="--border-color: green" mode="ios" color="green"
                                @click="finishTask(task)"></ion-checkbox>
                </ion-col>
                <ion-col>
                  {{ task.name }}
                </ion-col>
              </ion-row>
            </ion-grid>
          </ion-item>
          <TaskModal :modal-task="task"></TaskModal>
        </div>
      </ion-list>
      <ion-fab slot="fixed" horizontal="end" vertical="bottom">
        <ion-fab-button id="open-modal" v-on:click="setTask(newTask)" expand="block">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
      <TaskModal :modal-task="modalTask"></TaskModal>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonCheckbox,
  IonCol,
  IonContent,
  IonFab,
  IonFabButton,
  IonGrid,
  IonIcon,
  IonItem,
  IonList,
  IonPage,
  IonRow,
} from "@ionic/vue";
import {add} from 'ionicons/icons';
import {useTasks} from "../composables/useTasks";
import {useProjects} from "../composables/useProjects";
import {Priority, Task} from "../model/task";
import {onMounted} from "vue";
import TaskModal from '../components/TaskModal.vue'

const {newTask, tasks, addTask, updateExistingTask, finishTask} = useTasks();
const {inboxId, projects, getInboxProjectId} = useProjects();

let modalTask = newTask;

function setTask(task: Task) {
  modalTask.value = task;

}

onMounted(async () => {
  newTask.value.projectId = await getInboxProjectId();
  newTask.value.priority = Priority.LOW;
})
</script>