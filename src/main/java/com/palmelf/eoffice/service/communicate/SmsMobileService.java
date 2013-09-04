package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.SmsMobile;

import java.util.List;

public abstract interface SmsMobileService extends BaseService<SmsMobile>
{
  public abstract List<SmsMobile> getNeedToSend();

  public abstract void saveSms(String paramString1, String paramString2);

  public abstract void sendSms();

  public abstract void sendOneSms(SmsMobile paramSmsMobile);
}