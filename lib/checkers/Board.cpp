//
// Created by levizor on 10/21/24.
//

#include "Board.h"
#include <array>
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
                    table[i][j] = blank;
                }
            } else {
                table[i][j] = blank;
            }
        }
    }
}

bool Board::rightTurn(const int& checker) {
    if(checker%2==queue%2 and checker!=blank) return true;
    return false;
}



std::vector<std::array<int, 2>> Board::getPossibleActions(const std::array<int, 2> from) {
    if(!rightTurn(table[from[0]][from[1]])) return std::vector<std::array<int, 2>>();
    Checker checker = static_cast<Checker>(table[from[0]][from[1]]);
    std::vector<std::array<int, 2>> captures = getPossibleCaptures(from);
    if(captures.size()>0) return captures;
    if(pendingCaptures.size()>0) return std::vector<std::array<int, 2>>();

    std::vector<std::array<int, 2>> moves = getPossibleMoves(from);
    return moves;
}

bool isValid (const int& row, const int& col) {
    return row>=0 && row<8 && col>=0 && col<8;
}

std::vector<std::array<int, 2>> Board::getPossibleMoves(const std::array<int, 2> &from) {
    std::vector<std::array<int, 2>> moves;
    const int& row = from[0];
    const int& col = from[1];
    const int& checker = table[row][col];

    switch (checker) {
        case blackman:
            if(isValid(row+1, col+1) and table[row+1][col+1] == blank) {
                moves.push_back({{row+1, col+1}});
            }
            if(isValid(row+1, col-1) and table[row+1][col-1] == blank) {
                moves.push_back({{row+1, col-1}});
            }
            break;
        case whiteman:
            if(isValid(row-1, col+1) and table[row-1][col+1] == blank) {
                moves.push_back({{row-1, col+1}});
            }
            if(isValid(row-1, col-1) and table[row-1][col-1] == blank) {
                moves.push_back({{row-1, col-1}});
            }
            break;
        case blackking:
        case whiteking:
            for (int i = 1; i < 8; ++i) {
                if (isValid(row - i, col - i) && table[row - i][col - i] == blank) {
                    moves.push_back({{row - i, col - i}});
                } else break;
            }
            for (int i = 1; i < 8; ++i) {
                if (isValid(row - i, col + i) && table[row - i][col + i] == blank) {
                    moves.push_back({{row - i, col + i}});
                } else break;
            }
            for (int i = 1; i < 8; ++i) {
                if (isValid(row + i, col - i) && table[row + i][col - i] == blank) {
                    moves.push_back({{row + i, col - i}});
                } else break;
            }
            for (int i = 1; i < 8; ++i) {
                if (isValid(row + i, col + i) && table[row + i][col + i] == blank) {
                    moves.push_back({{row + i, col + i}});
                } else break;
            }
            break;
        default:
    }
    return moves;
}

std::vector<std::array<int, 2>> Board::getPossibleCaptures(const std::array<int, 2> from) {
    std::vector<std::array<int, 2>> captures;
    const int &row = from[0];
    const int &col = from[1];
    const int &checker = table[row][col];

    int directions[][2] = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    switch (checker) {
        case blackman:
        case whiteman:
            for(int i = 0; i<4; i++) {
                //checking validity
                int* dir = directions[i];
                if (!isValid(row+2*dir[0], col+2*dir[1])) {
                    continue;
                }
                //checking all 4 positions for enemies
                int enemy = table[row+dir[0]][col+dir[1]];
                if(enemy!=blank and enemy%2!=checker%2) {
                    //found enemy, check space behind them
                    if(table[row+2*dir[0]][col+2*dir[1]] == blank) {
                        captures.push_back({{row+2*dir[0], col+2*dir[1]}});
                    }
                }
            }
            break;
        case blackking:
        case whiteking:
            //checking all 4 direction for enemies
            for(int j = 0; j<4; j++) {
                int* dir = directions[j];
                bool found = false;
                for(int i=1; i<7; i++) {
                    const int r = row+i*dir[0];
                    const int c = col+i*dir[1];
                    //checking validity
                    if (!isValid(r, c)) {
                        break;
                    }
                    std::cout<<"Valid: "<<r<<" "<<c<<std::endl;
                    int enemy = table[r][c];
                    if(enemy==blank) {
                        if(found) captures.push_back({{r, c}});
                        continue;
                    };
                    if(enemy%2==checker%2) break;
                    //found enemy
                    if(found) {
                        std::cout<<"enemy was already found, breaking the loop\n";
                        break;
                    };
                    found = true;
                    std::cout<<std::endl;
                }
            }
    }
    return captures;
}

void Board::capture(std::array<int, 2> &from, std::array<int, 2> &to) {
    movesWithoutCaptures=0;
    int s = abs(from[0]-to[0]);
    int r = (from[0]>to[0])? -1: 1;
    int c = (from[1]>to[1])? -1: 1;

    for(int i = 0; i<s; i++) {
        table[from[0]+r*i][from[1]+c*i] = blank;
    }
}


bool Board::moveIsCapture(const std::array<int, 2>& to) {
    return pendingCaptures.contains(to);
}

int Board::makeMove(std::array<int, 2>& from, std::array<int, 2>& to){

    auto checker = getChecker(from);
    if(to[0]==7 or to[0]==0) {
        if(to[0]%2==checker%2 and checker<=2) {
            checker=Checker(checker+2);
        }
    }
    table[to[0]][to[1]] = checker;
    table[from[0]][from[1]] = blank;
    if(moveIsCapture(to)) {
        capture(from, to);
        if(getPossibleCaptures(to).size()>0) {
            updatePendingCaptures();
            return getState();
        }
    }
    else movesWithoutCaptures++;

    queue++;

    updatePendingCaptures();
    return getState();
}

void Board::updatePendingCaptures() {
    pendingCaptures.clear();
    for(int i=0; i<8; i++) {
        for(int j=0; j<8; j++) {
            if(table[i][j]==blank or table[i][j]%2!=queue%2) continue;
            std::array<int, 2> from = {i, j};
            auto f = getPossibleCaptures(from);
            pendingCaptures.insert(f.begin(), f.end());
        }
    }
}

State Board::getState() {
    if(movesWithoutCaptures>40) return tie;

    auto exist = [this](int c) {
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                if(table[i][j]%2==c%2 and table[i][j]!=blank) return true;
            }
        }
        return false;
    };

    auto canMove = [this]() {
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                if(table[i][j]%2==queue%2 and table[i][j]!=blank) {
                    std::array<int, 2> from = {i, j};
                    if(getPossibleActions(from).size()>0) return moved;
                }
            }
        }
        return static_cast<State>(queue % 2 + 1);
    };

    bool black = exist(blackman);
    bool white = exist(whiteman);

    if(black and white) return canMove();

    if(black) return blackwon;
    if(white) return whitewon;

    //imposible but who knows?
    return tie;
}



Checker Board::getChecker(const std::array<int, 2> &from) {
    return static_cast<Checker>(table[from[0]][from[1]]);
}

