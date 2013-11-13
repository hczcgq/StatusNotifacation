package com.chen.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

public class MyService extends Service{

	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		MyThread thread=new MyThread();
		thread.start();
		super.onStart(intent, startId);
	}
	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	
	class MyThread extends Thread{
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			String ticker = "The status bar messages"; // summary
			String title = "Notification";// title
			String content = "You has a messege....";// ����
			//get the notification manager
			Notification notification = new Notification(android.R.drawable.stat_notify_chat, ticker,System.currentTimeMillis());
			//it will jump when we click the notification,so,we can setting something which we want in here.current we will call phone.
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:123456789"));
			//it will send a intent to system when we click the notification
			PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 100, intent, 0);// 1Ϊ������ 0ΪFlag��־λ
			//perform operations intentions
			notification.setLatestEventInfo(MyService.this, title, content,pendingIntent);// ���֪ͨʱ����еĻ����contentIntent����
			//setting the default sound
			notification.defaults = Notification.DEFAULT_SOUND;
			//Cancel the status icon after click notification
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			//setting the default vibrate
			notification.defaults=Notification.DEFAULT_VIBRATE;
			NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			manager.notify(100, notification);
			super.run();
		}
	}
}
