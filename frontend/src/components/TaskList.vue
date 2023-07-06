<template>
  <ion-list lines="inset">
    <ion-list-header mode="ios">
      <ion-label>{{ projectName }}</ion-label>
    </ion-list-header>
    <div :key="task.id" v-for="task in tasksOfProject">
      <ion-item-sliding v-if="!task.done">
        <ion-item>
          <ion-grid>
            <ion-row>
              <ion-col size="1.5">
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
        <ion-item-options side="end">
          <ion-item-option color="danger" @click="removeTask(task)">
            <ion-icon slot="icon-only" :icon="trash"/>
          </ion-item-option>
        </ion-item-options>
      </ion-item-sliding>
    </div>
  </ion-list>
  <ion-fab slot="fixed" horizontal="end" vertical="bottom">
    <ion-fab-button expand="block" @click="openTaskModal({'projectId':projectId,'priority':Priority.LOW},true)">
      <ion-icon :icon="add"></ion-icon>
    </ion-fab-button>
  </ion-fab>
</template>
<script setup lang="ts">
import {Priority} from "@/model/task";
import {add, trash} from "ionicons/icons";
import {
  IonCheckbox,
  IonCol,
  IonFab,
  IonFabButton,
  IonGrid,
  IonIcon,
  IonItem,
  IonItemOption,
  IonItemSliding,
  IonList,
  IonRow
} from "@ionic/vue";
import {useTasksOfProjectId} from "@/composables/useTasksOfProjectId";
import {format, parseISO} from 'date-fns';
import {useProjects} from "@/composables/useProjects";

const props = defineProps(['projectId'])
const {tasksOfProject, removeTask, finishTask, openTaskModal} = useTasksOfProjectId(props.projectId);
const {projects} = useProjects();
const projectName = projects.value.find(project => project.id == props.projectId)?.name;

function format_date(inputDate: any) {
  if (inputDate != null) {
    return format(new Date(inputDate.toString()), 'dd.MM.yyyy');
  } else return null;
}

</script>