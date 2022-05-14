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

          <v-select
            :rules="[v => !!v || 'Kenttä on pakollinen!']"
            :items="cats"
            item-text="name"
            class="mt-5"
            item-value="id"
            color="pink accent-5"
            outlined
            label="Kissa"
            v-model="dose.catId"
          />

          <v-select
            :rules="[v => !!v || 'Kenttä on pakollinen!']"
            :items="items"
            item-text="name"
            @change="doseNote"
            item-value="id"
            color="pink accent-5"
            outlined
            label="Lisäravinne"
            v-model="dose.itemId"
          />

          <v-text-field
            :rules="[v => !!v || 'Kenttä on pakollinen!']"
            color="pink accent-5"
            type="number"
            outlined
            label="Määrä"
            v-model.number="dose.amount"
          />

       </v-form>
        <v-alert
            v-if="doseNoteText.length > 0"
            type="info"
            text
        >
          {{ doseNoteText }}
        </v-alert>
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

import dayjs from "dayjs";
import doseApi from "@/api/DoseApi";

export default {
  name: 'doseDialog',
  props: ['cats', 'items'],
  data: () => ({
    dialog: false,
    doseNoteText: '',
    dose: {
      catId: null,
      itemId: null,
      amount: null,
      date: null
    },
    giveDatePicker: false,
    expirationDatePicker: false,
  }),
  computed: {
  },
  methods: {
    doseNote(val) {
      this.doseNoteText = this.items.find(item => item.id === val).note;
    },
    open() {
      this.dialog = true
    },
    async agree() {
      const validation = this.$refs.vaccinationForm.validate();

      if (!validation) return;

        try {
          this.dose.date = dayjs();
          this.dose.cat = this.cats.find(cat => cat.id === this.dose.catId)
          this.dose.item = this.items.find(item => item.id === this.dose.itemId)
          await doseApi.createDose(this.dose);
        } catch (e) {
          console.log(e)
        }
      await this.$emit('update', this.dose.catId);
      this.cancel();
    },
    cancel() {
      this.dialog = false
    },
  }
}
</script>
