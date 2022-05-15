<template>
  <v-dialog v-model="dialog" max-width="600" @keydown.esc="cancel">
    <v-card>
      <v-toolbar dark color="pink accent-5" dense flat>
        <v-toolbar-title class="white--text">{{ newCat ? 'Lisää kissa' : 'Muokkaa kissan tietoja' }}</v-toolbar-title>
        <v-spacer/>
        <v-btn text icon @click="cancel"><v-icon>mdi-close</v-icon></v-btn>
      </v-toolbar>
      <v-card-text class="px-4">
        <v-form ref="catForm">
          <v-text-field
            :rules="[v => !!v || 'Kenttä on pakollinen!']"
            :disabled="saving"
            color="pink accent-5"
            outlined class="mt-5"
            label="Kissan nimi"
            v-model="cat.name"
          />
          <v-text-field
            color="pink accent-5"
            :disabled="saving"
            outlined type="number"
            label="Kissan paino (kg)"
            v-model="cat.weight"
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

import catApi from "@/api/CatApi";

export default {
  name: 'catDialog',
  data: () => ({
    dialog: false,
    newCat: false,
    cat: {
      name: '',
      weight: null
    },
    saving: false,
  }),
  methods: {
    open(newCat, cat) {
      if (newCat) {
        this.newCat = newCat;
      } else {
        const copyCat = JSON.parse(JSON.stringify(cat));
        this.cat = copyCat;
      }

      this.dialog = true
    },
    async agree() {
      const validation = this.$refs.catForm.validate();

      if (!validation) return;
      this.saving = true;
      try {
        if (this.newCat) {
           await catApi.createCat(this.cat);
        } else {
          await catApi.updateCat(this.cat.id, this.cat);
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
