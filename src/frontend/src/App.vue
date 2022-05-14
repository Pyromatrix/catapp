<template>
  <v-app>
    <!--
        <v-btn
            @click="drawer =! drawer"
            bottom
            fixed
            right
            fab
            color="teal accent-3"
        >
          <v-icon>mdi mdi-menu</v-icon>
        </v-btn>

        <v-navigation-drawer
            v-model="drawer"
            absolute
            bottom
            height="auto"
            dense
            temporary
        >
          <v-list
              nav
          >
              <v-list-item @click="() => {}" class="text-center">
                <v-list-item-title>Näkymä</v-list-item-title>
              </v-list-item>

              <v-list-item @click="() => {}" class="text-center">
                <v-list-item-title>Kissat</v-list-item-title>
              </v-list-item>

              <v-list-item @click="() => {}" class="text-center">
                <v-list-item-title>Lisäravinteet</v-list-item-title>
              </v-list-item>

              <v-list-item @click="() => {}" class="text-center">
                <v-list-item-title>Rokotteet</v-list-item-title>
              </v-list-item>
          </v-list>
        </v-navigation-drawer>
    -->
    <v-main>
      <cats-view v-if="view === 'kissat'"/>
      <items-view v-if="view === 'lisaravinteet'"/>
      <vaccinations-view v-if="view === 'rokotteet'"/>
    </v-main>
    <v-bottom-navigation
        background-color="pink accent-5"
    >
      <v-btn @click="view = 'nakyma'">
        <span class="white--text">Näkymä</span>

        <v-icon color="white">mdi-view-dashboard-outline</v-icon>
      </v-btn>

      <v-btn @click="view = 'kissat'">
        <span class="white--text">Kissat</span>

        <v-icon color="white">mdi-cat</v-icon>
      </v-btn>

      <v-btn @click="view = 'lisaravinteet'">
        <span class="white--text">Lisäravinteet</span>

        <v-icon color="white">mdi-food-apple-outline</v-icon>
      </v-btn>

      <v-btn @click="view = 'rokotteet'">
        <span class="white--text">Rokotteet</span>

        <v-icon color="white">mdi-needle</v-icon>
      </v-btn>

    </v-bottom-navigation>
    <confirm-dialog ref="confirm"></confirm-dialog>
  </v-app>
</template>

<script>
import CatsView from "@/views/CatsView";
import ConfirmDialog from "@/components/confirmDialog";
import ItemsView from "@/views/ItemsView";
import VaccinationsView from "@/views/VaccinationsView";

export default {
  name: 'App',

  components: {
    VaccinationsView,
    ItemsView,
    ConfirmDialog,
    CatsView,
  },

  data: () => ({
    drawer: false,
    view: 'nakyma'
  }),
  mounted() {
    this.$root.$confirm = this.$refs.confirm.open
  }
};
</script>
