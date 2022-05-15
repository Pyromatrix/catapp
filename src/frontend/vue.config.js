const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  pwa: {
    workboxPluginMode: 'InjectManifest',
    workboxOptions: {
      swSrc: 'public/service-worker.js',
      exclude: [
        /_redirects/,
        /\.map$/,
        /manifest\.json$/
      ],
    },
    background_color: '#ffffff',
    themeColor: '#ffffff',
    msTileColor: '#ffffff',
    backgroundColor: '#ffffff',
    theme_color: '#ffffff',
    start_url: '/.',
    display: 'standalone'
  }
})
