package org.apoorv.observerpattern;

import org.apoorv.observerpattern.observer.EmailAlertObserver;
import org.apoorv.observerpattern.observer.MobileAlertObserver;
import org.apoorv.observerpattern.observer.NotificationAlertObserver;
import org.apoorv.observerpattern.observerable.IphoneObservable;

public class Main {
    public static void main(String[] args) {
        IphoneObservable iphoneObservable = new IphoneObservable();

        NotificationAlertObserver observer1 = new EmailAlertObserver("apoorv.shamma@gmail.com", iphoneObservable);
        NotificationAlertObserver observer2 = new EmailAlertObserver("apoorv.shamma@gmail.com", iphoneObservable);
        NotificationAlertObserver observer3 = new MobileAlertObserver("9999XXXXX", iphoneObservable);

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.add(observer3);

        iphoneObservable.setCount(10);
    }
}
