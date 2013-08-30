package com.cyjt.core.util;

import java.util.Map;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class GmsUtil {

	public static void init() throws Exception {
		IOutboundMessageNotification outboundNotification = new IOutboundMessageNotification() {
			public void process(AGateway arg0, OutboundMessage arg1) {
				System.out.println("Outbound handler called from Gateway: " + arg0.getGatewayId());
				System.out.println(arg1);
			}
		};
		Map sysConfig = AppUtil.getSysConfig();
		String deviceName = (String) sysConfig.get("deviceName");
		Integer baudRate = Integer.valueOf(Integer.parseInt((String) sysConfig.get("baudRate")));
		SerialModemGateway gateway = new SerialModemGateway("modem.com3", deviceName, baudRate.intValue(), "wavecom",
				"");
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		Service.getInstance().addGateway(gateway);
	}
}