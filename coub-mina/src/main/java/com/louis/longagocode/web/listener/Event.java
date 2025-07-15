package com.louis.longagocode.web.listener;


/**
 * @author John·Louis
 * @date created on 2020/2/13
 * description:
 */
public class Event {

    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Event(People people) {
        this.people = people;
    }
}
