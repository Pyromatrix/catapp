<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <v-card elevation="0" color="transparent" style="width: 100%; height: 100%">
            <v-list color="transparent" class="pt-0">
              <!-- <cat-list-item v-for="cat in cats" :cat="cat" :key="cat.id" @update="getCats"/> -->
            </v-list>
          </v-card>
          <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
            <v-btn @click="createDose" color="pink accent-5 white--text" large block>Lisää uusi annos</v-btn>
          </v-card>
        </v-card>
      </v-col>
    </v-row>
    <dose-dialog :cats="cats" :items="items" ref="doseDialog"/>
  </v-container>
</template>

<script>

import catApi from "@/api/CatApi";
import itemApi from "@/api/ItemApi";

import DoseDialog from "@/components/mainView/doseDialog";

  export default {
    name: 'MainView',
    components: {DoseDialog},
    data: () => ({
      cats: [],
      items: [],
      loading: true
    }),
    methods: {
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

      this.getCats();
      this.getItems();

      this.loading = false;
    }
  }
</script>
