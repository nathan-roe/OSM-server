package com.digitalremains.dataremoval.service;

import com.digitalremains.dataremoval.model.SupportedService;
import com.digitalremains.dataremoval.service.accountremoval.AccountRemover;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;


@Service
public class DataRemovalService implements Serializable {

    public DataRemovalService() {}

    public void removeAccounts(final SupportedService[] supportedServices) {
        Arrays.stream(supportedServices).forEach(service -> {
            final AccountRemover accountRemover = DataRemovalFactory.create(service);
            accountRemover.removeAccount();
        });
    }

}
