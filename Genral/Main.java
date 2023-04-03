public class Main {
    public static void main(String[] args)
    {
        CustomList<String> list = new CustomList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        list.stream()
                .map(o->(o + "sdfg"))
                .forEach(System.out::println);
    }
}
