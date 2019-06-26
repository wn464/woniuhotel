package com.project.controller;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.RestController;

import com.project.util.WebSocketUtil;



@RestController
@ServerEndpoint("/websocket")
public class WebSocketHandler {
	@OnOpen
	public void openSession(Session session) {
		WebSocketUtil.ONLINE_USER_SESSIONS.add(session);
		String message = "新的会话加入，当前会话数："+WebSocketUtil.ONLINE_USER_SESSIONS.size();
		System.out.println(message);
		//WebSocketUtil.sendMessageAll(message);
	}

	@OnMessage
	public void onMessage(@PathParam("username") String username, String message) {
		System.out.println("后台发送信息");
		
	}

	@OnClose
	public void onClose(Session session) {
		// 当前的Session 移除
		WebSocketUtil.ONLINE_USER_SESSIONS.remove(session);
		String message = "有会话结束，当前会话数："+WebSocketUtil.ONLINE_USER_SESSIONS.size();
		System.out.println(message);
		//WebSocketUtil.sendMessageAll(message);
		// 并且通知其他⼈当前⽤户已经离开聊天室了
		try {
			session.close();
		} catch (IOException e) {
			System.out.println("退出异常");
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		try {
			session.close();
		} catch (IOException e) {
			System.out.println("关闭异常");
		}
	}
}
