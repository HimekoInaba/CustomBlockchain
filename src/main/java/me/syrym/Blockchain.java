package me.syrym;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Blockchain {
    private final Map<Long, Block> ledger;

    public Blockchain() {
        this.ledger = new HashMap<>();
    }

    public static void printBlockChain(Block block) {
        while (block.getNextBlock() != null) {
            System.out.println("Block:");
            System.out.println(String.format("Id: %d", block.getId()));
            System.out.println(String.format("Timestamp: %d", block.getTimeStamp()));
            System.out.println("Hash of the previous block:");
            System.out.println(block.getPreviousBlock().getHash());
            System.out.println("Hash of the block:");
            System.out.println(block.getHash());
            System.out.println();
            block = block.getNextBlock();
        }
    }

    public Block generateBlockChainFromNElements(int n) {
        var first = generateFirstBlock();
        first.generateSelfHash();
        var iter = first;

        for (int i = 2; i <= n; i++) {
            var block = generateBlock(i, iter);
            block.generateSelfHash();
            iter.setNextBlock(block);
            iter = block;
        }

        return first;
    }

    public Block generateFirstBlock() {
        var zeroBlock = new Block(0);
        zeroBlock.setHash("0");
        var block = new Block(new Date().getTime(), 1);
        block.setPreviousBlock(zeroBlock);
        ledger.put(1L, block);

        return block;
    }

    public Block generateBlock(long id, Block previousBlock) {
        var block = new Block(new Date().getTime(), id);
        block.setPreviousBlock(previousBlock);
        block.generateSelfHash();
        ledger.put(id, block);

        return block;
    }

    public boolean validateBlock(Block block) {
        if (!ledger.containsKey(block.getId())) {
            return false;
        }

        do {
            var existing = ledger.get(block.getId());

            if (!existing.getHash().equals(block.getHash())) {
                return false;
            }

            block = block.getPreviousBlock();
        } while (block != null);

        return true;
    }
}
