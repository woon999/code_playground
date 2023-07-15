package ch4.item24.local;

public class Practice {
	private String name;

	public Practice(String name) {
		this.name = name;
	}

	public void foo(String name){
		Local local = new Local(name);
		local.localPrint();
	}

	class Local {
		private String name;

		public Local(String name) {
			this.name = name;
		}

		public void localPrint() {
			System.out.println("outerClass: " + Practice.this.name + ", inner local: " + name);
		}
	}

	public static void main(String[] args) {
		Practice practice  = new Practice("outClass");
		practice.foo("inner");
	}
}
