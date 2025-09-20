package org.example.services.transactions;

import org.example.models.Card;
import org.example.services.AccountService;
import org.example.services.Atm;

public interface ITransaction {

    void execute(Atm atm);

}
