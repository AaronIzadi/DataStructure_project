package structure;

public class HashTable {

    private int sizeOfTable;
    private int numberOfElements;
    private LinkedList<Integer>[] table;


    //Hash formula values:
    private long a;
    private long b;
    private long p;

    public HashTable() {
        table = new LinkedList[sizeOfTable];
    }

    public void setValues(long a, long b, long p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }

    public int getHashValue(long code) {
        return (int) ((((a * code) + b) % p) % sizeOfTable);
    }

    public int findIndexByHash(long code) {
        return getHashValue(code);
    }

    public void insert(int code) {
        if (sizeOfTable == 0) {
            sizeOfTable++;
            table = new LinkedList[sizeOfTable];
        }

        int index = getHashValue(code);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        table[index].add(code);
        numberOfElements++;

        if (numberOfElements == sizeOfTable) {
            resizeTable(sizeOfTable * 2);
        }
    }

    public int findIndex(int code) {
        if (sizeOfTable == 0) {
            return -1;
        }
        int indexByHash = findIndexByHash(code);
        if (table[indexByHash].contains(code)) {
            return indexByHash;
        }
        return -1;
    }

    public void delete(int code) {

        double dynamic = ((double) (numberOfElements) / (double) sizeOfTable);

        if (findIndex(code) != -1) {
            table[findIndex(code)].delete(code);
            numberOfElements--;
            dynamic = ((double) (numberOfElements) / (double) sizeOfTable);
        }

        if (sizeOfTable > 2 && dynamic > 0.25 && dynamic < 0.5) {
            resizeTable(sizeOfTable / 2);
        }
    }

    private void resizeTable(int newSizeOfTable) {
        LinkedList[] oldTable = table.clone();

        table = new LinkedList[newSizeOfTable];

        sizeOfTable = newSizeOfTable;

        for (LinkedList codes : oldTable) {
            if (codes != null) {
                Object[] codeObj = codes.toArray();

                for (Object obj : codeObj) {
                    int code = (int) obj;
                    int index = findIndexByHash(code);
                    if (table[index] == null) {
                        table[index] = new LinkedList<>();
                    }
                    table[index].add(code);
                }
            }
        }
    }
}