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
                <ion-col @click="openModal(task)">
                  <ion-label>
                    <h2 style="font-weight: bold">{{ task.name }}</h2>
                    <p>{{ format_date(task.dueDate) }}</p>
                  </ion-label>
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
  IonLabel
} from "@ionic/vue";
import {add} from 'ionicons/icons';
import {useTasks} from "@/composables/useTasks";
import {useProjects} from "@/composables/useProjects";
import {Priority} from "@/model/task";
import {onMounted, ref} from "vue";
import moment from 'moment';

const {tasks, finishTask, openModal} = useTasks();
const {getInboxId} = useProjects();

let inboxId = ref(-1);
onMounted(async () => {
  inboxId.value = await getInboxId() as number;
})

function format_date(value: Date) {
  if (value) {
    return moment(String(value)).format('hh:mm DD.MM.YYYY')
  }
}

</script>