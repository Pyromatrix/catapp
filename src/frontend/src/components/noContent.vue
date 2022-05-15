<template>
  <div class="d-flex justify-center align-center fill-height" style="width: 100%">
    <v-col cols="12">
      <v-row class="d-flex justify-center">
        <v-icon color="pink accent-5" size="200">{{ icon }}</v-icon>
      </v-row>
      <v-row no-gutters class="mt-2 d-flex justify-center">
        <v-card-title style="font-size: 2rem">
          {{ title }}
        </v-card-title>
      </v-row>
      <v-row no-gutters class="mt-n5 d-flex justify-center">
        <v-card-subtitle class="text-center">
          {{ description }}
        </v-card-subtitle>
      </v-row>
    </v-col>
  </div>
</template>

<script>
export default {
  name: "noContent",
  props: ['title', 'description', 'icon']
}
</script>

<style scoped>

</style>
