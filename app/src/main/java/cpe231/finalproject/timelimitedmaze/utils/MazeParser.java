package cpe231.finalproject.timelimitedmaze.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public final class MazeParser {

  public Maze parse(String name, List<String> rawLines) {
    if (rawLines == null || rawLines.isEmpty()) {
      throw new IllegalArgumentException("Maze lines cannot be null or empty");
    }

    List<List<MazeCell>> rows = new ArrayList<>();
    Coordinate start = null;
    Coordinate goal = null;

    int rowIndex = 0;
    for (String line : rawLines) {
      List<MazeCell> parsedRow = new ArrayList<>();
      int columnIndex = 0;

      for (Token token : tokenize(line)) {
        MazeCell cell = switch (token.type()) {
          case WALL -> new MazeCell(MazeCellType.WALL, token.value(), OptionalInt.empty());
          case START -> {
            Coordinate coordinate = new Coordinate(rowIndex, columnIndex);
            if (start != null) {
              throw new IllegalArgumentException("Maze must contain exactly one start cell");
            }
            start = coordinate;
            yield new MazeCell(MazeCellType.START, token.value(), OptionalInt.empty());
          }
          case GOAL -> {
            Coordinate coordinate = new Coordinate(rowIndex, columnIndex);
            if (goal != null) {
              throw new IllegalArgumentException("Maze must contain exactly one goal cell");
            }
            goal = coordinate;
            yield new MazeCell(MazeCellType.GOAL, token.value(), OptionalInt.empty());
          }
          case WEIGHT ->
            new MazeCell(MazeCellType.WEIGHTED, token.value(), OptionalInt.of(Integer.parseInt(token.value())));
        };

        parsedRow.add(cell);
        columnIndex++;
      }

      rows.add(parsedRow);
      rowIndex++;
    }

    if (start == null || goal == null) {
      throw new IllegalArgumentException("Maze must contain both a start and a goal cell");
    }

    return new Maze(name, rows, start, goal);
  }

  private List<Token> tokenize(String line) {
    List<Token> tokens = new ArrayList<>();
    int index = 0;
    while (index < line.length()) {
      char current = line.charAt(index);
      switch (current) {
        case '#':
          tokens.add(new Token(TokenType.WALL, "#"));
          index++;
          break;
        case 'S':
          tokens.add(new Token(TokenType.START, "S"));
          index++;
          break;
        case 'G':
          tokens.add(new Token(TokenType.GOAL, "G"));
          index++;
          break;
        case '"':
          int closing = line.indexOf('"', index + 1);
          if (closing == -1) {
            throw new IllegalArgumentException("Unmatched quote in maze line: " + line);
          }
          String value = line.substring(index + 1, closing);
          tokens.add(new Token(TokenType.WEIGHT, value));
          index = closing + 1;
          break;
        default:
          throw new IllegalArgumentException("Unsupported maze token: '" + current + "' in line " + line);
      }
    }
    return tokens;
  }

  private enum TokenType {
    WALL,
    START,
    GOAL,
    WEIGHT
  }

  private record Token(TokenType type, String value) {
  }
}
