package cpe231.finalproject.timelimitedmaze.utils;

import java.util.List;
import java.util.Objects;

public final class Maze {

  private final String name;
  private final List<List<MazeCell>> grid;
  private final int width;
  private final int height;
  private final Coordinate start;
  private final Coordinate goal;

  public Maze(String name, List<List<MazeCell>> grid, Coordinate start, Coordinate goal) {
    this.name = Objects.requireNonNull(name, "name cannot be null");
    this.grid = List.copyOf(grid).stream()
        .map(List::copyOf)
        .toList();
    if (this.grid.isEmpty()) {
      throw new IllegalArgumentException("Maze grid cannot be empty");
    }
    this.height = this.grid.size();
    this.width = this.grid.getFirst().size();
    boolean rectangular = this.grid.stream().allMatch(row -> row.size() == width);
    if (!rectangular) {
      throw new IllegalArgumentException("Maze grid must be rectangular");
    }
    this.start = Objects.requireNonNull(start, "start cannot be null");
    this.goal = Objects.requireNonNull(goal, "goal cannot be null");
  }

  public String getName() {
    return name;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Coordinate getStart() {
    return start;
  }

  public Coordinate getGoal() {
    return goal;
  }

  public List<List<MazeCell>> getGrid() {
    return grid;
  }

  public MazeCell getCell(Coordinate coordinate) {
    Objects.requireNonNull(coordinate, "coordinate cannot be null");
    return grid.get(coordinate.row()).get(coordinate.column());
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Maze{name='")
        .append(name)
        .append("', width=")
        .append(width)
        .append(", height=")
        .append(height)
        .append(", start=")
        .append(start)
        .append(", goal=")
        .append(goal)
        .append('}');

    for (List<MazeCell> row : grid) {
      builder.append(System.lineSeparator());
      for (MazeCell cell : row) {
        builder.append(toToken(cell));
      }
    }

    return builder.toString();
  }

  private String toToken(MazeCell cell) {
    return switch (cell.type()) {
      case WALL -> "#";
      case START -> "S";
      case GOAL -> "G";
      case WEIGHTED -> "\"" + cell.rawToken() + "\"";
    };
  }
}
