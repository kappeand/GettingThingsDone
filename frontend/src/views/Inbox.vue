<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ion-list lines="inset">
        <ion-list-header>
          <ion-label mode="ios">Video Games</ion-label>
        </ion-list-header>
        <ion-item :key="task.id" v-for="task in tasks">
          <ion-grid>
            <ion-row>
              <ion-col size="1">
                <ion-checkbox labelPlacement="end" style="--border-color: green" mode="ios" color="success"
                              @click="finishTask(task)"></ion-checkbox>
              </ion-col>
              <ion-col>
                {{ task.title }}
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>
      </ion-list>
      <ion-item>
        <ion-input type="text" placeholder="New Task Title" v-model="newTask.title"></ion-input>
      </ion-item>
      <div padding>
        <ion-button @click="addTask()">Add New Task</ion-button>
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonCheckbox,
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonButton,
  IonInput,
} from "@ionic/vue";
import {useTasks} from "../composables/useTasks";
import {addTask} from "@/api/tasks";

const {newTask, tasks, getTasks, addTask, finishTask} = useTasks();

</script>