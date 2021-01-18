package org.swanix.designpattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        Publisher p = new NewsPaper();
        Subscriber s1 = new Person();

        p.register(s1);
    }
}

interface Publisher {
    void register(Subscriber s);

    void unregister(Subscriber s);

    void notifySubscribers();
}

interface Subscriber {
    void update();
}


class NewsPaper implements Publisher {
    List<Subscriber> subscriberList = new ArrayList<>();

    public void register(Subscriber s) {
        subscriberList.add(s);
    }

    public void unregister(Subscriber s) {
        subscriberList.remove(s);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscriberList) {
            s.update();
        }
    }
}

class Person implements Subscriber {
    public void update() {
        System.out.println("Received Update");
    }
}