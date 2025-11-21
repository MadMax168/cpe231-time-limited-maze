package cpe231.finalproject.timelimitedmaze;

import cpe231.finalproject.timelimitedmaze.utils.Maze;
import cpe231.finalproject.timelimitedmaze.utils.MazeStore;

public final class App {
  private App() {
  }

  public static void main(String[] args) {
    Maze maze = MazeStore.getMaze("m15_15.txt");
    System.out.println(maze);
  }
}
