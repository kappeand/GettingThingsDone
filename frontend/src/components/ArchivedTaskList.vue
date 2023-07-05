<template>
  <ion-list lines="inset">
    <ion-list-header mode="ios">
      <ion-label>{{ projectName }}</ion-label>
    </ion-list-header>
    <div :key="task.id" v-for="task in tasksOfProject">
      <ion-item-sliding v-if="task.done">
        <ion-item-options side="start">
          <ion-item-option color="success" @click="restoreTask(task)">
            <ion-icon slot="icon-only" :icon="arrowUndoOutline"/>
          </ion-item-option>
        </ion-item-options>
        <ion-item>
          <ion-grid>
            <ion-row>
              <ion-col>
                <ion-label>
                  <h2 style="font-weight: bold">{{ task.name }}</h2>
                  <p>{{ format_date(task.dueDate) }}</p>
                </ion-label>
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>
        <ion-item-options side="end">
          <ion-item-option color="danger" @click="removeTask(task)">
            <ion-icon slot="icon-only" :icon="trash"/>
          </ion-item-option>
        </ion-item-options>
      </ion-item-sliding>
    </div>
  </ion-list>
</template>
<script setup lang="ts">
import {arrowUndoOutline, trash} from "ionicons/icons";
import {IonCol, IonGrid, IonIcon, IonItem, IonItemOption, IonItemSliding, IonList, IonRow} from "@ionic/vue";
import {useTasksOfProjectId} from "@/composables/useTasksOfProjectId";
import {format, utcToZonedTime} from 'date-fns-tz';
import {useProjects} from "@/composables/useProjects";
import {Task} from "@/model/task";

const props = defineProps(['projectId'])
const {tasksOfProject, removeTask, addOrUpdateTask} = useTasksOfProjectId(props.projectId);
const {projects} = useProjects();
const userTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;

const projectName = projects.value.find(project => project.id == props.projectId)?.name;

async function restoreTask(task: Task) {
  task.done = false;
  await addOrUpdateTask(task);
}


function format_date(date: Date) {
  if (date != undefined) {
    const zonedTime = utcToZonedTime(date, userTimeZone);
    return format(zonedTime, 'dd.MM.yyyy HH:mm', {timeZone: userTimeZone});
  } else return null;
}

</script>