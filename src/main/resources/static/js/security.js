
function rsaEncrypt(message,publicKey){
    //加密
    var NodeRSA = require('node-rsa')
    var key = new NodeRSA();
    key.setOptions({encryptionScheme: 'pkcs1'}) // (此处是重点关注对象，如果多端加解密对应不上，请修改此处，更多细节参考node-rsa文档 )
    key.importKey(publicKey,"pkcs8-public")
    var x = key.encrypt(message, 'base64');
    //console.log('加密串：',x)
    return x

    //解密
    // var key2 = new NodeRSA();
    // key2.setOptions({encryptionScheme: 'pkcs1'})
    // key2.importKey('请使用私钥','pkcs8-private')
    // console.log('使用私钥解密后的串：' + key2.decrypt(x,'utf8'))
}