<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="Inbox" href="/tabs/inbox">
          <ion-icon :icon="fileTrayOutline" />
          <ion-label>Inbox</ion-label>
        </ion-tab-button>
          
        <ion-tab-button tab="projects" href="/tabs/projects">
          <ion-icon :icon="apps" />
          <ion-label>Projects</ion-label>
        </ion-tab-button>
        
        <ion-tab-button tab="archive" href="/tabs/archive">
          <ion-icon :icon="archiveOutline" />
          <ion-label>Archive</ion-label>
        </ion-tab-button>
      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script setup lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage, IonRouterOutlet } from '@ionic/vue';
import {fileTrayOutline, apps, archiveOutline} from 'ionicons/icons';
</script>