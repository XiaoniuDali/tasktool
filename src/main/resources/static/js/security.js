var AES_KEY;// AES算法的密钥
var AES_KEY_SEND_STATUS = false; // 判断AES算法的密钥是否发送到服务器的标志，true表示成功发送到服务器，
var RSA_PUBLIC_KEY;// RSA算法的公钥，需要从服务器获得


initSecurity();

function initSecurity(){
    //生成AES密钥
    AES_KEY = createAesKey();

    //从服务器获得RSA算法的公钥
    $.ajax({
        url:'/security/getRsaPublicKey',
        type:'post',
        dataType: "json",
        success: function (result) {

            if(result.status == 'ok')
            {
                console.log(result);
                RSA_PUBLIC_KEY = result.rsaPublicKey;
                sendAesKey();
            }
            else
            {
                console.log('获取RsaPublicKey失败！');
            }
        },
        error: function (result) {
            console.log(result);
        }
    });
}

//生成AES密钥
function createAesKey(){
    var result='';
    for(var i = 0; i < 16; i ++){
        result += Math.floor(Math.random() * 16).toString(16);//获取0-15并通过toString转16进制
    }
    return result.toUpperCase();
}

/**
 * 将AES算法的密钥用RSA算法的公钥加密后发送给服务器
 */
function sendAesKey(){
    //用RSA算法加密AES的密钥
    var encryptAesKey = rsaEncrypt(AES_KEY);//得到用RSA算法加密后的AES算法密钥
    $.ajax({
        url:'/security/receiveUserAesKey',
        type:'post',
        data:{
            aesKey:encryptAesKey
        },
        dataType:'json',
        success:function (reslult) {
            console.log(reslult.msg);
            AES_KEY_SEND_STATUS = true;
        },
        error:function (reslust) {
            console.log(reslust.msg)
        }
    })
}



//-------------------------------------------------------------RSA算法加密与解密----------------------------------------
// function rsaEncrypt(message, publicKey){
//     //加密
//     var NodeRSA = require('node-rsa')
//     var key = new NodeRSA();
//     key.setOptions({encryptionScheme: 'pkcs1'}) // (此处是重点关注对象，如果多端加解密对应不上，请修改此处，更多细节参考node-rsa文档 )
//     key.importKey(publicKey,"pkcs8-public")
//     var x = key.encrypt(message, 'base64');
//     //console.log('加密串：',x)
//     return x
//
//     //解密
//     // var key2 = new NodeRSA();
//     // key2.setOptions({encryptionScheme: 'pkcs1'})
//     // key2.importKey('请使用私钥','pkcs8-private')
//     // console.log('使用私钥解密后的串：' + key2.decrypt(x,'utf8'))
// }
function rsaEncrypt(msg) {
    var verify = new JSEncrypt();
    verify.setPublicKey(RSA_PUBLIC_KEY);
    return verify.encrypt(msg);
}




//-------------------------------------------------------------AES算法加密与解密----------------------------------------

function aesEncrypt(msg){
    if(!AES_KEY || ! AES_KEY_SEND_STATUS) {
        initSecurity();
        alert("不具备加密通讯环境！正在尝试重新构建加密通讯环境，稍后重试。");
        return;
    }

    var key = CryptoJS.enc.Utf8.parse(AES_KEY);
    var msg = CryptoJS.enc.Utf8.parse(msg);
    var encrypted = CryptoJS.AES.encrypt(msg, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
}

function aesDecrypt(msg){
    var decrypt = CryptoJS.AES.decrypt(msg, AES_KEY, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}