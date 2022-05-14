<template>
  <div>
    <v-list-item @click="openItem" color="transparent">
      <v-list-item-avatar>
        <v-icon size="30">mdi-food-apple-outline</v-icon>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-subtitle>Nimi</v-list-item-subtitle>
        <v-list-item-title>{{ item.name }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Annostyyppi</v-list-item-subtitle>
        <v-list-item-title>{{ item.doseType }}</v-list-item-title>
        <v-list-item-subtitle class="mt-2">Huomio</v-list-item-subtitle>
        <v-list-item-title class="text-wrap">{{ item.note ? item.note : 'Ei määritetty' }}</v-list-item-title>
      </v-list-item-content>
      <v-list-item-action>
        <v-btn icon>
          <v-icon @click.stop="deleteItem" color="error">mdi-delete</v-icon>
        </v-btn>
      </v-list-item-action>
    </v-list-item>
    <v-divider/>
    <item-dialog ref="itemDialog" @update="$emit('update')"></item-dialog>
  </div>

</template>

<script>
import itemApi from "@/api/ItemApi";
import ItemDialog from "@/components/itemsView/itemDialog";

export default {
  name: "itemListItem",
  components: {ItemDialog},
  props: ['item'],
  methods: {
    async openItem() {
      this.$refs.itemDialog.open(false, this.item);
    },
    async deleteItem() {
      const confirm = await this.$root.$confirm('Poista lisäravinne?', 'Se poistuu listalta ja sen palauttaminen vaatii tietokantatyötä.', {
        color: 'error'
      })

      if(!confirm) return;

      try {
        await itemApi.deleteItem(this.item.id);
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
