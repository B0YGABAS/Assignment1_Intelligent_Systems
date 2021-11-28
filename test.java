public class test {
    public int zz() {
        int x = 5;
        int former = x;
        for (int i = 0; i < 5; i++) {
            x += i;
            if (former != x) {
                former = x;
            }
            System.out.println(former);
        }
        return 0;
    }
}
