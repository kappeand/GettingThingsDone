<template>
  <ion-content class="ion-padding">
    <ion-grid>
      <ion-row>
        <ion-col>
          <ion-row>
            <ion-input v-model="modalProject.name" placeholder="Title" type="text"></ion-input>
          </ion-row>
        </ion-col>
      </ion-row>
    </ion-grid>
    <ion-button v-if="isNewProject" @click="handleButton" expand="full">Create Project</ion-button>
    <ion-button v-if="!isNewProject" @click="handleButton" expand="full">Update Project</ion-button>
  </ion-content>

</template>
<script setup lang="ts">
import {IonButton, IonCol, IonContent, IonGrid, IonInput, IonRow, modalController} from "@ionic/vue";
import {useProjects} from "@/composables/useProjects";

const {addOrUpdateProject} = useProjects();
const props = defineProps(['modalProject', 'isNewProject']);

async function handleButton() {
  await addOrUpdateProject(props.modalProject);
  await modalController.dismiss();
}
</script>