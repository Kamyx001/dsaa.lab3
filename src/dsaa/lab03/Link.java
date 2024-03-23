package dsaa.lab03;

public class Link{
    public String ref;
    // in the future there will be more fields
    public Link(String ref) {
        this.ref=ref;
    }
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    public String toString() {
        return ref;
    }

}