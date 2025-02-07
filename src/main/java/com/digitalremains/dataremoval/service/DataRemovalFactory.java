package com.digitalremains.dataremoval.service;

import com.digitalremains.dataremoval.model.SupportedService;
import com.digitalremains.dataremoval.service.accountremoval.*;

public class DataRemovalFactory {

    public static AccountRemover create(final SupportedService service) {
        return switch (service) {
            case SupportedService.FACEBOOK -> new FacebookAccountRemover();
            case SupportedService.TWITTER -> new TwitterAccountRemover();
            case SupportedService.INSTAGRAM -> new InstagramAccountRemover();
            case SupportedService.GOOGLE -> new GoogleAccountRemover();
        };
    }
}
