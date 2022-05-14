<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <v-card elevation="0" color="transparent" style="width: 100%; height: 100%">
            <v-list color="transparent" class="pt-0">
              <vaccination-list-item v-for="vaccination in vaccinations" :cats="cats" :vaccination="vaccination" :key="vaccination.id" @update="getVaccinations"/>
            </v-list>
          </v-card>
          <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
            <v-btn @click="createVaccination" color="pink accent-5 white--text" large block>Lisää uusi rokote</v-btn>
          </v-card>
        </v-card>
      </v-col>
    </v-row>
    <vaccination-dialog ref="vaccinationDialog" @update="getVaccinations" :cats="cats"/>
  </v-container>
</template>

<script>
  import vaccinationApi from "@/api/vaccinationApi";
  import catApi from "@/api/CatApi";
  import VaccinationListItem from "@/components/vaccinationsView/vaccinationListItem";
  import VaccinationDialog from "@/components/vaccinationsView/vaccinationDialog";

  export default {
    name: 'vaccinationsView',
    components: {VaccinationDialog, VaccinationListItem},
    data: () => ({
      vaccination: {
        name: '',
        weight: null
      },
      vaccinations: [],
      cats: [],
      loading: true
    }),
    methods: {
      async getCats() {
        try {
          this.cats = await catApi.getCats();
        } catch (e) {
          console.log(e)
        }
      },
      async createVaccination() {
        this.$refs.vaccinationDialog.open(true);
      },
      async getVaccinations() {
        try {
          this.vaccinations = await vaccinationApi.getVaccinations();
        } catch (e) {
          console.log(e)
        }
      },
    },
    mounted() {
      this.loading = true;
      this.getCats();
      this.getVaccinations();

      this.loading = false;
    }
  }
</script>
