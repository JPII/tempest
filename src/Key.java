public class Key {

	int num;
	long time;

	public Key(int k) {
		num = k;
		time = System.currentTimeMillis();
	}

	public int getCode() {
		return num;
	}

	public boolean canAct() {
		boolean flag = false;
		if (time + 500 < System.currentTimeMillis()) {
			flag = true;
			time = System.currentTimeMillis();
		}
		return flag;
	}
}