<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header mode="ios">
          <ion-label>Inbox</ion-label>
        </ion-list-header>
        <ion-item :key="task.id" v-for="task in tasks">
          <ion-grid>
            <ion-row>
              <ion-col size="1">
                <ion-checkbox labelPlacement="end" style="--border-color: green" mode="ios" color="success"
                              @click="finishTask(task)"></ion-checkbox>
              </ion-col>
              <ion-col>
                {{ task.name }}
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>
      </ion-list>
      <ion-fab slot="fixed" horizontal="end" vertical="bottom">
        <ion-fab-button id="open-modal" expand="block">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
      <ion-modal ref="modal" trigger="open-modal" :initial-breakpoint="0.5" :breakpoints="[0, 0.25, 0.5, 0.75]">
        <ion-content class="ion-padding">
          <ion-item>
            <ion-input type="text" placeholder="Title" v-model="newTask.name"></ion-input>
          </ion-item>
          <ion-item>
            <ion-textarea type="text" placeholder="Description" v-model="newTask.description"></ion-textarea>
          </ion-item>
          <ion-item>
            <ion-datetime-button datetime="datetime"></ion-datetime-button>
          </ion-item>
          <ion-item>
            <ion-select aria-label="priority" interface="popover" placeholder="Priority">
              <ion-select-option value="High">High</ion-select-option>
              <ion-select-option value="Medium">Medium</ion-select-option>
              <ion-select-option value="Low">Low</ion-select-option>
            </ion-select>
          </ion-item>
          <ion-item>
            <ion-button @click="addTask()" expand="full"> Create Task</ion-button>
          </ion-item>
          <ion-modal :keep-contents-mounted="true">
            <ion-datetime v-model="newTask.dueDate" id="datetime"></ion-datetime>
          </ion-modal>
        </ion-content>
      </ion-modal>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonDatetimeButton,
  IonDatetime,
  IonSelect,
  IonSelectOption,
  IonTextarea,
  IonFab,
  IonFabButton,
  IonModal,
  IonIcon,
  IonCheckbox,
  IonPage,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonButton,
  IonInput,
} from "@ionic/vue";
import {add, sendOutline} from 'ionicons/icons';
import {useTasks} from "../composables/useTasks";
import {addTask} from "@/api/tasks";

const {newTask, tasks, getTasks, addTask, finishTask} = useTasks();
</script>