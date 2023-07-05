<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <TaskList :project-id="inboxId" :is-archive="false" v-if="inboxId>-1"/>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonContent, IonPage,} from "@ionic/vue";
import TaskList from "@/components/TaskList.vue";
import {useProjects} from "@/composables/useProjects";

const {inboxId} = useProjects();

</script>