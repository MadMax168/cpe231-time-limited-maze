package cpe231.finalproject.timelimitedmaze;

import cpe231.finalproject.timelimitedmaze.solver.LeftWallFollowerSolver;
import cpe231.finalproject.timelimitedmaze.solver.MazeSolver;
import cpe231.finalproject.timelimitedmaze.solver.MazeSolvingException;
import cpe231.finalproject.timelimitedmaze.utils.Coordinate;
import cpe231.finalproject.timelimitedmaze.utils.Maze;
import cpe231.finalproject.timelimitedmaze.utils.MazeStore;
import java.util.List;

public final class App {
  private App() {
  }

  public static void main(String[] args) {
    Maze maze = MazeStore.getMaze("m15_15.txt");
    System.out.println(maze);

    MazeSolver solver = new LeftWallFollowerSolver();
    try {
      List<Coordinate> path = solver.solve(maze);
      System.out.printf("Path length: %d%n", path.size());
      int totalCost = solver.calculatePathCost(maze, path);
      System.out.printf("Total cost: %d%n", totalCost);
      path.forEach(System.out::println);
    } catch (MazeSolvingException exception) {
      System.err.println("Failed to solve maze: " + exception.getMessage());
    }
  }
}
