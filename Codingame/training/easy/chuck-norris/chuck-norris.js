/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/chuck-norris/
*/

var MESSAGE = readline().split("");
var answer = "";
var bitAtual = '9';
for(var s = 0; s < MESSAGE.length; s++) {
    var ascii = MESSAGE[s].charCodeAt(0);
    var byteArray = ascii.toString(2);
    byteArray = byteArray.split("");
    for(var i = byteArray.length; i < 7; i++) {
        byteArray.unshift('0');
    }
    for(var i = 0; i < byteArray.length; i++) {
        var bit = byteArray[i];
        if(bitAtual != bit) {
            if(bit == '0') {
                bitAtual = '0';
                answer += " 00 ";
            } else {
                bitAtual = '1';
                answer += " 0 ";
            }
        }
        answer += '0';
    }
}
print(answer.trim());
