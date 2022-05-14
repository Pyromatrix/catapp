<template>
  <v-card :class="index > 0 ? 'mt-4 ma-3' : 'ma-3'" :key="cat.id" color="white">
    <v-card-title style="background-color: #F50057" class="white--text d-flex justify-center">
      {{ cat.name }}
    </v-card-title>
    <v-divider/>
    <v-card-text>
      <v-list v-if="doses.length > 0" class="pa-0">
        <v-list-item v-for="dose in doses" :key="dose.id" class="px-0">
          <v-list-item-avatar>
            <v-icon size="30">mdi-food-apple-outline</v-icon>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>
              {{ dose.item.name }} ({{ dose.amount }}{{ dose.item.doseType }})
            </v-list-item-title>
            <v-list-item-subtitle>
              Annettu klo. {{ formatTimeNoDate(dose.date) }}
            </v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn large icon>
              <v-icon @click.stop="deleteDose(dose.id)" color="error">mdi-delete</v-icon>
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
      <v-list v-else class="pa-0">
        <v-list-item class="px-0">
          <v-list-item-avatar>
            <v-icon size="30">mdi-food-apple-outline</v-icon>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>
              Ei annoksia
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card-text>
  </v-card>
</template>

<script>

import doseApi from "@/api/DoseApi";
import dayjs from "dayjs";

export default {
  name: 'catCard',
  props: ['cat', 'index'],
  data: () => ({
    dialog: false,
    doses: []
  }),
  methods: {
    async deleteDose(doseId) {
      try {
        await doseApi.deleteDose(doseId);
        await this.getDoses()
      } catch (e) {
        console.log(e)
      }
    },
    formatTimeNoDate(date) {
      return dayjs(date).format('HH:MM')
    },
    async getDoses() {
      try {
        const doses = await doseApi.getDosesByCat(this.cat.id);
        this.doses = doses.filter(dose => dayjs(dose.date).format('YYYY-MM-DD') === dayjs().format('YYYY-MM-DD') );
      } catch (e) {
        console.log(e)
      }
    }
  },
  mounted() {
    this.getDoses()
  }
}
</script>
