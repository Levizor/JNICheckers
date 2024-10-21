//
// Created by levizor on 10/21/24.
//

#ifndef BOARD_H
#define BOARD_H


enum Checker {
    null=0,
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

private:

    bool rightTurn(const Checker& checker);
};



#endif //BOARD_H
