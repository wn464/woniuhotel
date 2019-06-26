package com.project.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

public class WebSocketUtil {

	//会话集合
	public static final List<Session> ONLINE_USER_SESSIONS = new ArrayList<Session>(10);
	public static void sendMessage(Session session, String message) {
		if (session == null) {
			return;
		}
		final RemoteEndpoint.Basic basic = session.getBasicRemote();
		if (basic == null) {
			return;
		}
		try {
			basic.sendText(message);
		} catch (IOException e) {
			System.out.println("消息发送失败");
		}
	}

	/**
	 * 发送所有的消息
	 * 
	 * @param message
	 */
	public static void sendMessageAll(String message) {
		for (Session session2 : ONLINE_USER_SESSIONS) {
			sendMessage(session2, message);
		}
	}
}
