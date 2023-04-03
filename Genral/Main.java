public class Main {
    public static void main(String[] args)
    {
        CustomList<String> list = new CustomList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        System.out.println(list.getFirst());
        list.removeFirst();
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.removeLast();
        System.out.println(list.getLast());
    }
}
