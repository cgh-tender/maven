package test;

public class cccEnumTest {
    Ccc day;

    public cccEnumTest(Ccc ccc) {
        this.day = ccc;
    }
    public void tellItLikeItIs() {
        switch (day) {
            case WANGER:
                int c = 0;
                while (day.getNext() != null && c < 10){
                    System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                    day =day.getNext();
                    c ++;
                }
                break;

            case WANGSAN:
                System.out.println(day.getName());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                break;

            default:
                System.out.println(day.getName());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
            break;
        }
    }

    public static void main(String[] args) {
        cccEnumTest firstDay = new cccEnumTest(Ccc.WANGER);
        firstDay.tellItLikeItIs();
    }
}
