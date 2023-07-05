<template>
  <ion-page>
    <ion-content :fullscreen="true">
      <ProjectList :is-archive="false"/>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonContent, IonPage} from '@ionic/vue';
import ProjectList from "@/components/ProjectList.vue";
</script>