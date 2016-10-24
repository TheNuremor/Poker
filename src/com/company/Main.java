package com.company;

public class Main {

    public static void main(String [] args){
        CardStack stack1 = new CardStack();
        stack1.shuffleStack();


        System.out.println("Karten entfernt");
        for(int i = 0; i<5; i++){
            stack1.deal();
        }
        stack1.print();
    }
}
