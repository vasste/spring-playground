package hello;

import java.io.Serializable;

/**
 * @author Vasilii Stepanov.
 * @since 19.06.2018
 */
public class Foo implements Serializable {
    private long index;

    public Foo(long index) {
        this.index = index;
    }

    public long getIndex() {
        return index;
    }
}

