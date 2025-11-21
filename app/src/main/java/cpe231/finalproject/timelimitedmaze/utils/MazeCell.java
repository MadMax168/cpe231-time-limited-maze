package cpe231.finalproject.timelimitedmaze.utils;

import java.util.Objects;
import java.util.OptionalInt;

public record MazeCell(MazeCellType type, String rawToken, OptionalInt weight) {

  public MazeCell {
    Objects.requireNonNull(type, "type cannot be null");
    Objects.requireNonNull(rawToken, "rawToken cannot be null");
    Objects.requireNonNull(weight, "weight cannot be null");
  }

  public boolean isWalkable() {
    return type != MazeCellType.WALL;
  }
}
