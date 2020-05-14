package me.syrym;

public class Main {
    public static void main(String[] args) {
        var blockchain = new Blockchain();
        var first = blockchain.generateBlockChainFromNElements(6);

        Blockchain.printBlockChain(first);
    }
}
