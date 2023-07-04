<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header mode="ios">
          <ion-label>Inbox</ion-label>
        </ion-list-header>
        <div :key="task.id" v-for="task in tasks">
          <ion-item v-if="!task.done">
            <ion-grid v-if="!task.done">
              <ion-row>
                <ion-col size="1">
                  <ion-checkbox v-if="task.priority == Priority.HIGH " labelPlacement="end" style="--border-color: red"
                                mode="ios" color="red"
                                @click="finishTask(task)"></ion-checkbox>
                  <ion-checkbox v-if="task.priority == Priority.MEDIUM " labelPlacement="end"
                                style="--border-color: yellow" mode="ios" color="yellow"
                                @click="finishTask(task)"></ion-checkbox>
                  <ion-checkbox v-if="task.priority == Priority.LOW  " labelPlacement="end"
                                style="--border-color: green"
                                mode="ios" color="green"
                                @click="finishTask(task)"></ion-checkbox>
                </ion-col>
                <ion-col>
                  {{ task.name }}
                </ion-col>
              </ion-row>
            </ion-grid>
          </ion-item>
        </div>
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
          <ion-button @click="addTask()" expand="full"> Create Task</ion-button>
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
  IonButton,
  IonCheckbox,
  IonCol,
  IonContent,
  IonDatetime,
  IonDatetimeButton,
  IonFab,
  IonFabButton,
  IonGrid,
  IonIcon,
  IonInput,
  IonItem,
  IonList,
  IonModal,
  IonPage,
  IonRow,
  IonSelect,
  IonSelectOption,
  IonTextarea,
} from "@ionic/vue";
import {add} from 'ionicons/icons';
import {useTasks} from "../composables/useTasks";
import {addTask} from "@/api/tasks";
import {Priority} from "../model/task"
import {Project} from "@/model/project";

const {newTask, tasks, getTasks, addTask, finishTask} = useTasks();
</script>