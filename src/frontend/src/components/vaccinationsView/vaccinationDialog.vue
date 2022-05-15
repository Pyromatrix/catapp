<template>
  <v-dialog v-if="dialog" v-model="dialog" max-width="600" @keydown.esc="cancel">
    <v-card>
      <v-toolbar dark color="pink accent-5" dense flat>
        <v-toolbar-title class="white--text">{{ newVaccination ? 'Lisää rokote' : 'Muokkaa rokotteen tietoja' }}</v-toolbar-title>
        <v-spacer/>
        <v-btn text icon @click="cancel"><v-icon>mdi-close</v-icon></v-btn>
      </v-toolbar>
      <v-card-text class="px-4">
        <v-form ref="vaccinationForm">
          <v-text-field
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              color="pink accent-5"
              :disabled="saving"
              outlined
              class="mt-5"
              label="Rokotteen nimi"
              v-model="vaccination.name"
          />
          <v-select
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              :items="cats"
              :disabled="saving"
              item-text="name"
              item-value="id"
              color="pink accent-5"
              outlined
              type="number"
              label="Kissa"
              v-model="vaccination.cat"
          />
        <v-menu
          v-model="giveDatePicker"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto"
        >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
              :rules="[v => !!v || 'Kenttä on pakollinen!']"
              outlined
              :disabled="saving"
              color="pink accent-5"
              v-model="vaccination.giveDate"
              label="Annettu"
              readonly
              v-bind="attrs"
              v-on="on"
          />
        </template>
        <v-date-picker
            color="pink accent-5"
            locale="fi"
            first-day-of-week="1"
            v-model="vaccination.giveDate"
            @input="giveDatePicker = false"
        />
        </v-menu>

        <v-menu
            v-model="expirationDatePicker"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                :disabled="saving"
                :rules="[v => !!v || 'Kenttä on pakollinen!']"
                outlined
                color="pink accent-5"
                v-model="vaccination.expirationDate"
                label="Vanhenee"
                readonly
                v-bind="attrs"
                v-on="on"
            />
          </template>
          <v-date-picker
              color="pink accent-5"
              locale="fi"
              first-day-of-week="1"
              v-model="vaccination.expirationDate"
              @input="expirationDatePicker = false"
          />
        </v-menu>
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

import vaccinationApi from "@/api/vaccinationApi";
import dayjs from "dayjs";

export default {
  name: 'vaccinationDialog',
  props: ['cats'],
  data: () => ({
    dialog: false,
    newVaccination: false,
    vaccination: {
      name: '',
      cat: null,
      giveDate: null,
      expirationDate: null
    },
    giveDatePicker: false,
    expirationDatePicker: false,
    saving: false,
  }),
  methods: {
    open(newVaccination, vaccination) {
      if (newVaccination) {
        this.newVaccination = newVaccination;
      } else {
        const copyVaccination = JSON.parse(JSON.stringify(vaccination));
        this.vaccination = copyVaccination;
        this.vaccination.giveDate = dayjs(this.vaccination.giveDate).format('YYYY-MM-DD');
        this.vaccination.expirationDate = dayjs(this.vaccination.expirationDate).format('YYYY-MM-DD');
      }
      this.dialog = true
    },
    async agree() {
      const validation = this.$refs.vaccinationForm.validate();

      if (!validation) return;

      this.saving = true;
      try {
        if (this.newVaccination) {
          this.vaccination.cat = this.cats.find(cat => cat.id === this.vaccination.cat)
          await vaccinationApi.createVaccination(this.vaccination);
        } else {
          this.vaccination.cat = this.cats.find(cat => cat.id === this.vaccination.cat)
          await vaccinationApi.updateVaccination(this.vaccination.id, this.vaccination);
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
