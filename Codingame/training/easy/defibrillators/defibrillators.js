/**
Author: William Meira
Date: 2016
Platform: CodingGame
Link: https://www.codingame.com/training/easy/defibrillators
*/

var longitudeA, latitudeA, longitudeB, latitudeB;
var LON = readline();
var LAT = readline();
var N = parseInt(readline());

function calculateDistance() {
    var x = (longitudeB - longitudeA) * Math.cos((latitudeA + latitudeB)/2);
    var y = (latitudeB - latitudeA);
    return Math.sqrt(x*x + y*y) * 6371;
}

longitudeA = Math.PI * parseFloat(LON.replace(",",".")) / 180;
latitudeA = Math.PI * parseFloat(LAT.replace(",",".")) / 180;
var nameClosest = "";
var dClosest = Number.MAX_SAFE_INTEGER;
for (var i = 0; i < N; i++) {
    var DEFIB = readline();
    var defibrillator = DEFIB.split(";");
    longitudeB = parseFloat(defibrillator[4].replace(",","."));
    latitudeB = parseFloat(defibrillator[5].replace(",","."));
    longitudeB = Math.PI * longitudeB / 180;
    latitudeB = Math.PI * latitudeB / 180;
    printErr(longitudeA + " " + latitudeA + " " + longitudeB  +" " + latitudeB);
    var d = calculateDistance();
    if(d < dClosest) {
        dClosest = d;
        nameClosest = defibrillator[1];
    }
}
print(nameClosest);