<template>
  <div>
    <v-list-item @click="openCat" color="transparent">
      <v-list-item-avatar>
        <v-icon size="30">mdi-cat</v-icon>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-subtitle>Nimi</v-list-item-subtitle>
        <v-list-item-title>{{ cat.name }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Paino</v-list-item-subtitle>
        <v-list-item-title>{{ cat.weight ? `${cat.weight} kg` : 'Paino määrittämättä' }}</v-list-item-title>
      </v-list-item-content>
      <v-list-item-action>
        <v-btn icon>
          <v-icon @click.stop="deleteCat" color="error">mdi-delete</v-icon>
        </v-btn>
      </v-list-item-action>
    </v-list-item>
    <v-divider/>
    <cat-dialog ref="catDialog" @update="emit"></cat-dialog>
  </div>

</template>

<script>
import catApi from "@/api/CatApi";
import CatDialog from "@/components/catsView/catDialog";

export default {
  name: "catListItem",
  components: {CatDialog},
  props: ['cat'],
  methods: {
    emit() {
      console.log('emit tulee')
      this.$emit('update')
    },
    async openCat() {
      this.$refs.catDialog.open(false, this.cat);
    },
    async deleteCat() {
      const confirm = await this.$root.$confirm('Poistetaanko kissa?', 'Se poistuu listalta ja sen palauttaminen vaatii tietokantatyötä.', {
        color: 'error'
      })

      if(!confirm) return;

      try {
        await catApi.deleteCat(this.cat.id);
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
