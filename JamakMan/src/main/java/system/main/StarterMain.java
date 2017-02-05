package system.main;

import java.util.Scanner;

public class StarterMain {

	public static void main(String[] args) {

		Functions fs = new Functions();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nice to mmet U, I am 'JamakMan'.");
		System.out.println("Please, Input path..:");
		String path = sc.nextLine();
		path = path.replace("\\", "/");
		System.out.println("[Path] " + path);
		fs.walker(path);
		sc.close();

	}

}
