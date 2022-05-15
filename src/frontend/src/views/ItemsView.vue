<template>
  <v-container class="fill-height">
    <v-row no-gutters class="fill-height">
      <v-col cols="12">
        <v-card elevation="0" color="grey lighten-5" class="fill-height d-flex align-end flex-column">
          <template v-if="!loading">
            <v-card v-if="items.length > 0" elevation="0" color="transparent" style="width: 100%; height: 100%">
              <v-list color="transparent" class="pt-0">
                <item-list-item v-for="item in items" :item="item" :key="item.id" @update="getItems"/>
              </v-list>
            </v-card>
            <no-content
                v-if="items.length === 0"
                title="Ei lisäravinteita"
                description="Lisää lisäravinne alla olevasta 'Lisää lisäravinne' -painikkeesta"
                icon="mdi-food-apple-outline"
            />
            <v-card elevation="0" color="transparent" class="mt-auto pa-3" style="width: 100%">
              <v-btn @click="createItem" color="pink accent-5 white--text" large block>Lisää lisäravinne</v-btn>
            </v-card>
          </template>
          <loading-indicator v-else/>
        </v-card>
      </v-col>
    </v-row>
    <item-dialog ref="lisaravinneDialog" @update="getItems"/>
  </v-container>
</template>

<script>
  import itemApi from "@/api/ItemApi";
  import ItemListItem from "@/components/itemsView/itemListItem";
  import ItemDialog from "@/components/itemsView/itemDialog";
  import LoadingIndicator from "@/components/loadingIndicator";
  import NoContent from "@/components/noContent";

  export default {
    name: 'ItemsView',
    components: {NoContent, LoadingIndicator, ItemDialog, ItemListItem},
    data: () => ({
      items: [],
      loading: true
    }),
    methods: {
      async createItem() {
        this.$refs.lisaravinneDialog.open(true);
      },
      async getItems() {
        try {
          this.items = await itemApi.getItems();
        } catch (e) {
          console.log(e)
        }
      },
    },
    async mounted() {
      this.loading = true;

      await this.getItems();

      this.loading = false;
    }
  }
</script>
