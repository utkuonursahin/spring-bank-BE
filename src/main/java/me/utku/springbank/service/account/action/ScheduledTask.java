package me.utku.springbank.service.account.action;

import org.springframework.scheduling.annotation.Scheduled;

public interface ScheduledTask {
    @Scheduled
    void perform();
}
