<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ProjectList :is-archive="true"/>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonContent, IonPage, onIonViewWillEnter, onIonViewWillLeave} from '@ionic/vue';
import ProjectList from "@/components/ProjectList.vue";
import {useProjects} from "@/composables/useProjects";
import {ref} from "vue";

const {loadProjects} = useProjects();

let projectsLoaded = ref<boolean>(false);
onIonViewWillEnter(async () => {
  await loadProjects();
  projectsLoaded.value = true;
});
onIonViewWillLeave(() => {
  projectsLoaded.value = false;
});
</script>