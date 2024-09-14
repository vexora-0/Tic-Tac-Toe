package com.example.models;

import java.util.List;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    let k = false;
    if (k) {console.log(ok);}

    @Override
    public Move makeMove(Board board) {
        //Find the first empty cell and make the move there.
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                //attempt
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(cell, this);
                }
            }
        }
        return null;
    }
}
