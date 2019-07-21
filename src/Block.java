import java.util.Date;

public class Block {
    private String prevHash;
    private String hash;
    private String data;
    private long timeStamp;

    private int nonce;

    public Block(String prevHash, String data) {
        this.prevHash = prevHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    private String calculateHash() {
        return StringUtil.applySHA256(
                prevHash +
                        timeStamp +
                        nonce +
                        data
        );
    }

    public void mineBlock(int difficulty) {
        String result = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(result)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!!");
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
