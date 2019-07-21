import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class CodyChain {

    public static int difficulty = 5;

    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    public static void main(String[] args) {
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo im the second block", blockChain.get(blockChain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 2... ");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Hey im the third block", blockChain.get(blockChain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 3... ");
        blockChain.get(2).mineBlock(difficulty);

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockChainJson);
    }

    private boolean isChainValid(ArrayList<Block> blockChain) {
        Block prevBlock;
        Block curBlock;
        String hashResult = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockChain.size(); i++) {
            prevBlock = blockChain.get(i - 1);
            curBlock = blockChain.get(i);

            if (!prevBlock.getHash().equals(curBlock.getPrevHash())) {
                return false;
            }

            if (!StringUtil.applySHA256(
                    curBlock.getPrevHash() +
                            curBlock.getTimeStamp() +
                            curBlock.getData()).equals(curBlock.getHash())) {
                return false;
            }

            if (!curBlock.getHash().substring(0, difficulty).equals(hashResult)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }
}
