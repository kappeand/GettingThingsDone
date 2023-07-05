<template>
  <ion-list lines="inset">
    <ion-list-header mode="ios">
      <ion-label>Projects</ion-label>
    </ion-list-header>
    <div v-if="projects.length">
      <div :key="project.id" v-for="project in projects">
        <ion-item-sliding>
          <ion-item>
            <ion-grid>
              <ion-row>
                <ion-col @click="openProjectModal(project,false)">
                  <ion-label>
                    <h2 style="font-weight: bold">{{ project.name }}</h2>
                  </ion-label>
                </ion-col>
              </ion-row>
            </ion-grid>
          </ion-item>
          <ion-item-options side="end">
            <ion-item-option color="success" @click="openProjectModal(project,false)">
              <ion-icon slot="icon-only" :icon="pencilOutline"></ion-icon>
            </ion-item-option>
            <ion-item-option color="danger">
              <ion-icon slot="icon-only" :icon="trashOutline" @click="removeProject(project)"></ion-icon>
            </ion-item-option>
          </ion-item-options>
        </ion-item-sliding>
      </div>
    </div>
  </ion-list>
  <ion-fab slot="fixed" horizontal="end" vertical="bottom">
    <ion-fab-button expand="block" @click="openProjectModal({},true)">
      <ion-icon :icon="add"></ion-icon>
    </ion-fab-button>
  </ion-fab>
</template>
<script setup lang="ts">
import {add, pencilOutline, trashOutline} from "ionicons/icons";
import {IonCol, IonFab, IonFabButton, IonGrid, IonIcon, IonItem, IonList, IonRow} from "@ionic/vue";
import {useProjects} from "@/composables/useProjects";

const {projects, openProjectModal, removeProject} = useProjects();

</script>