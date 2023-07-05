<template>
  <ion-list lines="inset">
    <ion-list-header mode="ios">
      <ion-label>Inbox</ion-label>
    </ion-list-header>
    <div v-if="tasksOfProjectId.length">
      <div :key="task.id" v-for="task in tasksOfProjectId">
        <ion-item v-if="isArchive ? task.done: !task.done">
          <ion-grid>
            <ion-row>
              <ion-col size="1">
                <ion-checkbox v-if="task.priority == Priority.HIGH"
                              style="--checkmark-color: background ;--border-color: red;--border-color-checked: red;--checkbox-background-checked: red"
                              mode="ios" color="red"
                              @click="finishTask(task)"></ion-checkbox>
                <ion-checkbox v-if="task.priority == Priority.MEDIUM"
                              style="--checkmark-color: background ;--border-color: yellow;--border-color-checked: yellow;--checkbox-background-checked: yellow"
                              mode="ios" color="yellow"
                              @click="finishTask(task)"></ion-checkbox>
                <ion-checkbox v-if="task.priority == Priority.LOW"
                              style="--border-color: green;--border-color-checked: green;--checkbox-background-checked: green"
                              mode="ios" color="green"
                              @click="finishTask(task)"></ion-checkbox>
              </ion-col>
              <ion-col @click="openTaskModal(task,false)">
                <ion-label>
                  <h2 style="font-weight: bold">{{ task.name }}</h2>
                  <p>{{ format_date(task.dueDate) }}</p>
                </ion-label>
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>
      </div>
    </div>
  </ion-list>
  <ion-fab slot="fixed" horizontal="end" vertical="bottom" v-if="!isArchive">
    <ion-fab-button expand="block" @click="openTaskModal({'projectId':projectId,'priority':Priority.LOW},true)">
      <ion-icon :icon="add"></ion-icon>
    </ion-fab-button>
  </ion-fab>
</template>
<script setup lang="ts">
import {Priority} from "@/model/task";
import {add} from "ionicons/icons";
import {IonCheckbox, IonCol, IonFab, IonFabButton, IonGrid, IonIcon, IonItem, IonList, IonRow} from "@ionic/vue";
import {useTasksOfProjectId} from "@/composables/useTasksOfProjectId";
import moment from "moment/moment";

const props = defineProps(['projectId', 'isArchive'])
const {tasksOfProjectId, finishTask, openTaskModal} = useTasksOfProjectId(props.projectId);

function format_date(value: Date) {
  if (value) {
    return moment(String(value)).format('DD.MM.YYYY hh:mm')
  }
}
</script>