
public class main {
	public static void main(String [] args){
		int x, y, p;
		x = 4;
		y = 5;
		if (x<y){
			p = g1(x,y);
		} else {
			p = g2(x,y);
		}
		System.out.println(p);
	}

	private static int g1(int a, int b) {
//		System.out.println(a+1);
		if((a+1)==b){
			return (a*a);
		} else {
			return (b*b);
		}
	}

	private static int g2(int a, int b) {
		if(a==(b+1)){
			return (b*b);
		} else {
			return (a*a);
		}
	}
}
