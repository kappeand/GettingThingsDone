<template>
  <ion-content class="ion-padding">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-button @click="dismiss">Close</ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <TaskList :project-id="projectId" :is-archive="false"/>
  </ion-content>
</template>
<script setup lang="ts">
import {IonContent, modalController} from "@ionic/vue";
import TaskList from "@/components/TaskList.vue";

defineProps(['projectId']);

function dismiss() {
  return modalController.dismiss(null, 'cancel');
}
</script>