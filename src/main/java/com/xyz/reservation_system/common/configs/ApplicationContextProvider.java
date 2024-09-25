package com.xyz.reservation_system.common.configs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** Need the Application context to generate unique ids based on entity ID */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextProvider.applicationContext = applicationContext;
  }
}
