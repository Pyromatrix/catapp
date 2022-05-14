<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <v-card elevation="0" color="transparent" style="width: 100%; height: 100%">
            <v-list color="transparent" class="pt-0">
              <cat-list-item v-for="cat in cats" :cat="cat" :key="cat.id" @update="getCats"/>
            </v-list>
          </v-card>
          <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
            <v-btn @click="createCat" color="pink accent-5 white--text" large block>Lisää uusi kissa</v-btn>
          </v-card>
        </v-card>
      </v-col>
    </v-row>
    <cat-dialog ref="catDialog" @update="getCats"/>
  </v-container>
</template>

<script>
  import catApi from "@/api/CatApi";
  import CatListItem from "@/components/catsView/catListItem";
  import CatDialog from "@/components/catsView/catDialog";

  export default {
    name: 'CatsView',
    components: {CatDialog, CatListItem},
    data: () => ({
      cat: {
        name: '',
        weight: null
      },
      cats: [],
      loading: true
    }),
    methods: {
      async createCat() {
        this.$refs.catDialog.open(true);
      },
      async getCats() {
        try {
          this.cats = await catApi.getCats();
        } catch (e) {
          console.log(e)
        }
      },
    },
    mounted() {
      this.loading = true;

      this.getCats();

      this.loading = false;
    }
  }
</script>
