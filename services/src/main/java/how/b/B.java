package how.b;

import how.a.A;
import how.a.AService;

public class B {
    private final A a;
    private long id;

    public B(A a, long id) {
        this.a = a;
        this.id = id;
    }

    public A getA() {
        return a;
    }

    public long getId() {
        return id;
    }

    public static class Builder {
        private long id;
        private String text;

        public Builder(long id, String text) {
            this.id = id;
            this.text = text;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public B build(AService aService) {
            return new B(aService.get(text), id);
        }
    }
}
