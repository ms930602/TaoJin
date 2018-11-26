var path = require('path')
var webpack = require('webpack')
var FriendlyErrors = require('friendly-errors-webpack-plugin')
var HtmlWebpackPlugin = require('html-webpack-plugin')
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var ProgressPlugin = require('webpack/lib/ProgressPlugin');

module.exports = {
  entry: {
    main: './src/main.js',
  },
  output: {
    path: path.resolve(__dirname, '../static/dist'),
    publicPath: 'dist/',
    filename: '[name].[chunkhash].js'
  },
  // resolveLoader: {
  //   root: path.join(__dirname, 'node_modules'),
  // },
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
      // {
      //   test: /\.css$/,
      //   loader: "style-loader!css-loader"
      // },
      { 
        test: /\.css$/, 
        use: ExtractTextPlugin.extract({
          fallback: "style-loader",
          use: "css-loader"
        })
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
  // vue: {
  //   preserveWhitespace: false
  // },
  resolve: {
    alias: {
      vue: 'vue/dist/vue.js',
      '@': path.resolve(__dirname, './src'),
    }
  },
  devtool: false,
  // progress: true,
  plugins: [
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        dead_code: true,
        warnings: false,
        drop_console: true,
        pure_funcs: ['console.log']
      },
      sourceMap: false
    }),
    new FriendlyErrors(),
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"'
      }
    }),
    new HtmlWebpackPlugin({
      filename: path.resolve(__dirname, '../static/index.html'),
      template: 'static/index.html',
      inject: true
    }),
    new ExtractTextPlugin('[name].[contenthash].css'),
    new ProgressPlugin(function(percentage, msg) {
      console.log((percentage * 100) + '%', msg);
    }),
  ]
}
