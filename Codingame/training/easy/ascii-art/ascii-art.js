/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/ascii-art/
*/

var L = parseInt(readline());
var H = parseInt(readline());
var T = readline().toUpperCase().split("");
var ASCII_A = 'A'.charCodeAt(0);
var ASCII_Z = 'Z'.charCodeAt(0);
for (var i = 0; i < H; i++) {
    var ROW = readline();
    var answer = "";
    for(var c = 0; c < T.length; c++) {
        var ascii = T[c].charCodeAt(0);

        if(ascii >= ASCII_A && ascii <= ASCII_Z) {
            answer = answer + ROW.substr((ascii - ASCII_A) * L, L);
        } else {
            answer = answer + ROW.substr((ASCII_Z - ASCII_A + 1) * L);
        }
    }
    print(answer);
}