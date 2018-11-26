var path = require('path')
var webpack = require('webpack')
var FriendlyErrors = require('friendly-errors-webpack-plugin')
var HtmlWebpackPlugin = require('html-webpack-plugin')
// var DashboardPlugin = require('webpack-dashboard/plugin');

module.exports = {
  entry: './src/main.js',
  output: {
    path: path.resolve(__dirname, './dist'),
    publicPath: 'dist/',
    filename: 'build.js'
  },
  resolveLoader: {
    // root: path.join(__dirname, 'node_modules'),
    modules: ["node_modules"]
  },
  module: {
    loaders: [
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(png|jpg|gif|svg)$/,
        loader: 'file-loader',
        query: {
          name: '[name].[ext]?[hash]'
        }
      },
      {
          test: /\.css$/,
          loader: "style-loader!css-loader"
      },
      {
          test: /(\.scss|\.sass)$/,
          use: [{
              loader: "style-loader"
          }, {
              loader: "css-loader"
          }, {
              loader: "sass-loader",
              options: {
                  outputStyle: 'compressed'
              }
          }]
      },
      {
          test: /\.(eot|woff|woff2|ttf)([\?]?.*)$/,
          loader: "file-loader"
      }
    ]
  },
  resolve: {
    alias: {
      vue: 'vue/dist/vue.js',
      '@': path.resolve(__dirname, './src'),
    }
  },
  devServer: {
    historyApiFallback: true,
    noInfo: true,
    port: 7778
  },
  devtool: '#eval-source-map',
  // devtool: false,
  // plugins: [
  //   new webpack.optimize.UglifyJsPlugin({
  //     compress: {
  //       warnings: false
  //     }
  //   }),
  //   new FriendlyErrors()
  // ],
  // plugins: [
  //   new HtmlWebpackPlugin({
  //     filename: path.resolve(__dirname, './index.html'),
  //     template: 'index.html',
  //     inject: true
  //   })
  // ]
  // plugins: [
  //   new DashboardPlugin({ port: 7777 })
  // ]
}

if (process.env.NODE_ENV === 'production') {
  module.exports.devtool = '#source-map'
  // http://vue-loader.vuejs.org/en/workflow/production.html
  module.exports.plugins = (module.exports.plugins || []).concat([
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"'
      }
    }),
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        warnings: false
      }
    })
  ])
}
