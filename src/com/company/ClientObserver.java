package com.company;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ClientObserver implements Observer{

    private List<ClientThread> threadList;
    private Table table;

    public ClientObserver(Table table, List<ClientThread> threadList)
    {
        this.threadList = threadList;
        this.table = table;
    }

    @Override
    public void update(Observable o, Object arg) {
        for (ClientThread cl : threadList) {
            if (cl.equals(o)) {
                for (ClientThread cl2 : threadList) {
                    if (!cl.equals(cl2)) {
                        cl2.output.println(cl.getName() + " gejoint");
                    }
                }
            }
        }
    }
}
