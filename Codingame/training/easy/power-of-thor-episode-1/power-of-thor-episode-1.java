/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/power-of-thor-episode-1/
*/

import java.util.*;
import java.io.*;

class Player{
    public static void main(String a[]) {
        Scanner i=new Scanner(System.in);
        int X=i.nextInt(),Y=i.nextInt(),T=i.nextInt(),L=i.nextInt();
        for(;;) {
            String r="";
            if(L<Y){
                r="S";L++;
            }
            if(L>Y){
                r="N";L--;
            }if(T<X){
                r+="E";T++;
            }if(T>X){
                r+="W";T--;
            }
            System.out.println(r);
        }
    }
}