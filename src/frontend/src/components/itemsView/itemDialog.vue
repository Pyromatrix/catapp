<template>
  <v-dialog v-model="dialog" max-width="600" @keydown.esc="cancel">
    <v-card>
      <v-toolbar dark color="pink accent-5" dense flat>
        <v-toolbar-title class="white--text">
          {{ newItem ? 'Lisää lisäravinne' : 'Muokkaa lisäravinteen tietoja' }}
        </v-toolbar-title>
        <v-spacer/>
        <v-btn text icon @click="cancel"><v-icon>mdi-close</v-icon></v-btn>
      </v-toolbar>
      <v-card-text class="px-4">
        <v-form ref="itemForm">
          <v-text-field
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :disabled="saving"
              color="pink accent-5"
              outlined
              class="mt-5"
              label="Lisäravinteen nimi"
              v-model="item.name"
          />
          <v-select
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :disabled="saving"
              color="pink accent-5"
              outlined
              label="Annostyyppi"
              :items="doseTypes"
              v-model="item.doseType"
          />
          <v-textarea
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :disabled="saving"
              color="pink accent-5"
              outlined
              rows="3"
              label="Huomio"
              v-model="item.note"
          />
       </v-form>
      </v-card-text>
      <v-card-actions class="pt-0">
        <v-col>
          <v-row>
            <v-btn :loading="saving" class="mb-2" large block color="white--text pink accent-5" @click.native="agree">Tallenna</v-btn>
          </v-row>
        </v-col>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

import itemApi from "@/api/ItemApi";

export default {
  name: 'itemDialog',
  data: () => ({
    dialog: false,
    newItem: false,
    doseTypes: ['kpl', 'ml', 'mg'],
    item: {
      name: '',
      note: '',
      doseType: ''
    },
    saving: false,
  }),
  methods: {
    open(newItem, item) {
      if (newItem) {
        this.newItem = newItem;
      } else {
        const copyItem = JSON.parse(JSON.stringify(item));
        this.item = copyItem;
      }

      this.dialog = true
    },
    async agree() {
      const validation = this.$refs.itemForm.validate();

      if (!validation) return;
      this.saving = true;
      try {
        if (this.newItem) {
           await itemApi.createItem(this.item);
        } else {
          await itemApi.updateItem(this.item.id, this.item);
        }
      } catch (e) {
        console.log(e)
      }
      this.saving = false;
      await this.$emit('update');
      this.cancel();
    },
    cancel() {
      this.dialog = false
    },
  }
}
</script>
