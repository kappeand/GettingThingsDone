<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header mode="ios">
          <ion-label>Inbox</ion-label>
        </ion-list-header>
        <div :key="task.id" v-for="task in tasks">
          <ion-item :id="task.id" v-if="!task.done && inboxId == task.projectId">
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
            <TaskModal :trigger="task.id" :modal-task="task"/>
          </ion-item>
        </div>
      </ion-list>
      <ion-fab slot="fixed" horizontal="end" vertical="bottom">
        <ion-fab-button id="newTask-Modal" expand="block">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
        <TaskModal trigger="newTask-Modal" :modal-task="newTask"/>
      </ion-fab>
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
import {useTasks} from "@/composables/useTasks";
import {useProjects} from "@/composables/useProjects";
import {Priority, Task} from "@/model/task";
import TaskModal from '../components/TaskModal.vue'
import {ref} from "vue";

const {tasks, finishTask} = useTasks();
const {inboxId, getInboxProjectId} = useProjects();

const newTask = ref<Task>({
  projectId: await getInboxProjectId(),
  priority: Priority.LOW
});

</script>