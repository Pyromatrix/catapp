<template>
  <v-container>
    <v-row class="">
      <v-col cols="4">
        <v-text-field outlined label="Kissan nimi" v-model="cat.name"/>
        <v-text-field outlined type="number" label="Kissan paino (kg)" v-model="cat.weight"/>
        <v-btn @click="saveCat">Tallenna kissa</v-btn>

        <v-list>
          Kissalistaus
          <v-list-item v-for="cat in cats" :key="cat.id">
            <v-list-item-content>
              <v-list-item-subtitle>
                Kissan nimi
              </v-list-item-subtitle>
              <v-list-item-title class="">
                {{ cat.name }}
              </v-list-item-title>
              <v-list-item-subtitle class="mt-2">
                Kissan paino
              </v-list-item-subtitle>
              <v-list-item-title>
                {{ cat.weight }} kg
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import catApi from "@/api/CatApi";

  export default {
    name: 'HelloWorld',
    data: () => ({
      catTemplate : {
        name: '',
        weight: null
      },
      cat: {
        name: '',
        weight: null
      },
      cats: [],
      loading: true
    }),
    methods: {
      async saveCat() {
        console.log(this.cat);
        try {
          const response = await catApi.createCat(this.cat);
          console.log(response);
          this.cat = this.catTemplate;
        } catch (e) {
          console.log(e)
        }
        await this.getCats();
      },
      async getCats() {
        try {
          this.cats = await catApi.getCats();
        } catch (e) {
          console.log(e)
        }
      }
    },
    mounted() {
      this.loading = true;

      this.getCats();

      this.loading = false;
    }
  }
</script>
