package ru.practicum.evm.comparator;

import ru.practicum.evm.model.UserAction;

import java.util.Comparator;

public class UserActionTimestampComparator implements Comparator<UserAction> {

    @Override
    public int compare(UserAction o1, UserAction o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}
