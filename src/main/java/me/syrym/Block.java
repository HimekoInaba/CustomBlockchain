package me.syrym;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
public class Block {
    private Long timeStamp;
    private final long id;

    @Setter
    private Block previousBlock;
    @Setter
    private Block nextBlock;
    @Setter
    private String hash;

    public Block(long id) {
        this.id = id;
    }

    public Block(long timeStamp, long id) {
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public void generateSelfHash() {
        if (previousBlock != null && timeStamp != null) {
            this.hash = StringUtil.applySha256(previousBlock.getHash() + timeStamp + id);
        } else {
            throw new RuntimeException("Initialize required fields!");
        }
    }
}
