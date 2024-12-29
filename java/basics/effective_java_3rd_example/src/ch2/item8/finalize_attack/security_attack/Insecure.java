package ch2.item8.finalize_attack.security_attack;

import java.io.FilePermission;
import java.security.AccessControlException;

enum Security{
	INSTANCE;
	public static Security getSecurityManager(){
		return INSTANCE;
	}

	public void checkPermission(FilePermission fp){
		if(!fp.getActions().contains("write")){
			throw new AccessControlException("Access denied" + fp);
		}
	}
}
public class Insecure {
	Integer value = 999;

	public Insecure(int value) {
		Security sm = Security.getSecurityManager();
		if (sm != null) {
			FilePermission fp = new FilePermission("index", "read");
			if (value>0) {
				fp = new FilePermission("index", "read write");
			}
			sm.checkPermission(fp);
		}
		this.value = value;
	}

	@Override
	public String toString() {
		return (value.toString());
	}
}
