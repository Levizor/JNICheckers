//
// Created by levizor on 10/21/24.
//

#include "Board.h"

#include <iostream>

//init checkers positions on the table

void printBoard(Board board) {
    for (int i=0; i<8; i++) {
        for (int j=0; j<8; j++) {
            std::cout<<"|"<<board.table[i][j]<<"|";
        }
        std::cout<<"\n";
    }
}

bool ret() {
    return true;
}

int main() {
    // Board board;
    // printBoard(board);

}

Board::Board() {
    for (int i=0; i<8; i++) {
        for (int j=0; j<8; j++) {
            if((i+j)%2 == 1) {
                if(i<3) {
                    table[i][j] = blackman;
                }
                else if(i>4) {
                    table[i][j] = whiteman;
                }
                else {
                    table[i][j] = null;
                }

            } else {
                table[i][j] = null;
            }
        }
    }
}

bool Board::rightTurn(const Checker& checker) {
    if(checker%2==queue%2 and checker!=null) return true;
    return false;
}

bool Board::makeMove(const int from[], const int to[]) {
    Checker checker = static_cast<Checker>(table[from[0]][from[1]]);

    if(!rightTurn(checker)) return false;


    queue++;
    return true;
}


