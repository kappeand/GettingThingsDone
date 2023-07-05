<template>
  <ion-content class="ion-padding">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-button @click="dismiss">Close</ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <TaskList v-if="!isArchive" :project-id="projectId"/>
    <ArchivedTaskList v-if="isArchive" :project-id="projectId"/>
  </ion-content>
</template>
<script setup lang="ts">
import {IonContent, modalController} from "@ionic/vue";
import TaskList from "@/components/TaskList.vue";
import ArchivedTaskList from "@/components/ArchivedTaskList.vue";
import {useProjects} from "@/composables/useProjects";

const {loadProjects} = useProjects();

defineProps(['projectId', 'isArchive']);

function dismiss() {
  loadProjects();
  return modalController.dismiss(null, 'cancel');
}
</script>