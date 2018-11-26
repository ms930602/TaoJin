var webpack = require('webpack')
var webpackConfig = require('./webpack.pro.config.js')




webpack(webpackConfig, function (err, stats) {
  if (err) throw err
  process.stdout.write(stats.toString({
    colors: true,
    modules: true,
    children: false,
    chunks: false,
    chunkModules: false,
  }) + '\n')
})




// var ProgressPlugin = require('webpack/lib/ProgressPlugin');

// var compiler = webpack(config);

// compiler.apply(new ProgressPlugin(function(percentage, msg) {
//   console.log((percentage * 100) + '%', msg);
// }));

// compiler.run(function(err, stats) {
//   // ...
// });

