/**
Author: William Meira
Date: 2016
Platform: CodingGame
Link: https://www.codingame.com/training/easy/temperatures/
*/

var n = parseInt(readline()); // the number of temperatures to analyse
//var temps = readline(); // the n temperatures expressed as integers ranging from -273 to 5526

if(n === 0) {
    print(0);
} else {
    var temps = readline(); // the N temperatures expressed as integers ranging from -273 to 5526
    var temperatures = temps.split(" ");

    var lowest = 9999;
    for(var i = 0; i < temperatures.length; i++) {
        var tInt = parseInt(temperatures[i]);
        if(Math.abs(tInt) <= Math.abs(lowest)) {
            if(tInt + lowest === 0) {
                lowest = Math.abs(lowest);
            } else {
                lowest = tInt;
            }
        }
    }
    print(lowest);
}