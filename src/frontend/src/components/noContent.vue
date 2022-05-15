<template>
  <div class="d-flex justify-center align-center fill-height">
    <v-col cols="12">
      <v-row class="d-flex justify-center">
        <v-icon color="pink accent-5" size="200">{{ icon }}</v-icon>
      </v-row>
      <v-row class="d-flex justify-center">
        <v-card-title style="font-size: 2rem">
          {{ title }}
        </v-card-title>
        <v-card-subtitle class="mt-1 text-center">
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
