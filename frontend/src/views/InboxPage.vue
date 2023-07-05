<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header mode="ios">
          <ion-label>Inbox</ion-label>
        </ion-list-header>
        <div :key="task.id" v-for="task in tasks" v-if="inboxId>-1">
          <ion-item v-if="!task.done && task.projectId == inboxId">
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
                <ion-col @click="openModal(task)">
                  {{ task.name }}
                </ion-col>
              </ion-row>
            </ion-grid>
          </ion-item>
        </div>
      </ion-list>
      <ion-fab slot="fixed" horizontal="end" vertical="bottom">
        <ion-fab-button expand="block" @click="openModal(null)">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
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
import {Priority} from "@/model/task";
import {onMounted, ref} from "vue";

const {tasks, finishTask, openModal} = useTasks();
const {getInboxId} = useProjects();

let inboxId = ref(-1);
onMounted(async () => {
  inboxId.value = await getInboxId() as number;
})
</script>