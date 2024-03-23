package dsaa.lab03;

import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name;
        link=new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String line;
        do {
            line = scan.nextLine().toLowerCase();
            String arr[] = line.split(" ");
            for (String word : arr) {
                if (word.startsWith("link=")) {
                    String link = word.substring(5);
                    if (correctLink(link)) {
                        this.link.add(new Link(link));
                    }
                }
            }
        } while (!line.equalsIgnoreCase("eod"));
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static boolean correctLink(String link) {
        return link.matches("^[a-zA-Z][a-zA-Z_0-9]*$");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Document: " + name);
        for (Link l : link) {
            builder.append("\n").append(l.ref);
        }
        return builder.toString();
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }

}
