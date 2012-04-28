package core;

import view.Gui;

public class Slave implements Runnable{
	private boolean alive=true;
	private String msg=null;
	private static Slave istance=null;
	
	private Slave(){
	}
	public static Slave getIstance(){
		if(istance==null){
			istance = new Slave();
			(new Thread(Slave.getIstance())).start();
		}
		return istance;
	}
	public void run() {
		while(alive){
//			try {
//				lock.wait();
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			if(msg!=null){
				SearchEngine.streamSearch(msg);
				msg=null;
			}
//			lock.notify();
			Gui.getGui().repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void sendMsg(String m){
//		try {
//			lock.wait();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		msg=m;
//		lock.notify();
	}
	
}
