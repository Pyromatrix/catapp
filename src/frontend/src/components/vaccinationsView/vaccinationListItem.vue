<template>
  <div>
    <v-list-item @click="openVaccination" color="transparent">
      <v-list-item-avatar>
        <v-icon size="30">mdi-needle</v-icon>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-subtitle>Rokotteen nimi</v-list-item-subtitle>
        <v-list-item-title>{{ vaccination.name }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Kissa</v-list-item-subtitle>
        <v-list-item-title>{{ vaccination.cat.name }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Annettu</v-list-item-subtitle>
        <v-list-item-title>{{ formatDate(vaccination.giveDate) }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Vanhenee</v-list-item-subtitle>
        <v-list-item-title>{{ formatDate(vaccination.expirationDate) }}</v-list-item-title>
      </v-list-item-content>
      <v-list-item-action>
        <v-btn icon>
          <v-icon @click.stop="deleteVaccination" color="error">mdi-delete</v-icon>
        </v-btn>
      </v-list-item-action>
    </v-list-item>
    <v-divider/>
    <vaccination-dialog ref="vaccinationDialog" @update="$emit('update')" :cats="cats"/>
  </div>

</template>

<script>
import vaccinationApi from "@/api/vaccinationApi";
import VaccinationDialog from "@/components/vaccinationsView/vaccinationDialog";
import dayjs from "dayjs";

export default {
  name: "vaccinationListItem",
  components: {VaccinationDialog},
  props: ['vaccination', 'cats'],
  methods: {
    formatDate(date) {
      return dayjs(date).format('DD.MM.YYYY')
    },
    async openVaccination() {
      this.$refs.vaccinationDialog.open(false, this.vaccination);
    },
    async deleteVaccination() {
      const confirm = await this.$root.$confirm('Poista rokote?', 'Se poistuu listalta ja sen palauttaminen vaatii tietokantatyötä.', {
        color: 'error'
      })

      if(!confirm) return;

      try {
        await vaccinationApi.deleteVaccination(this.vaccination.id);
        this.$emit('update');
      } catch (e) {
        console.log(e)
      }
    },
  }
}
</script>

<style scoped>

</style>
