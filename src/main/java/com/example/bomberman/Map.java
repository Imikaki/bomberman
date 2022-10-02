package com.example.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {



	public void mapLoading(int stage) {
		String mapPath = "./res/levels/Level" + stage + ".txt";
		File map = new File(mapPath);
		Scanner sc = null;
		try {
			sc = new Scanner(map);
		} catch(FileNotFoundException e) {
			System.out.println("File reading error: Error loading map");
			e.printStackTrace();
		}

		int level = sc.nextInt();
		int row = sc.nextInt();
		int col = sc.nextInt();

		for (int i = 0; i < row; ++i) {

			for (int j = 0; j < col; ++j) {

			}
		}
	}
}
