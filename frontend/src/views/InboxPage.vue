<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <TaskList :project-id="inboxId" :is-archive="false" v-if="inboxId>-1 && tasksLoaded"/>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonContent, IonPage, onIonViewWillEnter, onIonViewWillLeave,} from "@ionic/vue";
import TaskList from "@/components/TaskList.vue";
import {useProjects} from "@/composables/useProjects";
import {useTasksOfProjectId} from "@/composables/useTasksOfProjectId";
import {ref} from "vue";

const {inboxId} = useProjects();
const {loadTasks} = useTasksOfProjectId(inboxId.value);

let tasksLoaded = ref<boolean>(false);
onIonViewWillEnter(async () => {
  await loadTasks();
  tasksLoaded.value = true;
});
onIonViewWillLeave(() => {
  tasksLoaded.value = false;
});
</script>