//
// Created by levizor on 10/21/24.
//

#ifndef BOARD_H
#define BOARD_H
#include <vector>


enum Checker {
    blank=0,
    blackman=1,
    whiteman=2,
    blackking=3,
    whiteking=4,
};

class Board {
public:
    int queue=0;
    int table[8][8];
    Board();
    bool makeMove(const int from[], const int to[]);
    std::vector<int[2]> getPossibleActions(const int[]);
    std::vector<int[2]> getPossibleMoves(const int[], const Checker&);
    std::vector<int[2]> getPossibleCaptures(const int[], const Checker&);

private:

    bool rightTurn(const int& checker);
};



#endif //BOARD_H
