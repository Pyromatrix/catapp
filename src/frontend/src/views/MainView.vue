<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card elevation="0" color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <div elevation="0" color="transparent" style="width: 100%; height: 100%">
            <template>
              <cat-card v-for="(cat, index) in cats" :ref="`catCard_${cat.id}`" :index="index" :cat="cat" :key="cat.id"/>
            </template>
          </div>
          <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
            <v-btn v-if="cats.length > 0" @click="createDose" color="pink accent-5 white--text" large block>Lisää uusi annos</v-btn>
          </v-card>
        </v-card>
      </v-col>
    </v-row>
    <dose-dialog :cats="cats" :items="items" ref="doseDialog" @update="updateCatCard"/>
  </v-container>
</template>

<script>

import catApi from "@/api/CatApi";
import itemApi from "@/api/ItemApi";
import doseApi from "@/api/DoseApi";



import DoseDialog from "@/components/mainView/doseDialog";
import CatCard from "@/components/mainView/catCard";

  export default {
    name: 'MainView',
    components: {CatCard, DoseDialog},
    data: () => ({
      cats: [],
      items: [],
      doses: [],
      loading: true
    }),
    methods: {
      updateCatCard(catId) {
        this.$refs[`catCard_${catId.toString()}`][0].getDoses();
      },
      async getDosesByDate(date) {
        try {
          this.doses = await doseApi.getDosesByDate(date);
        } catch (e) {
          console.log(e)
        }
      },
      async createDose() {
        this.$refs.doseDialog.open();
      },
      async getCats() {
        try {
          this.cats = await catApi.getCats();
        } catch (e) {
          console.log(e)
        }
      },
      async getItems() {
        try {
          this.items = await itemApi.getItems();
        } catch (e) {
          console.log(e)
        }
      },
    },
    mounted() {
      this.loading = true;

      //const thisDay = dayjs();

      this.getCats();
      this.getItems();
      //this.getDosesByDate(thisDay);

      this.loading = false;
    }
  }
</script>
