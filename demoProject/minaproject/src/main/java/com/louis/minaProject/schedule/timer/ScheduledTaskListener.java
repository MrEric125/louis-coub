package com.louis.minaProject.schedule.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */

public class ScheduledTaskListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new TimerManager();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
