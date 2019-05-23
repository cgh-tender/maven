package test;

public enum  Ccc {
    WANGER(1,"我是 WANGER"){
        @Override
        public Ccc getNext(){
            return WANGSAN;
        }
    },WANGSAN(2,"我是 WANGSAN"){
        @Override
        public Ccc getNext(){
            return WANGSI;
        }
    }, WANGSI(3,"我是 WANGSI"){
        @Override
        public Ccc getNext(){
            return WANGER;
        }
    };

    private int index;
    private String name;

    Ccc(int i, String s) {
        this.index = i;
        this.name = s;
    }

    public abstract Ccc getNext();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}