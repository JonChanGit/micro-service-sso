module.exports = {
  lintOnSave: false,
  devServer: {
    proxy: 'http://localhost:8020' //没有匹配到静态文件的请求 代理到
  }
}
