package cpe231.finalproject.timelimitedmaze.utils;

import java.util.Objects;

public final class Coordinate {

  private final int row;
  private final int column;

  public Coordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int row() {
    return row;
  }

  public int column() {
    return column;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    Coordinate that = (Coordinate) other;
    return row == that.row && column == that.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return "Coordinate{" +
        "row=" + row +
        ", column=" + column +
        '}';
  }
}
