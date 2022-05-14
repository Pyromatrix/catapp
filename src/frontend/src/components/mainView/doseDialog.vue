<template>
  <v-dialog v-if="dialog" v-model="dialog" max-width="600" @keydown.esc="cancel">
    <v-card>
      <v-toolbar dark color="pink accent-5" dense flat>
        <v-toolbar-title class="white--text">Lisää annos</v-toolbar-title>
        <v-spacer/>
        <v-btn text icon @click="cancel"><v-icon>mdi-close</v-icon></v-btn>
      </v-toolbar>
      <v-card-text class="px-4">
        <v-form ref="vaccinationForm">
         <!-- <v-text-field
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              color="pink accent-5"
              outlined
              class="mt-5"
              label="Rokotteen nimi"
              v-model="vaccination.name"
          /> -->
          <v-select
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :items="cats"
              item-text="name"
              class="mt-5"
              item-value="id"
              color="pink accent-5"
              outlined
              label="Kissa"
              v-model="dose.cat"
          />

          <v-select
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :items="items"
              item-text="name"
              item-value="id"
              color="pink accent-5"
              outlined
              label="Lisäravinne"
              v-model="dose.item"
          />

       </v-form>
      </v-card-text>
      <v-card-actions class="pt-0">
        <v-col>
          <v-row>
            <v-btn class="mb-2" large block color="pink accent-5" @click.native="agree">Tallenna</v-btn>
          </v-row>
        </v-col>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

export default {
  name: 'doseDialog',
  props: ['cats', 'items'],
  data: () => ({
    dialog: false,
    dose: {
      cat: null,
      item: null
    },
    giveDatePicker: false,
    expirationDatePicker: false,
  }),
  methods: {
    open() {
      this.dialog = true
    },
    async agree() {
      const validation = this.$refs.vaccinationForm.validate();

      if (!validation) return;

        try {
         // TODO apikutsu tänne
        } catch (e) {
          console.log(e)
        }
      await this.$emit('update');
      this.cancel();
    },
    cancel() {
      this.dialog = false
    },
  }
}
</script>
