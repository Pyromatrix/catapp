<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card elevation="0" color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <template v-if="!loading">
            <v-card v-if="cats.length > 0" elevation="0" color="transparent" style="width: 100%; height: 100%">
              <v-list color="transparent" class="pt-0">
                <cat-list-item v-for="cat in cats" :cat="cat" :key="cat.id" @update="getCats"/>
              </v-list>
            </v-card>
            <no-content
                v-if="cats.length === 0"
                title="Ei kissoja"
                description="Lisää kissa alla olevasta 'Lisää kissa' -painikkeesta"
                icon="mdi-cat"
            />
            <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
              <v-btn @click="createCat" color="pink accent-5 white--text" large block>Lisää kissa</v-btn>
            </v-card>
          </template>
          <loading-indicator v-else/>
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
  import NoContent from "@/components/noContent";
  import LoadingIndicator from "@/components/loadingIndicator";

  export default {
    name: 'CatsView',
    components: {LoadingIndicator, NoContent, CatDialog, CatListItem},
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
    async mounted() {
      this.loading = true;

      await this.getCats();

      this.loading = false;
    }
  }
</script>
