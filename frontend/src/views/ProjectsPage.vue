<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ProjectList :is-archive="false" v-if="projectsLoaded"/>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonContent, IonPage, onIonViewWillEnter, onIonViewWillLeave} from '@ionic/vue';
import ProjectList from "@/components/ProjectList.vue";
import {ref} from "vue";
import {useProjects} from "@/composables/useProjects";

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