package org.swanix.designpattern;

public class DecoratorPattern {
    public static void main(String[] args) {

        ChocolateCake c1 = new ChocolateCake();
        CherriesDecorator cherriesDecorator = new CherriesDecorator(c1);
        cherriesDecorator.bake();


        ChocolateCake c2 = new ChocolateCake();
        CreamDecorator  creamDecorator = new CreamDecorator(c2);
        creamDecorator.bake();

        ChocolateCake c3 = new ChocolateCake();
        CherriesDecorator cherriesDecorator1 = new CherriesDecorator(c3);
        CreamDecorator creamDecorator1 = new CreamDecorator(cherriesDecorator1);
        creamDecorator1.bake();

    }
}


interface Cake {
    void bake();
}

class ChocolateCake implements Cake {
    public void bake() {
        // steps to bake chocolate cake
    }
}

class CherriesDecorator implements Cake {
    private Cake cake;

    CherriesDecorator(Cake c) {
        this.cake = c;
    }

    public void bake() {
        cake.bake();
        addCherries();
    }

    public void addCherries() {
        // extra functionalities
    }
}


class CreamDecorator implements Cake {
    private Cake cake;

    CreamDecorator(Cake c) {
        this.cake = c;
    }

    public void bake() {
        cake.bake();
        addCream();
    }

    public void addCream() {

    }
}


