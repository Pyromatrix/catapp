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
      <main-view v-if="view === 'nakyma'"/>
      <cats-view v-if="view === 'kissat'"/>
      <items-view v-if="view === 'lisaravinteet'"/>
      <vaccinations-view v-if="view === 'rokotteet'"/>
    </v-main>
    <v-bottom-navigation
        height="66px"
        app
        background-color="pink accent-5"
    >
      <v-btn @click="view = 'nakyma'">
        <span class="white--text mb-3">Näkymä</span>

        <v-icon color="white">mdi-view-dashboard-outline</v-icon>
      </v-btn>

      <v-btn @click="view = 'kissat'">
        <span class="white--text mb-3">Kissat</span>

        <v-icon color="white">mdi-cat</v-icon>
      </v-btn>

      <v-btn @click="view = 'lisaravinteet'">
        <span class="white--text mb-3">Lisäravinteet</span>

        <v-icon color="white">mdi-food-apple-outline</v-icon>
      </v-btn>

      <v-btn @click="view = 'rokotteet'">
        <span class="white--text mb-3">Rokotteet</span>

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
import MainView from "@/views/MainView";

export default {
  name: 'App',

  components: {
    MainView,
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
