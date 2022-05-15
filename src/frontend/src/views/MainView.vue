<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card elevation="0" color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <template v-if="!loading">
            <div v-if="cats.length > 0" elevation="0" color="transparent" style="width: 100%; overflow: scroll; max-height: calc(100vh - 150px)">
              <cat-card v-for="(cat, index) in cats"
                        :ref="`catCard_${cat.id}`"
                        :index="index"
                        :cat="cat"
                        :key="cat.id"
              />
            </div>
            <no-content
                v-if="cats.length === 0"
                title="Ei kissoja"
                description="Lisää kissa 'Kissat' -sivulta"
                icon="mdi-cat"
            />
            <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
              <v-btn v-if="cats.length > 0" @click="createDose" color="pink accent-5 white--text" large block>Lisää uusi annos</v-btn>
            </v-card>
          </template>
          <loading-indicator v-else/>
        </v-card>
      </v-col>
    </v-row>
    <dose-dialog :cats="cats" :items="items" ref="doseDialog" @update="updateCatCard"/>
  </v-container>
</template>

<script>

import catApi from "@/api/CatApi";
import itemApi from "@/api/ItemApi";

import DoseDialog from "@/components/mainView/doseDialog";
import CatCard from "@/components/mainView/catCard";
import LoadingIndicator from "@/components/loadingIndicator";
import NoContent from "@/components/noContent";

  export default {
    name: 'MainView',
    components: {NoContent, LoadingIndicator, CatCard, DoseDialog},
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
    async mounted() {
      this.loading = true;

      await this.getCats();
      await this.getItems();

      this.loading = false;
    }
  }
</script>
